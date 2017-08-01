package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsAttrShowType.java
 * @author caipeimin
 * @version 0.1
 * @WcsAttrShowType属性展示类型
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-03-16 11:25:15
 *      	Author: caipeimin
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsAttrShowType {
	private static final String DICT_CODE="ATTR_SHOW_TYPE";//对应数据库表SYS_DICT的DICT_CODE值
	/** 文本框 */
    public static final String TEXT_FIELD = "1";
    /** 单选 */
    public static final String SINGLE_SELECT = "2";
    /** 复选 */
    public static final String MULTI_SELECT = "3";
    
    /** 键值对象 */
    public static Map<String, String> WCS_ATTR_SHOW_TYPE_MAP = new LinkedHashMap<String, String>();
    static {
    	/*WCS_ATTR_SHOW_TYPE_MAP.put(TEXT_FIELD, "文本框");
    	WCS_ATTR_SHOW_TYPE_MAP.put(SINGLE_SELECT, "单选");
    	WCS_ATTR_SHOW_TYPE_MAP.put(MULTI_SELECT, "复选");*/
    	SysDictConstant.initSysDictByCode(WCS_ATTR_SHOW_TYPE_MAP, DICT_CODE);
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
