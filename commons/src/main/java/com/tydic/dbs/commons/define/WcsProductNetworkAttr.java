package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsProductNetworkAttr.java
 * @author yancan
 * @version 0.1
 * @WcsProductNetworkAttr货品-上网卡硬件类属性
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-03-12 04:25:15
 *      	Author: yancan
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsProductNetworkAttr {
	private static final String DICT_CODE="PRODUCT_NETWORK_ATTR";//对应数据库表SYS_DICT的DICT_CODE值
	
	/**颜色 */
    public static final String WCS_ATTR_COLOR = "color";
    /**总线接口 */
    public static final String WCS_ATTR_BUS = "bus";
    /**上行速率*/
    public static final String WCS_ATTR_UPLOAD = "upload";
    /**下行速率*/
    public static final String WCS_ATTR_DOWNLOAD = "download";
    /**套餐类型*/
    public static final String WCS_ATTR_PAY_TYPE = "pay_type";
    /**特点*/
    public static final String WCS_ATTR_FEATURE = "network_card_feature";
    /** 键值对象 */
    public static Map<String, String> WCS_PRODUCT_NETWORK_ATTR_MAP = new LinkedHashMap<String, String>();
    static {
    	SysDictConstant.initSysDictByCode(WCS_PRODUCT_NETWORK_ATTR_MAP, DICT_CODE);
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
