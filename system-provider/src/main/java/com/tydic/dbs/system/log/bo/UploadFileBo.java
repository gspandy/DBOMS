/**
 * com.sunrise.wcs.bo.UploadFileBo.java
 */
package com.tydic.dbs.system.log.bo;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.tydic.commons.utils.Page;
import com.tydic.dbs.commons.define.WcsCommonStatus;
import com.tydic.dbs.system.log.dao.UploadFileDao;
import com.tydic.dbs.system.log.mapper.UploadFile;
import com.tydic.dbs.system.log.service.UploadFileService;
import org.apache.commons.lang.StringUtils;

/**
 * @file  UploadFileBo.java
 * @author Carson
 * @version 0.1
 * @UploadFile业务操作接口实现类
 * Copyright(C), 2013-2014
 *		 zengql
 * History
 *   	1. Date: 2016-08-09 05:28:44
 *      	Author: zengql
 *   	2. ...
 */
public class UploadFileBo implements UploadFileService {
	
	private UploadFileDao uploadFileDao;
	

    /**
	 * @param uploadFileDao the uploadFileDao to set
	 */
	public void setUploadFileDao(UploadFileDao uploadFileDao) {
		this.uploadFileDao = uploadFileDao;
	}


	/**
	 * 查找所有数据库记录
     * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
     */
	@SuppressWarnings({ "rawtypes" })
    public List getAll() throws Exception {
		return uploadFileDao.selectAll();
	}
	
	/**
     * 查找符合条件的所有数据库记录
     * @param vo 与数据库中记录对应的值对象
     * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
     * @throws Exception
     */
	@SuppressWarnings({ "rawtypes" })
    public List get(UploadFile vo) throws Exception{
		if(vo == null) return null;
        return uploadFileDao.selectByVo(vo);
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
		return uploadFileDao.queryForPage(params);
	}
	
    /**
     * 查找符合条件的数据库记录
     * @return type : 返回查询操作符合条件的记录的VO对象，操作失败返回null
     * @throws Exception
     */
	public UploadFile get(String pkId) throws Exception{
		if(StringUtils.isEmpty(pkId)) return null;
     	return uploadFileDao.selectByPk(pkId);
	}
	
	/**
     * 向数据库中插入一条记录
     * @param vo 与数据库中记录对应的值对象
     * @return type : RpDir 返回插入操作是否成功
     * @throws Exception
     */
    public UploadFile save(UploadFile vo) throws Exception{
    	if(vo == null) return null;		
		if(vo.getUploadId()!=null) {
			return uploadFileDao.updateByVo(vo);
		} else {
			return uploadFileDao.insertByVo(vo);
		}
    }
    
    /**
     * 删除符合条件的数据库记录
     * @return type : boolean 返回删除操做是否成功，操作失败返回false
     */
    public boolean delete(String pkId) throws Exception{
    	if(StringUtils.isEmpty(pkId)) return false;
    	return uploadFileDao.deleteByPk(pkId);
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
    	return uploadFileDao.updateStatus(params);
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
    	return uploadFileDao.updateStatus(params);
	}
	
	public boolean updateByserNum(String serNum)throws Exception{
		if(StringUtils.isEmpty(serNum)) return false;
		return uploadFileDao.updateByserNum(serNum);
	}
}
