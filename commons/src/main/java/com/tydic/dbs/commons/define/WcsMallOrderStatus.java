package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsMallOrderStatus.java
 * @author yancan
 * @version 0.1
 * @WcsMallOrderStatus商城订单状态
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-03-16 11:25:15
 *      	Author: caipeimin
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsMallOrderStatus {
	private static final String DICT_CODE="MALL_ORDER_STATUS";//对应数据库表SYS_DICT的DICT_CODE值
	
	/**等待买家付款*/
	public static final String WAIT_BUYER_PAY = "1";
	/**等待卖家发货*/
	public static final String WAIT_SELLER_SEND_GOODS = "2";
	/**等待买家确认收货*/
	public static final String WAIT_BUYER_CONFIRM_GOODS = "3";
	/**买家已签收（货到付款专用）*/
	public static final String TRADE_BUYER_SIGNED = "4";
	/**交易成功*/
	public static final String TRADE_FINISHED = "5";
	/**交易取消*/
	public static final String TRADE_CANCLE = "6";
	/**退款处理中*/
	public static final String REFUND_PROCESS = "7";
	/**交易关闭*/
	public static final String TRADE_CLOSED = "0";
	/**交易取消中*/
	public static final String CANCLE_PROCESS="8";
	
	/**前端商城订单状态集合*/
	public static Map<String, String> WCS_MALL_ORDER_STATUS_MAP = new HashMap<String, String>();
	
	static {
		/*WCS_MALL_ORDER_STATUS_MAP.put(WAIT_BUYER_PAY, "等待买家付款");
		WCS_MALL_ORDER_STATUS_MAP.put(WAIT_SELLER_SEND_GOODS, "买家已付款,等待卖家发货");
		WCS_MALL_ORDER_STATUS_MAP.put(WAIT_BUYER_CONFIRM_GOODS, "等待买家确认收货");
		WCS_MALL_ORDER_STATUS_MAP.put(TRADE_BUYER_SIGNED, "买家已签收");//货到付款专用
		WCS_MALL_ORDER_STATUS_MAP.put(TRADE_FINISHED, "交易成功");
		WCS_MALL_ORDER_STATUS_MAP.put(TRADE_CANCLE, "交易取消");
		WCS_MALL_ORDER_STATUS_MAP.put(REFUND_PROCESS, "退款处理中");
		WCS_MALL_ORDER_STATUS_MAP.put(TRADE_CLOSED, "交易关闭");*/
		SysDictConstant.initSysDictByCode(WCS_MALL_ORDER_STATUS_MAP, DICT_CODE);
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
