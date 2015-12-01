package com.github.spring.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringUtils implements ApplicationContextAware {

    private static ApplicationContext ctx;

    @Override
    public void setApplicationContext(ApplicationContext paramCtx) throws BeansException {
        ctx = paramCtx;
    }

    public static Object getBean(String beanName) {
        return ctx.getBean(beanName);
    }
    
    public static <T>  T getBean(Class<T> clazz) {
        return ctx.getBean(clazz);
    }
}