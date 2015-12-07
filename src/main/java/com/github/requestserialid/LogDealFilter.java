/*
 * LogDealFilter.java Copyright 2012 dangdang.com, Inc. All rights reserved.
 * 
 * @author wangmingqiang
 * 
 * @version 0.0.0.1
 * 
 * @date 2012-11-6 name version date action wangmingqiang 0.0.0.1 2012-10-12 初始创建类
 */
package com.github.requestserialid;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import com.github.spring.utils.SpringContextUtils;


/**
 * LogDealFilter用于外部调用本系统的接口的详细信息进行拦截和执行时间处理;
 * 
 * @date 2012-11-6
 */
public class LogDealFilter implements Filter {

  private static final Logger logger = LoggerFactory.getLogger(LogDealFilter.class);

  /** filterConfig */
  private FilterConfig filterConfig;

  /** 内容过滤器 */
  private List<FilterContent> filterContents;

  // /** 重入锁 */
  // private final Lock lock = new ReentrantLock();
 

  /** 是否记录请求参数详细信息 */
  private boolean logParam = false;

  /** 模块类型inner,outer,external */
  private String logType;

  /** 模块名称 */
  private String module;
  /** 排除的请求类型 */
  private String excludeProjectRegEx = "chat";

  private ThreadLocal<String> serivalThreadLocal;

  /**
   * 清理内存
   */
  @Override
  public void destroy() {
    this.filterConfig = null;
    if (filterContents != null) {
      for (FilterContent filterContent : filterContents) {
        filterContent.destory();
      }
    }
    this.filterContents = null;
    ThreadContainer.destoryAllThreadLocal();
    logger.info("LogDealFilter  destory!");
  }

