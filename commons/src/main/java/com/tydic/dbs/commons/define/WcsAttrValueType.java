package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsAttrValueType.java
 * @author caipeimin
 * @version 0.1
 * @WcsAttrValueType属性值类型
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-03-16 11:25:15
 *      	Author: caipeimin
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsAttrValueType {
	private static final String DICT_CODE="ATTR_VALUE_TYPE";//对应数据库表SYS_DICT的DICT_CODE值
	/** 纯数字型 */
    public static final String NUMERIC = "1";
    /** 日期型 */
    public static final String DATE = "2";
    /** 字符型 */
    public static final String CHAR = "3";
    /** 非中文字符型 */
    public static final String NON_CHINESE_CHAR = "4";
    /** 中文字符型 */
    public static final String CHINESE_CHAR = "5";
    
    /** 键值对象 */
    public static Map<String, String> WCS_ATTR_VALUE_TYPE_MAP = new LinkedHashMap<String, String>();
    static {
    	/*WCS_ATTR_VALUE_TYPE_MAP.put(DATE, "纯数字型");
    	WCS_ATTR_VALUE_TYPE_MAP.put(CHAR, "日期型");
    	WCS_ATTR_VALUE_TYPE_MAP.put(CHAR, "字符型");
    	WCS_ATTR_VALUE_TYPE_MAP.put(NON_CHINESE_CHAR, "非中文字符型");
    	WCS_ATTR_VALUE_TYPE_MAP.put(CHINESE_CHAR, "中文字符型");*/
    	SysDictConstant.initSysDictByCode(WCS_ATTR_VALUE_TYPE_MAP, DICT_CODE);
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
