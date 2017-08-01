/**
 * 
 */
package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsOrderSource.java
 * @author yuchanghong
 * @version 0.1
 * @WcsOrderSource订单来源
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-05-05 14:25:15
 *      	Author: yuchanghong
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsOrderSource {
	private static final String DICT_CODE="ORDER_SOURCE";//对应数据库表SYS_DICT的DICT_CODE值
	
	/** 沃云购 */
	public static final String WCS_ORDER_SOURCE_WCS = "WCS";
	/** 沃理财冻结担保 */
	public static final String WCS_ORDER_SOURCE_WLC = "WLC";
	/** 老用户（PC） */
	public static final String WCS_ORDER_SOURCE_VIPPC = "VIP_PC";
	/** 老用户（WAP） */
	public static final String WCS_ORDER_SOURCE_VIPWAP = "VIP_WAP";
	/** 集客商城 */
	public static final String WCS_ORDER_SOURCE_JKM = "JKM";
	/** 南网沃店商城 */
	public static final String WCS_ORDER_SOURCE_NWM  = "NWM";
	/** 电话商城 */
	public static final String WCS_ORDER_SOURCE_TELM = "TELM";
	/** 电话营销商城 */
	public static final String WCS_ORDER_SOURCE_TMM  = "TMM";
	/** 沃财富商城 */
	public static final String WCS_ORDER_SOURCE_WLCNEW  = "WLCNEW";
	
	/** 键值对象 */
	public static Map<String, String> WCS_ORDER_SOURCE_MAP = new LinkedHashMap<String, String>();
	
	static {
		SysDictConstant.initSysDictByCode(WCS_ORDER_SOURCE_MAP, DICT_CODE);
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
