package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsPayType.java
 * @author yancan
 * @version 0.1
 * @WcsPayType支付类型
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-04-7 04:25:15
 *      	Author: yancan
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsPayType {
	private static final String DICT_CODE="PAY_TYPE";//对应数据库表SYS_DICT的DICT_CODE值
    /**货到付款 */
    public static final String WCS_CASH_ON_DELIVERY = "CASH_ON_DELIVERY";
    /**在线支付*/
    public static final String WCS_ONLINE_PAY = "ONLINE_PAY";
    /**冻结支付*/
    public static final String WCS_DJZF = "DJZF_PAY";
    /**现金/POS*/
    public static final String WCS_CASH_POS = "CASH_POS_PAY";
    /**上门收款*/
    public static final String WCS_SMSK = "SMSK";
    /**话费扣减*/
    public static final String WCS_HFKJ = "HFKJ";
    /**微支付*/
    public static final String WCS_WZF = "WZF";
    /**沃支付理财担保*/
    public static final String WOZF_PAY = "WOZF_PAY";

    /** 键值对象 */
    public static Map<String, String> WCS_PAY_TYPE_MAP = new LinkedHashMap<String, String>();
    static {
    	SysDictConstant.initSysDictByCode(WCS_PAY_TYPE_MAP, DICT_CODE);
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
