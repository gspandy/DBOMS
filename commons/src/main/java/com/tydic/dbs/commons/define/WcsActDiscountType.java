/**
 * 
 */
package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsActDiscountType.java
 * @author yuchanghong
 * @version 0.1
 * @WcsActDiscountType优惠类型
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-07-26 23:13:15
 *      	Author: yuchanghong
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsActDiscountType {
	private static final String DICT_CODE="WCS_ACT_DISCOUNT_TYPE";//对应数据库表SYS_DICT的DICT_CODE值
	
	/**折扣减免*/
	public static final String WCS_DISCOUNT_REDUCTION = "0";
	/**定额减免*/
	public static final String WCS_QUOTA_REDUCTION = "1";
	/**固定金额*/
	public static final String WCS_FIXED_AMOUNT = "2";
	/**定额加价*/
	public static final String WCS_FIXED_PRICE = "3";
	/** 键值对象 */
    public static Map<String, String> WCS_ACT_DISCOUNT_TYPE_MAP = new LinkedHashMap<String, String>();
    static {
    	/*WCS_ACCESS_TYPE_MAP.put(WCS_PC_TERMINAL, "PC");
    	WCS_ACCESS_TYPE_MAP.put(WCS_WAP_TERMINAL, "WAP");
    	WCS_ACCESS_TYPE_MAP.put(WCS_PAD_TERMINAL, "PAD");*/
    	SysDictConstant.initSysDictByCode(WCS_ACT_DISCOUNT_TYPE_MAP, DICT_CODE);
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
