package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/** 
 * @ClassName: WcsOperateType 
 * @Description: TODO(操作类型) 
 * @author davis
 * @date 2014-12-27 上午10:05:16 
 *  
 */
public class WcsOperateType {

	private static final String DICT_CODE="OPERATE_TYPE";//对应数据库表SYS_DICT的DICT_CODE值
	/**取消订单*/
    public static final String WCS_CANCEL_ORDER = "1";
    
    /**键值对象 */
    public static Map<String, String> WCS_OPERATE_TYPE_MAP = new LinkedHashMap<String, String>();
    static {
    	SysDictConstant.initSysDictByCode(WCS_OPERATE_TYPE_MAP, DICT_CODE);
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
