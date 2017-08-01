/**
 * com.tydic.dbs.basicConfig.basPhotoLib.dao.BasPhotoLibDao.java
 */
package com.tydic.dbs.system.basPhotoLib.dao;

import com.tydic.commons.dao.BaseDao;

import com.tydic.dbs.system.basPhotoLib.mapper.BasPhotoLib;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @file  BasPhotoLibDao.java
 * @author caipeimin
 * @version 0.1
 * @todo	BasPhotoLib数据操作类
 * Copyright(C), 2013-2014
 *		  Guangzhou Sunrise Electronics Development Co., Ltd.
 * History
 *   	1. Date: 2014-02-22 05:56:27
 *      	Author: caipeimin
 *      	Modification: this file was created
 *   	2. ...
 */
public class BasPhotoLibDao extends BaseDao {

	
	/**
     * 在本方法中组装出一个Map{[数据库列名:值],[数据库列名:值]}对象调用myBatis相关方法

	 * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes" })
	public List selectByParamMap(Map paramMap) throws Exception {
	    return getSqlSession().selectList("BAS_PHOTO_LIB.selectByParams", paramMap);
	}
	
	/**
	 * 搜索数据库中所有与对象对应的记录
	 * @param vo 对象对应到数据库中的一条记录
	 * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List selectByVo(BasPhotoLib vo) throws Exception {
	    if(vo == null) return null;
	    return queryForList("BAS_PHOTO_LIB.selectByVO", vo);
	}
    
    /**
     * 搜索数据库中与传入的主键对应的记录
     * @param pkid 与数据库主键对应
     * @return type : 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
	 * @throws Exception
     */
    public BasPhotoLib selectByPk(Long pkId) throws Exception {
    	if(pkId ==null) return null;
    	return (BasPhotoLib)getSqlSession().selectOne("BAS_PHOTO_LIB.selectByPk", pkId);
    }
    
	/**
	 * 向数据库中插入一个VO对象
	 * @param vo 对象对应到数据库中的一条记录
	 * @return type : RpDir 返回操作是否成功
	 * @throws Exception
	 */
	public BasPhotoLib insertByVo(BasPhotoLib vo) throws Exception {
	    if(vo == null) return null;
	    return (BasPhotoLib)insert("BAS_PHOTO_LIB.insertByVo", vo);
    }


}
