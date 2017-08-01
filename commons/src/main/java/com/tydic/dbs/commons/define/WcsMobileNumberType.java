package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsMobileNumberType.java
 * @author caipeimin
 * @version 0.1
 * @WcsMobileNumberType号码类型
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-03-16 11:25:15
 *      	Author: caipeimin
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsMobileNumberType {
	private static final String DICT_CODE="MOBILE_NUMBER_TYPE";//对应数据库表SYS_DICT的DICT_CODE值
	/** 靓号 */
    public static final String GOOD_NUMBER = "1";
    /** 普通号 */
    public static final String GENERAL_NUMBER = "2";
    
    /** 键值对象 */
    public static Map<String, String> WCS_MOBILE_NUMBER_TYPE_MAP = new LinkedHashMap<String, String>();
    static {
    	/*WCS_MOBILE_NUMBER_TYPE_MAP.put(GOOD_NUMBER, "靓号");
    	WCS_MOBILE_NUMBER_TYPE_MAP.put(GENERAL_NUMBER, "普通号");*/
    	SysDictConstant.initSysDictByCode(WCS_MOBILE_NUMBER_TYPE_MAP, DICT_CODE);
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
