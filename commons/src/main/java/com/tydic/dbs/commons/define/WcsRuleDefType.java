package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsRuleDefType.java
 * @author zhanjianming
 * @version 0.1
 * @WcsRuleDefType规则因子类型
 * Copyright(C), 2014-2015
 *		 Guangzhou Tydic Technology Co., Ltd.
 * History
 *   	1. Date: 2015-02-02 20:20:22
 *      	Author: zhanjianming
 *      	Modification: this file was created
 *   	2. ...
 */

public class WcsRuleDefType {
	
	private static final String DICT_CODE="RULE_DEF_TYPE";//对应数据库表SYS_DICT的DICT_CODE值
	/** 地区 */
    public static final String WCS_DISTRICT = "district";
    /** 配送方式 */
    public static final String WCS_DELIVERY = "delivery";
    /** 商品类型 */
    public static final String WCS_GOODSTYPE = "goodsType";
    /** 是否有实物商品 */
    public static final String WCS_ISREAL = "isReal";
    /** 键值对象 */
    public static Map<String, String> WCS_RULE_DEF_TYPE_MAP = new LinkedHashMap<String, String>();
    static {
    	SysDictConstant.initSysDictByCode(WCS_RULE_DEF_TYPE_MAP, DICT_CODE);
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
