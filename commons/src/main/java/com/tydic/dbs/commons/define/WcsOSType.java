package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsOSType.java
 * @author yancan
 * @version 0.1
 * @WcsOSType操作系统
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-03-12 04:25:15
 *      	Author: yancan
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsOSType {
	private static final String DICT_CODE="WCS_OS_TYPE";//对应数据库表SYS_DICT的DICT_CODE值
	/** 苹果 */
    public static final String WCS_IOS = "ios";
    /** 安卓 */
    public static final String WCS_ANDROID = "android";
    /** 微软*/
    public static final String WCS_WINDOWS = "windows";
    /** 其他*/
    public static final String WCS_OTHER= "other";
    /** 键值对象 */
    public static Map<String, String> WCS_OS_TYPE_MAP = new LinkedHashMap<String, String>();
    static {
    	SysDictConstant.initSysDictByCode(WCS_OS_TYPE_MAP, DICT_CODE);
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
