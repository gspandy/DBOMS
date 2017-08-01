/**
 * com.sunrise.wcs.bo.BussTenantBo.java
 */
package com.tydic.dbs.buyer.bo;

import java.util.*;

import com.tydic.dbs.buyer.dao.BussAuditStatusDao;
import com.tydic.dbs.buyer.dao.BussTenantRoleDao;

import com.tydic.dbs.buyer.service.BussAuditStatusService;
import com.tydic.dbs.buyer.vo.BussAuditStatus;
import com.tydic.dbs.buyer.vo.BussTenantRole;
import com.tydic.dbs.buyer.vo.DataPemissionDict;
import com.tydic.dbs.commons.enums.Status;
import com.tydic.dbs.commons.enums.AuditStatus;
import com.tydic.dbs.commons.enums.AuditType;


import org.apache.commons.lang.StringUtils;

import com.tydic.commons.utils.Page;
import com.tydic.dbs.buyer.dao.BussTenantDao;
import com.tydic.dbs.buyer.service.BussTenantService;
import com.tydic.dbs.buyer.vo.BussTenant;
import com.tydic.dbs.commons.define.WcsCommonStatus;

/**
 * @file  BussTenantBo.java
 * @author Carson
 * @version 0.1
 * @BussTenant业务操作接口实现类
 * Copyright(C), 2013-2014
 *		 Guangzhou tydic Technology Co., Ltd.
 * History
 *   	1. Date: 2016-07-12 06:58:33
 *      	Author: Carson
 *      	Modification: this file was created
 *   	2. ...
 */
public class BussTenantBo implements BussTenantService {
	
	private BussTenantDao bussTenantDao;

	private BussTenantRoleDao bussTenantRoleDao;

//	private BussAuditStatusService bussAuditStatusService;

    /**
	 * @param bussTenantDao the bussTenantDao to set
	 */
	public void setBussTenantDao(BussTenantDao bussTenantDao) {
		this.bussTenantDao = bussTenantDao;
	}

	public void setBussTenantRoleDao(BussTenantRoleDao bussTenantRoleDao) {
		this.bussTenantRoleDao = bussTenantRoleDao;
	}

//	public void setBussAuditStatusService(BussAuditStatusService bussAuditStatusService) {
//		this.bussAuditStatusService = bussAuditStatusService;
//	}

	/**
	 * 查找所有数据库记录
     * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
     */
	@SuppressWarnings({ "rawtypes" })
    public List getAll() throws Exception {
		return bussTenantDao.selectAll();
	}
	
	/**
     * 查找符合条件的所有数据库记录
     * @param vo 与数据库中记录对应的值对象
     * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
     * @throws Exception
     */
	@SuppressWarnings({ "rawtypes" })
    public List get(BussTenant vo) throws Exception{
		if(vo == null) return null;
        return bussTenantDao.selectByVo(vo);
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
		return bussTenantDao.queryForPage(params);
	}
	
    /**
     * 查找符合条件的数据库记录
     * @param pkId 与数据库中主键对应的值
     * @return type : 返回查询操作符合条件的记录的VO对象，操作失败返回null
     * @throws Exception
     */
	public BussTenant get(String pkId) throws Exception{
		if(StringUtils.isEmpty(pkId)) return null;
     	return bussTenantDao.selectByPk(pkId);
	}
	
	/**
     * 向数据库中插入一条记录
     * @param vo 与数据库中记录对应的值对象
     * @return type : RpDir 返回插入操作是否成功
     * @throws Exception
     */
    public BussTenant save(BussTenant vo) throws Exception{
    	if(vo == null) return null;
		if(vo.getRoleList()!=null){
			for (BussTenantRole role :vo.getRoleList()){
				bussTenantRoleDao.insertByVo(role);
			}
		}
//		//保存状态到商户审核状态表
//
//			BussAuditStatus auditStatus = new BussAuditStatus();
//			auditStatus.setBussId(vo.getBussId());
//			auditStatus.setType(AuditType.LEEAUTH.getCode());
//			auditStatus.setStatus(Status.VALID.getCode());
//			auditStatus.setAuditStatus(AuditStatus.WAIT.getCode());
//			bussAuditStatusService.saveAuditInfo(auditStatus);


		return bussTenantDao.insertByVo(vo);
    }

