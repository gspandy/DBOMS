/**
 * com.sunrise.pro.bo.PrdInfoBo.java
 */
package com.tydic.dbs.product.bo;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tydic.dbs.product.service.PrdInfoService;
import com.tydic.dbs.product.vo.PrdDataResource;
import com.tydic.dbs.product.vo.PrdInfo;

import com.tydic.commons.utils.Page;
import com.tydic.dbs.commons.define.WcsCommonStatus;
import com.tydic.dbs.product.dao.PrdInfoDao;
import com.tydic.commons.utils.OrderUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 
 * @ClassName: PrdInfoBo 
 * @Description: TODO(产品业务操作接口实现类) 
 * @author huangChuQin
 * @date 2016-7-15 下午6:07:07 
 *
 */
public class PrdInfoBo implements PrdInfoService {
	
	private PrdInfoDao prdInfoDao;
	

    /**
	 * @param prdInfoDao the prdInfoDao to set
	 */
	public void setPrdInfoDao(PrdInfoDao prdInfoDao) {
		this.prdInfoDao = prdInfoDao;
	}


	/**
	 * 查找所有数据库记录
     * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
     */
	@SuppressWarnings({ "rawtypes" })
    public List getAll() throws Exception {
		return prdInfoDao.selectAll();
	}
	
	/**
     * 查找符合条件的所有数据库记录
     * @param vo 与数据库中记录对应的值对象
     * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
     * @throws Exception
     */
	@SuppressWarnings({ "rawtypes" })
    public List get(PrdInfo vo) throws Exception{
		if(vo == null) return null;
        return prdInfoDao.selectByVo(vo);
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
		return prdInfoDao.queryForPage(params);
	}

    /**
     * 分页查找
     * @param params 与数据库中记录对应的值对象
     * @return type : Page 返回满足条件的记录集，操作失败返回null
     * @throws Exception
     */
	@SuppressWarnings({ "rawtypes" })
    public Page getPageByParamBussMap(Map params) throws Exception{
		if (params == null) return null;
		return prdInfoDao.queryForPageBuss(params);
	}
	
    /**
     * 查找符合条件的数据库记录
     * @param pkid 与数据库中主键对应的值
     * @return type : 返回查询操作符合条件的记录的VO对象，操作失败返回null
     * @throws Exception
     */
	public PrdInfo get(String pkId) throws Exception{
		if(pkId==null) return null;
     	return prdInfoDao.selectByPk(pkId);
	}
	
	/**
     * 向数据库中插入一条记录
     * @param vo 与数据库中记录对应的值对象
     * @return type : RpDir 返回插入操作是否成功
     * @throws Exception
     */
    @SuppressWarnings("static-access")
	public PrdInfo save(PrdInfo vo) throws Exception{
    	if(vo == null) return null;		
		if(vo.getPrdId()!=null&&!"".equals(vo.getPrdId())) {
			return prdInfoDao.updateByVo(vo);
		} else {
			vo.setPrdId(new OrderUtils().getProId());
			return prdInfoDao.insertByVo(vo);
		}
    }
    
    /**
     * 删除符合条件的数据库记录
     * @param pkid 与数据库中主键对应的值
     * @return type : boolean 返回删除操做是否成功，操作失败返回false
     */
    public boolean delete(String pkId) throws Exception{
    	if(pkId == null) return false;
    	return prdInfoDao.deleteByPk(pkId);
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
    	return prdInfoDao.updateStatus(params);
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
    	return prdInfoDao.updateStatus(params);
	}


	/* (非 Javadoc) 
	 * <p>Title: countWithStatus</p> 
	 * <p>Description: </p> 
	 * @param status
	 * @return
	 * @throws Exception 
	 * @see com.tydic.dbs.product.service.PrdInfoService#countWithStatus(java.lang.String) 
	 */
	@Override
	public int countWithStatus(String status) throws Exception {
		if (status!=null&&!"".equals(status)) {
			return prdInfoDao.countWithStatus(status);
		}
		return 0;
	}

	public PrdDataResource getResouceByPrdId(String prdId) throws Exception{
		if(StringUtils.isEmpty(prdId)) return null;
		return prdInfoDao.getResouceByPrdId(prdId);
	}

	public Page selectPrdByParam(Map map)throws Exception{
		if (map==null)return null;
		return  prdInfoDao.selectPrdByParam(map);
	}
	public Page selectPrdByParams(Map map)throws Exception{
		if (map==null)return null;
		return  prdInfoDao.selectPrdByParams(map);
	}
}