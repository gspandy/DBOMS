/**
 * com.tydic.dbs.system.menu.dao.SysDataFactorDao.java
 */
package com.tydic.dbs.system.menu.dao;

import java.util.List;
import java.util.Map;

import com.tydic.commons.dao.BaseDao;
import com.tydic.commons.utils.BaseVO;
import com.tydic.commons.utils.Page;
import com.tydic.commons.utils.Utils;
import com.tydic.dbs.system.menu.mapper.SysDataFactor;

/**
 * @file  SysDataFactorDao.java
 * @author liugaolin
 * @version 0.1
 * @SysDataFactor数据操作类
 * Copyright(C), 2013-2014
 *		  Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-02-27 05:42:53
 *      	Author: liugaolin
 *      	Modification: this file was created
 *   	2. ...
 */
public class SysDataFactorDao extends BaseDao {

	/**
	 * 生成一个主键
	 * @return
	 * @throws Exception
	 */
	public String getPKValue() throws Exception{
		return Utils.getUUID();
	}

	public Page queryByParams(String sqlDef, @SuppressWarnings("rawtypes") Map params, int currPage, int pageSize) throws Exception {
		return queryForPage(sqlDef, params, currPage, pageSize);
	}
	
	public Page queryByParams(@SuppressWarnings("rawtypes") Map params, int currPage, int pageSize) throws Exception {
		return queryForPage("SYS_DATA_FACTOR.selectByParams", params, currPage, pageSize);
	}
	
	public Page queryForPage(String sqlDef, @SuppressWarnings("rawtypes") Map params) throws Exception {
		return queryForPage(sqlDef, params, null);
	}
	
	public Page queryForPage(@SuppressWarnings("rawtypes") Map params) throws Exception {
		return queryForPage("SYS_DATA_FACTOR.selectByParams", params, null);
	}
	
    /**
	 * 对数据库进行查询并返回一个VO数组
	 * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SysDataFactor> selectAll() throws Exception {
		return queryForList("SYS_DATA_FACTOR.selectAll", null);
	}
	
	/**
     * 在本方法中组装出一个Map{[数据库列名:值],[数据库列名:值]}对象调用myBatis相关方法
	 * @param valueOne 需要进行查询的值。
	 * @param valueTwo 需要进行查询的值。
	 * @param valueThree 需要进行查询的值。
	 * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SysDataFactor> selectByParamMap(@SuppressWarnings("rawtypes") Map paramMap) throws Exception {
	    return queryForList("SYS_DATA_FACTOR.selectByParams", paramMap);
	}
	
	/**
	 * 搜索数据库中所有与对象对应的记录
	 * @param vo 对象对应到数据库中的一条记录
	 * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SysDataFactor> selectByVo(SysDataFactor vo) throws Exception {
	    if(vo == null) return null;
	    return queryForList("SYS_DATA_FACTOR.selectByVO", vo);
	}
	
    /**
     * 实现翻页
     * @param condition condition{Vo:条件VO对象，currPage:当前页面数，pageSize:每页最大行数}
     * @return type : Page 返回一个分页的List，操作失败返回null
	 * @throws Exception
     */
    public Page selectForPage(@SuppressWarnings("rawtypes") Map condition) throws Exception {
        if(condition == null) return null;
        int pageIndex = ((Integer) condition.get("currPage")).intValue();
        int perPageSize = ((Integer) condition.get("pageSize")).intValue();
        String sqlStr = "";
        BaseVO vo = (BaseVO) condition.get("Vo");
        if(vo == null) {
        	sqlStr = "SYS_DATA_FACTOR.selectAll";
        }else {
        	sqlStr = "SYS_DATA_FACTOR.selectByVO";
        }
        return queryForPage(sqlStr , vo, pageIndex, perPageSize);
    }
    
     /**
     * 实现翻页
     * @param {Vo:条件VO对象，currPage:当前页面数，pageSize:每页最大行数}
     * @return type : Page 返回一个分页的List，操作失败返回null
	 * @throws Exception
     */
    public Page selectForPage(SysDataFactor vo, int pageIndex, int perPageSize) throws Exception {
        String sqlStr = "";
        if(vo == null) {
        	sqlStr = "SYS_DATA_FACTOR.selectAll";
        }else {
        	sqlStr = "SYS_DATA_FACTOR.selectByVO";
        }
        return queryForPage(sqlStr , vo, pageIndex, perPageSize);
    }
    
    /**
     * 搜索数据库中与传入的主键对应的记录
     * @param pkid 与数据库主键对应
     * @return type : 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
	 * @throws Exception
     */
    public SysDataFactor selectByPk(Long pkId) throws Exception {
    	if(pkId ==null) return null;
    	return (SysDataFactor)queryForObject("SYS_DATA_FACTOR.selectByPk", pkId);
    }
    
	/**
	 * 向数据库中插入一个VO对象
	 * @param vo 对象对应到数据库中的一条记录
	 * @return type : 
	 * @throws Exception
	 */
	public SysDataFactor insertByVo(SysDataFactor vo) throws Exception {
	    if(vo == null) return null;
	    return (SysDataFactor)insert("SYS_DATA_FACTOR.insertByVo", vo);
    }
	
    /**
	 * 更新数据库中对象对应的记录
	 * @param vo 对象对应到数据库中的一条记录记录
	 * @return type : 
	 * @throws Exception
	 */
	public SysDataFactor updateByVo(SysDataFactor vo) throws Exception {
		if(vo == null) return null;
		update("SYS_DATA_FACTOR.updateByVO", vo);
		return vo;
	}
	
    /**
	 * 更新数据库中对象对应的记录
	 * @param vo 对象对应到数据库中的一条记录记录
	 * @return type : 
	 * @throws Exception
	 */
	public SysDataFactor updateByVoNotNull(SysDataFactor vo) throws Exception {
		if(vo == null) return null;
		update("SYS_DATA_FACTOR.updateByVoNotNull", vo);
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
    	getSqlSession().update("SYS_DATA_FACTOR.updateStatus", params);
    	return true;
    }
	   
	/**
	 * 删除数据库中所有记录
	 * @return type : boolean 返回删除操作是否成功
	 * @throws Exception
	 */
	public boolean deleteAll() throws Exception {
		return delete("SYS_DATA_FACTOR.deleteAll", null) > 0?true:false;
	}
	

	/**
	 * 删除数据库中与对象对应的记录
	 * @param vo 对象对应到数据库中的一条记录
	 * @return type : boolean 返回删除操作是否成功
	 * @throws Exception
	 */
	public boolean deleteByVo(SysDataFactor vo) throws Exception {
		if(vo == null) return false;
		int i = delete("SYS_DATA_FACTOR.deleteByVO", vo);
		return i==0?false:true;
	}
	
    /**
	 * 删除数据库中与传入的主键对应的一条记录
	 * @param pkid 与数据库主键对应
	 * @return type : boolean 返回删除操作是否成功,如果传入参数为空则返回false,如果操作失败则抛出Exception异常
	 * @throws Exception
	 */
    public boolean deleteByPk(Long pkId) throws Exception {
		if(pkId == null) return false;
		delete("SYS_DATA_FACTOR.deleteByPk", pkId);
		return true;
	}
}
