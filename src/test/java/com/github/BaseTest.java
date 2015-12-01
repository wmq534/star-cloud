package com.github;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles("development")   //development
@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = { "classpath：*applicationContext.xml" })
public class BaseTest {
    @Test
    public void test() {
        System.out.println("test");
    }
}
