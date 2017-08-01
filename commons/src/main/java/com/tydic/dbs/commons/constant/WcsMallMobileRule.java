package com.tydic.dbs.commons.constant;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @file  WcsMallMobileRule.java
 * @author yancan
 * @version 0.1
 * @WcsMallMobileRule前端商城靓号规则
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-05-27 04:25:15
 *      	Author: yancan
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsMallMobileRule {
	
	/** AAAAA */
    public static final String WCS_AAAAA = "9";
    /** AAAA */
    public static final String WCS_AAAA = "8";
    /** ABCDE*/
    public static final String WCS_ABCDE = "7";
    /** ABCD*/
    public static final String WCS_ABCD = "5";
    /** AAA*/
    public static final String WCS_AAA = "6";
    /** AABB*/
    public static final String WCS_AABB = "4";
    /** ABAB*/
    public static final String WCS_ABAB = "3";
    /** ABC*/
    public static final String WCS_ABC = "2";
    /** AA*/
    public static final String WCS_AA = "1";
    /** 键值对象 */
    public static Map<String, String> WCS_MALL_MOBILE_RULE_MAP = new LinkedHashMap<String, String>();
    static {
    	WCS_MALL_MOBILE_RULE_MAP.put(WCS_AAAAA, "AAAAA");
    	WCS_MALL_MOBILE_RULE_MAP.put(WCS_AAAA, "AAAA");
    	WCS_MALL_MOBILE_RULE_MAP.put(WCS_ABCDE, "ABCDE");
    	WCS_MALL_MOBILE_RULE_MAP.put(WCS_ABCD, "ABCD");
    	WCS_MALL_MOBILE_RULE_MAP.put(WCS_AAA, "AAA");
    	WCS_MALL_MOBILE_RULE_MAP.put(WCS_AABB, "AABB");
    	WCS_MALL_MOBILE_RULE_MAP.put(WCS_ABAB, "ABAB");
    	WCS_MALL_MOBILE_RULE_MAP.put(WCS_ABC, "ABC");
    	WCS_MALL_MOBILE_RULE_MAP.put(WCS_AA, "AA");
    }
    
}
