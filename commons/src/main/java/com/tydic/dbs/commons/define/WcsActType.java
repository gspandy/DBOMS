/**
 * 
 */
package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsActType.java
 * @author yuchanghong
 * @version 0.1
 * @WcsActVipPageTypey外部活动类型
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-07-22 23:13:15
 *      	Author: yuchanghong
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsActType {
	private static final String DICT_CODE="WCS_ACT_TYPE";//对应数据库表SYS_DICT的DICT_CODE值
	
	/**满减*/
	public static final String WCS_FULL_REDUCTION = "0";
	/**满赠*/
	public static final String WCS_FULL_GIFTS = "1";
	/**团购*/
	public static final String WCS_GROUP_PURCHASE = "2";
	/**秒杀*/
	public static final String WCS_SECOND_KILL = "3";
	/**直降*/
	public static final String WCS_STRAIGHT_DOWN = "4";
	/**预售*/
	public static final String WCS_PRE_SAL = "5";
	/**溢价*/
	public static final String WCS_PREMIUM_SAL = "6";
	/**常规促销*/
	public static final String WCS_COMMON_PROMOTION = "7";
	/**月度主促*/
	public static final String WCS_MONTH_PROMOTION = "8";
	/**年度大促*/
	public static final String WCS_YEAR_PROMOTION = "9";
	/**赠品*/
	public static final String WCS_GIFT = "10";
	/** 键值对象 */
    public static Map<String, String> WCS_ACT_TYPE_MAP = new LinkedHashMap<String, String>();
    static {
    	/*WCS_ACCESS_TYPE_MAP.put(WCS_PC_TERMINAL, "PC");
    	WCS_ACCESS_TYPE_MAP.put(WCS_WAP_TERMINAL, "WAP");
    	WCS_ACCESS_TYPE_MAP.put(WCS_PAD_TERMINAL, "PAD");*/
    	SysDictConstant.initSysDictByCode(WCS_ACT_TYPE_MAP, DICT_CODE);
    }
	/**
	 * 
	 * @Title: getMap 
	 * @Description: 获取指定语种
	 * @param @param lang
	 * @param @return    设定文件 
	 * @return Map<String,String>    返回类型 
	 * @throws
	 */
	public static Map<String, String> getMap(String lang) {
		Map<String, String> result = new HashMap<String, String>();
		SysDictConstant.initSysDictByCode(result, DICT_CODE, lang);
		return result;
	}
}
