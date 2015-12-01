package com.github.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//注意这里的@Target与@Description里的不同,参数成员也不同  
@Documented  
@Target(ElementType.METHOD)   
@Retention(RetentionPolicy.RUNTIME)   
public @interface Name {   
    String originate();   
    String community();   
}  