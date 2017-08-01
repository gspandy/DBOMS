/**
 * 
 */
package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsDeliveryMode.java
 * @author yancan
 * @version 0.1
 * @WcsDeliveryMode配送方式/提货方式
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-03-12 04:25:15
 *      	Author: zengxianlian
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsDeliveryMode {
	private static final String DICT_CODE="DELIVERY_MODE";//对应数据库表SYS_DICT的DICT_CODE值
	/**物流配送*//*
	public static final String WCS_KD = "KD";
//	/** 送货 */
//	public static final String WCS_SH = "SH";
	//**自提*//*
	/*public static final String WCS_ZT = "ZT";
	*//**就近配送*//*
	public static final String WCS_JJ = "JJ";
	*//**小米物流*//*
	public static final String WCS_XM = "XM";
	*//**无需配送*//*
	public static final String WCS_WX = "WX";
	*//**现场提货*//*
	public static final String WCS_XCTH = "XCTH";*/
	/**门店自提*/
	public static final String WCS_KHZT="KHZT";
	/**送货上门*/
	public static final String WCS_ZYPS="ZYPS";
	
    /** 键值对象 */
	public static Map<String, String> WCS_DELIVERY_MODE_MAP = new LinkedHashMap<String, String>();
    static {
    	SysDictConstant.initSysDictByCode(WCS_DELIVERY_MODE_MAP, DICT_CODE);
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
