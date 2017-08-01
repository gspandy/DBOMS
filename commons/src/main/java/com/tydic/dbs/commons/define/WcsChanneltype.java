package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

public class WcsChanneltype {
	private static final String DICT_CODE="CHANNEL_TYPE";//对应数据库表SYS_DICT的DICT_CODE值
	
	/**海币购买*/
	public static final String CHANNELTYPE_purch = "purch";
	/**海币转赠*/
	public static final String CHANNELTYPE_prsnt = "prsnt";
	/**crm同步*/
	public static final String CHANNELTYPE_point = "point";
	/**前端商城充值渠道集合*/
	public static Map<String, String> WCS_CHANNEl_MAP = new HashMap<String, String>();
	
	static {
		
		SysDictConstant.initSysDictByCode(WCS_CHANNEl_MAP, DICT_CODE);
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
