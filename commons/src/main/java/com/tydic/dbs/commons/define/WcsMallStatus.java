package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsMallStatus.java
 * @author yancan
 * @version 0.1
 * @WcsMallStatus商城状态
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-03-12 04:25:15
 *      	Author: zengxianlian
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsMallStatus {
	private static final String DICT_CODE="MALL_STATUS";//对应数据库表SYS_DICT的DICT_CODE值
	 /** 有效 */
    public static final String MALL_VALID = "1";
    /** 无效*/
    public static final String MALL_INVALID = "0";
    /** 草稿 */
    /*public static final String MALL_DRAFT="-1";*/
    /** 键值对象 */
    public static Map<String, String> WCS_MALL_STATUS_MAP = new LinkedHashMap<String, String>();
    static {
    	/*WCS_MALL_STATUS_MAP.put(MALL_VALID, "可用");
    	WCS_MALL_STATUS_MAP.put(MALL_INVALID, "禁用");
    	WCS_MALL_STATUS_MAP.put(MALL_DRAFT, "草稿");*/
    	SysDictConstant.initSysDictByCode(WCS_MALL_STATUS_MAP, DICT_CODE);
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
