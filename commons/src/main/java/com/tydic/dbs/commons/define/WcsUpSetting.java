package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsUpSetting.java
 * @author yancan
 * @version 0.1
 * @WcsUpSetting渠道销售商品上架方式
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-03-12 04:25:15
 *      	Author: yancan
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsUpSetting {
	private static final String DICT_CODE="UP_SETTING";//对应数据库表SYS_DICT的DICT_CODE值
	 /** 手动上架 */
    public static final String WCS_MANUAL_UP = "1";
    /** 立即上架 */
    public static final String WCS_IMMEDIATE_UP = "2";
    /** 定时上架*/
    public static final String WCS_TIMING_UP = "3";
    /** 键值对象 */
    public static Map<String, String> WCS_UP_SETTING_MAP = new LinkedHashMap<String, String>();
    static {
    	/*WCS_UP_SETTING_MAP.put(WCS_MANUAL_UP, " 手动上架");
    	WCS_UP_SETTING_MAP.put(WCS_IMMEDIATE_UP, "立即上架");
    	WCS_UP_SETTING_MAP.put(WCS_TIMING_UP, "定时上架");*/
    	SysDictConstant.initSysDictByCode(WCS_UP_SETTING_MAP, DICT_CODE);
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
