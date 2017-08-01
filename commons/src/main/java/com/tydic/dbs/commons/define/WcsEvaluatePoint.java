package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsEvaluatePoint.java
 * @author molei
 * @version 0.1
 * @WcsEvaluatePoint评价打分
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2016-02-26 15:25:15
 *      	Author: molei
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsEvaluatePoint {
	private static final String DICT_CODE="EVALUATE_POINT";//对应数据库表SYS_DICT的DICT_CODE值
	
	/** 1分 */
    public static final String WCS_Evaluate_Point1 = "1";
    /** 2分 */
    public static final String WCS_Evaluate_Point2 = "2";
    /** 3分 */
    public static final String WCS_Evaluate_Point3 = "3";
    /** 4分 */
    public static final String WCS_Evaluate_Point4 = "4";
    /** 5分 */
    public static final String WCS_Evaluate_Point5 = "5";
    /** 键值对象 */
    public static Map<String, String> WCS_EVALUATE_POINT_MAP = new LinkedHashMap<String, String>();
    static {
    	SysDictConstant.initSysDictByCode(WCS_EVALUATE_POINT_MAP, DICT_CODE);
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
