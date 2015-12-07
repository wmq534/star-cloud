package com.github.requestserialid;
public  interface FilterContent {

	public void init();
	
	public void destory();
	
	public String excuteFilterContent(String content);
}