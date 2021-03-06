package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsOnlineExtend.java
 * @author yancan
 * @version 0.1
 * @WcsOnlineExtend在线推广情况
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-07-22 11:32:15
 *      	Author: yancan
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsOnlineExtend {
	private static final String DICT_CODE="ONLINE_EXTEND";//对应数据库表SYS_DICT的DICT_CODE值
	
	/** 急单 */
    public static final String WCS_ORDER_EMERGENCY = "1";
    /** 二次跟进单 */
    public static final String WCS_ORDER_SECOND_FOLLOW = "2";
    /** 键值对象 */
    public static Map<String, String> WCS_ONLINE_EXTEND_MAP = new LinkedHashMap<String, String>();
    static {
    	SysDictConstant.initSysDictByCode(WCS_ONLINE_EXTEND_MAP, DICT_CODE);
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
