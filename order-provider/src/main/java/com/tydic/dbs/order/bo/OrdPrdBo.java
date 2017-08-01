/**
 * com.sunrise.ord.bo.OrdPrdBo.java
 */
package com.tydic.dbs.order.bo;

import java.util.*;

import com.tydic.commons.utils.OrderUtils;
import com.tydic.dbs.order.dao.OrdInfoDao;
import com.tydic.dbs.order.dao.OrdLogDao;
import com.tydic.dbs.order.vo.OrdInfo;
import com.tydic.dbs.order.vo.OrdLog;
import com.tydic.dbs.order.vo.OrdPrdAndData;
import org.apache.commons.lang.StringUtils;

import com.tydic.commons.utils.Page;
import com.tydic.dbs.commons.define.WcsCommonStatus;
import com.tydic.dbs.order.dao.OrdPrdDao;
import com.tydic.dbs.order.service.OrdPrdService;
import com.tydic.dbs.order.vo.OrdPrd;

/**
 * @file  OrdPrdBo.java
 * @author Carson
 * @version 0.1
 * @OrdPrd业务操作接口实现类
 * Copyright(C), 2013-2014
 *		 Guangzhou tydic Technology Co., Ltd.
 * History
 *   	1. Date: 2016-07-08 01:11:36
 *      	Author: Carson
 *      	Modification: this file was created
 *   	2. ...
 */
public class OrdPrdBo implements OrdPrdService {

	private OrdPrdDao ordPrdDao;

	private OrdLogDao ordLogDao;

	private OrdInfoDao ordInfoDao;

	public void setOrdInfoDao(OrdInfoDao ordInfoDao) {
		this.ordInfoDao = ordInfoDao;
	}

	/**
	 * @param ordPrdDao the ordPrdDao to set
	 */
	public void setOrdPrdDao(OrdPrdDao ordPrdDao) {

		this.ordPrdDao = ordPrdDao;
	}

	public void setOrdLogDao(OrdLogDao ordLogDao) {
		this.ordLogDao = ordLogDao;
	}

	/**
	 * 查找所有数据库记录
     * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
     */
	@SuppressWarnings({ "rawtypes" })
    public List getAll() throws Exception {
		return ordPrdDao.selectAll();
	}

	/**
     * 查找符合条件的所有数据库记录
     * @param vo 与数据库中记录对应的值对象
     * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
     * @throws Exception
     */
	@SuppressWarnings({ "rawtypes" })
    public List get(OrdPrd vo) throws Exception{
		if(vo == null) return null;
        return ordPrdDao.selectByVo(vo);
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
		return ordPrdDao.queryForPage(params);
	}

    /**
     * 查找符合条件的数据库记录
     * @return type : 返回查询操作符合条件的记录的VO对象，操作失败返回null
     * @throws Exception
     */
	public OrdPrd get(String pkId) throws Exception{
		if(StringUtils.isEmpty(pkId)) return null;
     	return ordPrdDao.selectByPk(pkId);
	}

	/**
     * 向数据库中插入一条记录
     * @param vo 与数据库中记录对应的值对象
     * @return type : RpDir 返回插入操作是否成功
     * @throws Exception
     */
    public OrdPrd save(OrdPrd vo) throws Exception{
    	if(vo == null) return null;
		OrdPrd ordPrd=new OrdPrd();
		OrdLog ordLog=new OrdLog();
		OrdInfo ordInfo=new OrdInfo();
		ordInfo.setBusId(vo.getBussId());
		
		if("10".equals(vo.getOperType()) || "00".equals(vo.getOperType())){
			ordInfo.setOrdStatus("2");
		}else{
			ordInfo.setOrdStatus("1");
		}
		if("01".equals(vo.getOperType()) || "00".equals(vo.getOperType())) {
			ordPrd=ordPrdDao.updateByVo(vo);
			ordLog.setOrdLogType("2");
			ordLog.setOrdLogMemo("修改工单：工单编号为："+vo.getOrdId());
			ordLog.setOrdLogUser(vo.getBussId());
			ordInfo.setModifyTime(Calendar.getInstance().getTime());
			ordInfo.setOrdId(vo.getOrdId());
			ordInfoDao.updateByVo(ordInfo);
		} else {
			ordPrd=ordPrdDao.insertByVo(vo);
			ordLog.setOrdLogType("1");
			ordLog.setOrdLogMemo("新增工单：工单编号为："+vo.getOrdId());
			ordLog.setOrdLogUser(vo.getBussId());
			ordInfo.setCreateTime(Calendar.getInstance().getTime());
			ordInfo.setOrdId(vo.getOrdId());
			ordInfoDao.insertByVo(ordInfo);
		}
		ordLog.setOrdLogId(OrderUtils.generateOutTradeNo());
		ordLog.setOrdId(vo.getOrdId());
		ordLog.setOrdLogTime(Calendar.getInstance().getTime());
		ordLogDao.insertByVo(ordLog);
		return ordPrd;
    }

    /**
     * 删除符合条件的数据库记录
     * @return type : boolean 返回删除操做是否成功，操作失败返回false
     */
    public boolean delete(String pkId) throws Exception{
    	if(StringUtils.isEmpty(pkId)) return false;
    	return ordPrdDao.deleteByPk(pkId);
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
    	return ordPrdDao.updateStatus(params);
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
    	return ordPrdDao.updateStatus(params);
	}

	public OrdPrd getByOrdId(String ordId)throws Exception{
		if (StringUtils.isEmpty(ordId)) return null;
		return ordPrdDao.getByOrdId(ordId);
	}

	public OrdPrdAndData selectPrdByOrderId(String prdId)throws Exception{
		if (StringUtils.isEmpty(prdId)) return null;
		return ordPrdDao.selectPrdByOrderId(prdId);
	}

	
	public boolean deleteByFkId(String fkId) throws Exception {
		if(StringUtils.isEmpty(fkId)) return false;
    	return ordPrdDao.deleteByFk(fkId);
	}
}
