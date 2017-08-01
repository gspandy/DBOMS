/**
 * com.sunrise.wcs.bo.InfFileLogBo.java
 */
package com.tydic.dbs.system.log.bo;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tydic.commons.utils.Page;
import com.tydic.dbs.commons.define.WcsCommonStatus;
import com.tydic.dbs.system.log.dao.InfFileLogDao;
import com.tydic.dbs.system.log.mapper.InfFileLog;
import com.tydic.dbs.system.log.service.InfFileLogService;
import org.apache.commons.lang.StringUtils;

/**
 * @file  InfFileLogBo.java
 * @author zjl
 * @version 0.1
 * @InfFileLog业务操作接口实现类
 * Copyright(C), 2013-2014
 *		 Guangzhou tydic Technology Co., Ltd.
 * History
 *   	1. Date: 2016-08-10 02:13:44
 *      	Author: zjl
 *      	Modification: this file was created
 *   	2. ...
 */
public class InfFileLogBo implements InfFileLogService {

	private InfFileLogDao infFileLogDao;


	/**
	 * @param infFileLogDao the infFileLogDao to set
	 */
	public void setInfFileLogDao(InfFileLogDao infFileLogDao) {
		this.infFileLogDao = infFileLogDao;
	}


	/**
	 * 查找所有数据库记录
	 * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
	 */
	@SuppressWarnings({ "rawtypes" })
	public List getAll() throws Exception {
		return infFileLogDao.selectAll();
	}

	/**
	 * 查找符合条件的所有数据库记录
	 * @param vo 与数据库中记录对应的值对象
	 * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes" })
	public List get(InfFileLog vo) throws Exception{
		if(vo == null) return null;
		return infFileLogDao.selectByVo(vo);
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
		return infFileLogDao.queryForPage(params);
	}

	/**
	 * 查找符合条件的数据库记录
	 * @return type : 返回查询操作符合条件的记录的VO对象，操作失败返回null
	 * @throws Exception
	 */
	public InfFileLog get(String pkId) throws Exception{
		if(StringUtils.isEmpty(pkId)) return null;
		return infFileLogDao.selectByPk(pkId);
	}

	/**
	 * 向数据库中插入一条记录
	 * @param vo 与数据库中记录对应的值对象
	 * @return type : RpDir 返回插入操作是否成功
	 * @throws Exception
	 */
	public InfFileLog save(InfFileLog vo) throws Exception{
		if(vo == null) return null;
		if(vo.getInfLogId()!=null) {
			return infFileLogDao.updateByVo(vo);
		} else {
			return infFileLogDao.insertByVo(vo);
		}
	}

	/**
	 * 删除符合条件的数据库记录
	 * @return type : boolean 返回删除操做是否成功，操作失败返回false
	 */
	public boolean delete(String pkId) throws Exception{
		if(StringUtils.isEmpty(pkId)) return false;
		return infFileLogDao.deleteByPk(pkId);
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
		return infFileLogDao.updateStatus(params);
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
		return infFileLogDao.updateStatus(params);
	}

	public List getLogCount(String infName)throws Exception{
		if (StringUtils.isEmpty(infName))return null;
		return  infFileLogDao.getLogCount(infName);
	}
}
