/**
 * 
 */
package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsBusinessPackageType.java
 * @author yancan
 * @version 0.1
 * @WcsActVipPageTypey业务包类型
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-07-22 23:13:15
 *      	Author: yuchanghong
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsBusinessPackageType {
	private static final String DICT_CODE="WCS_BUSINESS_PACKAGE_TYPE";//对应数据库表SYS_DICT的DICT_CODE值
	
	/**业务包-->全国语音包*/
	public static final String WCS_VOICE_PACKAGE = "690700003";
	/**业务包-->短/彩信包*/
	public static final String WCS_SMS_PACKAGE = "690700004";
	/**业务包-->来电显示包*/
	public static final String WCS_CALL_DISPLAY_PACKAGE = "690700005";
	/**业务包-->增值业务包*/
	public static final String WCS_VALUE_ADDED_SERVICE_PACKAGE = "690700006";
	/**业务包-->可选产品包*/
	public static final String WCS_OPTIONAL_PRODUCTS_PACKAGE = "690700007";
	/**业务包-->全国流量包*/
	public static final String WCS_FLOW_PACKAGE = "690700001";
	/** 键值对象 */
    public static Map<String, String> WCS_BUSINESS_PACKAGE_TYPE_MAP = new LinkedHashMap<String, String>();
    static {
    	/*WCS_ACCESS_TYPE_MAP.put(WCS_PC_TERMINAL, "PC");
    	WCS_ACCESS_TYPE_MAP.put(WCS_WAP_TERMINAL, "WAP");
    	WCS_ACCESS_TYPE_MAP.put(WCS_PAD_TERMINAL, "PAD");*/
    	SysDictConstant.initSysDictByCode(WCS_BUSINESS_PACKAGE_TYPE_MAP, DICT_CODE);
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
