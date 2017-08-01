package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsTradeStatus.java
 * @author caipeimin
 * @version 0.1
 * @WcsTradeStatus交易状态
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-03-12 04:25:15
 *      	Author: yancan
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsTradeStatus {
	private static final String DICT_CODE="TRADE_STATUS";//对应数据库表SYS_DICT的DICT_CODE值

	/**交易成功*/
	public static final String SUCCESS = "0";
	/**交易失败*/
	public static final String FAILED = "1";
	
	public static final Map<String, String> WCS_TRADE_STATUS_MAP = new HashMap<String, String>();
	
	static {
		SysDictConstant.initSysDictByCode(WCS_TRADE_STATUS_MAP, DICT_CODE);
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
