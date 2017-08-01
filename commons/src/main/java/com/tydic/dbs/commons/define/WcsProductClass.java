package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsProductClass.java
 * @author yancan
 * @version 0.1
 * @WcsProductClass货品品类
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-03-12 04:25:15
 *      	Author: yancan
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsProductClass {
	private static final String DICT_CODE="PRODUCT_CLASS";//对应数据库表SYS_DICT的DICT_CODE值
	/**手机*/
	public static final String WCS_PHONE = "10000";
	/**合约计划*/
	public static final String WCS_CONTRACT_PLAN = "10001";
	/**套餐*/
	public static final String WCS_PACKAGE = "10002";
	/**上网卡硬件*/
	public static final String WCS_NETWORK = "10003";
	/**成品卡*/
	public static final String WCS_PRODUCT_CARD = "10004";
	/**配件*/
	public static final String WCS_PARTS = "10006";
	/**礼品*/
	public static final String WCS_GIFT = "10007";
	/**增值业务*/
	public static final String WCS_VALUE_ADD = "10008";
	/**业务包*/
	public static final String WCS_PACKAGE_BUSINESS = "10009";
	/**转兑包*/
	public static final String WCS_AGAINST_PACK = "10010";
	/**汽车**/
	public static String GOODS_CLASS_CAR = "10011";
	/**旅游**/
	public static String GOODS_CLASS_TRAVEL = "10012";
	/**资费计划**/
	public static final String WCS_PRICE_PLAN = "10013";
	/**房产**/
	public static String GOODS_CLASS_HOUSE = "10020";
	/**单件产品*/
	public static final String WCS_SINGLE = "10021";
	
	
	public static Map<String, String> WCS_PRODUCT_CLASS_MAP = new HashMap<String, String>();
	static {
		/*WCS_PRODUCT_CLASS_MAP.put(WCS_PHONE, "手机");
		WCS_PRODUCT_CLASS_MAP.put(WCS_CONTRACT_PLAN, "合约计划");
		WCS_PRODUCT_CLASS_MAP.put(WCS_PACKAGE, "套餐");
		WCS_PRODUCT_CLASS_MAP.put(WCS_NETWORK, "上网卡硬件");
		WCS_PRODUCT_CLASS_MAP.put(WCS_PRODUCT_CARD, "成品卡");
		WCS_PRODUCT_CLASS_MAP.put(WCS_WHITE_CARD, "白卡");
		WCS_PRODUCT_CLASS_MAP.put(WCS_PARTS, "配件");
		WCS_PRODUCT_CLASS_MAP.put(WCS_GIFT, "礼品");
		WCS_PRODUCT_CLASS_MAP.put(WCS_VALUE_ADD, "增值业务");
		WCS_PRODUCT_CLASS_MAP.put(WCS_PACKAGE_BUSINESS, "业务包");
		WCS_PRODUCT_CLASS_MAP.put(WCS_AGAINST_PACK, "转兑包");*/
		SysDictConstant.initSysDictByCode(WCS_PRODUCT_CLASS_MAP, DICT_CODE);
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