  /**
   * 初始化
   */
  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    this.filterConfig = filterConfig;
    this.logType = filterConfig.getInitParameter("logType");
    this.serivalThreadLocal = new ThreadLocal<String>();
    ThreadContainer.addMyThreadLocal(Constants.ThreadLocalConstant.INNER_SERVICE_THREAD_LOCAL,
        this.serivalThreadLocal);
    this.module = filterConfig.getServletContext().getInitParameter("module");
   /* this.filterContents = new ArrayList<FilterContent>();
    String content = this.filterConfig.getInitParameter("filterContent");
    if (content != null && !"".equals(content)) {
      String[] filters = content.split(";");
      if (filters != null) {
        for (String filter : filters) {
          if (filter != null && !"".equals(filter)) {
            try {
              FilterContent filterContent =
                  (FilterContent) Class.forName(filter.trim()).newInstance();
              filterContent.init();
              filterContents.add(filterContent);
              logger.error("Add  content filter " + filter + " success!");
            } catch (Exception e) {
              logger.error("Add  content filter " + filter + " error!", e);
            }
          }
        }
      }
    }
    if (this.filterContents.size() < 1) this.filterContents = null;*/
    logger.info("LogDealFilter  init!");
  }

  /**
   * 需要排除的规则
   * 
   * @param url 前端请求的url实例
   * @return 如果符合例外规则，则返回true,不符合规则返回false
   */
  private boolean isExcludeFilter(String url) {
    Pattern p = Pattern.compile(excludeProjectRegEx);
    Matcher m = p.matcher(url);
    boolean result = m.find();
    return result;
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response = (HttpServletResponse) servletResponse;
    // 如果是心跳检查包则直接处理并返回
    String uri = request.getRequestURI().substring(1);
    if (uri != null && uri.indexOf(Constants.HEART_BEAT) != -1) {
      heartBeat(request, response);
      return;
    }
    HttpServletRequest merchantRequest = null;
    // 过滤websocket聊天类型请求
    String url = request.getRequestURI();
    boolean excludeFlag = isExcludeFilter(url);
    if (excludeFlag) {
      filterChain.doFilter(request, response);
      return;
    }

    // 如果是文件流
    if (isMultipartRequest(request)) {
      try {
        // 如果不是DefaultMultipartHttpServletRequest类型实例
        if (!(request instanceof DefaultMultipartHttpServletRequest)) {
          ApplicationContext ctx = SpringContextUtils.getApplicationContext(request);
          MultipartResolver resolver = (MultipartResolver) ctx.getBean(MultipartResolver.class);
          // 转换为 MultipartHttpServletRequest
          request = resolver.resolveMultipart(request);
        }
        if (request instanceof DefaultMultipartHttpServletRequest) {
          DefaultMultipartHttpServletRequest requestwrapper =
              (DefaultMultipartHttpServletRequest) request;
          Field paramField =
              DefaultMultipartHttpServletRequest.class.getDeclaredField("multipartParameters");
          paramField.setAccessible(true);
          @SuppressWarnings("unchecked")
          Map<String, String[]> map = (Map<String, String[]>) paramField.get(requestwrapper);
          if (map == null) {
            map = new HashMap<String, String[]>();
            map.put(Constants.DEFAULT_REQUEST_PARAM_NAME.INNERSERIALID,
                new String[] {GenerateSequenceUtils.generateSequenceNo()});
            paramField.set(requestwrapper, map);
          } else {
            map.put(Constants.DEFAULT_REQUEST_PARAM_NAME.INNERSERIALID,
                new String[] {GenerateSequenceUtils.generateSequenceNo()});
          }
          paramField.setAccessible(false);
          merchantRequest = requestwrapper;
        }
      } catch (Exception e) {
        logger.error(e.getMessage());
      }
    } else {
      // 包装request
      MerchantRequestWrapper requestwrapper = new MerchantRequestWrapper(request);
      requestwrapper.setParameter(Constants.DEFAULT_REQUEST_PARAM_NAME.INNERSERIALID,
          GenerateSequenceUtils.generateSequenceNo());
      merchantRequest = requestwrapper;
    }
    if (merchantRequest == null) merchantRequest = request;
    // 包装response
    MerchantResponseWrapper merchantResponse =
        new MerchantResponseWrapper((HttpServletResponse) response);
    MonitorReceiverLog logModel = new MonitorReceiverLog();

    doBefore(merchantRequest, merchantResponse, logModel);

    try {
      filterChain.doFilter(merchantRequest, merchantResponse);
    } finally {
      ThreadContainer.cleanAllThreadLocal();
      doAfter(merchantRequest, merchantResponse, logModel);
    }
  }

  /**
   * request response等事前处理;记录事前logger
   */
  private void doBefore(HttpServletRequest request, MerchantResponseWrapper crwresponse, Object log)
      throws IOException, ServletException {

    String interfaceId = request.getRequestURI().substring(1);
    String requestUrl = request.getRequestURL().toString();
    String innerSerial = request.getParameter(Constants.DEFAULT_REQUEST_PARAM_NAME.INNERSERIALID);
    this.serivalThreadLocal.set(innerSerial);
    // 记录model
    MonitorReceiverLog logModel = (MonitorReceiverLog) log;
    logModel
        .setSourceSystem(request.getParameter(Constants.DEFAULT_REQUEST_PARAM_NAME.SOURCESYSTEM));
    String cell = request.getParameter(Constants.DEFAULT_REQUEST_PARAM_NAME.CELL);
    if (ValidateHelper.isValidNumber(cell)) logModel.setCell(Long.parseLong(cell));
    logModel.setInterfaceName(interfaceId);
    logModel.setModule(module);
    logModel.setFullUrl(requestUrl);
    logModel.setInnerSerialId(innerSerial);
    logModel.setLocalIp(request.getLocalAddr());
    logModel.setRemoteIp(request.getRemoteAddr());
    logModel.setClientIp(request.getParameter(Constants.DEFAULT_REQUEST_PARAM_NAME.CLIENTIP));
    Long currentMillsBegin = System.currentTimeMillis();
    Timestamp invokeDate = new Timestamp(currentMillsBegin);

    logModel.setInvokeDate(invokeDate);
    logModel.setTimeCost(currentMillsBegin.intValue());
    String contentType = request.getContentType();
    System.out.println("contentType==============" + contentType);
    String beginPre = MakeCommonBody.makeInnerserialBody(logModel.getInnerSerialId());
    if (logger.isDebugEnabled()) {
      logger.debug("{}请求的地址信息->:{}", beginPre, logModel.getFullUrl());
      if (logParam) {
        logger.debug("{}请求详细参数信息->:{}", beginPre,
            ObjectToString.convertToString(request.getParameterMap()));
      }
      logger.debug("{}请求开始执行时间->:{}", beginPre, invokeDate.toString());
    }
  }

  /**
   * request response等事后处理;记录响应时间;
   */

  private void doAfter(HttpServletRequest request, MerchantResponseWrapper crwresponse, Object log)
      throws IOException, ServletException {
    // 设置请求结束时间
    MonitorReceiverLog logModel = (MonitorReceiverLog) log;

    String beginPre = MakeCommonBody.makeInnerserialBody(logModel.getInnerSerialId());

    long currentMillsBegin = logModel.getTimeCost();
    long currentMillsEnd = System.currentTimeMillis();
    Timestamp returnDate = new Timestamp(currentMillsEnd);
    Long timeCost = currentMillsEnd - currentMillsBegin;
    String content = crwresponse.getContent();// response流的内容
    if (content == null) {
      content = "";
    }
    logModel.setTimeCost(timeCost.intValue());
    logModel.setReturnDate(returnDate);
    if (logger.isDebugEnabled()) logger.debug("{}请求响应信息->:{}", beginPre, content);
    Object code = request.getAttribute(Constants.DEFAULT_REQUEST_PARAM_NAME.ERRORCODE);
    if (code != null) {
      logModel.setErrorCode(code.toString());
    }
    Object errorMsg = request.getAttribute(Constants.DEFAULT_REQUEST_PARAM_NAME.ERRORMSG);
    if (errorMsg != null) {
      logModel.setErrorMsg(errorMsg.toString());
    }
    if (logModel.getErrorCode() == null || "".equals(logModel.getErrorCode().trim())) {

      System.out.println("logModel=====" + logModel.toString());
      /*
       * try { ResponseXml res = (ResponseXml) JaxbUtils.xml2Object(content, ResponseXml.class); if
       * (res != null) { if (res.getResult() != null && res.getResult().booleanValue()) {
       * logModel.setErrorCode(Constant.COMMON_SUCCESS.SUCCESS_CODE + "");
       * logModel.setErrorMsg(Constant.COMMON_SUCCESS.SUCCESS_MSG); } else { if (res.getResultCode()
       * != null) { String errorCode = res.getResultCode().toString(); String errorMessage =
       * res.getResultMessage(); logModel.setErrorCode(errorCode);
       * logModel.setErrorMsg(errorMessage); } else { // external 验证 if
       * (content.indexOf("<response>") > 0 && content.indexOf("<functionID>") > 0) {
       * logModel.setErrorCode(String.valueOf(Constant.COMMON_SUCCESS.SUCCESS_CODE));
       * logModel.setErrorMsg(Constant.COMMON_SUCCESS.SUCCESS_MSG); } } } } else { // 商品 特殊格式 inner
       * SubcatController, outer // StoreCategoryApiResponse， queryCategoryInfoById if
       * (content.indexOf("<resultObject>") > 0 && content.indexOf("<result>") > 0) { //
       * logModel.setErrorCode(String.valueOf(Constants.COMMON_SUCCESS.SUCCESS_CODE));
       * logModel.setErrorMsg(Constants.COMMON_SUCCESS.SUCCESS_MSG); } else if
       * (content.trim().startsWith("[")) { // 接口 outer CategoryController 返回的是JSON
       * logModel.setErrorCode(String.valueOf(Constants.COMMON_SUCCESS.SUCCESS_CODE));
       * logModel.setErrorMsg(Constant.COMMON_SUCCESS.SUCCESS_MSG); } } } catch (Exception e) {
       * logger.error(beginPre + e.getMessage()); }
       */
    }
    if (logModel.getErrorCode() == null || "".equals(logModel.getErrorCode().trim())) {
      logModel.setErrorCode(Constants.COMMON_UNFOUND.UNFOUND_CODE + "");
      logModel.setErrorMsg(Constants.COMMON_UNFOUND.UNFOUND_MSG);
    }
    if (logModel.getErrorMsg() == null && "".equals(logModel.getErrorMsg())
        && (Constants.COMMON_SUCCESS.SUCCESS_CODE + "").equals(logModel.getErrorCode())) {
      logModel.setErrorMsg(Constants.COMMON_SUCCESS.SUCCESS_MSG);
    }
    if (logger.isDebugEnabled()) {
      logger.debug("{}请求结束执行时间->:{}", beginPre, returnDate.toString());
      logger.debug("{}请求响应时间->:{}ms", beginPre, logModel.getTimeCost());
    }
    logger.info("{}请求的详细信息->:{}", beginPre, logModel.toString());
    // 此处可以对content做处理,然后再把content写回到输出流中
    HttpServletResponse response = (HttpServletResponse) crwresponse.getResponse();
    response.setContentLength(-1);
    // content = this.dealContent(content);
    writeContent(request, response, content);
  }

  private boolean isMultipartRequest(HttpServletRequest request) {
    if (!"post".equals(request.getMethod().toLowerCase())) {
      return false;
    }
    String contentType = request.getContentType();
    return (contentType != null && contentType.toLowerCase().startsWith("multipart/"));
  }

   /**
   * 内容过滤器
   
   private String dealContent(String content) throws UnsupportedEncodingException {
   if (filterContents == null || filterContents.size() < 1) return content;
   if (content == null) return content;
   for (FilterContent filterContent : filterContents) {
   // debug级别;
   if (logger.isDebugEnabled()) {
   logger.debug(filterContent.getClass().getName() + "处理前信息->:", content);
   }
   content = filterContent.excuteFilterContent(content);
   if (logger.isDebugEnabled()) {
   logger.debug(filterContent.getClass().getName() + "处理后信息->:", content);
   }
   }
   if (logger.isDebugEnabled()) {
   logger.debug("处理的最终结果->:{}", content);
   }
   return content;
   }
*/
  /**
   * 心跳检查包
   * 
   * @author wangmingqiang
   * @param request
   * @param response void
   */
  private void heartBeat(HttpServletRequest request, HttpServletResponse response) {
    StringBuilder sb = new StringBuilder();
    sb.append("<response>");
    sb.append("<result>true</result>");
    sb.append("<resultCode>" + Constants.COMMON_SUCCESS.SUCCESS_CODE + "</resultCode>");
    sb.append("<resultMessage>I am alive!</resultMessage>");
    sb.append("</response>");
    writeContent(request, response, sb.toString());
  }

  /**
   * 写入流信息到原response中
   * 
   * @author wangmingqiang
   * @param request
   * @param response
   * @param content void
   */
  private void writeContent(HttpServletRequest request, HttpServletResponse response,
      String content) {
    Writer t = null;
    try {
      t = response.getWriter();
      t.write(content);
      t.flush();
    } catch (IOException e1) {
      logger.error(e1.getMessage(), e1);
    } finally {
      if (t != null) {
        try {
          t.close();
        } catch (IOException e1) {
          logger.error(e1.getMessage(), e1);
        } finally {
          t = null;
        }
      }
    }
  }
}
