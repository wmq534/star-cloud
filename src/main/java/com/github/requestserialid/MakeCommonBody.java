package com.github.requestserialid;
public class MakeCommonBody {
    //头的输出格式为:innerSerialId:23133232322->
    public static String makeInnerserialBody(String innerSerialId) {
        return Constants.DEFAULT_REQUEST_PARAM_NAME.INNERSERIALID + ":"
                + innerSerialId + "->";
    }
    
   //头的输出格式为:externalSerialId:23133232322->
    public static String makeExternalserialBody(String innerSerialId) {
        return Constants.DEFAULT_REQUEST_PARAM_NAME.EXTERNALSERIALID + ":"
                + innerSerialId + "->";
    }
    
  //头的输出格式为:jobSerialId:23133232322->
    public static String makeJobserialBody(String innerSerialId) {
        return Constants.DEFAULT_REQUEST_PARAM_NAME.JOBSERIALID + ":"
                + innerSerialId + "->";
    }
    
}