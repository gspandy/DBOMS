package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsOrderStatus.java
 * @author yancan
 * @version 0.1
 * @WcsOrderStatus订单外部状态
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-03-12 04:25:15
 *      	Author: yancan
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsOrderOutStatus {
	private static final String DICT_CODE="OUT_ORDER_STATUS";//对应数据库表SYS_DICT的DICT_CODE值
	/** 等待商家付款 */
    public static final String ORDER_SELLER_PAY = "01";
	/** 等待商家发货 */
    public static final String ORDER_SELLER_SEND_GOODS = "02";
    /** 商家已发货 */
    public static final String ORDER_SENDED_GOODS = "03";
    /** 交易成功 */
    public static final String ORDER_TRADE_SUCCESS = "04";
    /** 退单处理中 */
    public static final String ORDER_REFUND_PROCESSING= "05";
    /** 交易关闭 */
    public static final String ORDER_TRADE_TURN_OFF = "06";
    /** 键值对象 */
    public static Map<String, String> WCS_OUT_ORDER_STATUS_MAP = new LinkedHashMap<String, String>();
    
    static {
       SysDictConstant.initSysDictByCode(WCS_OUT_ORDER_STATUS_MAP, DICT_CODE);
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
