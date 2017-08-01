/**
 * com.sunrise.wss.bo.SysLoginSmsService.java
 */
package com.tydic.dbs.system.SysLoginSms.service;

import java.util.List;
import java.util.Map;

import com.tydic.commons.utils.Page;
import com.tydic.dbs.system.SysLoginSms.mapper.SysLoginSms;


/**
 * 
 * @ClassName: SysLoginSmsService 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author Michael dengwenjie@tydic.com 
 * @date 2016-7-20 下午4:23:09 
 *
 */
public abstract interface SysLoginSmsService {

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
	public SysLoginSms get(String pkId) throws Exception;
	
	/**
     * 向数据库中插入一条记录
     * @param vo 与数据库中记录对应的值对象
     * @return type : 返回已保存或者已更新的VO对象
     * @throws Exception
     */
    public SysLoginSms save(SysLoginSms vo) throws Exception;
    
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
	
	/***
	 * 
	 * @Title: isRightSms 
	 * @Description: TODO(验证短信验证码是否正确) 
	 * @param @param mobile
	 * @param @param smsCode
	 * @param @param type
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return boolean    返回类型 
	 * @throws
	 */
	boolean isRightSms(String mobile,String smsCode,Integer type) throws Exception;
}
