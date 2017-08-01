package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsOrderCancleReason.java
 * @author yancan
 * @version 0.1
 * @WcsOrderCancleReason订单取消原因
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-03-12 04:25:15
 *      	Author: yancan
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsOrderCancleReason {
	private static final String DICT_CODE="ORDER_CANCLE_REASON";//对应数据库表SYS_DICT的DICT_CODE值
    /**客户申请取消订单 */
    public static final String WCS_APPLY_CANCEL_ORDER = "1";
    /**客户身份证为黑名单*/
    public static final String WCS_CUSTOMER_BLACKLIST = "2";
    /**客户身份证下面有欠费号码*/
    public static final String WCS_EXISTING_ARREARS = "3";
    /**客户身份证已有5户3G号码*/
    public static final String WCS_EXISTING_FIVE3G = "4";
    /**号码已被开户*/
    public static final String WCS_NUMBER_OPENED = "5";
    /**客户要求取消订单*/
    public static final String WCS_REQUEST_CANCEL_ORDER = "6";
    /**客户更改其他号码*/
    public static final String WCS_CHANGE_NUMBER = "7";
    /**渠道商要求取消订单*/
    public static final String WCS_CHANNEL_CANCEL_ORDER = "8";
    /**其他原因*/
    public static final String WCS_ELSE = "9";
    /** 键值对象 */
    public static Map<String, String> WCS_ORDER_CANCLE_REASON_MAP = new LinkedHashMap<String, String>();
    static {
    	/*WCS_ORDER_CANCLE_REASON_MAP.put(WCS_APPLY_CANCEL_ORDER, "客户申请取消订单");
    	WCS_ORDER_CANCLE_REASON_MAP.put(WCS_CUSTOMER_BLACKLIST, "客户身份证为黑名单");
    	WCS_ORDER_CANCLE_REASON_MAP.put(WCS_EXISTING_ARREARS, "客户身份证下面有欠费号码");
    	WCS_ORDER_CANCLE_REASON_MAP.put(WCS_EXISTING_FIVE3G, "客户身份证已有5户3G号码");
    	WCS_ORDER_CANCLE_REASON_MAP.put(WCS_NUMBER_OPENED, "号码已被开户");
    	WCS_ORDER_CANCLE_REASON_MAP.put(WCS_REQUEST_CANCEL_ORDER, "客户要求取消订单");
    	WCS_ORDER_CANCLE_REASON_MAP.put(WCS_CHANGE_NUMBER, "客户更改其他号码");
    	WCS_ORDER_CANCLE_REASON_MAP.put(WCS_CHANNEL_CANCEL_ORDER, "渠道商要求取消订单");
    	WCS_ORDER_CANCLE_REASON_MAP.put(WCS_ELSE, "其他原因");*/
    	SysDictConstant.initSysDictByCode(WCS_ORDER_CANCLE_REASON_MAP, DICT_CODE);
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
