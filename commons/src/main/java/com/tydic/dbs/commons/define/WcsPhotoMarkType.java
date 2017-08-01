package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsPhotoMarkType.java
 * @author caipeimin
 * @version 0.1
 * @WcsPhotoMarkType图片标识类型
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-03-12 04:25:15
 *      	Author: caipeimin
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsPhotoMarkType {
	private static final String DICT_CODE="PHOTO_MARK_TYPE";//对应数据库表SYS_DICT的DICT_CODE值
	/** 品类 */
    public static final Integer CLASS = 1;
    /** 品牌 */
    public static final Integer BRAND = 2;
    /** 标签 */
    public static final Integer LABEL = 3;
    /** 销售商品 */
    public static final Integer SALE_GOODS = 4;
    /** 渠道销售商品 */
    public static final Integer CHANNEL_SALE_GOODS = 5;
    /** 货品 */
    public static final Integer GOODS = 6;
    
    /** 键值对象 */
    public static Map<String, String> WCS_PHOTO_MARK_TYPE_MAP = new LinkedHashMap<String, String>();
    static {
    	/*WCS_PHOTO_MARK_TYPE_MAP.put(CLASS, "品类");
    	WCS_PHOTO_MARK_TYPE_MAP.put(BRAND, "品牌");
    	WCS_PHOTO_MARK_TYPE_MAP.put(LABEL, "标签");
    	WCS_PHOTO_MARK_TYPE_MAP.put(SALE_GOODS, "销售商品");
    	WCS_PHOTO_MARK_TYPE_MAP.put(CHANNEL_SALE_GOODS, "渠道销售商品");
    	WCS_PHOTO_MARK_TYPE_MAP.put(GOODS, "货品");*/
    	SysDictConstant.initSysDictByCode(WCS_PHOTO_MARK_TYPE_MAP, DICT_CODE);
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
