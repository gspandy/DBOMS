package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsAttrShowType.java
 * @author caipeimin
 * @version 0.1
 * @WcsLogisticsComType物流公司类型
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-03-16 11:25:15
 *      	Author: caipeimin
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsLogisticsComType {
	private static final String DICT_CODE="LOGISTICS_COM_TYPE";//对应数据库表SYS_DICT的DICT_CODE值
	/** 物流公司 */
    public static final String LOGISTICS_COM = "1";
    /** 自有物流 */
    public static final String FREE_LOGISTICS = "2";
    
    /** 键值对象 */
    public static Map<String, String> WCS_LOGISTICS_COM_TYPE_MAP = new LinkedHashMap<String, String>();
    static {
    	/*WCS_LOGISTICS_COM_TYPE_MAP.put(LOGISTICS_COM, "物流公司");
    	WCS_LOGISTICS_COM_TYPE_MAP.put(FREE_LOGISTICS, "自有物流");*/
    	SysDictConstant.initSysDictByCode(WCS_LOGISTICS_COM_TYPE_MAP, DICT_CODE);
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
