package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  wcsCsmAppAppClass.java
 * @author yuchanghong
 * @version 0.1
 * @wcsCsmAppAppClass聚合app应用小类
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-03-12 04:25:15
 *      	Author: yuchanghong
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsCsmAppAppClass {
	private static final String DICT_CODE="CSM_APP_APP_CLASS";//对应数据库表SYS_DICT的DICT_CODE值
	/**金融理财*/
	public static final String FINANCIAL_MANAGEMENT = "01";
	/**系统工具*/
	public static final String SYSTEM_TOOLS = "02";
	/**网络通信*/
	public static final String NETWORK_COMMUNICATIONS = "03";
	/**社交网络*/
	public static final String SOCIAL_NETWORK = "04";
	/**生活实用*/
	public static final String LIFE_PRACTICAL = "05";
	/**主题壁纸*/
	public static final String THEME_WALLPAPER = "06";
	/**学习办公*/
	public static final String STUDY_OFFICE = "07";
	/**娱乐影音*/
	public static final String AUDIO_ENT = "08";
	/**图书阅读*/
	public static final String  BOOK_READING = "09";
	
	/**手厅app*/
	public static final String  SEATEL_APP = "10";
	
	public static Map<String, String> CSM_APP_APP_CLASS = new HashMap<String, String>();
	
	static {
		SysDictConstant.initSysDictByCode(CSM_APP_APP_CLASS, DICT_CODE);
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
