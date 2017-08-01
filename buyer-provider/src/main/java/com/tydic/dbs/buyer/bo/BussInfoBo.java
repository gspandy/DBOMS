/**
 * com.sunrise.qqq.bo.BussInfoBo.java
 */
package com.tydic.dbs.buyer.bo;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.tydic.commons.utils.Page;
import com.tydic.dbs.buyer.dao.BussAuditStatusDao;
import com.tydic.dbs.buyer.dao.BussInfoDao;
import com.tydic.dbs.buyer.service.BussInfoService;
import com.tydic.dbs.buyer.vo.BussAuditStatus;
import com.tydic.dbs.buyer.vo.BussInfo;
import com.tydic.dbs.commons.define.WcsCommonStatus;

/**
 * @file  BussInfoBo.java
 * @author Carson
 * @version 0.1
 * @BussInfo业务操作接口实现类
 * Copyright(C), 2013-2014
 *		 Guangzhou tydic Technology Co., Ltd.
 * History
 *   	1. Date: 2016-07-14 07:04:48
 *      	Author: Carson
 *      	Modification: this file was created
 *   	2. ...
 */
public class BussInfoBo implements BussInfoService {
	
	private BussInfoDao bussInfoDao;
	
	private BussAuditStatusDao bussAuditStatusDao;
	

    public void setBussAuditStatusDao(BussAuditStatusDao bussAuditStatusDao) {
		this.bussAuditStatusDao = bussAuditStatusDao;
	}


	/**
	 * @param bussInfoDao the bussInfoDao to set
	 */
	public void setBussInfoDao(BussInfoDao bussInfoDao) {
		this.bussInfoDao = bussInfoDao;
	}


	/**
	 * 查找所有数据库记录
     * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
     */
	@SuppressWarnings({ "rawtypes" })
    public List getAll() throws Exception {
		return bussInfoDao.selectAll();
	}
	
	/**
     * 查找符合条件的所有数据库记录
     * @param vo 与数据库中记录对应的值对象
     * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
     * @throws Exception
     */
	@SuppressWarnings({ "rawtypes" })
    public List get(BussInfo vo) throws Exception{
		if(vo == null) return null;
        return bussInfoDao.selectByVo(vo);
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
		return bussInfoDao.queryForPage(params);
	}
	
    /**
     * 查找符合条件的数据库记录
     * @param pkid 与数据库中主键对应的值
     * @return type : 返回查询操作符合条件的记录的VO对象，操作失败返回null
     * @throws Exception
     */
	public BussInfo get(String pkId) throws Exception{
		if(StringUtils.isEmpty(pkId)) return null;
     	return bussInfoDao.selectByPk(pkId);
	}
	public BussInfo getaccId(String accountId) throws Exception{
		if(StringUtils.isEmpty(accountId)) return null;
     	return bussInfoDao.selectBuPk(accountId);
	}
	
	/**
     * 向数据库中插入一条记录
     * @param vo 与数据库中记录对应的值对象
     * @return type : RpDir 返回插入操作是否成功
     * @throws Exception
     */
    public BussInfo save(BussInfo vo) throws Exception{
    	if(vo == null) return null;	
    	BussAuditStatus bussAuditStatus = new BussAuditStatus();
		bussAuditStatus.setStatus("0");
		bussAuditStatus.setBussId(vo.getBussId());
		bussAuditStatusDao.updateByVoNotNull(bussAuditStatus);
		if(vo.getBussId()!=null) {
			return bussInfoDao.updateByVo(vo);
		} else {
			return bussInfoDao.insertByVo(vo);
		}
    }
    public BussInfo saveBuss(BussInfo vo) throws Exception{
    	if(vo == null) return null;		
		if(vo.getBussId()!=null) {
			return bussInfoDao.insertByVo(vo);
		} else {
			return bussInfoDao.updateByVo(vo);
		}
    }
    
    /**
     * 删除符合条件的数据库记录
     * @param pkid 与数据库中主键对应的值
     * @return type : boolean 返回删除操做是否成功，操作失败返回false
     */
    public boolean delete(String pkId) throws Exception{
    	if(StringUtils.isEmpty(pkId)) return false;
    	return bussInfoDao.deleteByPk(pkId);
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
    	return bussInfoDao.updateStatus(params);
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
    	return bussInfoDao.updateStatus(params);
	}
	/* (非 Javadoc) 
	 * <p>Title: countWithStatus</p> 
	 * <p>Description: </p> 
	 * @param status
	 * @return
	 * @throws Exception 
	 * @see com.tydic.dbs.buyer.service.BussInfoService#countWithStatus(java.lang.String) 
	 */
	@Override
	public int countWithStatus(String status) throws Exception {
		if (status!=null&&!"".equals(status)) {
			return bussInfoDao.countWithStatus(status);
		}
		return 0;
	}

	/**
	 * 查询商户状态
	 * @param map
	 * @return
	 * @throws Exception
     */
	public List getDataByMap(Map map)throws  Exception{
		if (null==map) return  null;
		return bussInfoDao.selectByParamMap(map);
	}
}
