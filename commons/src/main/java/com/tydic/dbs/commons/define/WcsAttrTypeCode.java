package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsAttrTypeCode.java
 * @author caipeimin
 * @version 0.1
 * @WcsAttrType属性类型
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-03-16 11:25:15
 *      	Author: caipeimin
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsAttrTypeCode {
	private static final String DICT_CODE="ATTR_TYPE_CODE";//对应数据库表SYS_DICT的DICT_CODE值
	/** 主题 */
    public static final String ATTR_TYPE_THEME = "theme";
    /** 网络*/
    public static final String ATTR_TYPE_NETWORK = "network";
    /** 存储*/
    public static final String ATTR_TYPE_STORAGE = "storage";
    /** 显示*/
    public static final String ATTR_TYPE_DISPLAY = "display";
    /** 娱乐功能*/
    public static final String ATTR_TYPE_ENT = "ent";
    /** 摄像功能*/
    public static final String ATTR_TYPE_CAMERA = "camera";
    /** 传输功能*/
    public static final String ATTR_TYPE_TRANS = "trans";
    /** 电池信息*/
    public static final String ATTR_TYPE_BATTERY = "battery";
    /** 相关配件*/
    public static final String ATTR_TYPE_FITTINGS = "fit";
    
    /** 键值对象 */
    public static Map<String, String> WCS_ATTR_TYPE_CODE_MAP = new LinkedHashMap<String, String>();
    static {
    	SysDictConstant.initSysDictByCode(WCS_ATTR_TYPE_CODE_MAP, DICT_CODE);
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
