/**
 * com.tydic.dc.common.CommonQuery.java
 */
package com.tydic.dc.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tydic.commons.dao.BaseDao;

 /**
 * @file  CommonQuery.java
 * @author weishaojia(viscar)
 * @version 0.1
 * @todo 公共数据查询[此类只用于查询，不能做增删改，因为此类为直接调用]
 * Copyright(C), 2013-2014
 *			Guangzhou Sunrise Electronics Development Co., Ltd.
 * History
 *   	1. Date: 2013-5-14
 *      	Author: weishaojia(viscar)
 *      	Modification: this file was created
 *   	2. ...
 */
public class CommonQuery extends BaseDao{

	/**
	 * 查出第一层维度数据
	 * @return
	 * @throws Exception
	 */
	public List<Object> getDimLevelFirst()throws Exception{
	    return getSqlSession().selectList("COMMON_QUERY.getDimLevelFirst");
	}
	
	/**
	 * 查出维度
	 * @return
	 * @throws Exception
	 */
	public List<Object> getDimDefine()throws Exception{
	    return getSqlSession().selectList("COMMON_QUERY.getDimDefine");
	}
	
	/**
	 * 查出指标
	 * @return
	 * @throws Exception
	 */
	public List<Object> getIndex()throws Exception{
	    return getSqlSession().selectList("COMMON_QUERY.getIndex");
	}
	
	/**
	 * 查出维度
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<Object> getDimByUser(String userId)throws Exception{
		Map<String,String> paramMap=new HashMap<String,String>();
		paramMap.put("userId",userId);
		return getSqlSession().selectList("COMMON_QUERY.getDimByUser", paramMap);
	}
	
	/**
	 * 调用后台过程，返回表名[初始]
	 * @param param
	 * @return
	 */
	public String getDataMetaInit(Map<String,String> param)throws Exception{
		getSqlSession().selectOne("COMMON_QUERY.getDataMetaInit", param);
		String tb=param.get("tbname");
		return tb.substring(tb.indexOf(".")+1,tb.length());
	}
	
	/**
	 * 调用后台过程，返回表名[初始,模拟]
	 * @param param
	 * @return
	 */
	public String getDataMetaInitDemo(Map<String,String> param)throws Exception{
		getSqlSession().selectOne("COMMON_QUERY.getDataMetaInitDemo", param);
		String tb=param.get("tbname");
		return tb.substring(tb.indexOf(".")+1,tb.length());
	}
	
	/**
	 * 调用后台过程，返回表名[钻取]
	 * @param param
	 * @return
	 */
	public String getDataMeta(Map<String,String> param)throws Exception{
		getSqlSession().selectOne("COMMON_QUERY.getDataMeta", param);
		String tb=param.get("tbname");
		return tb.substring(tb.indexOf(".")+1,tb.length());
	}
	
	/**
	 * 通过表名查询cache表的数据
	 * @param tb
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> getDataList(String tb,Map<String,Object> param)throws Exception{
		StringBuffer sql=new StringBuffer(30);
		sql.append("select * from ");
		sql.append(tb);
		return cacheJt.queryForList(sql.toString());
	}	
	
	/**
	 * 查出维度硬编码
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> getDimStoreCode()throws Exception{
	   List<Object> list=getSqlSession().selectList("COMMON_QUERY.getDimStoreCode");
	   Map<String,String> ret=new HashMap<String,String>();
	   if(list!=null&&list.size()>0){
		   for(int i=0;i<list.size();i++){
			   Map m=(Map)list.get(i);
			   ret.put(String.valueOf(m.get("DIM_ID")), String.valueOf(m.get("DIM_PATH")));
		   }
	   }
	   return ret;
	}
	
	/**
	 * 查出最新数据时间
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> getNowMetaDate()throws Exception{
	   List<Object> list=getSqlSession().selectList("COMMON_QUERY.getNowMetaDate");
	   Map<String,String> ret=new HashMap<String,String>();
	   if(list!=null&&list.size()>0){
		   for(int i=0;i<list.size();i++){
			   Map m=(Map)list.get(i);
			   ret.put(String.valueOf(m.get("DATA_FLAG")), String.valueOf(m.get("DATA_NEW_DATE")));
		   }
	   }
	   return ret;
	}
}
