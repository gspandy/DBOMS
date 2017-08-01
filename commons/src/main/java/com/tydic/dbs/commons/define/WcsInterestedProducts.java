package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsInterestedProducts.java
 * @author yancan
 * @version 0.1
 * @WcsInterestedProducts意向产品
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-07-22 11:25:15
 *      	Author: yancan
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsInterestedProducts {
	private static final String DICT_CODE="INTERESTED_PRODUCTS";//对应数据库表SYS_DICT的DICT_CODE值
	
	/** 3G语音 */
    public static final String WCS_3G_SPEECH = "1";
    /** 3G手机 */
    public static final String WCS_3G_PHONE = "2";
    /** 无线上网卡*/
    public static final String WCS_NETWORK = "3";
    /** 4G一体化*/
    public static final String WCS_4G_YTH = "4";
    /** 4GDIY*/
    public static final String WCS_4G_DIY = "5";
    /** 4G合约手机*/
    public static final String WCS_4G_HYJ = "6";
    /** 键值对象 */
    public static Map<String, String> WCS_INTERESTED_PRODUCTS_MAP = new LinkedHashMap<String, String>();
    static {
    	SysDictConstant.initSysDictByCode(WCS_INTERESTED_PRODUCTS_MAP, DICT_CODE);
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
