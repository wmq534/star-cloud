package com.github.requestserialid;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class MyRequestWarpper extends HttpServletRequestWrapper {
 
    private HttpServletRequest request;
     
    public MyRequestWarpper(HttpServletRequest request) {
        super(request);
        this.request = request;
    }
     
    @Override
    public String getParameter(String parameter) {
        if ("username".equals(parameter) && request.getParameter(parameter).equals("Jack")) {
            return "Tom";
        }
        return request.getParameter(parameter);
    }
}