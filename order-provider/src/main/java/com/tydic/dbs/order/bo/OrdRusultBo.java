/**
 * com.sunrise.ord.bo.OrdRusultBo.java
 */
package com.tydic.dbs.order.bo;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.tydic.commons.utils.Page;
import com.tydic.dbs.commons.define.WcsCommonStatus;
import com.tydic.dbs.order.dao.OrdRusultDao;
import com.tydic.dbs.order.service.OrdRusultService;
import com.tydic.dbs.order.vo.OrdRusult;

/**
 * @file  OrdRusultBo.java
 * @author Carson
 * @version 0.1
 * @OrdRusult业务操作接口实现类
 * Copyright(C), 2013-2014
 *		 Guangzhou tydic Technology Co., Ltd.
 * History
 *   	1. Date: 2016-07-08 01:11:36
 *      	Author: Carson
 *      	Modification: this file was created
 *   	2. ...
 */
public class OrdRusultBo implements OrdRusultService {
	
	private OrdRusultDao ordRusultDao;
	

    /**
	 * @param ordRusultDao the ordRusultDao to set
	 */
	public void setOrdRusultDao(OrdRusultDao ordRusultDao) {
		this.ordRusultDao = ordRusultDao;
	}


	/**
	 * 查找所有数据库记录
     * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
     */
	@SuppressWarnings({ "rawtypes" })
    public List getAll() throws Exception {
		return ordRusultDao.selectAll();
	}
	
	/**
     * 查找符合条件的所有数据库记录
     * @param vo 与数据库中记录对应的值对象
     * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
     * @throws Exception
     */
	@SuppressWarnings({ "rawtypes" })
    public List get(OrdRusult vo) throws Exception{
		if(vo == null) return null;
        return ordRusultDao.selectByVo(vo);
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
		return ordRusultDao.queryForPage(params);
	}
	
    /**
     * 查找符合条件的数据库记录
     * @param pkid 与数据库中主键对应的值
     * @return type : 返回查询操作符合条件的记录的VO对象，操作失败返回null
     * @throws Exception
     */
	public OrdRusult get(String pkId) throws Exception{
		if(StringUtils.isEmpty(pkId)) return null;
     	return ordRusultDao.selectByPk(pkId);
	}
	
	/**
     * 向数据库中插入一条记录
     * @param vo 与数据库中记录对应的值对象
     * @return type : RpDir 返回插入操作是否成功
     * @throws Exception
     */
    public OrdRusult save(OrdRusult vo) throws Exception{
    	if(vo == null) return null;		
		if(vo.getOrdRusultId()!=null) {
			return ordRusultDao.updateByVo(vo);
		} else {
			return ordRusultDao.insertByVo(vo);
		}
    }
    
    /**
     * 删除符合条件的数据库记录
     * @param pkid 与数据库中主键对应的值
     * @return type : boolean 返回删除操做是否成功，操作失败返回false
     */
    public boolean delete(String pkId) throws Exception{
    	if(StringUtils.isEmpty(pkId)) return false;
    	return ordRusultDao.deleteByPk(pkId);
    }
    
    /**
	 * 逻辑删除数据库中的对象
	 * @param ids: Id列表, modifier: 修改者
	 * @return type : boolean 返回操作是否成功
	 * @throws Exception
	 */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean deleteLogical(List ids, String modifier) throws Exception{
    	Map params = new HashMap();
    	params.put("ids", ids);
    	params.put("status", WcsCommonStatus.WCS_INVALID);
    	params.put("modifier", modifier);
    	params.put("modifyTime", Calendar.getInstance().getTime());
    	return ordRusultDao.updateStatus(params);
	}
	
    /**
	 * 恢复已逻辑删除的数据库中的对象
	 * @param ids: Id列表, modifier: 修改者
	 * @return type : boolean 返回操作是否成功
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean recover(List ids, String modifier) throws Exception{
    	Map params = new HashMap();
    	params.put("ids", ids);
    	params.put("status", WcsCommonStatus.WCS_VALID);
    	params.put("modifier", modifier);
    	params.put("modifyTime", Calendar.getInstance().getTime());
    	return ordRusultDao.updateStatus(params);
	}
}
