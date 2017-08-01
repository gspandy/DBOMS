/**
 * com.sunrise.88.bo.InterfaceLogBo.java
 */
package com.tydic.dbs.buyer.bo;

import java.util.*;


import com.tydic.commons.utils.OrderUtils;
import com.tydic.commons.utils.Page;
import com.tydic.dbs.buyer.dao.InterfaceLogDao;
import com.tydic.dbs.buyer.service.InterfaceLogService;
import com.tydic.dbs.buyer.vo.InterfaceLog;
import com.tydic.dbs.commons.define.WcsCommonStatus;
import com.tydic.dbs.commons.utils.DateUtil;
import org.apache.commons.lang.StringUtils;

/**
 * @file  InterfaceLogBo.java
 * @author Carson
 * @version 0.1
 * @InterfaceLog业务操作接口实现类
 * Copyright(C), 2013-2014
 *		 Guangzhou tydic Technology Co., Ltd.
 * History
 *   	1. Date: 2016-08-03 03:48:28
 *      	Author: Carson
 *      	Modification: this file was created
 *   	2. ...
 */
public class InterfaceLogBo implements InterfaceLogService {
	
	private InterfaceLogDao interfaceLogDao;
	

    /**
	 * @param interfaceLogDao the interfaceLogDao to set
	 */
	public void setInterfaceLogDao(InterfaceLogDao interfaceLogDao) {
		this.interfaceLogDao = interfaceLogDao;
	}


	/**
	 * 查找所有数据库记录
     * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
     */
	@SuppressWarnings({ "rawtypes" })
    public List getAll() throws Exception {
		return interfaceLogDao.selectAll();
	}
	
	/**
     * 查找符合条件的所有数据库记录
     * @param vo 与数据库中记录对应的值对象
     * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
     * @throws Exception
     */
	@SuppressWarnings({ "rawtypes" })
    public List get(InterfaceLog vo) throws Exception{
		if(vo == null) return null;
        return interfaceLogDao.selectByVo(vo);
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
		return interfaceLogDao.queryForPage(params);
	}
	
    /**
     * 查找符合条件的数据库记录
     * @param pkId 与数据库中主键对应的值
     * @return type : 返回查询操作符合条件的记录的VO对象，操作失败返回null
     * @throws Exception
     */
	public InterfaceLog get(String pkId) throws Exception{
		if(StringUtils.isEmpty(pkId)) return null;
     	return interfaceLogDao.selectByPk(pkId);
	}
	
	/**
     * 向数据库中插入一条记录
     * @param vo 与数据库中记录对应的值对象
     * @return type : RpDir 返回插入操作是否成功
     * @throws Exception
     */
    public InterfaceLog save(InterfaceLog vo) throws Exception{
    	if(vo == null) return null;
		if(vo.getSerialNum()==null||"".equals(vo.getSerialNum())){
			vo.setSerialNum(OrderUtils.generateOutTradeNo());
		}
		vo.setCreateTime(new Date());
		//vo.setTabName("INTERFACE_LOG_"+ DateUtil.DateToString(new Date(),"YYYYMM"));
		if(vo.getLogId()!=null) {
			return interfaceLogDao.updateByVo(vo);
		} else {
			return interfaceLogDao.insertByVo(vo);
		}
    }
    
    /**
     * 删除符合条件的数据库记录
     * @param pkId 与数据库中主键对应的值
     * @return type : boolean 返回删除操做是否成功，操作失败返回false
     */
    public boolean delete(String pkId) throws Exception{
    	if(StringUtils.isEmpty(pkId)) return false;
    	return interfaceLogDao.deleteByPk(pkId);
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
    	return interfaceLogDao.updateStatus(params);
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
    	return interfaceLogDao.updateStatus(params);
	}

	/**
	 * 更新日志表
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public InterfaceLog update(InterfaceLog vo) throws Exception{
		if(vo == null){
			return null;
		}
		return interfaceLogDao.updateByVoNotNull(vo);
	}
}
