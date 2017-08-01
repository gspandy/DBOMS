/**
 * 
 */
package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsContractPlanGoodsAttr.java
 * @author yancan
 * @version 0.1
 * @WcsCustomerType合约机商品属性信息
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-03-12 04:25:15
 *      	Author: yuchanghong
 *      	Modification: this file was created
 *   	2. ...
 */

public class WcsContractPlanGoodsAttr {
	private static final String DICT_CODE = "CONTRACT_PLAN_GOODS_ATTR";// 对应数据库表SYS_DICT的DICT_CODE值
	/** 预存金额 */
	public static final String WCS_DEPOSIT_FEE = "deposit_fee";
	
	/** 入网返还 */
	public static final String WCS_NET_RETURN_FEE = "net_return_fee";

	/** 分月返还 */
	public static final String WCS_MONTH_PRESENT_FEE = "month_present_fee";
	
	/** 手机款 */
	public static final String WCS_PHONE_FEE = "phone_fee";	
	
	/** 键值对象 */
	public static Map<String, String> WCS_CONTRACT_PLAN_GOODS_ATTR_MAP = new LinkedHashMap<String, String>();
	
	static {
		SysDictConstant.initSysDictByCode(WCS_CONTRACT_PLAN_GOODS_ATTR_MAP, DICT_CODE);
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
