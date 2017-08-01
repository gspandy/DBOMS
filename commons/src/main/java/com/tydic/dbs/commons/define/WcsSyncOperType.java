package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsSyncOperType.java
 * @author yancan
 * @version 0.1
 * @WcsSyncOperType同步操作类型
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-04-22 04:25:15
 *      	Author: yancan
 *      	Modification: this file was created
 *   	2. ...
 */
public class WcsSyncOperType {
	private static final String DICT_CODE="SYNC_OPER_TYPE";//对应数据库表SYS_DICT的DICT_CODE值
	
	/**同步接口名称: 活动信息同步 ; 同步操作: 添加 ; 同步方向:外->内         **/
	public static final String 	AES_A_IN="AES_A_IN";
	/**同步接口名称: 活动信息同步 ; 同步操作: 修改 ; 同步方向:外->内         **/
	public static final String 	AES_M_IN="AES_M_IN";
	/**同步接口名称: 活动信息同步 ; 同步操作: 删除 ; 同步方向:外->内         **/
	public static final String 	AES_D_IN="AES_D_IN";

	
	/**同步接口名称: 品牌信息同步 ; 同步操作: 添加 ; 同步方向:外->内         **/
	public static final String 	BB_A_IN="BB_A_IN";
	/**同步接口名称: 品牌信息同步 ; 同步操作: 修改 ; 同步方向:外->内         **/
	public static final String 	BB_M_IN="BB_M_IN";
	/**同步接口名称: 品牌信息同步 ; 同步操作: 删除 ; 同步方向:外->内         **/
	public static final String 	BB_D_IN="BB_D_IN";
	/**同步接口名称: 品牌信息同步 ; 同步方向:外->内         **/
	public static final String 	BB_IN="BB_IN";
	
	
	/**同步接口名称: 品类信息同步 ; 同步操作: 添加 ; 同步方向:外->内         **/
	public static final String 	BC_A_IN="BC_A_IN";
	/**同步接口名称: 品类信息同步 ; 同步操作: 修改 ; 同步方向:外->内         **/
	public static final String 	BC_M_IN="BC_M_IN";
	/**同步接口名称: 品类信息同步 ; 同步操作: 删除 ; 同步方向:外->内         **/
	public static final String 	BC_D_IN="BC_D_IN";
	/**同步接口名称: 品类信息同步 ; 同步方向:外->内         **/
	public static final String 	BC_IN="BC_IN";
	
	
	/**同步接口名称: 品型信息同步 ; 同步操作: 添加 ; 同步方向:外->内         **/
	public static final String 	BM_A_IN="BM_A_IN";
	/**同步接口名称: 品型信息同步 ; 同步操作: 修改 ; 同步方向:外->内         **/
	public static final String 	BM_M_IN="BM_M_IN";
	/**同步接口名称: 品型信息同步 ; 同步操作: 删除 ; 同步方向:外->内         **/
	public static final String 	BM_D_IN="BM_D_IN";
	/**同步接口名称: 品型信息同步 ; 同步方向:外->内         **/
	public static final String 	BM_IN="BM_IN";
	
	
	/**同步接口名称: 货品库存信息同步 ; 同步操作: 出库 ; 同步方向:外->内         **/
	public static final String 	GI_RD_IN="GI_RD_IN";
	/**同步接口名称: 货品库存信息同步 ; 同步操作: 入库 ; 同步方向:外->内         **/
	public static final String 	GI_A_IN="GI_A_IN";
	/**同步接口名称: 货品库存信息同步 ; 同步操作: 回库 ; 同步方向:外->内         **/
	public static final String 	GI_RS_IN="GI_RS_IN";
	
	
	/**同步接口名称: 物理库存信息同步 ; 同步操作: 添加 ; 同步方向:外->内         **/
	public static final String 	PI_A_IN="PI_A_IN";
	/**同步接口名称: 物理库存信息同步 ; 同步操作: 修改 ; 同步方向:外->内         **/
	public static final String 	PI_M_IN="PI_M_IN";
	/**同步接口名称: 物理库存信息同步 ; 同步操作: 删除 ; 同步方向:外->内         **/
	public static final String 	PI_D_IN="PI_D_IN";

	
	/**同步接口名称: 商品信息同步 ; 同步操作: 添加 ; 同步方向:外->内         **/
	public static final String 	PG_A_IN="PG_A_IN";
	/**同步接口名称: 商品信息同步 ; 同步操作: 修改 ; 同步方向:外->内         **/
	public static final String 	PG_M_IN="PG_M_IN";
	/**同步接口名称: 商品信息同步 ; 同步操作: 删除 ; 同步方向:外->内         **/
	public static final String 	PG_D_IN="PG_D_IN";

	
	/**同步接口名称: 货品信息同步 ; 同步操作: 添加 ; 同步方向:外->内         **/
	public static final String 	PP_A_IN="PP_A_IN";
	/**同步接口名称: 货品信息同步 ; 同步操作: 修改 ; 同步方向:外->内         **/
	public static final String 	PP_M_IN="PP_M_IN";
	/**同步接口名称: 货品信息同步 ; 同步操作: 删除 ; 同步方向:外->内         **/
	public static final String 	PP_D_IN="PP_D_IN";
	/**同步接口名称: 货品信息同步 ; 同步方向:外->内         **/
	public static final String 	PP_IN="PP_IN";

	
	/**同步接口名称: 商品库存信息同步 ; 同步操作: 出库 ; 同步方向:外->内         **/
	public static final String 	POI_RD_IN="POI_RD_IN";
	/**同步接口名称: 商品库存信息同步 ; 同步操作: 入库 ; 同步方向:外->内         **/
	public static final String 	POI_A_IN="POI_A_IN";
	/**同步接口名称: 商品库存信息同步 ; 同步操作: 回库 ; 同步方向:外->内         **/
	public static final String 	POI_RS_IN="POI_RS_IN";
	
	
	/**同步接口名称: 商城信息同步 ; 同步操作: 添加 ; 同步方向:外->内         **/
	public static final String 	SM_A_IN="SM_A_IN";
	/**同步接口名称: 商城信息同步 ; 同步操作: 修改 ; 同步方向:外->内         **/
	public static final String 	SM_M_IN="SM_M_IN";
	/**同步接口名称: 商城信息同步 ; 同步操作: 删除 ; 同步方向:外->内         **/
	public static final String 	SM_D_IN="SM_D_IN";
	
	
	/**同步接口名称: 号码信息同步 ; 同步操作: 添加 ; 同步方向:外->内         **/
	public static final String 	NMN_A_IN="NMN_A_IN";

	
	/**同步接口名称: 订单信息同步 ; 同步操作: 添加 ; 同步方向:内->外         **/
	public static final String 	OO_A_OUT="OO_A_OUT";
	
	
	/**同步接口名称: 订单状态同步同步 ; 同步操作: 修改 ; 同步方向:外->内         **/
	public static final String 	OOS_M_IN="OOS_M_IN";
	/**同步接口名称: 订单状态同步同步 ; 同步操作: 修改 ; 同步方向:内->外         **/
	public static final String 	OOS_M_OUT="OOS_M_OUT";
	
	/**同步接口名称: 短信发送接口 ; 同步操作: 添加 ; 同步方向:外->内	**/
	public static final String SMS_A_IN="SMS_A_IN";
	
    /** 键值对象 */
    public static Map<String, String> WCS_SYNC_OPER_TYPE_MAP = new LinkedHashMap<String, String>();
    static {
    	SysDictConstant.initSysDictByCode(WCS_SYNC_OPER_TYPE_MAP, DICT_CODE);
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
