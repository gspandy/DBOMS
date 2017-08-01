package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsActSalesGoodsPriceClass.java
 * @author caipeimin
 * @version 0.1
 * @WcsActSalesGoodsPriceClass营销活动类型
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-03-20 16:25:15
 *      	Author: caipeimin
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsActSalesGoodsPriceClass {
	private static final String DICT_CODE="ACT_SALES_GOODS_PRICE_CLASS";//对应数据库表SYS_DICT的DICT_CODE值
	
	/** 降价 */
    public static final String PRICE_REDUCE = "1";
    /** 折扣 */
    public static final String PRICE_DISCOUNT = "2";
    /** 直减 */
    public static final String PRICE_CUT_DOWN = "3";
    
    /** 键值对象 */
    public static Map<String, String> WCS_ACT_SALES_GOODS_PRICE_CLASS_MAP = new LinkedHashMap<String, String>();
    static {
    /*	WCS_ACT_SALES_GOODS_PRICE_CLASS_MAP.put(PRICE_REDUCE, "降价");
    	WCS_ACT_SALES_GOODS_PRICE_CLASS_MAP.put(PRICE_DISCOUNT, "折扣");
    	WCS_ACT_SALES_GOODS_PRICE_CLASS_MAP.put(PRICE_CUT_DOWN, "直减");*/
    	SysDictConstant.initSysDictByCode(WCS_ACT_SALES_GOODS_PRICE_CLASS_MAP, DICT_CODE);
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
