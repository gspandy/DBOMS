
package com.tydic.dbs.buyer.service;

import java.util.List;
import java.util.Map;

import com.tydic.commons.utils.Page;
import com.tydic.dbs.buyer.vo.BussAuditStatus;
import com.tydic.dbs.buyer.vo.BussDataPemission;
import com.tydic.dbs.buyer.vo.BussItResource;

/**
 * @file  BussDataPemissionService.java
 * @BussDataPemission数据权限操作接口
 * @author zjl
 * 2016-7-11
 */
public  interface BussDataPemissionService {

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
	public BussDataPemission get(String pkId) throws Exception;
	
	/**
     * 向数据库中插入一条记录
     * @param vo 与数据库中记录对应的值对象
     * @return type : 返回已保存或者已更新的VO对象
     * @throws Exception
     */
    public BussDataPemission save(BussDataPemission vo) throws Exception;
    
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
	 * 查询数据权限文件
	 * @param map
	 * @return
	 * @throws Exception
     */
	public  List selectPemission(Map map)throws Exception;

	/**
	 * 根据商户ID批量更新数据权限
	 * @param
	 * @return
	 * @throws Exception
	 */
	public boolean updateByBussId(String bussId,List<BussDataPemission> list) throws Exception;

	/**
	 * 批量更新数据权限
	 * @param
	 * @return
	 * @throws Exception
	 */
	public boolean updateBatch(List<BussDataPemission> list,BussAuditStatus auditStatus) throws Exception;

	/**
	 * 保存数据权限信息
	 * @param pemList
	 * @return
	 * @throws Exception
	 */
	public boolean saveDataPem(String bussId,List<BussDataPemission> pemList,boolean isModifyDataPem,BussItResource bussItResource) throws Exception;
}
