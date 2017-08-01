/**
 * com.tydic.commons.bo.ExtendBo.java
 */
package com.tydic.commons.bo;

import java.util.List;
import java.util.Map;

import com.tydic.commons.dao.ExtendDao;
import com.tydic.commons.utils.BaseVO;
import com.tydic.commons.utils.Page;

/**
* @file  ExtendBo.java
* @author zhangtian(Fattor)
* @version 0.1
* @todo	TODO
* Copyright(C), 2013-2014
*			Guangzhou Sunrise Electronics Development Co., Ltd.
* History
*   	1. Date: 2013-6-7
*      	Author: zhangtian(Fattor)
*      	Modification: this file was created
*   	2. ...
*/
public abstract class ExtendBo {

	public abstract ExtendDao getBaseDao();

	/*
	 * 
	 * 配置文件中编号：(1)
	 * 查找所有数据库记录
	 * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
	 */
	@SuppressWarnings({ "rawtypes" })
	public List getAll() throws Exception {
		return getBaseDao().selectAll();
	}

	/**
	 * 配置文件中编号：(2)
	 * @param valueOne 需要进行查询的值。
	 * @param valueTwo 需要进行查询的值。
	 * @param valueThree 需要进行查询的值。
	 * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes" })
	public List getByParamMap(Map paramMap) throws Exception {
		return getBaseDao().selectByParamMap(paramMap);
	}

	/**
	 * 配置文件中编号：(3)
	 * 查找符合条件的所有数据库记录
	 * @param vo 与数据库中记录对应的值对象
	 * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes" })
	public List get(BaseVO vo) throws Exception {
		if (vo == null)
			return null;
		return getBaseDao().selectByVo(vo);
	}

	/**
	 * 配置文件中编号：(3-2)
	 * 分页查找
	 * @param condition 与数据库中记录对应的值对象
	 * @return type : Page 返回满足条件的记录集，操作失败返回null
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public Page getPage(Map condition) throws Exception {
		if (condition == null)
			return null;
		return getBaseDao().selectForPage(condition);
	}

	/**
	 * 配置文件中编号：(3-3)
	 * 查找符合条件的所有数据库记录
	 * @param pkid 与数据库中主键对应的值
	 * @return type : RpDir 返回查询操作所有符合条件的记录的VO对象，操作失败返回null
	 * @throws Exception
	 */
	public BaseVO get(String pkid) throws Exception {
		if (pkid == null)
			return null;
		return getBaseDao().selectByPk(pkid);
	}

	/**
	 * 配置文件中编号：(4)
	 * 向数据库中插入一条记录
	 * @param vo 与数据库中记录对应的值对象
	 * @return type : RpDir 返回插入操作是否成功
	 * @throws Exception
	 */
	public BaseVO save(BaseVO vo) throws Exception {

		if (vo == null)
			return null;
		//		if (vo.getFcId() != null) {
		//			return getBaseDao().updateByVo(vo);
		//		}
		else {
			//	vo.setFcId(getBaseDao().getPKValue());
			return getBaseDao().insertByVo(vo);
		}
	}

	public BaseVO update(BaseVO vo) throws Exception {

		if (vo == null)
			return null;
		else {
			return getBaseDao().updateByVo(vo);
		}
	}

	/**
	 * 配置文件中编号：(5)
	 * 删除符合条件的所有数据库记录
	 * @param pkid 与数据库中主键对应的值
	 * @return type : boolean 返回删除操做是否成功，操作失败返回false
	 */
	public boolean remove(String pkid) throws Exception {
		if (pkid == null)
			return false;
		return getBaseDao().deleteByPk(pkid);
	}

	/**
	 * 配置文件中编号：(6)
	 * 从上层接受需要查询的值
	 * @param valueOne 需要进行查询的值。
	 * @param valueTwo 需要进行查询的值。
	 * @param valueThree 需要进行查询的值。
	 * @return type : boolean 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
	 * @throws Exception
	 */
	public boolean removeByParams(String valueOne, String valueTwo,
			String valueThree) throws Exception {
		return getBaseDao().deleteByParams(valueOne, valueTwo, valueThree);
	}

	/**
	 * 配置文件中编号：(7)
	 * 删除数据库中与传入的值对象对应的记录
	 * @param vo 与数据库中记录对应的值对象
	 * @return type : boolean 返回删除操作是否成功
	 * @throws Exception
	 */
	public boolean remove(BaseVO vo) throws Exception {
		if (vo == null)
			return false;
		return getBaseDao().deleteByVo(vo);
	}

}
