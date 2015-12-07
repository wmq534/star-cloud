package com.github.spring.mvc;

import org.springframework.stereotype.Service;

@Service
public class BaseService implements IBaseService{
  
  public String getTest(){
    return "test";
  }

  public String getProfileName(String string) {
//    return loader.getProperty(string);
    return "999";
  }
}
