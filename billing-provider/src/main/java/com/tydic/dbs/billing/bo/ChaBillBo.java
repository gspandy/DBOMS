/**
 * com.sunrise.accout.bo.ChaBillBo.java
 */
package com.tydic.dbs.billing.bo;

import java.util.*;

import com.tydic.dbs.billing.dao.BillingListDao;
import com.tydic.dbs.billing.vo.BillVo;
import org.apache.commons.lang.StringUtils;

import com.tydic.commons.utils.Page;
import com.tydic.dbs.billing.dao.ChaBillDao;
import com.tydic.dbs.billing.service.ChaBillService;
import com.tydic.dbs.billing.vo.ChaBill;
import com.tydic.dbs.commons.define.WcsCommonStatus;
import org.apache.log4j.Logger;

/**
 * 
 * @ClassName: ChaBillBo 
 * @Description: TODO(月账单BO) 
 * @author Michael dengwenjie@tydic.com 
 * @date 2016-8-8 下午3:31:48 
 *
 */
public class ChaBillBo implements ChaBillService {
	
	private ChaBillDao chaBillDao;

	private BillingListDao billingListDao;

	private Logger logger = Logger.getLogger(this.getClass());

    /**
	 * @param chaBillDao the chaBillDao to set
	 */
	public void setChaBillDao(ChaBillDao chaBillDao) {
		this.chaBillDao = chaBillDao;
	}

	public void setBillingListDao(BillingListDao billingListDao) {
		this.billingListDao = billingListDao;
	}

	/**
	 * 查找所有数据库记录
     * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
     */
	@SuppressWarnings({ "rawtypes" })
    public List getAll() throws Exception {
		return chaBillDao.selectAll();
	}
	
	/**
     * 查找符合条件的所有数据库记录
     * @param vo 与数据库中记录对应的值对象
     * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
     * @throws Exception
     */
	@SuppressWarnings({ "rawtypes" })
    public List get(ChaBill vo) throws Exception{
		if(vo == null) return null;
        return chaBillDao.selectByVo(vo);
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
		return chaBillDao.queryForPage(params);
	}
	
    /**
     * 查找符合条件的数据库记录
     * @param pkId 与数据库中主键对应的值
     * @return type : 返回查询操作符合条件的记录的VO对象，操作失败返回null
     * @throws Exception
     */
	public ChaBill get(String pkId) throws Exception{
		if(StringUtils.isEmpty(pkId)) return null;
     	return chaBillDao.selectByPk(pkId);
	}
	
	/**
     * 向数据库中插入一条记录
     * @param vo 与数据库中记录对应的值对象
     * @return type : RpDir 返回插入操作是否成功
     * @throws Exception
     */
    public ChaBill save(ChaBill vo) throws Exception{
    	if(vo == null) return null;		
		if(vo.getBillId()!=null) {
			return chaBillDao.updateByVo(vo);
		} else {
			return chaBillDao.insertByVo(vo);
		}
    }
    
    /**
     * 删除符合条件的数据库记录
     * @param pkId 与数据库中主键对应的值
     * @return type : boolean 返回删除操做是否成功，操作失败返回false
     */
    public boolean delete(String pkId) throws Exception{
    	if(StringUtils.isEmpty(pkId)) return false;
    	return chaBillDao.deleteByPk(pkId);
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
    	return chaBillDao.updateStatus(params);
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
    	return chaBillDao.updateStatus(params);
	}

	@SuppressWarnings({ "rawtypes" })
	public Page getPagesByParamMap(Map params) throws Exception {
		if (params == null) return null;
		return chaBillDao.queryForPages(params);
	}


	@SuppressWarnings("rawtypes")
	public Page getPageByParamsMap(Map params) throws Exception {
		return chaBillDao.selectListByParamMap(params);
	}


	@SuppressWarnings("rawtypes")
	public double getPageByParamsSum(Map params) throws Exception {
		return chaBillDao.getPageByParamsSum(params);
	}


	
	@SuppressWarnings("rawtypes")
	public List selectByMap(Map map) throws Exception {
		if(map==null) return null;
		return chaBillDao.selectByMap(map);
	}

	@SuppressWarnings("rawtypes")
	public List selectMBDByMap(Map map) throws Exception {
		if(map==null) return null;
		return chaBillDao.selectMBDByMap(map);
	}

	/**
	 * 根据月份删除当月的账单数据
	 * @param monthId
	 * @return
	 * @throws Exception
	 */
	public boolean statMonthBill(String monthId) throws Exception{
		if(StringUtils.isEmpty(monthId)) return false;
		//查询计费清单表并统计指定月份账单数据
 		List<BillVo> billVoList = billingListDao.selectBillByMonth(monthId);
		if(billVoList ==null || billVoList.size()==0){
			logger.error(monthId+"月份统计账单为空！");
			return false;
		}
		chaBillDao.deleteByMonthId(monthId);
		List<Map<String,Object>> list = new ArrayList<>();
		for(BillVo b:billVoList){
			Map<String,Object> billMap =new HashMap<>();
			billMap.put("monthId",b.getMonthId());
			billMap.put("bussId",b.getBussId());
			billMap.put("consumTotal",b.getConsumTotal());
			billMap.put("payTotal",b.getPayTotal());

			list.add(billMap);
		}
		chaBillDao.addBathch(list);
		return false;

	}

}
