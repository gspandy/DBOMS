/**
 * com.sunrise.accout.dao.ChaBillDao.java
 */
package com.tydic.dbs.billing.dao;

import java.util.List;
import java.util.Map;

import com.tydic.dbs.billing.vo.BillVo;
import org.apache.commons.lang.StringUtils;

import com.tydic.commons.dao.BaseDao;
import com.tydic.commons.utils.Page;
import com.tydic.dbs.billing.vo.ChaBill;

/**
 * 
 * @ClassName: ChaBillDao 
 * @Description: TODO(月账单DAO) 
 * @author Michael dengwenjie@tydic.com 
 * @date 2016-8-8 下午3:31:13 
 *
 */
public class ChaBillDao extends BaseDao {	
    /**
     * 分页查询
     * @param sqlDef 预定义SQL名称, params params{属性名:属性值, currPage:当前页面数, pageSize:每页最大行数, orderBy:List<Map<属性名:升序或者降序>>, nopaging:boolean是否分页}
     * @return type : Page 返回一个分页的List，操作失败返回null
	 * @throws Exception
     */
	@SuppressWarnings("rawtypes")
	public Page queryForPage(String sqlDef, Map params) throws Exception {
		return queryForPage(sqlDef, params, null);
	}
	
    /**
     * 分页查询
     * @param params params{属性名:属性值, currPage:当前页面数, pageSize:每页最大行数, orderBy:List<Map<属性名:升序或者降序>>, nopaging:boolean是否分页}
     * @return type : Page 返回一个分页的List，操作失败返回null
	 * @throws Exception
     */
	@SuppressWarnings("rawtypes")
	public Page queryForPage(Map params) throws Exception {
		return queryForPage("CHA_BILL.selectByParams", params, null);
	}
	
    /**
	 * 对数据库进行查询并返回一个VO列表
	 * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes" })
	public List selectAll() throws Exception {
		return queryForList("CHA_BILL.selectAll", null);
	}
	
	/**
     * 在本方法中组装出一个Map{[数据库列名:值],[数据库列名:值]}对象调用myBatis相关方法
	 * @param valueOne 需要进行查询的值。
	 * @param valueTwo 需要进行查询的值。
	 * @param valueThree 需要进行查询的值。
	 * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List selectByParamMap(Map paramMap) throws Exception {
	    return queryForList("CHA_BILL.selectByParams", paramMap);
	}
	
	/**
     * 在本方法中组装出一个Map{[数据库列名:值],[数据库列名:值]}对象调用myBatis相关方法
	 * @param valueOne 需要进行查询的值。
	 * @param valueTwo 需要进行查询的值。
	 * @param valueThree 需要进行查询的值。
	 * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public Page selectListByParamMap(Map paramMap) throws Exception {
	    return queryForPage("CHA_BILL.selectListByParams", paramMap, null);
	}
	
	/**
	 * 搜索数据库中所有与对象对应的记录
	 * @param vo 对象对应到数据库中的一条记录
	 * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List selectByVo(ChaBill vo) throws Exception {
	    if(vo == null) return null;
	    return queryForList("CHA_BILL.selectByVO", vo);
	}
    
    /**
     * 搜索数据库中与传入的主键对应的记录
     * @param pkid 与数据库主键对应
     * @return type : 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
	 * @throws Exception
     */
	public ChaBill selectByPk(String pkId) throws Exception {
    	if(StringUtils.isEmpty(pkId)) return null;
    	return (ChaBill)queryForObject("CHA_BILL.selectByPk", pkId);
    }
    
	/**
	 * 向数据库中插入一个VO对象
	 * @param vo 对象对应到数据库中的一条记录
	 * @return type : 
	 * @throws Exception
	 */
	public ChaBill insertByVo(ChaBill vo) throws Exception {
	    if(vo == null) return null;
	    return (ChaBill)insert("CHA_BILL.insertByVo", vo);
    }
	
    /**
	 * 更新数据库中对象对应的记录
	 * @param vo 对象对应到数据库中的一条记录记录
	 * @return type : 
	 * @throws Exception
	 */
	public ChaBill updateByVo(ChaBill vo) throws Exception {
		if(vo == null) return null;
		update("CHA_BILL.updateByVO", vo);
		return vo;
	}
	
