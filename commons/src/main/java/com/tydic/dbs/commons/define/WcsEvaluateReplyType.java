package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsEvaluateReplyType.java
 * @author molei
 * @version 0.1
 * @WcsEvaluateReplyType评价回复状态
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2016-02-26 15:25:15
 *      	Author: molei
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsEvaluateReplyType {
	private static final String DICT_CODE="EVALUATE_REPLY_TYPE";//对应数据库表SYS_DICT的DICT_CODE值
	
	/** 未回复 */
    public static final String WCS_Evaluate_No_Reply = "0";
    /** 已回复 */
    public static final String WCS_Evaluate_Replied = "1";
    /** 键值对象 */
    public static Map<String, String> WCS_EVALUATE_REPLY_TYPE_MAP = new LinkedHashMap<String, String>();
    static {
    	SysDictConstant.initSysDictByCode(WCS_EVALUATE_REPLY_TYPE_MAP, DICT_CODE);
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
