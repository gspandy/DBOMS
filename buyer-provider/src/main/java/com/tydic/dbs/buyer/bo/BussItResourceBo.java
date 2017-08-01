/**
 */
package com.tydic.dbs.buyer.bo;

import java.util.*;


import com.tydic.dbs.buyer.service.BussAuditStatusService;
import com.tydic.dbs.buyer.service.BussDataPemissionService;
import com.tydic.dbs.buyer.vo.*;

import com.tydic.dbs.commons.enums.AuditStatus;
import com.tydic.dbs.commons.enums.AuditType;
import com.tydic.dbs.commons.enums.Status;
import org.apache.commons.lang.StringUtils;

import com.tydic.commons.utils.Page;
import com.tydic.dbs.buyer.dao.BussItResourceDao;
import com.tydic.dbs.buyer.service.BussItResourceService;
import com.tydic.dbs.commons.define.WcsCommonStatus;


/**
 * @file  BussItResourceBo.java
 * @author Carson
 * @version 0.1
 * @BussItResource业务操作接口实现类
 * Copyright(C), 2013-2014
 *		 Guangzhou tydic Technology Co., Ltd.
 * History
 *   	1. Date: 2016-07-12 06:58:33
 *      	Author: Carson
 *      	Modification: this file was created
 *   	2. ...
 */
public class BussItResourceBo implements BussItResourceService {
	
	private BussItResourceDao bussItResourceDao;

	private BussDataPemissionService bussDataPemissionService;

	private BussAuditStatusService bussAuditStatusService;
	

    /**
	 * @param bussItResourceDao the bussItResourceDao to set
	 */
	public void setBussItResourceDao(BussItResourceDao bussItResourceDao) {
		this.bussItResourceDao = bussItResourceDao;
	}

	public void setBussDataPemissionService(BussDataPemissionService bussDataPemissionService) {
		this.bussDataPemissionService = bussDataPemissionService;
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
		return bussItResourceDao.selectAll();
	}
	
	/**
     * 查找符合条件的所有数据库记录
     * @param vo 与数据库中记录对应的值对象
     * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
     * @throws Exception
     */
	@SuppressWarnings({ "rawtypes" })
    public List get(BussItResource vo) throws Exception{
		if(vo == null) return null;
        return bussItResourceDao.selectByVo(vo);
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
		return bussItResourceDao.queryForPage(params);
	}
	
    /**
     * 查找符合条件的数据库记录
     * @param pkId 与数据库中主键对应的值
     * @return type : 返回查询操作符合条件的记录的VO对象，操作失败返回null
     * @throws Exception
     */
	public BussItResource get(String pkId) throws Exception{
		if(StringUtils.isEmpty(pkId)) return null;
     	return bussItResourceDao.selectByPk(pkId);
	}
	
	/**
     * 向数据库中插入一条记录
     * @param vo 与数据库中记录对应的值对象
     * @return type : RpDir 返回插入操作是否成功
     * @throws Exception
     */
    public BussItResource save(BussItResource vo) throws Exception{
    	if(vo == null) return null;
		if(vo.getResoureId()!=null&&!"".equals(vo.getResoureId())){
			return bussItResourceDao.updateByVo(vo);
		}else {
			return bussItResourceDao.insertByVo(vo);
		}

    }


	public BussItResource modifyFtpInfoByBussId(BussItResource vo,BussAuditStatus auditStatus) throws Exception{
		if(vo == null||auditStatus==null) return null;
		//更新商务审核状态
		bussAuditStatusService.saveAuditInfo(auditStatus);
		return bussItResourceDao.modifyFtpInfoByBussId(vo);

	}
    /**
     * 删除符合条件的数据库记录
     * @param pkId 与数据库中主键对应的值
     * @return type : boolean 返回删除操做是否成功，操作失败返回false
     */
    public boolean delete(String pkId) throws Exception{
    	if(StringUtils.isEmpty(pkId)) return false;
    	return bussItResourceDao.deleteByPk(pkId);
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
    	return bussItResourceDao.updateStatus(params);
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
    	return bussItResourceDao.updateStatus(params);
	}

