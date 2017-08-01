package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsMemStatus.java
 * @author yancan
 * @version 0.1
 * @WcsMemStatus买家用户状态
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-05-7 04:25:15
 *      	Author: yancan
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsMemStatus {
	private static final String DICT_CODE="MEM_STATUS";
	/**冻结*/
	public static final String FROZEN = "0";
	/**正常使用*/
	public static final String NORMAL = "1";
	
	/**键值*/
	public static Map<String, String> WCS_MEM_STATUS_MAP = new LinkedHashMap<String, String>();
	
	static {
		SysDictConstant.initSysDictByCode(WCS_MEM_STATUS_MAP, DICT_CODE);
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
