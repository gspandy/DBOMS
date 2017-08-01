package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsAttrShowType.java
 * @author caipeimin
 * @version 0.1
 * @WcsMobileRuleType靓号规则类型
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-03-16 11:25:15
 *      	Author: caipeimin
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsMobileRuleType {
	private static final String DICT_CODE="MOBILE_RULE_TYPE";//对应数据库表SYS_DICT的DICT_CODE值
	/** 标准规则 */
    public static final String STANDARD_RULE = "1";
    /** 个性化规则 */
    public static final String PERSONALIZE_RULE = "2";
    /** 预付费规则 */
    public static final String PRE_PAY_RULE = "3";
    
    /** 键值对象 */
    public static Map<String, String> WCS_MOBILE_RULE_TYPE_MAP = new LinkedHashMap<String, String>();
    static {
    	/*WCS_MOBILE_RULE_TYPE_MAP.put(STANDARD_RULE, "标准规则");
    	WCS_MOBILE_RULE_TYPE_MAP.put(PERSONALIZE_RULE, "个性化规则");
    	WCS_MOBILE_RULE_TYPE_MAP.put(PRE_PAY_RULE, "预付费规则");*/
    	SysDictConstant.initSysDictByCode(WCS_MOBILE_RULE_TYPE_MAP, DICT_CODE);
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
