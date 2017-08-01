/**
 */
package com.tydic.dbs.buyer.bo;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tydic.dbs.buyer.service.BussAuditStatusService;
import com.tydic.dbs.buyer.vo.BussAuditStatus;
import org.apache.commons.lang.StringUtils;

import com.tydic.commons.utils.Page;
import com.tydic.dbs.buyer.dao.BussTenantRoleDao;
import com.tydic.dbs.buyer.service.BussTenantRoleService;
import com.tydic.dbs.buyer.vo.BussTenantRole;
import com.tydic.dbs.commons.define.WcsCommonStatus;

/**
 * @file  BussTenantRoleBo.java
 * @author Carson
 * @version 0.1
 * @BussTenantRole业务操作接口实现类
 * Copyright(C), 2013-2014
 *		 Guangzhou tydic Technology Co., Ltd.
 * History
 *   	1. Date: 2016-07-15 10:17:42
 *      	Author: Carson
 *      	Modification: this file was created
 *   	2. ...
 */
public class BussTenantRoleBo implements BussTenantRoleService {
	
	private BussTenantRoleDao bussTenantRoleDao;

	private BussAuditStatusService bussAuditStatusService;

    /**
	 * @param bussTenantRoleDao the bussTenantRoleDao to set
	 */
	public void setBussTenantRoleDao(BussTenantRoleDao bussTenantRoleDao) {
		this.bussTenantRoleDao = bussTenantRoleDao;
	}

	public void setBussAuditStatusService(BussAuditStatusService bussAuditStatusService) {
		this.bussAuditStatusService = bussAuditStatusService;
	}

	/**
	 * 查找所有数据库记录
     * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
     */
	@SuppressWarnings({ "rawtypes" })
    public List getAll() throws Exception {
		return bussTenantRoleDao.selectAll();
	}
	
	/**
     * 查找符合条件的所有数据库记录
     * @param vo 与数据库中记录对应的值对象
     * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
     * @throws Exception
     */
	@SuppressWarnings({ "rawtypes" })
    public List get(BussTenantRole vo) throws Exception{
		if(vo == null) return null;
        return bussTenantRoleDao.selectByVo(vo);
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
		return bussTenantRoleDao.queryForPage(params);
	}
	
    /**
     * 查找符合条件的数据库记录
     * @param pkId 与数据库中主键对应的值
     * @return type : 返回查询操作符合条件的记录的VO对象，操作失败返回null
     * @throws Exception
     */
	public BussTenantRole get(String pkId) throws Exception{
		if(StringUtils.isEmpty(pkId)) return null;
     	return bussTenantRoleDao.selectByPk(pkId);
	}
	
	/**
     * 向数据库中插入一条记录
     * @param vo 与数据库中记录对应的值对象
     * @return type : RpDir 返回插入操作是否成功
     * @throws Exception
     */
    public BussTenantRole save(BussTenantRole vo) throws Exception{
    	if(vo == null) return null;		
		if(vo.getTenantId()!=null) {
			return bussTenantRoleDao.updateByVo(vo);
		} else {
			return bussTenantRoleDao.insertByVo(vo);
		}
	
    }
    
    /**
     * 删除符合条件的数据库记录
     * @param pkId 与数据库中主键对应的值
     * @return type : boolean 返回删除操做是否成功，操作失败返回false
     */
    public boolean delete(String pkId) throws Exception{
    	
    	if(StringUtils.isEmpty(pkId)) return false;
    	return bussTenantRoleDao.deleteByPk(pkId);
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
    	return bussTenantRoleDao.updateStatus(params);
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
    	return bussTenantRoleDao.updateStatus(params);
	}

	@Override
	public void modify(List<BussTenantRole> list,BussAuditStatus auditStatus) throws Exception {
		if (list == null||list.size()==0||auditStatus==null){
			return ;
		}

		//变更商户审核表状态
		bussAuditStatusService.saveAuditInfo(auditStatus);

		for(BussTenantRole role : list){
			bussTenantRoleDao.updateByVo(role);

		}
	}

	public List selectRoleByMap(Map map)throws  Exception{
		if (null ==map )return  null;
		return  bussTenantRoleDao.selectByParamMap(map);
	}
}
