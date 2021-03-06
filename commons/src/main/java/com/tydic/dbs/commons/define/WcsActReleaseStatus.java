package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsActReleaseStatus.java
 * @author caipeimin
 * @version 0.1
 * @WcsActReleaseStatus活动渠道上架状态
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-03-20 16:25:15
 *      	Author: caipeimin
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsActReleaseStatus {
	private static final String DICT_CODE="ACT_RELEASE_STATUS";//对应数据库表SYS_DICT的DICT_CODE值
	/** 未上架 */
    public static final String PENDING = "1";
    /** 上架 */
    public static final String ON_SHELF = "2";
    /** 下架 */
    public static final String DOWN_SHELF = "3";
    
    /** 键值对象 */
    public static Map<String, String> WCS_ACT_RELEASE_STATUS_MAP = new LinkedHashMap<String, String>();
    static {
    	/*WCS_ACT_RELEASE_STATUS_MAP.put(PENDING, "未上架");
    	WCS_ACT_RELEASE_STATUS_MAP.put(ON_SHELF, "上架");
    	WCS_ACT_RELEASE_STATUS_MAP.put(DOWN_SHELF, "下架");*/
    	SysDictConstant.initSysDictByCode(WCS_ACT_RELEASE_STATUS_MAP, DICT_CODE);
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
