package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsWfNodesCode.java
 * @author zhanjianming
 * @version 0.1
 * @WcsWfNodesCode流程节点名称编码
 * Copyright(C), 2014-2015
 *		 Guangzhou Tydic Technology Co., Ltd.
 * History
 *   	1. Date: 2015-01-22 15:35:15
 *      	Author: zhanjianming
 *      	Modification: this file was created
 *   	2. ...
 */

public class WcsWfNodesCode {
	
	private static final String DICT_CODE="WF_NODES_CODE";//对应数据库表SYS_DICT的DICT_CODE值
	/** 业务处理 */
    public static final String WCS_ORDER_PROC = "order_process";
    /** 发货处理 */
    public static final String WCS_DELIVERY_PROC = "delivery_process";
    /** 回单归档 */
    public static final String WCS_CLOSE_FILE = "close_file";
    /** 退单处理 */
    public static final String WCS_REFUND_PROC = "refund_process";
    /** 键值对象 */
    public static Map<String, String> WCS_WF_NODES_CODE_MAP = new LinkedHashMap<String, String>();
    static {
    	SysDictConstant.initSysDictByCode(WCS_WF_NODES_CODE_MAP, DICT_CODE);
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
