package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsProductPhoneAttr.java
 * @author yancan
 * @version 0.1
 * @WcsProductPhoneAttr货品-手机类属性
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-03-12 04:25:15
 *      	Author: yancan
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsProductPhoneAttr {
	private static final String DICT_CODE="PRODUCT_PHONE_ATTR";//对应数据库表SYS_DICT的DICT_CODE值

	/**品牌 */
    public static final String WCS_ATTR_BRAND = "brand";
	/**型号 */
    public static final String WCS_ATTR_MODEL = "model";
	/**颜色 */
    public static final String WCS_ATTR_COLOR = "color";
    /**容量 */
    public static final String WCS_ATTR_SIZE = "size";
    /**屏幕尺寸 */
    public static final String WCS_SCREEN_SIZE = "screen_size";
    /**前置摄像头像素*/
    public static final String WCS_ATTR_PARAM1 = "param1";
    /**闪光灯*/
    public static final String WCS_ATTR_PARAM2 = "param2";
    /**产品其他属性*/
    public static final String WCS_ATTR_PARAM3 = "param3";
    /** 自动对焦*/
    public static final String WCS_ATTR_PARAM4 = "param4";
    /** 图像尺寸*/
    public static final String WCS_ATTR_PARAM5 = "param5";
    /** 连拍功能*/
    public static final String WCS_ATTR_PARAM6 = "param6";
    /** 定时器*/
    public static final String WCS_ATTR_PARAM7 = "param7";
    /** 视频拍摄*/
    public static final String WCS_ATTR_PARAM8 = "param8";
    /** 可视电话*/
    public static final String WCS_ATTR_PARAM9 = "param9";
    /** GPS*/
    public static final String WCS_ATTR_PARAM10 = "param10";
    /** 蓝牙*/
    public static final String WCS_ATTR_PARAM11 = "param11";
    /** WIFI*/
    public static final String WCS_ATTR_PARAM12 = "param12";
    /** NFC*/
    public static final String WCS_ATTR_PARAM13 = "param13";
    /** 数据接口*/
    public static final String WCS_ATTR_PARAM14 = "param14";
    /** 耳机插口*/
    public static final String WCS_ATTR_PARAM15 = "param15";
    /** 图形格式*/
    public static final String WCS_ATTR_PARAM16 = "param16";
    /** 视频播放*/
    public static final String WCS_ATTR_PARAM17 = "param17";
    /** 电子邮件*/
    public static final String WCS_ATTR_PARAM18 = "param18";
    /** 理论通话时间*/
    public static final String WCS_ATTR_PARAM19 = "param19";
    /** 理论待机时间*/
    public static final String WCS_ATTR_PARAM20 = "param20";
    /** 长*/
    public static final String WCS_ATTR_PARAM21 = "param21";
    /** 宽*/
    public static final String WCS_ATTR_PARAM22 = "param22";
    /** 高*/
    public static final String WCS_ATTR_PARAM23 = "param23";
    /** 重量*/
    public static final String WCS_ATTR_PARAM24 = "param24";
    /** 操作系统*/
    public static final String WCS_ATTR_OS = "OS";
    /** 优惠活动*/
    public static final String WCS_ATTR_CONTRACT_PLAN_TYPE = "contract_plan_type";
    /** 特点*/
    public static final String WCS_ATTR_PHONE_FEATURE = "phone_feature";
    /** 包装清单*/
    public static final String WCS_ATTR_PACKAGE_LIST = "package_list";
    
    
    /** 键值对象 */
    public static Map<String, String> WCS_PRODUCT_PHONE_ATTR_MAP = new LinkedHashMap<String, String>();
   
    static {
    	SysDictConstant.initSysDictByCode(WCS_PRODUCT_PHONE_ATTR_MAP, DICT_CODE);
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
