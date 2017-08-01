package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsInvoicePrintType.java
 * @author yancan
 * @version 0.1
 * @WcsInvoicePrintType发票打印类型
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-03-12 04:25:15
 *      	Author: yancan
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsInvoicePrintType {
	private static final String DICT_CODE="INVOICE_PRINT_TYPE";//对应数据库表SYS_DICT的DICT_CODE值
	/**一次性打印*/
	public static final String DISPOSABLE_PRINT = "1"; 
	/**分月打印*/
	public static final String MONTHLY_PRINT = "2";
	/**不打印发票*/
	public static final String NOT_PRINT = "3";
	public static Map<String, String> WCS_INVOICE_PRINT_TYPE_MAP = new LinkedHashMap<String, String>();
	static {
		SysDictConstant.initSysDictByCode(WCS_INVOICE_PRINT_TYPE_MAP, DICT_CODE);
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
