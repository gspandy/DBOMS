package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsCsmAppOsType.java
 * @author yuchanghong
 * @version 0.1
 * @WcsCsmAppOsType聚合app系统
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-03-12 04:25:15
 *      	Author: yuchanghong
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsCsmAppOsType {
	private static final String DICT_CODE="CSM_APP_OS_TYPE";//对应数据库表SYS_DICT的DICT_CODE值
	/**Android*/
	public static final String ANDROID = "android";
	/**IOS*/
	public static final String IOS = "ios";
	/**windows*/
	public static final String PLACE_FILE_ORDER = "windows";
	
	public static Map<String, String> CSM_APP_OS_TYPE = new HashMap<String, String>();
	
	static {
		SysDictConstant.initSysDictByCode(CSM_APP_OS_TYPE, DICT_CODE);
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
