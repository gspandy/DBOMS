package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsChannelGrade.java
 * @author yancan
 * @version 0.1
 * @WcsChannelGrade渠道等级
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-03-12 04:25:15
 *      	Author: yancan
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsChannelGrade {
	private static final String DICT_CODE="CHANNEL_GRADE";//对应数据库表SYS_DICT的DICT_CODE值
	
	/** 一级渠道 */
    public static final String WCS_ONE = "1";
    /** 二级渠道  */
    public static final String WCS_TWO = "2";
    /** 三级渠道 */
    public static final String WCS_THREE = "3";
    /** 四级渠道 */
    public static final String WCS_FOUR = "4";
    /** 键值对象 */
    public static Map<String, String> WCS_CHANNEL_GRADE_MAP = new LinkedHashMap<String, String>();
    static {
    	SysDictConstant.initSysDictByCode(WCS_CHANNEL_GRADE_MAP, DICT_CODE);
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
