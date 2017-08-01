package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsActRuleSource.java
 * @author caipeimin
 * @version 0.1
 * @WcsActRuleSource营销活动规则来源
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-03-20 11:25:15
 *      	Author: caipeimin
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsActRuleSource {
	private static final String DICT_CODE="ACT_RULE_SOURCE";//对应数据库表SYS_DICT的DICT_CODE值
	
	/** 优惠券规则 */
    public static final String COUPON = "1";
    /** 抽奖规则 */
    public static final String PRIZE = "2";
    /** 团购规则 */
    public static final String GROUP_PURCHASE = "3";
    /** 抢购规则 */
    public static final String QUICK_PURCHASE = "4";
    /** 秒杀规则 */
    public static final String FLASHSALE = "5";
    /** 减免靓号规则 */
    public static final String REDUCE = "6";
    
    /** 键值对象 */
    public static Map<String, String> WCS_ACT_RULE_SOURCE_MAP = new LinkedHashMap<String, String>();
    static {
    	/*WCS_ACT_RULE_SOURCE_MAP.put(COUPON, "优惠券规则");
    	WCS_ACT_RULE_SOURCE_MAP.put(PRIZE, "抽奖规则");
    	WCS_ACT_RULE_SOURCE_MAP.put(GROUP_PURCHASE, "团购规则");
    	WCS_ACT_RULE_SOURCE_MAP.put(QUICK_PURCHASE, "抢购规则");
    	WCS_ACT_RULE_SOURCE_MAP.put(FLASHSALE, "秒杀 规则");
    	WCS_ACT_RULE_SOURCE_MAP.put(REDUCE, "减免靓号规则");*/
    	SysDictConstant.initSysDictByCode(WCS_ACT_RULE_SOURCE_MAP, DICT_CODE);
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
