package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsInvoiceType.java
 * @author yancan
 * @version 0.1
 * @WcsInvoiceType发票类型
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-03-12 04:25:15
 *      	Author: yancan
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsInvoiceType {
	private static final String DICT_CODE="INVOICE_TYPE";//对应数据库表SYS_DICT的DICT_CODE值
	/**普通发票*/
	public static final String COMMERCIAL_INVOICE = "1";
	/**增值税发票*/
//	public static final String VAT_INVOICE = "2";
	
	public static Map<String, String> WCS_INVOICE_TYPE_MAP= new HashMap<String, String>();
	static {
	/*	WCS_INVOICE_TYPE_MAP.put(COMMERCIAL_INVOICE, "普通发票");
		WCS_INVOICE_TYPE_MAP.put(VAT_INVOICE, "增值税发票");*/
		SysDictConstant.initSysDictByCode(WCS_INVOICE_TYPE_MAP, DICT_CODE);
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
