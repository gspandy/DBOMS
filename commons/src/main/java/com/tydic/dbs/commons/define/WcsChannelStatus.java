package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 *<p>File: WcsChannelStatus.java</p>
 *<p>Description: 渠道状态定义</p>
 *<p>Company: 从兴技术有限公司</p>
 *<p>Author: 刘高林</p>
 *<p>Version: 1.0,2014-3-24 上午10:42:12,刘高林,Ins</p>
 *
 */
public class WcsChannelStatus {
	private static final String DICT_CODE="CHANNEL_STATUS";//对应数据库表SYS_DICT的DICT_CODE值
	public static final String FROZEN = "0";/**冻结*/
	public static final String NORMAL_USE = "1";/**正常使用*/
	//public static final String NOT_ACTIVE = "2";/**未激活*/
	
	public static Map<String, String> WCS_CHANNEL_STATUS_MAP = new HashMap<String, String>();
	
	static {
		/*channelStatusHash.put(FROZEN, "冻结");
		channelStatusHash.put(NORMAL_USE, "正常使用");
		channelStatusHash.put(NOT_ACTIVE, "未激活");*/
		SysDictConstant.initSysDictByCode(WCS_CHANNEL_STATUS_MAP, DICT_CODE);
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
