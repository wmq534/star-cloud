package com.github.servlet3;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(urlPatterns = {"/servlet/fragment"}, asyncSupported = true, 
loadOnStartup = -1, name = "SimpleServlet", displayName = "ss", 
initParams = {@WebInitParam(name = "username", value = "tom")} 
) 
public class FragmentServlet extends HttpServlet{

  /**
   * 
   */
  private static final long serialVersionUID = -7582561046272266338L;
  
}