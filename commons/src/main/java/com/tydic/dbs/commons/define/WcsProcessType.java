package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsProcessType.java
 * @author zhanjianming
 * @version 0.1
 * @WcsProcessType流程类型
 * Copyright(C), 2014-2015
 *		 Guangzhou Tydic Technology Co., Ltd.
 * History
 *   	1. Date: 2015-01-22 15:35:15
 *      	Author: zhanjianming
 *      	Modification: this file was created
 *   	2. ...
 */

public class WcsProcessType {
	
	private static final String DICT_CODE="PROCESS_TYPE";//对应数据库表SYS_DICT的DICT_CODE值
	/** 业务处理 */
    public static final String WCS_NORMAL = "normal";
    /** 退单处理 */
    public static final String WCS_REFUND = "refund";
    /** 默认处理 */
    public static final String WCS_DEFAULT = "default";
    /** 键值对象 */
    public static Map<String, String> WCS_PROCESS_TYPE_MAP = new LinkedHashMap<String, String>();
    static {
    	SysDictConstant.initSysDictByCode(WCS_PROCESS_TYPE_MAP, DICT_CODE);
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
