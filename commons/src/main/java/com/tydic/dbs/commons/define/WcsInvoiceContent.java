package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsInvoiceContent.java
 * @author yancan
 * @version 0.1
 * @WcsInvoiceContent发票内容
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-03-12 04:25:15
 *      	Author: yancan
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsInvoiceContent {
	private static final String DICT_CODE="INVOICE_CONTENT";//对应数据库表SYS_DICT的DICT_CODE值
    /** 明细 */
    public static final String WCS_INVOICE_DETAIL = "1";
    /** 键值对象 */
    public static Map<String, String> WCS_INVOICE_CONTENT_MAP = new LinkedHashMap<String, String>();
    static {
    	/*WCS_INVOICE_CONTENT_MAP.put(WCS_INVOICE_DETAIL, "明细");*/
    	SysDictConstant.initSysDictByCode(WCS_INVOICE_CONTENT_MAP, DICT_CODE);
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
