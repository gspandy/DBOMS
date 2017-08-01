package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsProductPackageAttr.java
 * @author yancan
 * @version 0.1
 * @WcsProductPackageAttr货品-套餐类属性
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-03-12 04:25:15
 *      	Author: yancan
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsProductPackageAttr {
	private static final String DICT_CODE="PRODUCT_PACKAGE_ATTR";//对应数据库表SYS_DICT的DICT_CODE值
	
	/**BSS套餐编码 */
    public static final String WCS_ATTR_BSS_CODE = "bss_code";
    /**ESS套餐编码 */
    public static final String WCS_ATTR_ESS_CODE = "ess_code";
    /**类别*/
    public static final String WCS_ATTR_PLAN_TYPE = "plan_type";
    /**月租*/
    public static final String WCS_ATTR_MONTH_FEE = "month_fee";
    /**国内短信发送条数*/
    public static final String WCS_ATTR_SMS_SEND_NUM = "sms_send_num";
    /**接听免费*/
    public static final String WCS_ATTR_ANSWERING_FREE = "answering_free";
    /**国内语音拨打分钟数*/
    public static final String WCS_ATTR_CALL_TIMES = "call_times";
    /**国内流量*/
    public static final String WCS_ATTR_FlOW = "flow";
    /**其它业务*/
    public static final String WCS_ATTR_OTHER_BUSINESS = "other_business";
    /**套餐类型（手机、上网卡）*/
    public static final String WCS_ATTR_OFFER_TYPE = "offer_type";
    /**套餐类型*/
    public static final String WCS_ATTR_PACKAGE_TYPE = "package_type";
    /**月通话时长*/
    public static final String WCS_ATTR_MONTH_CALL_TIMES = "month_call_times";
    /**月上网流量*/
    public static final String WCS_ATTR_MONTH_FLOW = "month_flow";
    /**套餐外通话费用（元/分钟）*/
    public static final String WCS_ATTR_OUT_CALL_TIMES = "out_call_times";
    /**套餐外流量费用（元/分钟）*/
    public static final String WCS_ATTR_OUT_FLOW= "out_flow";
    /**套餐外可视电话（元/分钟）*/
    public static final String WCS_ATTR_OUT_VISUAL_PHONE = "out_visual_phone";
    /**网别*/
    public static final String WCS_ATTR_NET_TYPE = "net_type";
    /**套餐名称*/
    public static final String WCS_ATTR_PACKAGE_NAME="package_name";
    /**套餐价格*/
    public static final String WCS_ATTR_PACKAGE_PRICE="price";
    /**资费说明*/
    public static final String WCS_ATTR_PACKAGE_TARIFF="package_tariff";
    
    /** 键值对象 */
    public static Map<String, String> WCS_PRODUCT_PACKAGE_ATTR_MAP = new LinkedHashMap<String, String>();
    static {
    	SysDictConstant.initSysDictByCode(WCS_PRODUCT_PACKAGE_ATTR_MAP, DICT_CODE);
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
