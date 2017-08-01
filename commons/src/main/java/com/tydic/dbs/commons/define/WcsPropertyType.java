package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsOrderVerifyRule.java
 * @author shuyongfu
 * @version 0.1
 * @WcsOrderVerifyRule下单校验规则
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-08-18 10:15:15
 *      	Author: yancan
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsPropertyType {
	private static final String DICT_CODE="PROPERTY_TYPE";//对应数据库表SYS_DICT的DICT_CODE值
	/** 住宅*/
    public static final String WCS_RESIDENTIAL = "1";
    /** 别墅	 */
    public static final String WCS_VILLAS= "2";
    /** 写字楼*/
    public static final String WCS_OFFICE_BUILDINGS = "3";
    /** 商铺*/
    public static final String WCS_SHOPS = "4";
    /** 综合体*/
    public static final String WCS_COMPLEX = "5";
    /** 键值对象 */
    public static Map<String, String> WCS_PROPERTY_TYPE_MAP = new LinkedHashMap<String, String>();
    static {
    	SysDictConstant.initSysDictByCode(WCS_PROPERTY_TYPE_MAP, DICT_CODE);
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
