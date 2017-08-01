/**
 * com.tydic.dbs.basicConfig.basPhotoLib.bo.BasPhotoLibService.java
 */
package com.tydic.dbs.system.basPhotoLib.service;



import com.tydic.dbs.system.basPhotoLib.mapper.BasPhotoLib;

import java.util.List;

/**
 * @file  BasPhotoLibService.java
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
public abstract interface BasPhotoLibService {

	
	/**
     * 向数据库中插入一条记录
     * @param vo 与数据库中记录对应的值对象
     * @return type : 返回已保存或者已更新的VO对象
     * @throws Exception
     */
    public BasPhotoLib savePhoto(BasPhotoLib vo) throws Exception;
    

    



}
