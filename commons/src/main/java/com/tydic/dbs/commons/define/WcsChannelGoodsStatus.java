package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsChannelGoodsStatus.java
 * @author yancan
 * @version 0.1
 * @WcsChannelGoodsStatus 渠道销售商品状态
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-03-12 04:25:15
 *      	Author: yancan
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsChannelGoodsStatus {
	private static final String DICT_CODE="CHANNEL_GOODS_STATUS";//对应数据库表SYS_DICT的DICT_CODE值
	/** 待上架*/
    public static final String WCS_WAITING_UP = "1";
    /** 已上架 */
    public static final String WCS_READY_UP = "2";
    /** 已归档 */
    public static final String WCS_READY_FILE = "3";
    /** 停售 */
    public static final String WCS_STOP_SELLING = "0";
    /** 键值对象 */
    public static Map<String, String> WCS_CHANNEL_GOODS_STATUS_MAP = new LinkedHashMap<String, String>();
    static {
    	/*WCS_CHANNEL_GOODS_STATUS_MAP.put(WCS_WAITING_UP, "待上架");
    	WCS_CHANNEL_GOODS_STATUS_MAP.put(WCS_READY_UP, "已上架");
    	WCS_CHANNEL_GOODS_STATUS_MAP.put(WCS_READY_FILE, "已归档");
    	WCS_CHANNEL_GOODS_STATUS_MAP.put(WCS_INVALID, "失效");*/
    	SysDictConstant.initSysDictByCode(WCS_CHANNEL_GOODS_STATUS_MAP, DICT_CODE);
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
