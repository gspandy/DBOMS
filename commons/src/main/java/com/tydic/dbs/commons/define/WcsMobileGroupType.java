package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsMobileGroupType.java
 * @author caipeimin
 * @version 0.1
 * @WcsMobileGroupType号码分组类型
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-03-16 11:25:15
 *      	Author: caipeimin
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsMobileGroupType {
	private static final String DICT_CODE="MOBILE_GROUP_TYPE";//对应数据库表SYS_DICT的DICT_CODE值
	/** 预留号码组 */
    public static final String RESERVE_GROUP = "1";
    /** 销售号码组  */
    public static final String SALE_GROUP = "2";
    
    /** 键值对象 */
    public static Map<String, String> WCS_MOBILE_GROUP_TYPE_MAP = new LinkedHashMap<String, String>();
    static {
    	/*WCS_MOBILE_GROUP_TYPE_MAP.put(GENERAL_GROUP, "普通组");
    	WCS_MOBILE_GROUP_TYPE_MAP.put(SHARING_GROUP, "共享组");
    	WCS_MOBILE_GROUP_TYPE_MAP.put(TEMP_GROUP, "暂存组");*/
    	SysDictConstant.initSysDictByCode(WCS_MOBILE_GROUP_TYPE_MAP, DICT_CODE);
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
