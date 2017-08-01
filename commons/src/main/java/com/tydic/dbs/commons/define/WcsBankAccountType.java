package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsBankAccountType.java
 * @author yancan
 * @version 0.1
 * @WcsBankAccountType银行账号类型
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-07-14 04:25:15
 *      	Author: yancan
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsBankAccountType {
	private static final String DICT_CODE="BANK_ACCOUNT_TYPE";//对应数据库表SYS_DICT的DICT_CODE值
	
	/** 中国银行 */
    public static final String PM_BOC = "1";
    /** 深圳发展银行（平安银行） */
    public static final String PM_SDB = "2";
    /** 招商银行 */
    public static final String PM_CMB = "3";
    /** 浦发银行 */
    public static final String PM_SPDB = "4";
    /** 交通银行 */
    public static final String PM_BCM = "5";
    /** 支付宝账号 */
    public static final String PM_ALIPAY = "6";
    /** 易宝账号 */
    public static final String PM_YEEPAY = "7";
    /** 财付通账号 */
    public static final String PM_TENPAY = "8";
    /** 农业银行 */
    public static final String PM_ABC = "9";
    /** 建设银行 */
    public static final String PM_CCB = "10";
    /** 工商银行 */
    public static final String PM_ICBC = "11";
    /** 广东发展银行 */
    public static final String PM_GDB = "12";
    /** 光大银行 */
    public static final String PM_CEB = "13";
    /** 东莞银行 */
    public static final String PM_DGB = "14";
    /** 汇丰银行 */
    public static final String PM_HSBC = "15";
    /** 兴业银行 */
    public static final String PM_CIB = "16";
    /** 键值对象 */
    public static Map<String, String> WCS_BANK_ACCOUNT_TYPE_MAP = new LinkedHashMap<String, String>();
    static {
    	/*WCS_ACCESS_TYPE_MAP.put(WCS_PC_TERMINAL, "PC");
    	WCS_ACCESS_TYPE_MAP.put(WCS_WAP_TERMINAL, "WAP");
    	WCS_ACCESS_TYPE_MAP.put(WCS_PAD_TERMINAL, "PAD");*/
    	SysDictConstant.initSysDictByCode(WCS_BANK_ACCOUNT_TYPE_MAP, DICT_CODE);
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
