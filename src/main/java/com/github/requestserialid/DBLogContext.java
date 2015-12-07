package com.github.requestserialid;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class DBLogContext implements ApplicationContextAware {
	
	private static ApplicationContext context;
	
	private static DBLog dbLog;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		context = applicationContext; 
	}

	public static DBLog getDBLog(){
		if(dbLog==null){
			dbLog = context.getBean(DBLog.class);
		}
		return dbLog;
	}
}