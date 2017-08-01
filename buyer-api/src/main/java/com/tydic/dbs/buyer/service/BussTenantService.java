/**
 * com.sunrise.wcs.bo.BussTenantService.java
 */
package com.tydic.dbs.buyer.service;

import java.util.List;
import java.util.Map;

import com.tydic.commons.utils.Page;
import com.tydic.dbs.buyer.vo.BussTenant;
import com.tydic.dbs.buyer.vo.DataPemissionDict;

/**
 * @file  BussTenantService.java
 * @author Carson
 * @version 0.1
 * @BussTenant业务操作接口
 * Copyright(C), 2013-2014
 *		 Guangzhou tydic Technology Co., Ltd.
 * History
 *   	1. Date: 2016-07-12 06:58:33
 *      	Author: Carson
 *      	Modification: this file was created
 *   	2. ...
 */
public  interface BussTenantService {

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
	public BussTenant get(String pkId) throws Exception;
	
	/**
     * 向数据库中插入一条记录
     * @param vo 与数据库中记录对应的值对象
     * @return type : 返回已保存或者已更新的VO对象
     * @throws Exception
     */
    public BussTenant save(BussTenant vo) throws Exception;

    /**
     * 修改租户权限信息
     * @param vo
     * @return
     * @throws Exception
     */
    public BussTenant modify(BussTenant vo) throws Exception;
    
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

	public  List getBussTenant(Map map)throws  Exception;

	/**
	 * 获取数据权限列表
	 * @return
	 * @throws Exception
     */
	public List<DataPemissionDict> getDataPemissionDict()throws Exception;

	/**
	 *
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public BussTenant syncTenantRoleInfo(BussTenant vo) throws Exception;
}
