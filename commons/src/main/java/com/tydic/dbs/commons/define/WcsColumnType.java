/*
 * com.tydic.dbs.commons.define  2015-6-2
 *
 * Copyright 2010 Shenzhen TYDIC Information Technology Co.,Ltd.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/** 
 * @ClassName: WcsColumnType 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author Carson yuchanghong@tydic.com 
 * @date 2015-6-2 上午10:40:43 
 *  
 */
public class WcsColumnType {
	
	private static final String DICT_CODE="COLUMN_TYPE";//对应数据库表SYS_DICT的DICT_CODE值
	/** 新闻分类  */
	public static final String WCS_COLUMN_NEWS = "NEWS";
	/** 广告分类  */
	public static final String WCS_COLUMN_ADS = "ADS";
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
