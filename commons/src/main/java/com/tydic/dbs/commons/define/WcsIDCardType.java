/**
 * 
 */
package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsIDCardType.java
 * @author yancan
 * @version 0.1
 * @WcsIDCardType证件类型
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-03-12 04:25:15
 *      	Author: zengxianlian
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsIDCardType {
	private static final String DICT_CODE="ID_CARD_TYPE";//对应数据库表SYS_DICT的DICT_CODE值
	/** 15位身份证  */
	public static final String WCS_SFZ15 = "SFZ15";
	/** 18位身份证*/
	public static final String WCS_SFZ18 = "SFZ18";
	/** 护照 */
	public static final String WCS_HZB = "HZB";
	/** 户口簿  */
	public static final String WCS_HKB = "HKB";
	/** 军官证 */
	public static final String WCS_JUZ = "JUZ";
	/** 警官证 */
	public static final String WCS_JGZ = "JGZ";
	/** 港澳居民来往内地通行证 */
	public static final String WCS_GOT = "GOT";
	/** 台湾军民来往大陆通行证 */
	public static final String WCS_TWT = "TWT";
	/** 营业执照 */
	public static final String WCS_YYZ = "YYZ";
	/** 无需证件 */
	public static final String WCS_WXZ = "WXZ";
	/**身份证*/
	public static final String WCS_SFZ = "SFZ";
	/**房产证*/
	public static final String WCS_FCZ = "FCZ";
	/**驾驶执照*/
	public static final String WCS_JSZ = "JSZ";
    /** 键值对象 */
	public static Map<String, String> WCS_ID_CARD_TYPE_MAP = new LinkedHashMap<String, String>();
    static {
    	SysDictConstant.initSysDictByCode(WCS_ID_CARD_TYPE_MAP, DICT_CODE);
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
