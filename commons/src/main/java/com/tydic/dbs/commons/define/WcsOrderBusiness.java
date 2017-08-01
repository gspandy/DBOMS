package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsOrderBusiness.java
 * @author yancan
 * @version 0.1
 * @WcsOrderBusiness订单业务类型
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-03-12 04:25:15
 *      	Author: yancan
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsOrderBusiness {
	private static final String DICT_CODE="ORDER_BUSINESS";//对应数据库表SYS_DICT的DICT_CODE值

	/**总部业务*/
	public static final String HEADQUARTERS = "0";
	/**省份业务*/
	public static final String PROVINCE = "1";
	
	public static Map<String, String> WCS_ORDER_BUSINESS_MAP = new HashMap<String, String>();
	
	static {
		/*WCS_ORDER_BUSINESS_MAP.put(HEADQUARTERS, "总部业务");
		WCS_ORDER_BUSINESS_MAP.put(PROVINCE, "省份业务");*/
		SysDictConstant.initSysDictByCode(WCS_ORDER_BUSINESS_MAP, DICT_CODE);
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
