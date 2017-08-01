/**
 * com.tydic.dbs.system.operator.bo.SysChanOperRelBo.java
 */
package com.tydic.dbs.system.operator.bo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.tydic.commons.utils.Page;
import com.tydic.dbs.system.operator.dao.SysChanOperRelDao;
import com.tydic.dbs.system.operator.mapper.SysChanOperRel;
import com.tydic.dbs.system.operator.service.SysChanOperRelService;

/**
 * @file  SysChanOperRelBo.java
 * @author caipeimin
 * @version 0.1
 * @SysChanOperRel业务操作接口实现类
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-03-22 02:22:02
 *      	Author: caipeimin
 *      	Modification: this file was created
 *   	2. ...
 */
public class SysChanOperRelBo implements SysChanOperRelService {
	
	private SysChanOperRelDao sysChanOperRelDao;

    /**
	 * @param sysChanOperRelDao the sysChanOperRelDao to set
	 */
	public void setSysChanOperRelDao(SysChanOperRelDao sysChanOperRelDao) {
		this.sysChanOperRelDao = sysChanOperRelDao;
	}

	/**
	 * 查找所有数据库记录
     * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
     */
    public List<SysChanOperRel> getAll() throws Exception {
		return sysChanOperRelDao.selectAll();
	}
	
	/**
     * 查找符合条件的所有数据库记录
     * @param vo 与数据库中记录对应的值对象
     * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
     * @throws Exception
     */
    public List<SysChanOperRel> get(SysChanOperRel vo) throws Exception{
		if(vo == null) return null;
        return sysChanOperRelDao.selectByVo(vo);
	}
	
    /**
     * 分页查找
     * @param params 与数据库中记录对应的值对象
     * @return type : Page 返回满足条件的记录集，操作失败返回null
     * @throws Exception
     */
	@SuppressWarnings({ "rawtypes" })
    public Page getPageByParamMap(Map params) throws Exception{
		if (params == null) return null;
		return sysChanOperRelDao.queryForPage(params);
	}
	
    /**
     * 查找符合条件的数据库记录
     * @param pkid 与数据库中主键对应的值
     * @return type : 返回查询操作符合条件的记录的VO对象，操作失败返回null
     * @throws Exception
     */
	public SysChanOperRel get(Long pkId) throws Exception{
		if(pkId==null) return null;
     	return sysChanOperRelDao.selectByPk(pkId);
	}
	
	/**
     * 向数据库中插入一条记录
     * @param vo 与数据库中记录对应的值对象
     * @return type : RpDir 返回插入操作是否成功
     * @throws Exception
     */
    public SysChanOperRel save(SysChanOperRel vo) throws Exception{
    	if(vo == null) return null;		
		if(vo.getOperChannelId()!=null) {
			return sysChanOperRelDao.updateByVo(vo);
		} else {
			return sysChanOperRelDao.insertByVo(vo);
		}
    }
    
    /**
     * 删除符合条件的数据库记录
     * @param pkid 与数据库中主键对应的值
     * @return type : boolean 返回删除操做是否成功，操作失败返回false
     */
    public boolean delete(Long pkId) throws Exception{
    	if(pkId == null) return false;
    	return sysChanOperRelDao.deleteByPk(pkId);
    }
    
    /**
     * 根据操作员编码获取渠道关系数据
     * @param operId 操作员编码
     * @return
     * @throws Exception
     */
    public List<SysChanOperRel> getChanOperRelByOperId(String operId) throws Exception {
    	if (StringUtils.isEmpty(operId))
    		return null;
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put("operId", operId);
    	return sysChanOperRelDao.selectByParamMap(params);
    }
    
}
