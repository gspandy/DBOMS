package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/** 
 * @ClassName: WcsReturnCancelType 
 * @Description: TODO(退回、消单的原因/类型) 
 * @author davis
 * @date 2014-12-27 上午10:13:20 
 *  
 */
public class WcsReturnCancelType {

	private static final String DICT_CODE="RETURN_CANCEL_TYPE";//对应数据库表SYS_DICT的DICT_CODE值
	//退单
	/**信息变更*/
    public static final String WCS_INFO_CHANGE = "1";
    /**号码问题*/
    public static final String WCS_NUM_ERROR = "2";
    /**信息错误*/
    public static final String WCS_INFO_ERROR = "3";
    /**缺货*/
    public static final String WCS_NO_GOODS = "4";
    /**客户不想要*/
    public static final String WCS_CUSTOMER_REJECT = "5";
    /**申请退款*/
    public static final String WCS_APP_REFUND = "6";
    /**其他问题*/
    public static final String WCS_OTHER = "7";
    
    //取消订单
    /**用户退单*/
    public static final String WCS_USER_CANCEL = "8";
    /**缺货退单*/
    public static final String WCS_NOSUPPLY_CANCEL = "9";
    /**订单资料缺失退单*/
    public static final String WCS_MISSING_DATA_CANCEL = "10";
    
    /**键值对象 */
    public static Map<String, String> WCS_RETURN_CANCEL_TYPE_MAP = new LinkedHashMap<String, String>();
    static {
    	SysDictConstant.initSysDictByCode(WCS_RETURN_CANCEL_TYPE_MAP, DICT_CODE);
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
