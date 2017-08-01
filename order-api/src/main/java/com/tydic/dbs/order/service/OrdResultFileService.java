/**
 * com.tydic.dbs.bo.OrdResultFileService.java
 */
package com.tydic.dbs.order.service;

import com.tydic.commons.utils.Page;
import com.tydic.dbs.order.vo.OrdResultFile;

import java.util.List;
import java.util.Map;



/**
 * @file  OrdResultFileService.java
 * @author zhongjialian
 * @version 0.1
 * @OrdResultFile业务操作接口
 * Copyright(C), 2013-2014
 *		 Guangzhou tydic Technology Co., Ltd.
 * History
 *   	1. Date: 2016-08-09 05:40:01
 *      	Author: zhongjialian
 *      	Modification: this file was created
 *   	2. ...
 */
public abstract interface OrdResultFileService {

	/**
	 * 查找所有数据库记录
     * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
     */
	@SuppressWarnings({ "rawtypes" })
    public List getAll() throws Exception;
	
    /**
     * 分页查找
     * @param params 与数据库中记录对应的值对象
     * @return type : Page 返回满足条件的记录集，操作失败返回null
     * @throws Exception
     */
	@SuppressWarnings({ "rawtypes" })
    public Page getPageByParamMap(Map params) throws Exception;
	
    /**
     * 查找符合条件的数据库记录
     * @param pkId 与数据库中主键对应的值
     * @return type : 返回查询操作符合条件的记录的VO对象，操作失败返回null
     * @throws Exception
     */
	public OrdResultFile get(String pkId) throws Exception;
	
	/**
     * 向数据库中插入一条记录
     * @param vo 与数据库中记录对应的值对象
     * @return type : 返回已保存或者已更新的VO对象
     * @throws Exception
     */
    public OrdResultFile save(OrdResultFile vo) throws Exception;
    
    /**
     * 删除符合条件的数据库记录
     * @param pkId 与数据库中主键对应的值
     * @return type : boolean 返回删除操做是否成功，操作失败返回false
     */
    public boolean delete(String pkId) throws Exception;
    
    /**
	 * 逻辑删除数据库中的对象
	 * @param ids: Id列表, modifier: 修改者
	 * @return type : boolean 返回操作是否成功
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public boolean deleteLogical(List ids, String modifier) throws Exception;
	
    /**
	 * 恢复已逻辑删除的数据库中的对象
	 * @param ids: Id列表, modifier: 修改者
	 * @return type : boolean 返回操作是否成功
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public boolean recover(List ids, String modifier) throws Exception;

    /**
     * @param params 与数据库中记录对应的值对象
     * @return type : Page 返回满足条件的记录集，操作失败返回null
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes" })
    public List getListByParamMap(Map params) throws Exception;

    /**
     * 批量更新状态
     * @return type : Page 返回满足条件的记录集，操作失败返回null
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes" })
    public boolean modifyStatus(List<Long> list) throws Exception;

    /**
     * 计量、计费
     * @return type
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes" })
    public boolean meteAndCharge(OrdResultFile ordResultFile) throws Exception;

    /**
     * 将工单返回文件表中的记录转移到历史表中
     * @param ordResultFile
     * @param dealFlag
     * @param dealRemark
     */
    public void moveToHis(OrdResultFile ordResultFile,String dealFlag,String dealRemark);



}
