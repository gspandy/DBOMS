/**
 * com.tydic.commons.dao.ExtendDao.java
 */
package com.tydic.commons.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tydic.commons.utils.BaseVO;
import com.tydic.commons.utils.Page;
import com.tydic.commons.utils.Utils;

/**
* @file  ExtendDao.java
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
public abstract class ExtendDao extends BaseDao {

	public abstract String getNameSpace();

	/**
			 * 生成一个主键
			 * @return
			 * @throws Exception
			 */
	public String getPKValue() throws Exception {
		return Utils.getUUID();
	}

	/**
	 * 配置文件中编号：(1)
	 * 对数据库进行查询并返回一个VO数组
	 * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes" })
	public List selectAll() throws Exception {
		return queryForList(getNameSpace() + ".selectAll", null);
	}

	/**
	 * 配置文件中编号：(2)
	 * 在本方法中组装出一个Map{[数据库列名:值],[数据库列名:值]}对象调用myBatis相关方法
	 * @param valueOne 需要进行查询的值。
	 * @param valueTwo 需要进行查询的值。
	 * @param valueThree 需要进行查询的值。
	 * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List selectByParamMap(Map paramMap) throws Exception {

		return getSqlSession().selectList(getNameSpace() + ".selectByParams",
				paramMap);
	}

	/**
	 * 配置文件中编号：(3)
	 * 搜索数据库中所有与对象对应的记录
	 * @param vo 对象对应到数据库中的一条记录
	 * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List selectByVo(BaseVO vo) throws Exception {
		if (vo == null)
			return null;
		return queryForList(getNameSpace() + ".selectByVO", vo);
	}

	/**
	 * 配置文件中编号：(3-2)
	 * 实现翻页
	 * @param condition condition{Vo:条件VO对象，currPage:当前页面数，pageSize:每页最大行数}
	 * @return type : Page 返回一个分页的List，操作失败返回null
	 * @throws Exception
	 */
	public Page selectForPage(@SuppressWarnings("rawtypes") Map condition)
			throws Exception {
		if (condition == null)
			return null;
		int pageIndex = ((Integer) condition.get("currPage")).intValue();
		int perPageSize = ((Integer) condition.get("pageSize")).intValue();
		String sqlStr = "";
		BaseVO vo = (BaseVO) condition.get("Vo");
		if (vo == null) {
			sqlStr = getNameSpace() + ".selectAll";
		} else {
			sqlStr = getNameSpace() + ".selectByVO";
		}
		return queryForPage(sqlStr, vo, pageIndex, perPageSize);
	}

	/**
	* 配置文件中编号：(3-3)
	* 实现翻页
	* @param {Vo:条件VO对象，currPage:当前页面数，pageSize:每页最大行数}
	* @return type : Page 返回一个分页的List，操作失败返回null
	* @throws Exception
	*/
	public Page selectForPage(BaseVO vo, int pageIndex, int perPageSize)
			throws Exception {
		String sqlStr = "";
		if (vo == null) {
			sqlStr = getNameSpace() + ".selectAll";
		} else {
			sqlStr = getNameSpace() + ".selectByVO";
		}
		return queryForPage(sqlStr, vo, pageIndex, perPageSize);
	}

	/**
	 * 配置文件中编号：(3-3)
	 * 搜索数据库中与传入的主键对应的记录
	 * @param pkid 与数据库主键对应
	 * @return type : RpDir 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
	 * @throws Exception
	 */
	public BaseVO selectByPk(String pkid) throws Exception {
		if (pkid == null)
			return null;
		return (BaseVO) getSqlSession().selectOne(
				getNameSpace() + ".selectByPk", pkid);
	}

	/**
	 * 配置文件中编号：(4)
	 * 向数据库中插入一个VO对象
	 * @param vo 对象对应到数据库中的一条记录
	 * @return type : RpDir 返回操作是否成功
	 * @throws Exception
	 */
	public BaseVO insertByVo(BaseVO vo) throws Exception {
		if (vo == null)
			return null;
		return (BaseVO) insert(getNameSpace() + ".insertByVo", (BaseVO) vo);
	}

	/**
	 * 配置文件中编号：(5)
	 * 更新数据库中对象对应的记录
	 * @param vo 对象对应到数据库中的一条记录记录
	 * @return type : RpDir 返回修改操作参数
	 * @throws Exception
	 */
	public BaseVO updateByVo(BaseVO vo) throws Exception {
		if (vo == null)
			return null;
		update(getNameSpace() + ".updateByVO", vo);
		return vo;
	}

	/**
	 * 配置文件中编号：(6)
	 * 删除数据库中所有记录
	 * @return type : boolean 返回删除操作是否成功
	 * @throws Exception
	 */
	public boolean deleteAll() throws Exception {
		return getSqlSession().delete(getNameSpace() + ".deleteAll", null) > 0 ? true
				: false;
	}

	/**
	 * 配置文件中编号：(7)
	 * 在本方法中组装出一个Map{[数据库列名:值],[数据库列名:值]}对象调用myBatis相关方法
	 * @param valueOne 需要进行删除的值。
	 * @param valueTwo 需要进行删除的值。
	 * @param valueThree 需要进行删除的值。
	 * @return type : boolean 删除所有符合条件的记录的VO对象集合，操作失败返回null
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public boolean deleteByParams(String valueOne, String valueTwo,
			String valueThree) throws Exception {
		@SuppressWarnings("rawtypes")
		Map Pmap = new HashMap();
		if (valueOne != null) {
			Pmap.put("列名1", valueOne);//键值为列名
		} else {
			Pmap.put("列名1", "{-0-}");//键值为列名
		}
		if (valueTwo != null) {
			Pmap.put("列名2", valueTwo);//键值为列名
		} else {
			Pmap.put("列名1", "{-0-}");//键值为列名
		}
		if (valueThree != null) {
			Pmap.put("列名3", valueThree);//键值为列名
		} else {
			Pmap.put("列名1", "{-0-}");//键值为列名
		}
		return getSqlSession().delete(getNameSpace() + ".deleteByParams", Pmap) > 0 ? true
				: false;
	}

	/**
	 * 配置文件中编号：(8)
	 * 删除数据库中与对象对应的记录
	 * @param vo 对象对应到数据库中的一条记录
	 * @return type : boolean 返回删除操作是否成功
	 * @throws Exception
	 */
	public boolean deleteByVo(BaseVO vo) throws Exception {
		if (vo == null)
			return false;
		int i = delete(getNameSpace() + ".deleteByVO", vo);
		return i == 0 ? false : true;
	}

	/**
	 * 配置文件中编号：(9)
	 * 删除数据库中与传入的主键对应的一条记录
	 * @param pkid 与数据库主键对应
	 * @return type : boolean 返回删除操作是否成功,如果传入参数为空则返回false,如果操作失败则抛出Exception异常
	 * @throws Exception
	 */
	public boolean deleteByPk(String pkid) throws Exception {
		if (pkid == null || "".equals(pkid))
			return false;
		getSqlSession().delete(getNameSpace() + ".deleteByPk", pkid);
		return true;
	}

}