	/**
	 * 查询当前用户IT资源
	 * @param map
	 * @return
	 * @throws Exception
     */
	public  List selectResourceByMap(Map map)throws  Exception{
		if (null == map) return  null;
		return bussItResourceDao.selectByParamMap(map);

	}

	/**
	 *
	 * 变更it资源信息
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public BussItResource update(BussItResource vo) throws Exception{
		if(vo==null){
			return null;
		}
		bussItResourceDao.updateByVoNotNull(vo);
		//保存商务审核信息
		//保存IT资源审核
		BussAuditStatus auditStatus = new BussAuditStatus();
		auditStatus.setBussId(vo.getBussId());
		auditStatus.setType(AuditType.ITRESOURCE.getCode());
		auditStatus.setStatus(Status.VALID.getCode());
		auditStatus.setAuditStatus(AuditStatus.WAIT.getCode());
		bussAuditStatusService.saveAuditInfo(auditStatus);

		//保存数据权限审核信息
		auditStatus.setType(AuditType.DATAPEM.getCode());
		bussAuditStatusService.saveAuditInfo(auditStatus);
		return vo;
	}

	/**
	 * 保存商户配置信息
	 * @param itResource
	 * @param pemList
	 * @return
	 * @throws Exception
	 */
	public boolean saveBussConfig(String operType,BussItResource itResource,List<BussDataPemission> pemList,boolean isModifyITResource,boolean isModifyDataPem) throws Exception {
		if (itResource==null){
			return false;
		}

		if(isModifyITResource){
			//保存it资源信息
			save(itResource);
			//保存IT资源审核
			BussAuditStatus auditStatus = new BussAuditStatus();
			auditStatus.setBussId(itResource.getBussId());
			auditStatus.setType(AuditType.ITRESOURCE.getCode());
			auditStatus.setStatus(Status.VALID.getCode());
			if(AuditStatus.DRAFT.getCode().equals(operType)){
				auditStatus.setAuditStatus(AuditStatus.DRAFT.getCode());
			}else{
				auditStatus.setAuditStatus(AuditStatus.WAIT.getCode());
			}

			bussAuditStatusService.saveAuditInfo(auditStatus);
		}

		//更新数据权限信息及审核状态
		if(isModifyDataPem){
			//更新数据权限信息
			bussDataPemissionService.updateByBussId(itResource.getBussId(),pemList);
			//保存数据权限审核信息
			BussAuditStatus auditStatus = new BussAuditStatus();
			auditStatus.setBussId(itResource.getBussId());
			auditStatus.setStatus(Status.VALID.getCode());
			if(AuditStatus.DRAFT.getCode().equals(operType)){
				auditStatus.setAuditStatus(AuditStatus.DRAFT.getCode());
			}else{
				auditStatus.setAuditStatus(AuditStatus.WAIT.getCode());
			}
			auditStatus.setType(AuditType.DATAPEM.getCode());
			bussAuditStatusService.saveAuditInfo(auditStatus);
		}



		return true;
	}

	/**
	 * 保存商户配置信息
	 * @param itResource
	 * @return
	 * @throws Exception
	 */
	public boolean saveItResource(BussItResource itResource,boolean isModifyITResource) throws Exception {
		if (itResource==null){
			return false;
		}

		if(isModifyITResource){
			//保存it资源信息
			save(itResource);
			//保存IT资源审核
			BussAuditStatus auditStatus = new BussAuditStatus();
			auditStatus.setBussId(itResource.getBussId());
			auditStatus.setType(AuditType.ITRESOURCE.getCode());
			auditStatus.setStatus(Status.VALID.getCode());
			auditStatus.setAuditStatus(AuditStatus.WAIT.getCode());

			bussAuditStatusService.saveAuditInfo(auditStatus);
		}
		return true;
	}


	public BussItResource selectByBussId(String bussId)throws Exception{
		if (StringUtils.isEmpty(bussId)) return null;
		return bussItResourceDao.selectByBussId(bussId);
	}
}
