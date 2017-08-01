package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsInventoryChangeReason.java
 * @author yancan
 * @version 0.1
 * @WcsInventoryChangeReason库存变动原因
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-03-12 04:25:15
 *      	Author: yancan
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsInventoryChangeReason {
	private static final String DICT_CODE="INVENTORY_CHANGE_REASON";//对应数据库表SYS_DICT的DICT_CODE值
	 /**订单出库*/
    public static final String WCS_ORDER_OUT_LIBRARY = "0";
    /**采购入库 */
    public static final String WCS_GOODS_PURCHASE = "1";
    /**商品回库 */
    public static final String WCS_GOODS_BACK_LIBRARY = "2";
    
    /** 键值对象 */
    public static Map<String, String> WCS_INVENTORY_CHANGE_REASON_MAP = new LinkedHashMap<String, String>();
    static {
    	SysDictConstant.initSysDictByCode(WCS_INVENTORY_CHANGE_REASON_MAP, DICT_CODE);
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
