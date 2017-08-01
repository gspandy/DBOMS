package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsActSaleType.java
 * @author caipeimin
 * @version 0.1
 * @WcsActSaleType营销活动类型
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-03-20 11:25:15
 *      	Author: caipeimin
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsActSaleType {
	private static final String DICT_CODE="ACT_SALE_TYPE";//对应数据库表SYS_DICT的DICT_CODE值
	/** 优惠券 */
    public static final String COUPON = "1";
    /** 抽奖 */
    public static final String PRIZE = "2";
    /** 团购 */
    public static final String GROUP_PURCHASE = "3";
    /** 抢购 */
    public static final String QUICK_PURCHASE = "4";
    /** 秒杀 */
    public static final String FLASHSALE = "5";
    /** 减免靓号 */
    public static final String REDUCE = "6";
    
    /** 键值对象 */
    public static Map<String, String> WCS_ACT_SALE_TYPE_MAP = new LinkedHashMap<String, String>();
    static {
    	/*WCS_ACT_SALE_TYPE_MAP.put(COUPON, "优惠券");
    	WCS_ACT_SALE_TYPE_MAP.put(PRIZE, "抽奖");
    	WCS_ACT_SALE_TYPE_MAP.put(GROUP_PURCHASE, "团购");
    	WCS_ACT_SALE_TYPE_MAP.put(QUICK_PURCHASE, "抢购");
    	WCS_ACT_SALE_TYPE_MAP.put(FLASHSALE, "秒杀 ");
    	WCS_ACT_SALE_TYPE_MAP.put(REDUCE, "减免靓号");*/
    	SysDictConstant.initSysDictByCode(WCS_ACT_SALE_TYPE_MAP, DICT_CODE);
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
