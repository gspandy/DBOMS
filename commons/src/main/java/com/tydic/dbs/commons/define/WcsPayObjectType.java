package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsPayObjectType.java
 * @author yancan
 * @version 0.1
 * @WcsPayObjectType支付对象类型
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-03-12 04:25:15
 *      	Author: yancan
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsPayObjectType {
	private static final String DICT_CODE="PAY_OBJECT_TYPE";//对应数据库表SYS_DICT的DICT_CODE值
	/** 支付提供商 */
    public static final String PAY_PROVIDER = "1";
    /** 分期付款 */
    public static final String PAY_INSTALLMENT = "2";
    
    /** 键值对象 */
    public static Map<String, String> WCS_PAY_OBJECT_TYPE_MAP = new LinkedHashMap<String, String>();
    static {
    	/*WCS_PAY_OBJECT_TYPE_MAP.put(PAY_PROVIDER, "支付提供商");
    	WCS_PAY_OBJECT_TYPE_MAP.put(PAY_INSTALLMENT, "分期付款");*/
    	SysDictConstant.initSysDictByCode(WCS_PAY_OBJECT_TYPE_MAP, DICT_CODE);
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
