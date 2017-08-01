/**
 * com.tydic.dbs.bo.SysDataFactorBo.java
 */
package com.tydic.dbs.system.menu.bo;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tydic.commons.utils.Page;
import com.tydic.dbs.system.menu.dao.SysDataFactorDao;
import com.tydic.dbs.system.menu.mapper.SysDataFactor;
import com.tydic.dbs.system.menu.service.SysDataFactorService;

/**
 * @file  SysDataFactorBo.java
 * @author liugaolin
 * @version 0.1
 * @SysDataFactor业务操作接口实现类
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-02-27 05:42:53
 *      	Author: liugaolin
 *      	Modification: this file was created
 *   	2. ...
 */
public class SysDataFactorBo implements SysDataFactorService {
	
	private SysDataFactorDao sysDataFactorDao;

    /**
	 * @param sysDataFactorDao the sysDataFactorDao to set
	 */
	public void setSysDataFactorDao(SysDataFactorDao sysDataFactorDao) {
		this.sysDataFactorDao = sysDataFactorDao;
	}

	/**
	 * 查找所有数据库记录
     * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
     */
	public List<SysDataFactor> getAll() throws Exception {
		return sysDataFactorDao.selectAll();
	}
	
	/**
     * 查找符合条件的所有数据库记录
     * @param vo 与数据库中记录对应的值对象
     * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
     * @throws Exception
     */
	@SuppressWarnings({ "rawtypes" })
    public List get(SysDataFactor vo) throws Exception{
		if(vo == null) return null;
        return sysDataFactorDao.selectByVo(vo);
	}
	
    /**
     * 分页查找
     * @param params 与数据库中记录对应的值对象
     * @return type : Page 返回满足条件的记录集，操作失败返回null
     * @throws Exception
     */
	@SuppressWarnings({ "rawtypes" })
    public Page getPageByParamMap(Map params) throws Exception{
		if (params == null)
			return null;
		return sysDataFactorDao.queryForPage(params);
	}
	
    /**
     * 查找符合条件的所有数据库记录
     * @param pkid 与数据库中主键对应的值
     * @return type : 返回查询操作所有符合条件的记录的VO对象，操作失败返回null
     * @throws Exception
     */
	public SysDataFactor get(Long pkId) throws Exception{
		if(pkId==null) return null;
     	return sysDataFactorDao.selectByPk(pkId);
	}
	
	/**
     * 向数据库中插入一条记录
     * @param vo 与数据库中记录对应的值对象
     * @return type : RpDir 返回插入操作是否成功
     * @throws Exception
     */
    public SysDataFactor save(SysDataFactor vo) throws Exception{
    	if(vo == null) return null;		
		if(vo.getFactorId()!=null) {
			return sysDataFactorDao.updateByVo(vo);
		} else {
			return sysDataFactorDao.insertByVo(vo);
		}
    }
    
    /**
     * 删除符合条件的所有数据库记录
     * @param pkid 与数据库中主键对应的值
     * @return type : boolean 返回删除操做是否成功，操作失败返回false
     */
    public boolean delete(Long pkId) throws Exception{
    	if(pkId == null) return false;
    	return sysDataFactorDao.deleteByPk(pkId);
    }
    
    /**
     * 删除数据库中与传入的值对象对应的记录
     * @param vo 与数据库中记录对应的值对象
     * @return type : boolean 返回删除操作是否成功
     * @throws Exception
     */
    public boolean delete(SysDataFactor vo) throws Exception{
    	if(vo == null) return false;
     	return sysDataFactorDao.deleteByVo(vo);
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
    	params.put("status", "0");
    	params.put("modifier", modifier);
    	params.put("modifyTime", Calendar.getInstance().getTime());
    	return sysDataFactorDao.updateStatus(params);
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
    	params.put("status", "1");
    	params.put("modifier", modifier);
    	params.put("modifyTime", Calendar.getInstance().getTime());
    	return sysDataFactorDao.updateStatus(params);
	}
	
}
