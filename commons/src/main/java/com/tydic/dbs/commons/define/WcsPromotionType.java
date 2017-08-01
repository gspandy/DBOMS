package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsPromotionType.java
 * @author yancan
 * @version 0.1
 * @WcsPromotionType促销活动类型
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-03-12 04:25:15
 *      	Author: yancan
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsPromotionType {
	private static final String DICT_CODE="PROMOTION_TYPE";//对应数据库表SYS_DICT的DICT_CODE值
	 /**满减*/
    public static final String WCS_FULL_REDUCTION = "0";
    /**满赠 */
    public static final String WCS_FULL_GIFT = "1";
    /**团购*/
    public static final String WCS_GROUP_PURCHASE = "2";
    /**秒杀 */
    public static final String WCS_SECKILL = "3";
    /**直降*/
    public static final String WCS_STRAIGHT_DOWN = "4";
    /**预售*/
    public static final String WCS_PRE_SELL = "5";
    /** 键值对象 */
    public static Map<String, String> WCS_PROMOTION_TYPE_MAP = new LinkedHashMap<String, String>();
    static {
    	SysDictConstant.initSysDictByCode(WCS_PROMOTION_TYPE_MAP, DICT_CODE);
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
