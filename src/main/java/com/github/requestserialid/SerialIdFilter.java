package com.github.requestserialid;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(urlPatterns = "/*", asyncSupported = true)
public class SerialIdFilter implements Filter {

  @Override
  public void destroy() {
    System.out.println("webFilter annotation destory");
  }

  @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain arg2)
      throws IOException, ServletException {
    System.out.println("webFilter annotation run");
    String seq = GenerateSequenceUtils.generateSequenceNo();
    System.out.println("seq="+seq);
    req.setAttribute("baseinfo.innerSerialId", seq);
    req.setAttribute("baseinfo.operatorIp", req.getRemoteAddr() );
    arg2.doFilter(req, res);
  }

  @Override
  public void init(FilterConfig arg0) throws ServletException {
    System.out.println("webFilter annotation init");
  }

}
