package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsAccessType.java
 * @author yancan
 * @version 0.1
 * @WcsAccessType接入方式
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-03-12 04:25:15
 *      	Author: yancan
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsLanguage {
	private static final String DICT_CODE="LANG_CLASS";//对应数据库表SYS_DICT的DICT_CODE值
	
	 /** KM */
    public static final String KM = "1";
    /** EN */
    public static final String EN = "2";
    /** CN*/
    public static final String CN = "8";
    /** 键值对象 */
    public static Map<String, String> WCS_LANG_MAP = new LinkedHashMap<String, String>();
    static {
    	/*WCS_ACCESS_TYPE_MAP.put(WCS_PC_TERMINAL, "PC");
    	WCS_ACCESS_TYPE_MAP.put(WCS_WAP_TERMINAL, "WAP");
    	WCS_ACCESS_TYPE_MAP.put(WCS_PAD_TERMINAL, "PAD");*/
    	SysDictConstant.initSysDictByCode(WCS_LANG_MAP, DICT_CODE);
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
