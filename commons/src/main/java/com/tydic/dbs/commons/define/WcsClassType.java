/**
 * 
 */
package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsClassType.java
 * @author yancan
 * @version 0.1
 * @WcsClassType 品类类型
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-03-12 04:25:15
 *      	Author: yancan
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsClassType {
	private static final String DICT_CODE="CLASS_TYPE";//对应数据库表SYS_DICT的DICT_CODE值
	/** 货品分类  */
	public static final String WCS_PRODUCT_CLASS_TYPE = "1";
	/** 商品分类  */
	public static final String WCS_GOODS_CLASS_TYPE = "2";
    /** 键值对象 */
	public static Map<String, String> WCS_CLASS_TYPE_MAP = new LinkedHashMap<String, String>();
	
    static {
    	/*WCS_CLASS_TYPE_MAP.put(WCS_PRODUCT_CLASS_TYPE, "货品分类");
    	WCS_CLASS_TYPE_MAP.put(WCS_GOODS_CLASS_TYPE, "商品分类 ");*/
    	SysDictConstant.initSysDictByCode(WCS_CLASS_TYPE_MAP, DICT_CODE);
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
