package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsPayMode.java
 * @author yancan
 * @version 0.1
 * @WcsPayMode付款方式
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-03-12 04:25:15
 *      	Author: yancan
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsPayMode {
	private static final String DICT_CODE="PAY_MODE";//对应数据库表SYS_DICT的DICT_CODE值
	
	/**货到付款 */
    public static final String WCS_CASH_ON_DELIVERY = "CASH_ON_DELIVERY";
    /**在线支付*/
    public static final String WCS_ONLINE_PAY = "ONLINE_PAY";
    /**现金 *//*
    public static final String WCS_CASH = "1";
    *//** POS机刷卡*//*
    public static final String WCS_POS = "2";
    *//** 支票 *//*
    public static final String WCS_CHEQUE = "3";*/

    /** 键值对象 */
    public static Map<String, String> WCS_PAY_MODE_MAP = new LinkedHashMap<String, String>();
    static {
    	/*WCS_PAY_MODE_MAP.put(WCS_CASH, "现金");
    	WCS_PAY_MODE_MAP.put(WCS_POS, "POS机刷卡");
    	WCS_PAY_MODE_MAP.put(WCS_CHEQUE, "支票");*/
    	SysDictConstant.initSysDictByCode(WCS_PAY_MODE_MAP, DICT_CODE);
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
