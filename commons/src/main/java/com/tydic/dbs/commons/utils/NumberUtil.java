package com.tydic.dbs.commons.utils;

import java.math.BigDecimal;

/** 
 * @ClassName: NumberUtil 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author davis
 * @date 2015-4-29 下午10:21:06 
 *  
 */
public class NumberUtil {

	/*
	 * 取对象中的数值
	 */
	public static long getLong(Long number){
		long num = 0;
		if(number != null && !"".equals(number)){
			num = number.longValue();
		}
		return num;
	}
	
	/*
	 * 取对象中的数值
	 */
	public static double getDouble(Double number){
		double num = 0;
		if(number != null && !"".equals(number)){
			num = number.doubleValue();
		}
		return num;
	}
	
	/*
	 * 取对象中的数值
	 */
	public static int getInteger(Integer number){
		int num = 0;
		if(number != null && !"".equals(number)){
			num = number.intValue();
		}
		return num;
	}
	
	/*
	 * 数值对象求和
	 */
	public static Long AddLong(Long first,Long second){
		
		return getLong(first)+getLong(second);
	}
	
	/*
	 * 数值对象求和
	 */
	public static Double AddDouble(Double first,Double second){
		return getDouble(first)+getDouble(second);
	}
	
	/***
	 * 
	 * @description: 科学计数转字符串
	 * @param num
	 * @return
	 */
	public static String doubleToString(Double num){
		BigDecimal b=new BigDecimal(num);
		return b.toPlainString();
	}
	
	/*
	 * 数值对象求和
	 */
	public static Integer AddInteger(Integer first,Integer second){
		return getInteger(first)+getInteger(second);
	}
	
	/*
	 * 数值对象相减
	 */
	public static Long decreaLong(Long first,Long second){
		
		return getLong(first)-getLong(second);
	}
	
	/*
	 * 数值对象相减
	 */
	public static Double decreaDouble(Double first,Double second){
		return getDouble(first)-getDouble(second);
	}
	
	/*
	 * 数值对象相减
	 */
	public static Integer decreaInteger(Integer first,Integer second){
		return getInteger(first)-getInteger(second);
	}
	
	/***
	 * 
	 * @Title: crmNumChange 
	 * @Description: TODO(CRM返回数值转换) 
	 * @param @param num
	 * @param @return    设定文件 
	 * @return Double    返回类型 
	 * @throws
	 */
	public static Double crmNumChange(Double num){
		if(num==null) return 0d;
		return num==0?0:num*-1;
	}
	
	public static void main(String[] arg){
		String aa=doubleToString(1.13777664E8);
		System.out.println("aa="+aa);
	}
}
