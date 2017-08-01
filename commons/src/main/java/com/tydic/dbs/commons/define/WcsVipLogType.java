package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsVipLogType.java
 * @author yancan
 * @version 0.1
 * @WcsVipLogType老用户日志类型
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-06-30 04:25:15
 *      	Author: yancan
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsVipLogType {
	private static final String DICT_CODE="VIP_LOG_TYPE";//对应数据库表SYS_DICT的DICT_CODE值
	
	/** 验证码日志 */
    public static final String WCS_LOG_VALIDATECODE = "0";
	/** 登录日志 */
    public static final String WCS_LOG_LOGIN = "1";
    /** 页面访问日志 */
    public static final String WCS_LOG_PAGE = "2";
    
    /** 键值对象 */
    public static Map<String, String> WCS_VIP_LOG_TYPE_MAP = new LinkedHashMap<String, String>();
    static {
    	SysDictConstant.initSysDictByCode(WCS_VIP_LOG_TYPE_MAP, DICT_CODE);
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
