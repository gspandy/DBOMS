package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsQuartzType.java
 * @author liulu
 * @version 0.1
 * @WcsQuartzType定时器类型
 * Copyright(C), 2015-2090
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-03-12 04:25:15
 *      	Author: yancan
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsQuartzType {
	private static final String DICT_CODE="QUARTZ_TYPE";//对应数据库表SYS_DICT的DICT_CODE值
	 /**取消订单*/
    public static final String CANCEL_ORDER = "1";
    /** 键值对象 */
    public static Map<String, String> WCS_QUARTZ_TYPE_MAP = new LinkedHashMap<String, String>();
    static {
   	    SysDictConstant.initSysDictByCode(WCS_QUARTZ_TYPE_MAP, DICT_CODE);
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
