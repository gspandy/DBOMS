package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsSaleGoodsStatus.java
 * @author yancan
 * @version 0.1
 * @WcsSaleGoodsStatus 销售商品状态
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-03-12 04:25:15
 *      	Author: yancan
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsSaleGoodsStatus {
	private static final String DICT_CODE="SALE_GOODS_STATUS";//对应数据库表SYS_DICT的DICT_CODE值
	 /** 待审核*/
    public static final String WCS_WAITING_REVIEW = "1";
    /** 审核不通过 */
    public static final String WCS_REVIEW_NOTHROUGH = "2";
    /** 可发布 */
    public static final String WCS_WAITING_RELEASE = "3";
    /** 停售 */
    public static final String WCS_STOP_SELLING = "0";
    /** 已下架*/
    public static final String WCS_WAITING_UP = "4";
    /** 已上架 */
    public static final String WCS_READY_UP = "5";
    /** 键值对象 */
    public static Map<String, String> WCS_SALE_GOODS_STATUS_MAP = new LinkedHashMap<String, String>();
    static {
    	/*WCS_SALE_GOODS_STATUS_MAP.put(WCS_WAITING_REVIEW, "待审核");
    	WCS_SALE_GOODS_STATUS_MAP.put(WCS_REVIEW_NOTHROUGH, "审核不通过");
    	WCS_SALE_GOODS_STATUS_MAP.put(WCS_WAITING_RELEASE, "待发布");
    	WCS_SALE_GOODS_STATUS_MAP.put(WCS_INVALID, "失效");*/
    	SysDictConstant.initSysDictByCode(WCS_SALE_GOODS_STATUS_MAP, DICT_CODE);
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
