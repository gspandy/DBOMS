/**
 * com.sunrise.ord.dao.OrdLogDao.java
 */
package com.tydic.dbs.order.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;


import com.tydic.commons.dao.BaseDao;
import com.tydic.commons.utils.Page;
import com.tydic.dbs.order.vo.OrdLog;

/**
 * @file  OrdLogDao.java
 * @author Carson
 * @version 0.1
 * @OrdLog数据操作类
 * Copyright(C), 2013-2014
 *		 Guangzhou tydic Technology Co., Ltd.
 * History
 *   	1. Date: 2016-07-08 01:11:36
 *      	Author: Carson
 *      	Modification: this file was created
 *   	2. ...
 */
public class OrdLogDao extends BaseDao {	
    /**
     * 分页查询
     * @param sqlDef 预定义SQL名称, params params{属性名:属性值, currPage:当前页面数, pageSize:每页最大行数, orderBy:List<Map<属性名:升序或者降序>>, nopaging:boolean是否分页}
     * @return type : Page 返回一个分页的List，操作失败返回null
	 * @throws Exception
     */
	@SuppressWarnings("rawtypes")
	public Page queryForPage(String sqlDef, Map params) throws Exception {
		return queryForPage(sqlDef, params, null);
	}
	
    /**
     * 分页查询
     * @param params params{属性名:属性值, currPage:当前页面数, pageSize:每页最大行数, orderBy:List<Map<属性名:升序或者降序>>, nopaging:boolean是否分页}
     * @return type : Page 返回一个分页的List，操作失败返回null
	 * @throws Exception
     */
	@SuppressWarnings("rawtypes")
	public Page queryForPage(Map params) throws Exception {
		return queryForPage("ORD_LOG.selectByParams", params, null);
	}
	
    /**
	 * 对数据库进行查询并返回一个VO列表
	 * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes" })
	public List selectAll() throws Exception {
		return queryForList("ORD_LOG.selectAll", null);
	}
	
	/**
     * 在本方法中组装出一个Map{[数据库列名:值],[数据库列名:值]}对象调用myBatis相关方法
	 * @param valueOne 需要进行查询的值。
	 * @param valueTwo 需要进行查询的值。
	 * @param valueThree 需要进行查询的值。
	 * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List selectByParamMap(Map paramMap) throws Exception {
	    return queryForList("ORD_LOG.selectByParams", paramMap);
	}
	
	/**
	 * 搜索数据库中所有与对象对应的记录
	 * @param vo 对象对应到数据库中的一条记录
	 * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List selectByVo(OrdLog vo) throws Exception {
	    if(vo == null) return null;
	    return queryForList("ORD_LOG.selectByVO", vo);
	}
    
    /**
     * 搜索数据库中与传入的主键对应的记录
     * @param pkid 与数据库主键对应
     * @return type : 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
	 * @throws Exception
     */
	public OrdLog selectByPk(String pkId) throws Exception {
    	if(StringUtils.isEmpty(pkId)) return null;
    	return (OrdLog)queryForObject("ORD_LOG.selectByPk", pkId);
    }
    
	/**
	 * 向数据库中插入一个VO对象
	 * @param vo 对象对应到数据库中的一条记录
	 * @return type : 
	 * @throws Exception
	 */
	public OrdLog insertByVo(OrdLog vo) throws Exception {
	    if(vo == null) return null;
	    return (OrdLog)insert("ORD_LOG.insertByVo", vo);
    }
	
    /**
	 * 更新数据库中对象对应的记录
	 * @param vo 对象对应到数据库中的一条记录记录
	 * @return type : 
	 * @throws Exception
	 */
	public OrdLog updateByVo(OrdLog vo) throws Exception {
		if(vo == null) return null;
		update("ORD_LOG.updateByVO", vo);
		return vo;
	}
	
    /**
	 * 更新数据库中对象对应的记录
	 * @param vo 对象对应到数据库中的一条记录记录
	 * @return type : 
	 * @throws Exception
	 */
	public OrdLog updateByVoNotNull(OrdLog vo) throws Exception {
		if(vo == null) return null;
		update("ORD_LOG.updateByVoNotNull", vo);
		return vo;
	}
	
    /**
	 * 更新数据库中对象的状态
	 * @param map: <ids: Id列表>, <status: 状态>, <modifier: 修改者>
	 * @return type : boolean 返回操作是否成功
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
    public boolean updateStatus(Map params) throws Exception {
    	if(params == null) return false;
    	getSqlSession().update("ORD_LOG.updateStatus", params);
    	return true;
    }
	   
	/**
	 * 删除数据库中所有记录
	 * @return type : boolean 返回删除操作是否成功
	 * @throws Exception
	 */
	public boolean deleteAll() throws Exception {
		return delete("ORD_LOG.deleteAll", null) > 0?true:false;
	}
	

	/**
	 * 删除数据库中与对象对应的记录
	 * @param vo 对象对应到数据库中的一条记录
	 * @return type : boolean 返回删除操作是否成功
	 * @throws Exception
	 */
	public boolean deleteByVo(OrdLog vo) throws Exception {
		if(vo == null) return false;
		int i = delete("ORD_LOG.deleteByVO", vo);
		return i==0?false:true;
	}
	
    /**
	 * 删除数据库中与传入的主键对应的一条记录
	 * @param pkid 与数据库主键对应
	 * @return type : boolean 返回删除操作是否成功,如果传入参数为空则返回false,如果操作失败则抛出Exception异常
	 * @throws Exception
	 */
    public boolean deleteByPk(String pkId) throws Exception {
		if(StringUtils.isEmpty(pkId)) return false;
		delete("ORD_LOG.deleteByPk", pkId);
		return true;
	}
}
