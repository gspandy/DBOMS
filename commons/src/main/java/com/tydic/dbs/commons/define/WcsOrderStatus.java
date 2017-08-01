package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsOrderStatus.java
 * @author yancan
 * @version 0.1
 * @WcsOrderStatus订单内部状态
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-03-12 04:25:15
 *      	Author: yancan
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsOrderStatus {
	private static final String DICT_CODE="ORDER_STATUS";//对应数据库表SYS_DICT的DICT_CODE值
	/**等待买家付款*/
	public static final String WAIT_BUYER_PAY = "00";
	/** 业务受理 */
    public static final String ORDER_BUSINESS_ACCEPTANCE = "01";
	/** 发货 */
    public static final String ORDER_SENDGOODS = "02";
    /** 待归档 */
    public static final String ORDER_CUSTOMER_RETURN = "03";
    /** 已归档 */
    public static final String ORDER_PRE_PICKING = "04";
    /** 申请退单 */
    public static final String ORDER_SINGLE_APPLICATION = "05";
    /** 退单成功 */
    public static final String ORDER_SINGLE_SUCCESS = "06";
    /** 退单失败 */
    public static final String ORDER_SINGLE_FAIL = "07";
    /** 键值对象 */
    public static Map<String, String> WCS_ORDER_STATUS_MAP = new LinkedHashMap<String, String>();
    
    static {
       SysDictConstant.initSysDictByCode(WCS_ORDER_STATUS_MAP, DICT_CODE);
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
