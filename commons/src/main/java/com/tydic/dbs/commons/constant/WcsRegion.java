/**
 * 
 */
package com.tydic.dbs.commons.constant;

import java.util.HashMap;
import java.util.Map;

/**地市转换
 * <p>Title: </p>
 * <p>Description:   </p>
 * <p>Company: 从兴技术有限公司 </p>
 * @author: yuchanghong
 * @version 1.0
 * @date: 2014-7-15 下午4:15:50
 */

public class WcsRegion {
	public static Map<String, String> REGION_NAME_TO_KEY = new HashMap<String, String>();
	static {
		REGION_NAME_TO_KEY.put("广州", "440100");
		REGION_NAME_TO_KEY.put("韶关", "440200");
		REGION_NAME_TO_KEY.put("深圳", "440300");
		REGION_NAME_TO_KEY.put("珠海", "440400");
		REGION_NAME_TO_KEY.put("汕头", "440500");
		REGION_NAME_TO_KEY.put("佛山", "440600");
		REGION_NAME_TO_KEY.put("江门", "440700");
		REGION_NAME_TO_KEY.put("湛江", "440800");
		REGION_NAME_TO_KEY.put("茂名", "440900");
		REGION_NAME_TO_KEY.put("肇庆", "441200");
		REGION_NAME_TO_KEY.put("惠州", "441300");
		REGION_NAME_TO_KEY.put("梅州", "441400");
		REGION_NAME_TO_KEY.put("汕尾", "441500");
		REGION_NAME_TO_KEY.put("河源", "441600");
		REGION_NAME_TO_KEY.put("阳江", "441700");
		REGION_NAME_TO_KEY.put("清远", "441800");
		REGION_NAME_TO_KEY.put("东莞", "441900");
		REGION_NAME_TO_KEY.put("中山", "442000");
		REGION_NAME_TO_KEY.put("潮州", "445100");
		REGION_NAME_TO_KEY.put("揭阳", "445200");
		REGION_NAME_TO_KEY.put("云浮", "445300");
	};
	
	
	public static String get(String key) {
		return REGION_NAME_TO_KEY.get(key);
	}
}
