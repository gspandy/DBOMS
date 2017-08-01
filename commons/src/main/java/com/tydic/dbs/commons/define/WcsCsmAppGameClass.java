package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsCsmAppGameClass.java
 * @author yuchanghong
 * @version 0.1
 * @WcsCsmAppGameClass聚合app游戏小类
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-03-12 04:25:15
 *      	Author: yuchanghong
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsCsmAppGameClass {
	private static final String DICT_CODE="CSM_APP_GAME_CLASS";//对应数据库表SYS_DICT的DICT_CODE值
	/**角色冒险*/
	public static final String ROLE_ADVENTURE = "01";
	/**休闲益智*/
	public static final String CASUAL_PUZZLE = "02";
	/**射击游戏*/
	public static final String SHOOTING_GAME = "03";
	/**模拟策略*/
	public static final String SIMULATION_STRATEGY = "04";
	/**动作格斗*/
	public static final String FIGHTING_ACTION = "05";
	/**体育赛车*/
	public static final String SPORTS_CAR_RACING = "06";
	
	public static Map<String, String> CSM_APP_GAME_CLASS = new HashMap<String, String>();
	
	static {
		SysDictConstant.initSysDictByCode(CSM_APP_GAME_CLASS, DICT_CODE);
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
