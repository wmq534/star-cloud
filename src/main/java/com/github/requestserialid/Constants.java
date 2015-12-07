package com.github.requestserialid;

public final class Constants {
  public static final String HEART_BEAT = "heartbeat";
  /**
   * request 相关常量
   * 
   * @author wangmingqiang
   *
   */
  public static final class DEFAULT_REQUEST_PARAM_NAME {

    public static final String JOBSERIALID = "jobSerialid"; // JOB流水号

    public static final String INNERSERIALID = "innerSerialId"; // 内部流水号

    public static final String OUTERSERIALID = "outerSerialId"; // 外部流水号

    public static final String EXTERNALSERIALID = "externalSerialId"; // 外部流水号

    public static final String SOURCESYSTEM = "sourceSystem"; // 来源系统

    public static final String TOKEN = "token"; // inner outer 模块token码名称

    public static final String VALIDATESTRING = "validateString"; // external模块外部传递过来的MD5码
    
    public static final String CELL = "cell"; // 手机号码
    
    public static final String CLIENTIP = "operatorIp";

    public static final String ERRORCODE = "errorCode";

    public static final String ERRORMSG = "errorMsg";
    
  }
//系统成功码
  public static final class COMMON_SUCCESS {

      public static final int SUCCESS_CODE = 10000;

      public static final String SUCCESS_MSG = "处理成功!";

  }

  // 系统失败码
  public static final class COMMON_FAIL {

      public static final int FAIL_CODE = 10003;

      public static final String FAIL_MSG = "处理失败!";

  }

  // 系统异常码
  public static final class COMMON_EXCEPTION {

      public static final int EXCEPTION_CODE = 10001;

      public static final String EXCEPTION_MSG = "系统内部异常!";

  }

  // 系统未处理
  public static final class COMMON_UNFOUND {

      public static final int UNFOUND_CODE = 10002;

      public static final String UNFOUND_MSG = "未发现处理信息!";

  }
  
//分页默认值
  public static final class LOGTYPE {
      // 默认页
      public static final String INNER = "inner";

      // 默认页行数
      public static final String OUTER = "outer";

      public static final String EXTERNAL = "external";

      public static final String JOB = "job";
  }


  public static final class ThreadLocalConstant {

    public static final String INNER_SERVICE_THREAD_LOCAL = "innerSerialThreadLocal";

  }

  // 特殊字符
  public static final class SPECIAL_CHARACTER {

    public static final String NEWLINE_CHARACTER = "\n";

    public static final String TABLE_CHARACTER = "\t";
    // ;
    public static final String SEMICOLON = ";";

    // :
    public static final String COLON = ":";

    // ,
    public static final String COMMA = ",";
    // '
    public static final String SINGLE_MARK = "'";

    // "
    public static final String DOUBLE_MARK = "\"";

    // ?
    public static final String QUESTION = "?";

    // >>
    public static final String DOUBLE_GREATER = ">>";

    // -
    public static final String SHORT_LINE = "-";

    // >>
    public static final String DOUBLEARROW = ">>";

    // _
    public static final String UNDER_LINE = "_";

    //
    public static final String BLANK = " ";

    // /
    public static final String SOLIDUS = "/";

    // 句点
    public static final String PERIOD = ".";

    // 竖线
    public static final String VERTICAL_LINE = "|";

    // 星号
    public static final String STAR = "*";

    public static final String PARAMETER_SEPARATOR = "&";

    // empty
    public static final String EMPTY = "empty";
  }

}
