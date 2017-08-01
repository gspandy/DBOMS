/**
 * 
 */
package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsDeliveryStatus.java
 * @author yancan
 * @version 0.1
 * @WcsDeliveryStatus配送状态
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-04-24 04:25:15
 *      	Author: yancan
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsDeliveryStatus {
	private static final String DICT_CODE="DELIVERY_STATUS";//对应数据库表SYS_DICT的DICT_CODE值
	/** 未配送  */
	public static final String WCS_NON_DELIVERY = "1";
	/** 配送中 */
	public static final String WCS_DELIVERY_ING = "2";
	/** 已完成 */
	public static final String WCS_DELIVERY_FINISH = "3";
    /** 键值对象 */
	public static Map<String, String> WCS_DELIVERY_STATUS_MAP = new LinkedHashMap<String, String>();
    static {
    	SysDictConstant.initSysDictByCode(WCS_DELIVERY_STATUS_MAP, DICT_CODE);
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
