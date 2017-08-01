package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsChannelMark.java
 * @author yancan
 * @version 0.1
 * @WcsChannelMark渠道标记
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-07-31 04:25:15
 *      	Author: yancan
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsChannelMark {
	private static final String DICT_CODE="CHANNEL_MARK";//对应数据库表SYS_DICT的DICT_CODE值
	
	/** 传统营业厅 */
    public static final String WCS_MARK_CTYYT = "1";
    /** 电话营销 */
    public static final String WCS_MARK_DHTX = "2";
    /** 非集团合作直销*/
    public static final String WCS_MARK_FJTHZZX = "3";
    /** 非集团自有直销*/
    public static final String WCS_MARK_FJTZYZX = "4";
    /** 集客*/
    public static final String WCS_MARK_JK = "5";
    /** 家客直销*/
    public static final String WCS_MARK_JKZX = "6";
    /** 宽固代理*/
    public static final String WCS_MARK_KGDL = "7";
    /** 社会渠道*/
    public static final String WCS_MARK_SHQD = "8";
    /** 社会微厅*/
    public static final String WCS_MARK_SHWT = "9";
    /** 网盟*/
    public static final String WCS_MARK_WM = "10";
    /**异业联盟*/
    public static final String WCS_MARK_YYWM = "11";
    /** 员工渠道*/
    public static final String WCS_MARK_YGQD = "12";
    /** 自营渠道*/
    public static final String WCS_MARK_ZYQD = "13";
    /**自营微厅*/
    public static final String WCS_MARK_ZYWT = "14";
    /** 其他*/
    public static final String WCS_MARK_QT = "15";
   
    /** 键值对象 */
    public static Map<String, String> WCS_CHANNEL_MARK_MAP = new LinkedHashMap<String, String>();
    static {
    	SysDictConstant.initSysDictByCode(WCS_CHANNEL_MARK_MAP, DICT_CODE);
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
