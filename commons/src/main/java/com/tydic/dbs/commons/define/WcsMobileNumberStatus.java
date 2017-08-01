package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsAttrShowType.java
 * @author caipeimin
 * @version 0.1
 * @WcsMobileNumberStatus号卡状态
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-03-16 11:25:15
 *      	Author: caipeimin
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsMobileNumberStatus {
	private static final String DICT_CODE="MOBILE_NUMBER_STATUS";//对应数据库表SYS_DICT的DICT_CODE值
	/** 已入库 */
    public static final String IN_WAREHOUSE = "1";
    /** 已分配 */
    public static final String ALLOCATED = "2";
    /** 已上架 */
    public static final String ON_SHELF = "3";
	/** 已预占 */
    public static final String PRE_TAKE = "4";
    /** 已下架 */
    public static final String DOWN_SHELF = "5";
    /** 已预订 */
    public static final String PRE_BOOKED = "6";
	/** 付费预付 */
    public static final String PRE_PAY = "7";
    /** 开户 */
    public static final String OPEN_ACCOUNT = "8";
    /** 回收 */
    public static final String RECYCLE = "9";
    /** 出库 */
    public static final String OUT_WAREHOUSE = "10";
    
    /** 键值对象 */
    public static Map<String, String> WCS_MOBILE_NUMBER_STATUS_MAP = new LinkedHashMap<String, String>();
    static {
    	/*WCS_MOBILE_NUMBER_STATUS_MAP.put(IN_WAREHOUSE, "已入库");
    	WCS_MOBILE_NUMBER_STATUS_MAP.put(ALLOCATED, "已分配");
    	WCS_MOBILE_NUMBER_STATUS_MAP.put(ON_SHELF, "已上架");
    	WCS_MOBILE_NUMBER_STATUS_MAP.put(PRE_TAKE, "已预占");
    	WCS_MOBILE_NUMBER_STATUS_MAP.put(DOWN_SHELF, "已下架");
    	WCS_MOBILE_NUMBER_STATUS_MAP.put(PRE_BOOKED, "已预订");
    	WCS_MOBILE_NUMBER_STATUS_MAP.put(PRE_PAY, "付费预付");
    	WCS_MOBILE_NUMBER_STATUS_MAP.put(OPEN_ACCOUNT, "开户");
    	WCS_MOBILE_NUMBER_STATUS_MAP.put(RECYCLE, "回收");
    	WCS_MOBILE_NUMBER_STATUS_MAP.put(OUT_WAREHOUSE, "出库");*/
    	SysDictConstant.initSysDictByCode(WCS_MOBILE_NUMBER_STATUS_MAP, DICT_CODE);
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
