package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file WcsGoodsClass.java
 * @author yancan
 * @version 0.1
 * @WcsGoodsClass商品分类 Copyright(C), 2013-2014 Guangzhou Sunrise Technology Co.,
 *                    Ltd. History 1. Date: 2014-03-12 04:25:15 Author: yancan
 *                    Modification: this file was created 2. ...
 */
public class WcsGoodsClass {
	private static final String DICT_CODE="GOODS_CLASS";//对应数据库表SYS_DICT的DICT_CODE值
	/** 号卡（套餐包） */
	public static final String WCS_SIM_CARD = "20000";
	/** 自由组合套餐*/
	public static final String WCS_FREE_PACKAGE= "69010206";
	/** 上网卡（大类） */
	public static final String WCS_NETWORK = "20001";
	/** 上网卡（不含终端） */
	public static final String WCS_NETWORK_UNTERMINAL = "69021000";
	/** 上网卡（含终端） */
	public static final String WCS_NETWORK_TERMINAL = "69022000";
	/** 合约机 */
	public static final String WCS_CONTRACT_TERMINAL ="20002";
	/** 合约机:定制机全国存费送机 */
	public static final String WCS_CONTRACT_TERMINAL_69030101 ="69030101";
	/** 合约机:定制机全国购机送费*/
	public static final String WCS_CONTRACT_TERMINAL_69030102 ="69030102";
	/** 裸机 */
	public static final String WCS_TERMINAL = "20003";
	/** 成品卡（暂不考虑） */
	public static final String WCS_FINISHED_CARD = "20004";
	/** 配件 */
	public static final String WCS_PARTS = "69070000";
	/**汽车**/
	public static final String GOODS_CLASS_CAR = "20008";
	/**旅游**/
	public static final String GOODS_CLASS_TRAVEL = "20007";
	/**房产**/
	public static final String GOODS_CLASS_HOUSE = "20010";
	/**单个商品*/
	public static final String GOODS_CLASS_INDIVIDUAL = "69080000";

	public static Map<String, String> WCS_GOODS_CLASS_MAP = new HashMap<String, String>();
	static {
		/*WCS_GOODS_CLASS_MAP.put(WCS_PACKAGE, "基本套餐类");
		WCS_GOODS_CLASS_MAP.put(WCS_SIM_CARD, "号卡类");
		WCS_GOODS_CLASS_MAP.put(WCS_CONTRACT_CARD, "号卡合约类");
		WCS_GOODS_CLASS_MAP.put(WCS_TERMINAL, "裸终端类");
		WCS_GOODS_CLASS_MAP.put(WCS_CONTRACT_TERMINAL, "终端合约类");
		WCS_GOODS_CLASS_MAP.put(WCS_NETWORK, "上网卡类");
		WCS_GOODS_CLASS_MAP.put(WCS_PARTS, "配件类");
		WCS_GOODS_CLASS_MAP.put(WCS_PRESENT, "礼品");*/
		SysDictConstant.initSysDictByCode(WCS_GOODS_CLASS_MAP, DICT_CODE);
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
