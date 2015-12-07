package com.github.requestserialid;

import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateHelper {
	//null 和string 为空匹配
	public static boolean isNull(Object obj) {
		if (obj == null)
			return true;
		if (obj instanceof String) {
			String temp = (String) obj;
			if ("".equals(temp.trim()))
				return true;
		}
		return false;
	}
	//整数匹配
	public static boolean isIntNumber(String str) {

		if(str==null ||"".equals(str.trim()))
			return false;
		String rep = "^-?\\d+$";
		Pattern p = Pattern.compile(rep);
		Matcher m = p.matcher(str.trim());
		return m.matches();
	}
	//浮点数匹配
	public static boolean isDoubleNumber(String str) {

		if(str==null ||"".equals(str.trim()))
			return false;
		String rep = "^([1-9]\\d*\\.\\d*|0\\.\\d+|0)$";
		Pattern p = Pattern.compile(rep);
		Matcher m = p.matcher(str.trim());
		return m.matches();
	}

	//匹配
	public static boolean isValidNumber(String str) {

		if(str==null ||"".equals(str.trim()))
			return false;
		String rep = "^[0-9]*\\.?[0-9]+$";
		Pattern p = Pattern.compile(rep);
		Matcher m = p.matcher(str.trim());
		return m.matches();
	}
	
	/**
	 * 判断字符是否超长
	 * 
	 * GBK
	 * @param str
	 * @param maxLength
	 * @return
	 */
	public static Boolean isStringTooLongForGBK(String str, Integer maxLength){
		
		Boolean tooLong = Boolean.TRUE;
		
		if(null == str || "".equals(str)){
			tooLong = Boolean.FALSE;
		}else{
			if(null != maxLength &&  maxLength > 0){
				tooLong = (str.getBytes(Charset.forName("GBK")).length > (maxLength*2));
			}
		}
		
		return tooLong;
	}
	
	/**
	 * 判断字符是否超长
	 * 
	 * @param str
	 * @param maxLength
	 * @return
	 */
	public static Boolean isStringTooLong(String str, Integer maxLength){
		
		Boolean tooLong = Boolean.TRUE;
		
		if(null == str || "".equals(str)){
			tooLong = Boolean.FALSE;
		}else{
			if(null != maxLength &&  maxLength > 0){
				tooLong = (str.length() > maxLength);
			}
		}
		
		return tooLong;
	}
	
	
	//整数匹配
	public static void main(String str[]) {

		System.out.println(isDoubleNumber("5"));
	}
	
}
