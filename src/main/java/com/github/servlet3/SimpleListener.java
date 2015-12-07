package com.github.servlet3;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener("This is only a demo listener") 
public class SimpleListener implements ServletContextListener{

  @Override
  public void contextDestroyed(ServletContextEvent arg0) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void contextInitialized(ServletContextEvent arg0) {
    // TODO Auto-generated method stub
    
  }}