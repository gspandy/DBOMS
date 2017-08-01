/**
 * 
 */
package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsActAttrType.java
 * @author yuchanghong
 * @version 0.1
 * @WcsActDiscountType活动属性类型
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-07-26 23:13:15
 *      	Author: yuchanghong
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsActAttrType {
	private static final String DICT_CODE="WCS_ACT_ATTR_TYPE";//对应数据库表SYS_DICT的DICT_CODE值
	
	/**礼品*/
	public static final String WCS_GIFT = "0";
	/**赠送业务*/
	public static final String WCS_GIVE_BUSINESS = "1";
	/**转兑包*/
	public static final String WCS_TRANS_PACKAGE = "2";
	/** 键值对象 */
    public static Map<String, String> WCS_ACT_ATTR_TYPE_MAP = new LinkedHashMap<String, String>();
    static {
    	/*WCS_ACCESS_TYPE_MAP.put(WCS_PC_TERMINAL, "PC");
    	WCS_ACCESS_TYPE_MAP.put(WCS_WAP_TERMINAL, "WAP");
    	WCS_ACCESS_TYPE_MAP.put(WCS_PAD_TERMINAL, "PAD");*/
    	SysDictConstant.initSysDictByCode(WCS_ACT_ATTR_TYPE_MAP, DICT_CODE);
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
