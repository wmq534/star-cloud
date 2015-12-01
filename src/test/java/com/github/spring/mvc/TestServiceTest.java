package com.github.spring.mvc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = { "classpath:*applicationContext.xml" })
@ActiveProfiles("test")
public class TestServiceTest {
  
  @Autowired
  private TestService service;
  @Before
  public void before(){
//    loader = new ClassPathXmlApplicationContext(configLocation)
  }
  
  @Test
  public void test(){
    String profileName = service.getProfileName("profile.name");
    System.out.println(9999+"profile.name="+profileName);
  }
}
