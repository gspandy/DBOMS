package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsSyncOperType.java
 * @author yuchanghong
 * @version 0.1
 * @WcsSyncOperType库存操作类型
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-05-14 04:25:15
 *      	Author: WcsSyncOperType.java
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsStockOperType {
	private static final String DICT_CODE="STOCK_OPER_TYPE";//对应数据库表SYS_DICT的DICT_CODE值
	
	/**预留仓位重置         **/
	public static final String 	WCS_RESERVED_STOCK_REST="WS_RESERVED_REST";
	/**销售仓位回退         **/
	public static final String 	WCS_SALE_STOCK_REST="WS_SALE_REST";
	/**库存调拨         **/
	public static final String 	WCS_STOCK_TRANSFER ="WS_TRANSFER";
	/**库存扣减         **/
	public static final String 	WCS_STOCK_DEDUCT="WS_DEDUCT";
	/**库存释放    **/
	public static final String WCS_STOCK_RELEASE="WS_RELEASE";

	public static Map<String, String> WCS_STOCK_OPER_TYPE_MAP = new LinkedHashMap<String, String>();
    static {
    	SysDictConstant.initSysDictByCode(WCS_STOCK_OPER_TYPE_MAP, DICT_CODE);
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
