package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsOrderPayStatus.java
 * @author yancan
 * @version 0.1
 * @WcsOrderPayStatus订单支付状态
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-03-12 04:25:15
 *      	Author: yancan
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsOrderPayStatus {
	private static final String DICT_CODE="ORDER_PAY_STATUS";//对应数据库表SYS_DICT的DICT_CODE值

	/**未支付*/
	public static final String PAY_NO = "0";
	/**已支付*/
	public static final String PAY_YES = "1";
	/**部分退款*/
	public static final String REFUND_PART = "2";
	/**全额退款*/
	public static final String REFUND_FULL = "3";
	/**支付失败*/
	public static final String PAY_FAILURE = "4";
	/**支付处理中*/
	public static final String PAY_ING = "5";
	
	public static final Map<String, String> WCS_ORDER_PAY_STATUS_MAP = new HashMap<String, String>();
	
	static {
		SysDictConstant.initSysDictByCode(WCS_ORDER_PAY_STATUS_MAP, DICT_CODE);
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
