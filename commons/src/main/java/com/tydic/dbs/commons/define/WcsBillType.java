package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

public class WcsBillType {
private static final String DICT_CODE="BILL_TYPE";//对应数据库表SYS_DICT的DICT_CODE值
	
	/**其他*/
	public static final String BILLTYPE_other = "other";
	/**国际长途*/
	public static final String BILLTYPE_voiceInternational = "voice_internat";
	/**流量*/
	public static final String BILLTYPE_data = "data";
	/**短信*/
	public static final String BILLTYPE_sms = "sms";
	/**网内通话*/
	public static final String BILLTYPE_voiceInnet = "voice_innet";
	/**网外通话*/
	public static final String BILLTYPE_voiceOutnet = "voice_outnet";
	/**月租*/
	public static final String BILLTYPE_rent = "rent";
	/**购买数据包*/
	public static final String BILLTYPE_buyData = "buy_data";
	/**购买短信包*/
	public static final String BILLTYPE_buySms = "buy_sms";
	/**购买语音包*/
	public static final String BILLTYPE_buyVoice = "buy_voice";
	/**国际漫游通话*/
	public static final String BILLTYPE_roamingVoice = "roaming_voice";
	/**国际漫游短信*/
	public static final String BILLTYPE_roamingSms = "roaming_sms";
	/**国际漫游数据*/
	public static final String BILLTYPE_roamingData = "roaming_data";
	/**前端商城已付款支出科目集合*/
	public static Map<String, String> WCS_BILLTYPE_MAP = new HashMap<String, String>();
	
	static {
		
		SysDictConstant.initSysDictByCode(WCS_BILLTYPE_MAP, DICT_CODE);
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
