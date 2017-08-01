package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsPromotionGiftType.java
 * @author yancan
 * @version 0.1
 * @WcsPromotionGiftType促销赠品类型
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-03-12 04:25:15
 *      	Author: yancan
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsPromotionGiftType {
	private static final String DICT_CODE="PROMOTION_GIFT_TYPE";//对应数据库表SYS_DICT的DICT_CODE值
	 /**礼品*/
    public static final String WCS_GIFT = "0";
    /**增值业务 */
    public static final String WCS_VALUE_ADD = "1";
    /**业务包 */
    public static final String WCS_PACKAGE_BUSINESS = "2";
    /**配件 */
    public static final String WCS_PARTS = "3";
    /**转兑包 */
    public static final String WCS_AGAINST_PACK = "4";
    
    /** 键值对象 */
    public static Map<String, String> WCS_PROMOTION_GIFT_TYPE_MAP = new LinkedHashMap<String, String>();
    static {
    	SysDictConstant.initSysDictByCode(WCS_PROMOTION_GIFT_TYPE_MAP, DICT_CODE);
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
