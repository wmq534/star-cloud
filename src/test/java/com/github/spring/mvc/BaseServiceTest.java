package com.github.spring.mvc;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.BaseTest;
public class BaseServiceTest extends BaseTest{
  
  @Autowired
  private BaseService baseService;
  @Before
  public void before(){
//    loader = new ClassPathXmlApplicationContext(configLocation)
  }
  
  @Test
  public void test(){
    String profileName = baseService.getTest();
    System.out.println(9999+"profile.name="+profileName);
  }
}
