package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsOrderVerifyRule.java
 * @author shuyongfu
 * @version 0.1
 * @WcsOrderVerifyRule下单校验规则
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-08-18 10:15:15
 *      	Author: yancan
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsOrderVerifyRule {
	private static final String DICT_CODE="ORDER_VERIFY_RULE";//对应数据库表SYS_DICT的DICT_CODE值
	
	 /** 黑名单校验 */
    public static final String WCS_BLACKLIST_VERIFY = "1";
    /** 欠费校验	 */
    public static final String WCS_OVERDUE_VERIFY = "2";
    /** 合约互斥校验*/
    public static final String WCS_MUTEX_VERIFY = "3";
    /** 一证多户校验*/
    public static final String WCS_MULTI_VERIFY = "4";
    /** 宽带融合业务校验*/
    public static final String WCS_BROADBAND_VERIFY = "5";
    /** 未解冻活动赠款校验*/
    public static final String WCS_NOT_THAW_VERIFY = "6";
    /** 用户实时欠费状态拦截校验*/
    public static final String WCS_REALTIME_VERIFY = "7";
    /** 键值对象 */
    public static Map<String, String> WCS_ORDER_VERIFY_RULE_MAP = new LinkedHashMap<String, String>();
    static {
    	/*WCS_ACCESS_TYPE_MAP.put(WCS_PC_TERMINAL, "PC");
    	WCS_ACCESS_TYPE_MAP.put(WCS_WAP_TERMINAL, "WAP");
    	WCS_ACCESS_TYPE_MAP.put(WCS_PAD_TERMINAL, "PAD");*/
    	SysDictConstant.initSysDictByCode(WCS_ORDER_VERIFY_RULE_MAP, DICT_CODE);
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