	public BussTenant modify(BussTenant vo) throws Exception{
		if(vo == null) return null;
		List <BussTenantRole> roleList = vo.getRoleList();

		if(roleList == null||roleList.size()==0){
			return bussTenantDao.updateByVoNotNull(vo);
		}
		String tenantId = vo.getTenantId();
		Map<String,String> param = new HashMap<>();
		param.put("tenantId",tenantId);
		param.put("status","1");
		List <BussTenantRole> oldRoleList=bussTenantRoleDao.selectByParamMap(param);

		List <BussTenantRole> addRoleList = new ArrayList<>();
		List <BussTenantRole> modifyRoleList = new ArrayList<>();
		List <BussTenantRole> delRoleList = new ArrayList<>();

		for (BussTenantRole newRole:roleList){
			boolean isExists = false;
			for(BussTenantRole oldRole:oldRoleList){
				if(newRole.getRoleId().equals(oldRole.getRoleId())){
					modifyRoleList.add(newRole);
					isExists = true;
					break;
				}
			}
			if(!isExists){
				addRoleList.add(newRole);
			}
		}
		for(BussTenantRole oldRole:oldRoleList){
			boolean isExists = false;
			for (BussTenantRole newRole:roleList){
				if(newRole.getRoleId().equals(oldRole.getRoleId())){
					isExists = true;
					break;
				}
			}
			if(!isExists){
				delRoleList.add(oldRole);
			}
		}

		for(BussTenantRole role : addRoleList){
			bussTenantRoleDao.insertByVo(role);
		}

		for(BussTenantRole role : modifyRoleList){
			if(Status.UNVALID.getCode().equals(vo.getStatus())){
				role.setStatus(Status.UNVALID.getCode());
			}
			role.setModifyTime(new Date());
			bussTenantRoleDao.updateByVoNotNull(role);
		}

		for(BussTenantRole role : delRoleList){
			bussTenantRoleDao.deleteByVo(role);
		}

		return bussTenantDao.updateByVoNotNull(vo);
	}

	/**
	 *
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public BussTenant syncTenantRoleInfo(BussTenant vo) throws Exception{
		if(vo == null) return null;
		List <BussTenantRole> roleList = vo.getRoleList();
		if(roleList == null||roleList.size()==0){
			return bussTenantDao.updateByVoNotNull(vo);
		}
		for(BussTenantRole role : roleList){
			bussTenantRoleDao.updateByVoNotNull(role);
		}
		return bussTenantDao.updateByVoNotNull(vo);
	}
    
    /**
     * 删除符合条件的数据库记录
     * @return type : boolean 返回删除操做是否成功，操作失败返回false
     */
    public boolean delete(String pkId) throws Exception{
    	if(StringUtils.isEmpty(pkId)) return false;
		Map<String,String> param = new HashMap<>();
		param.put("TENANT_ID",pkId);
		List <BussTenantRole> roleList=bussTenantRoleDao.selectByParamMap(param);
		for (BussTenantRole role : roleList){
			role.setStatus("0");
			bussTenantRoleDao.deleteByVo(role);
		}

    	return bussTenantDao.deleteByPk(pkId);
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
    	return bussTenantDao.updateStatus(params);
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
    	return bussTenantDao.updateStatus(params);
	}

	/**
	 * 查询操作员列表
	 * @param map
	 * @return
	 * @throws Exception
     */
	public  List getBussTenant(Map map)throws  Exception{
		return bussTenantDao.selectByParamMap(map);
	}

	/**
	 * 获取数据权限信息
	 * @return
	 * @throws Exception
     */
	public List<DataPemissionDict> getDataPemissionDict()throws Exception{
		return bussTenantDao.getDataPemissionDict();
	}

}
