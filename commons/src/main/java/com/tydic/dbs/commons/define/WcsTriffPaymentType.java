package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsPaymentType.java
 * @author yancan
 * @version 0.1
 * @WcsPaymentType付费类型
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-03-12 04:25:15
 *      	Author: yancan
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsTriffPaymentType {
	private static final String DICT_CODE="TRIFF_PAYMENT_TYPE";//对应数据库表SYS_DICT的DICT_CODE值
	/**预付费 */
    public static final String WCS_PRE = "PRE";
    /**后付费*/
    public static final String WCS_BAK = "BAK";
    /**所有类型*/
    public static final String WCS_ALL = "ALL";
    /** 键值对象 */
    public static Map<String, String> WCS_TRIFF_PAYMENT_TYPE_MAP = new LinkedHashMap<String, String>();
    static {
    		SysDictConstant.initSysDictByCode(WCS_TRIFF_PAYMENT_TYPE_MAP, DICT_CODE);
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
