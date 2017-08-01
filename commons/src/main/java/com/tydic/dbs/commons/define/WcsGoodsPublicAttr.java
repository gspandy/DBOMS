/**
 * 
 */
package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsGoodsPublicAttr.java
 * @author yancan
 * @version 0.1
 * @WcsCustomerType商品属性信息
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-03-12 04:25:15
 *      	Author: yuchanghong
 *      	Modification: this file was created
 *   	2. ...
 */

public class WcsGoodsPublicAttr {
	private static final String DICT_CODE = "GOODS_PUBLIC_ATTR";// 对应数据库表SYS_DICT的DICT_CODE值
	/** BSS编码 */
	public static final String WCS_BSS = "bss_code";
	
	/** 是否副卡*/
	public static final String WCS_IS_ATTACHED = "is_attached";
	
	/** 键值对象 */
	public static Map<String, String> WCS_GOODS_PUBLIC_ATTR_MAP = new LinkedHashMap<String, String>();
	
	static {
		SysDictConstant.initSysDictByCode(WCS_GOODS_PUBLIC_ATTR_MAP, DICT_CODE);
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
