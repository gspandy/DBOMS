/**
 * com.tydic.dbs.basicConfig.basPhotoLib.bo.BasPhotoLibBo.java
 */
package com.tydic.dbs.system.basPhotoLib.bo;


import com.tydic.dbs.system.basPhotoLib.dao.BasPhotoLibDao;
import com.tydic.dbs.system.basPhotoLib.mapper.BasPhotoLib;
import com.tydic.dbs.system.basPhotoLib.service.BasPhotoLibService;

/**
 * @file  BasPhotoLibBo.java
 * @author caipeimin
 * @version 0.1
 * @todo	BasPhotoLib业务操作接口
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Electronics Development Co., Ltd.
 * History
 *   	1. Date: 2014-02-22 05:56:27
 *      	Author: caipeimin
 *      	Modification: this file was created
 *   	2. ...
 */
public class BasPhotoLibBo implements BasPhotoLibService {
	
	private BasPhotoLibDao basPhotoLibDao;

	
	public BasPhotoLibDao getBasPhotoLibDao() {
		return basPhotoLibDao;
	}
	
	public void setBasPhotoLibDao(BasPhotoLibDao basPhotoLibDao) {
		this.basPhotoLibDao = basPhotoLibDao;
	}


	
	/**
     * 向数据库中插入一条记录
     * @param vo 与数据库中记录对应的值对象
     * @return type : 返回已保存或者已更新的VO对象
     * @throws Exception
     */
    public BasPhotoLib savePhoto(BasPhotoLib vo) throws Exception {
    	if (vo == null) return null;
    	return basPhotoLibDao.insertByVo(vo);
    }
	



}
