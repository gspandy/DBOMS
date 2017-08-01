/**
 * com.sunrise.ord.bo.OrdInfoBo.java
 */
package com.tydic.dbs.order.bo;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.tydic.commons.utils.OrderUtils;
import com.tydic.commons.utils.Page;
import com.tydic.dbs.commons.define.WcsCommonStatus;
import com.tydic.dbs.order.dao.OrdInfoDao;
import com.tydic.dbs.order.dao.OrdLogDao;
import com.tydic.dbs.order.dao.OrdPrdDao;
import com.tydic.dbs.order.dao.OrdRusultDao;
import com.tydic.dbs.order.service.OrdInfoService;
import com.tydic.dbs.order.vo.OrdInfo;
import com.tydic.dbs.order.vo.OrdInfoVo;
import com.tydic.dbs.order.vo.OrdLog;
import com.tydic.dbs.order.vo.OrdPrd;
import com.tydic.dbs.order.vo.OrdRusult;

/**
 * 
 * @ClassName: OrdInfoBo 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author Michael dengwenjie@tydic.com 
 * @date 2016-7-25 下午2:09:39 
 *
 */
public class OrdInfoBo implements OrdInfoService {
	
	private OrdInfoDao ordInfoDao;
	private OrdPrdDao ordPrdDao;
	private OrdRusultDao ordRusultDao;
	
	private OrdLogDao ordLogDao;
    /**
	 * @param ordInfoDao the ordInfoDao to set
	 */
	public void setOrdInfoDao(OrdInfoDao ordInfoDao) {
		this.ordInfoDao = ordInfoDao;
	}
	public void setOrdPrdDao(OrdPrdDao ordPrdDao) {
		this.ordPrdDao = ordPrdDao;
	}
	public void setOrdRusultDao(OrdRusultDao ordRusultDao) {
		this.ordRusultDao = ordRusultDao;
	}

	public void setOrdLogDao(OrdLogDao ordLogDao) {
		this.ordLogDao = ordLogDao;
	}

	/**
	 * 查找所有数据库记录
     * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
     */
	@SuppressWarnings({ "rawtypes" })
    public List getAll() throws Exception {
		return ordInfoDao.selectAll();
	}
	
	/**
     * 查找符合条件的所有数据库记录
     * @param vo 与数据库中记录对应的值对象
     * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
     * @throws Exception
     */
	@SuppressWarnings({ "rawtypes" })
    public List get(OrdInfo vo) throws Exception{
		if(vo == null) return null;
        return ordInfoDao.selectByVo(vo);
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
		return ordInfoDao.queryForPage(params);
	}
	
    /**
     * 查找符合条件的数据库记录
     * @param pkid 与数据库中主键对应的值
     * @return type : 返回查询操作符合条件的记录的VO对象，操作失败返回null
     * @throws Exception
     */
	public OrdInfo get(String pkId) throws Exception{
		if(StringUtils.isEmpty(pkId)) return null;
     	return ordInfoDao.selectByPk(pkId);
	}
	
	/**
     * 向数据库中插入一条记录
     * @param vo 与数据库中记录对应的值对象
     * @return type : RpDir 返回插入操作是否成功
     * @throws Exception
     */
    public OrdInfo save(OrdInfo vo) throws Exception{
    	if(vo == null) return null;		
		if(vo.getOrdId()!=null) {
			return ordInfoDao.updateByVo(vo);
		} else {
			return ordInfoDao.insertByVo(vo);
		}
    }
    
    /**
     * 删除符合条件的数据库记录
     * @param pkid 与数据库中主键对应的值
     * @return type : boolean 返回删除操做是否成功，操作失败返回false
     */
    public boolean delete(String pkId) throws Exception{
    	if(StringUtils.isEmpty(pkId)) return false;
    	return ordInfoDao.deleteByPk(pkId);
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
    	return ordInfoDao.updateStatus(params);
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
    	return ordInfoDao.updateStatus(params);
	}

	/**
     * 查找符合条件的数据库记录
     * @param pkid 与数据库中主键对应的值
     * @return type : 返回查询操作符合条件的记录的VO对象，操作失败返回null
     * @throws Exception
     */	
	public OrdInfoVo getByPkId(String pkId) throws Exception {
		if(StringUtils.isEmpty(pkId)) return null;
     	return (OrdInfoVo)ordInfoDao.selectByPkLeftIn(pkId);
	}
	
	public boolean updateByOrdId(String ordId,String bussId)throws Exception{
		if(StringUtils.isEmpty(ordId)) return false;
		OrdLog ordLog=new OrdLog();
		ordLog.setOrdLogId(OrderUtils.generateOutTradeNo());
		ordLog.setOrdId(ordId);
		ordLog.setOrdLogTime(Calendar.getInstance().getTime());
		ordLog.setOrdLogType("4");
		ordLog.setOrdLogMemo("提交工单");
		ordLog.setOrdLogUser(bussId);
		boolean temp=ordInfoDao.updateByOrdId(ordId,bussId);
		ordLogDao.insertByVo(ordLog);
		return temp;
	}

	@Override
	public Page getPageByMap(Map map) throws Exception {
		if (map == null) return null;
		return ordInfoDao.queryForPageMap(map);
	}
	public List selectByMap(Map map)throws Exception{
		if(map==null) return null;
		return ordInfoDao.selectByMap(map);
	}
	
	/**
	 * 
	 * @Title: saveStatus 
	 * @Description: TODO(通知接口更新工单状态) 
	 * @param @param vo
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return OrdInfoVo    返回类型 
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public OrdInfoVo saveStatus(OrdInfoVo vo) throws Exception {
		if(vo == null) return null;
		
		OrdInfo ordInfo = new OrdInfo();
		ordInfo.setOrdId(vo.getOrdId());
		ordInfo.setBusId(vo.getBusId());
		ordInfo.setOrdStatus(vo.getOrdStatus());
//		ordInfo.setModifyTime(vo.getModifyTime());//暂时不需要修改工单时间
		ordInfo = ordInfoDao.updateByVo(ordInfo);
		if (ordInfo==null) {
			return null;
		}
		
		OrdPrd ordPrd = new OrdPrd();
		ordPrd.setOrdId(vo.getOrdId());
		List<OrdPrd> ordPrdList = ordPrdDao.selectByVo(ordPrd);
		ordPrd = ordPrdList.get(0);
		if (ordPrd==null) {
			return null;
		}
		
		OrdRusult ordRusult = new OrdRusult();
		ordRusult.setOrdPrdId(ordPrd.getOrdPrdId());
		ordRusult.setFileName(vo.getFileName());
		ordRusult.setFilePath(vo.getFilePath());
		ordRusult.setRunTime(vo.getModifyTime());
		ordRusult = ordRusultDao.insertByVo(ordRusult);
		if (ordRusult==null) {
			return null;
		}
		
		return vo;
	}

	/**
	 * 更新工单状态
	 * @param vo
	 * @return
	 */
	public OrdInfo update(OrdInfo vo) throws Exception {
		if(vo ==null){
			return null;
		}
		return ordInfoDao.updateByVoNotNull(vo);
	}


}

