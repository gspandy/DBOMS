/**
 * com.sunrise.887.bo.BussAuditStatusBo.java
 */
package com.tydic.dbs.buyer.bo;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.tydic.commons.utils.Page;
import com.tydic.dbs.buyer.dao.BussAuditStatusDao;
import com.tydic.dbs.buyer.service.BussAuditStatusService;
import com.tydic.dbs.buyer.vo.BussAuditStatus;
import com.tydic.dbs.commons.define.WcsCommonStatus;
import com.tydic.dbs.commons.enums.Status;
import org.apache.commons.lang.StringUtils;

/**
 * @file  BussAuditStatusBo.java
 * @author Carson
 * @version 0.1
 * @BussAuditStatus业务操作接口实现类
 * Copyright(C), 2013-2014
 *		 Guangzhou tydic Technology Co., Ltd.
 * History
 *   	1. Date: 2016-07-26 05:13:08
 *      	Author: Carson
 *      	Modification: this file was created
 *   	2. ...
 */
public class BussAuditStatusBo implements BussAuditStatusService {
	
	private BussAuditStatusDao bussAuditStatusDao;
	

    /**
	 * @param bussAuditStatusDao the bussAuditStatusDao to set
	 */
	public void setBussAuditStatusDao(BussAuditStatusDao bussAuditStatusDao) {
		this.bussAuditStatusDao = bussAuditStatusDao;
	}


	/**
	 * 查找所有数据库记录
     * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
     */
	@SuppressWarnings({ "rawtypes" })
    public List getAll() throws Exception {
		return bussAuditStatusDao.selectAll();
	}
	
	/**
     * 查找符合条件的所有数据库记录
     * @param vo 与数据库中记录对应的值对象
     * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
     * @throws Exception
     */
	@SuppressWarnings({ "rawtypes" })
    public List get(BussAuditStatus vo) throws Exception{
		if(vo == null) return null;
        return bussAuditStatusDao.selectByVo(vo);
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
		return bussAuditStatusDao.queryForPage(params);
	}
	
    /**
     * 查找符合条件的数据库记录
     * @param pkId 与数据库中主键对应的值
     * @return type : 返回查询操作符合条件的记录的VO对象，操作失败返回null
     * @throws Exception
     */
	public BussAuditStatus get(String pkId) throws Exception{
		if(StringUtils.isEmpty(pkId)) return null;
     	return bussAuditStatusDao.selectByPk(pkId);
	}
	
	/**
     * 向数据库中插入一条记录
     * @param vo 与数据库中记录对应的值对象
     * @return type : RpDir 返回插入操作是否成功
     * @throws Exception
     */
    public BussAuditStatus save(BussAuditStatus vo) throws Exception{
    	if(vo == null) return null;		
		if(vo.getId()!=null) {
			return bussAuditStatusDao.updateByVo(vo);
		} else {
			return bussAuditStatusDao.insertByVo(vo);
		}
    }
    
    /**
     * 删除符合条件的数据库记录
     * @param pkId 与数据库中主键对应的值
     * @return type : boolean 返回删除操做是否成功，操作失败返回false
     */
    public boolean delete(String pkId) throws Exception{
    	if(StringUtils.isEmpty(pkId)) return false;
    	return bussAuditStatusDao.deleteByPk(pkId);
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
    	return bussAuditStatusDao.updateStatus(params);
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
    	return bussAuditStatusDao.updateStatus(params);
	}

	/**
	 * 根据商户ID和TYPE更新审核状态
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public BussAuditStatus updateByBussIdAndType(BussAuditStatus vo) throws Exception{
		if(vo ==null){
			return null;
		}
		return bussAuditStatusDao.updateByBussIdAndType(vo);
	}

	/**
	 * 变更保存商户审核信息
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public BussAuditStatus saveAuditInfo(BussAuditStatus vo) throws Exception{
		if(vo ==null){
			return null;
		}
		Map<String,String> param = new HashMap<>();
		param.put("bussId",vo.getBussId());
		param.put("type",vo.getType());
		param.put("status", Status.VALID.getCode());
		List <BussAuditStatus> list = bussAuditStatusDao.selectByParamMap(param);
		if(list ==null || list.size()==0){
			save(vo);
		}else{
			updateByBussIdAndType(vo);
		}
		return  vo;
	}

}
