/**
 * 
 */
package com.tydic.dbs.commons.sysDict;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.mapper.SysDict;
import com.tydic.dbs.commons.sysDict.service.SysDictService;

/**
 * <p>Title: </p>
 * <p>Description:   </p>
 * <p>Company: 从兴技术有限公司 </p>
 * @author: yuchanghong
 * @version 1.0
 * @date: 2014-3-20 下午7:17:07
 */

public class SysDictConstant  {

	public static Map<String, Map<String, String>> SYS_DICT_MAP = null ;
	
	private static SysDictService sysDictService = new SysDictService();
	
	static{
		init();
	}
	
	public static void init() {
		SYS_DICT_MAP = new HashMap<String, Map<String, String>>();
		
		synchronized(SYS_DICT_MAP){
			
			try{
			
				List<SysDict> sysDicts = sysDictService.getSysDicts();
				String dictCode = null;
				Map<String, String> dictMap = null;
				
				for(SysDict sysDict : sysDicts){
//					System.out.println("code:" + sysDict.getDictCode() + " key:" + sysDict.getDictKey() + " value:" + sysDict.getDictValue());
					if(dictCode == null || !dictCode.equals(sysDict.getDictCode())){
						dictCode = sysDict.getDictCode();
						dictMap = new LinkedHashMap<String, String>();
						dictMap.put(sysDict.getDictKey(), sysDict.getDictValue());
						SYS_DICT_MAP.put(dictCode, dictMap);
					}else {
						dictMap.put(sysDict.getDictKey(), sysDict.getDictValue());
					}
				}
			}catch(Exception e){
				System.out.println("Initialize the data dictionary Error");
				System.exit(0);
			}
		}
		
	}
	
	/**
	 * 
	 * @Title: initSysDictByCode 
	 * @Description: 获取数据字典
	 * @param @param dictMap
	 * @param @param dictCode    设定文件 
	 * @return void    返回类型 
	 * @throws
	 */
	public static void initSysDictByCode(Map<String, String> dictMap, String dictCode){
		//Map<String, String> tmpDictMap = SYS_DICT_MAP.get(WcsDefinition.WcsLangType.WCS_EN + "_" + dictCode);
		Map<String, String> tmpDictMap = SYS_DICT_MAP.get(dictCode);
		if(tmpDictMap != null){
			dictMap.putAll(SYS_DICT_MAP.get(dictCode));
		}
	}
	
	/**
	 * 
	 * @Title: initSysDictByCode 
	 * @Description: 根据语种获取数据字典
	 * @param @param dictMap
	 * @param @param dictCode
	 * @param @param lang    语种
	 * @return void    返回类型 
	 * @throws
	 */
	public static void initSysDictByCode(Map<String, String> dictMap, String dictCode, String lang){
		Map<String, String> tmpDictMap = SYS_DICT_MAP.get(lang + "_" + dictCode);
		if(tmpDictMap != null){
			dictMap.putAll(SYS_DICT_MAP.get(lang + "_" + dictCode));
		}
	}
}
