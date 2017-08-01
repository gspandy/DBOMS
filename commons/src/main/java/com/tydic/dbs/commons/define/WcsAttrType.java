package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsAttrShowType.java
 * @author caipeimin
 * @version 0.1
 * @WcsAttrType属性类型
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-03-16 11:25:15
 *      	Author: caipeimin
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsAttrType {
	private static final String DICT_CODE="ATTR_TYPE";//对应数据库表SYS_DICT的DICT_CODE值
	/** 品类属性 */
    public static final String CLASS_ATTR = "0";
    /** 自定义属性 */
    public static final String CUSTOMIZED_ATTR = "1";
    
    /** 键值对象 */
    public static Map<String, String> WCS_ATTR_TYPE_MAP = new LinkedHashMap<String, String>();
    static {
    /*	WCS_ATTR_TYPE_MAP.put(CLASS_ATTR, "文本框");
    	WCS_ATTR_TYPE_MAP.put(CUSTOMIZED_ATTR, "单选");*/
    	SysDictConstant.initSysDictByCode(WCS_ATTR_TYPE_MAP, DICT_CODE);
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