    /**
	 * 更新数据库中对象对应的记录
	 * @param vo 对象对应到数据库中的一条记录记录
	 * @return type : 
	 * @throws Exception
	 */
	public ChaBill updateByVoNotNull(ChaBill vo) throws Exception {
		if(vo == null) return null;
		update("CHA_BILL.updateByVoNotNull", vo);
		return vo;
	}
	
    /**
	 * 更新数据库中对象的状态
	 * @param map: <ids: Id列表>, <status: 状态>, <modifier: 修改者>
	 * @return type : boolean 返回操作是否成功
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
    public boolean updateStatus(Map params) throws Exception {
    	if(params == null) return false;
    	getSqlSession().update("CHA_BILL.updateStatus", params);
    	return true;
    }
	   
	/**
	 * 删除数据库中所有记录
	 * @return type : boolean 返回删除操作是否成功
	 * @throws Exception
	 */
	public boolean deleteAll() throws Exception {
		return delete("CHA_BILL.deleteAll", null) > 0?true:false;
	}
	

	/**
	 * 删除数据库中与对象对应的记录
	 * @param vo 对象对应到数据库中的一条记录
	 * @return type : boolean 返回删除操作是否成功
	 * @throws Exception
	 */
	public boolean deleteByVo(ChaBill vo) throws Exception {
		if(vo == null) return false;
		int i = delete("CHA_BILL.deleteByVO", vo);
		return i==0?false:true;
	}
	
    /**
	 * 删除数据库中与传入的主键对应的一条记录
	 * @param pkid 与数据库主键对应
	 * @return type : boolean 返回删除操作是否成功,如果传入参数为空则返回false,如果操作失败则抛出Exception异常
	 * @throws Exception
	 */
    public boolean deleteByPk(String pkId) throws Exception {
		if(StringUtils.isEmpty(pkId)) return false;
		delete("CHA_BILL.deleteByPk", pkId);
		return true;
	}
    
    /**
     * 分页查询
     * @param params params{属性名:属性值, currPage:当前页面数, pageSize:每页最大行数, orderBy:List<Map<属性名:升序或者降序>>, nopaging:boolean是否分页}
     * @return type : Page 返回一个分页的List，操作失败返回null
	 * @throws Exception
     */
	@SuppressWarnings("rawtypes")
	public Page queryForPages(Map params) throws Exception {
		return queryForPage("CHA_BILL.selectListByParams", params, null);
	}

    
    /**
     * 
     * @Title: getPageByParamsSum 
     * @Description: TODO(统计总金额) 
     * @param @param params
     * @param @return
     * @param @throws Exception    设定文件 
     * @return double    返回类型 
     * @throws
     */
	@SuppressWarnings("rawtypes")
	public double getPageByParamsSum(Map params) throws Exception {
		return getSqlSession().selectOne("CHA_BILL.selectListByParamsSum",params);
	}
	
	@SuppressWarnings("rawtypes")
	public List selectByMap(Map map)throws Exception{
		return queryForList("CHA_BILL.getChaBillbyMap",map);
	}
	
	@SuppressWarnings("rawtypes")
	public List selectMBDByMap(Map map)throws Exception{
		return queryForList("CHA_BILL.getChaBillDetailbyMap",map);
	}

	/**
	 * 根据月份删除当月的账单数据
	 * @param monthId
	 * @return
	 * @throws Exception
	 */
	public boolean deleteByMonthId(String monthId) throws Exception {
		if(StringUtils.isEmpty(monthId)) return false;
		delete("CHA_BILL.deleteByMonthId", monthId);
		return true;
	}

	/**
	 * 批量保存当月的账单数据
	 * @return
	 * @throws Exception
	 */
	public boolean addBathch(List<Map<String,Object>> billVoList) throws Exception {
		if(billVoList==null||billVoList.size()==0) return false;
		//insert("CHA_BILL.addBatch", billVoList);
		getSqlSession().insert("CHA_BILL.addBatch", billVoList);
		return true;
	}
}
