/**
 * com.tydic.dc.utils.ContentUtils.java
 */
package com.tydic.dc.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;

 /**
 * @file  ContentUtils.java
 * @author weishaojia(viscar)
 * @version 0.1
 * @todo WEB容器共享常量或静态变量
 * Copyright(C), 2013-2014
 *			Guangzhou Sunrise Electronics Development Co., Ltd.
 * History
 *   	1. Date: 2013-4-18
 *      	Author: weishaojia(viscar)
 *      	Modification: this file was created
 *   	2. ...
 */
public class ContentUtils {
	//Web绝对路径
	public static String APP_REAL_PATH="/";
	//Web相对路径
	public static String APP_CTX_PATH="/";
	
	public static final String USER_SESSION_VALUE="USER_SESSION_VALUE";
	
	public static ApplicationContext ac=null;
	
	//报表SESSION键值
	public static String APP_SESSION_REPORT="APP_SESSION_REPORT";
	public static String APP_SESSION_REPORT_CELLS_DATA="APP_SESSION_REPORT_CELLS_DATA";
	
	//图表SESSION键值
	public static String APP_SESSION_REPORT_CHART_DATA="APP_SESSION_REPORT_CHART_DATA";
	
	//钻取行列维度信息值
	public static String APP_SESSION_REPORT_RC_DIM_DATA="APP_SESSION_REPORT_RC_DIM_DATA";
	public static String APP_SESSION_REPORT_RC_DIM_DATA_EXPDATA="APP_SESSION_REPORT_RC_DIM_DATA_EXPDATA";
	
	/**
	 * 报表的Grid数据
	 */
	public static String APP_SESSION_REPORT_GRID_DATA="APP_SESSION_REPORT_GRID_DATA";
	
	/**
	 * 报表描述grid数据
	 */
	public static String APP_SESSION_REPORT_GRID_README="APP_SESSION_REPORT_GRID_README";
	
	/**
	 * 省公司机构编码
	 */
	public static String OPT_AREA_TOP="0010";
	
	/**
	 * 维度值
	 */
	public static String APP_DATA_DIM="APP_DATA_DIM";
	/**
	 * 维度
	 */
	public static String APP_DATA_DIM_DEF="APP_DATA_DIM_DEF";
	/**
	 * 指标
	 */
	public static String APP_DATA_INDEX="APP_DATA_INDEX";
	/**
	 * 图形数据
	 * */
	public static String APP_DATA_CHART="APP_DATA_CHART";
	
	/**
	 * 月数据字典
	 */
	public static String APP_DATA_MONTH="APP_DATA_MONTH";
	
	/**
	 * 日数据字典
	 */
	public static String APP_DATA_DAY="APP_DATA_DAY";
	
	/**
	 * 单位
	 */
	public static String APP_DATA_UNIT="APP_DATA_UNIT";
	
	/**
	 * 指标Map
	 */
	public static String APP_DATA_INDEX_MAP="APP_DATA_INDEX_MAP";	

	/**
	 * 单位
	 */
	public static String APP_DIM_STORE_CODE="APP_DIM_STORE_CODE";
	
	
	/**
	 * 全局数据变量
	 */
	@SuppressWarnings("rawtypes")
	public static Map APP_DATA_VALUE=new HashMap();
	
	/**
	 * 全局SESSION
	 */
	public static Map APP_SESSIONS=new HashMap();
	
	/**
	 * 最新元数据
	 */
	public static Map APP_META_BUILD_DATE=new HashMap();

	/**
	 * 元数据类型：
	 * RPT_DAY--报表日表
	 * RPT_MONTH--报表月表
	 */
	public static String APP_META_BUILD_DATE_REPORT_DAY="RPT_DAY";
	public static String APP_META_BUILD_DATE_REPORT_MONTH="RPT_MONTH";
	
	/**
	 * 报表SQL信息表
	 */
	public static String APP_RPT_SQL_DATA="APP_RPT_SQL_DATA";
}
