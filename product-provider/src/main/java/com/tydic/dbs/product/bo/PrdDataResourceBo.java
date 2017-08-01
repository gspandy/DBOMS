/**
 * com.tydic.dbs.bo.PrdDataResourceBo.java
 */
package com.tydic.dbs.product.bo;

import java.util.*;

import org.apache.commons.lang.StringUtils;

import com.tydic.commons.utils.Page;
import com.tydic.dbs.commons.define.WcsCommonStatus;
import com.tydic.dbs.product.dao.PrdDataResourceDao;
import com.tydic.dbs.product.service.PrdDataResourceService;
import com.tydic.dbs.product.vo.PrdDataResource;

/**
 * 
 * @ClassName: PrdDataResourceBo 
 * @Description: TODO(产品数据服务) 
 * @author huangChuQin
 * @date 2016-8-3 下午8:11:15 
 *
 */
public class PrdDataResourceBo implements PrdDataResourceService {
	
	private PrdDataResourceDao prdDataResourceDao;
	

    /**
	 * @param prdDataResourceDao the prdDataResourceDao to set
	 */
	public void setPrdDataResourceDao(PrdDataResourceDao prdDataResourceDao) {
		this.prdDataResourceDao = prdDataResourceDao;
	}


	/**
	 * 查找所有数据库记录
     * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
     */
	@SuppressWarnings({ "rawtypes" })
    public List getAll() throws Exception {
		return prdDataResourceDao.selectAll();
	}
	
	/**
     * 查找符合条件的所有数据库记录
     * @param vo 与数据库中记录对应的值对象
     * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
     * @throws Exception
     */
	@SuppressWarnings({ "rawtypes" })
    public List get(PrdDataResource vo) throws Exception{
		if(vo == null) return null;
        return prdDataResourceDao.selectByVo(vo);
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
		return prdDataResourceDao.queryForPage(params);
	}
	
    /**
     * 查找符合条件的数据库记录
     * @param pkId 与数据库中主键对应的值
     * @return type : 返回查询操作符合条件的记录的VO对象，操作失败返回null
     * @throws Exception
     */
	public PrdDataResource get(String pkId) throws Exception{
		if(StringUtils.isEmpty(pkId)) return null;
     	return prdDataResourceDao.selectByPk(pkId);
	}
	
	/**
     * 向数据库中插入一条记录
     * @param vo 与数据库中记录对应的值对象
     * @return type : RpDir 返回插入操作是否成功
     * @throws Exception
     */
    public PrdDataResource save(PrdDataResource vo) throws Exception{
		if(vo == null){
			return null;
		}
		PrdDataResource old = prdDataResourceDao.selectByPk(vo.getDataResourceId());
		if(old ==null){
			vo.setCreateTime(new Date());
			prdDataResourceDao.insertByVo(vo);
		}else{
			prdDataResourceDao.updateByVoNotNull(vo);
		}
		return vo;
    }
    
    /**
     * 删除符合条件的数据库记录
     * @param pkId 与数据库中主键对应的值
     * @return type : boolean 返回删除操做是否成功，操作失败返回false
     */
    public boolean delete(String pkId) throws Exception{
    	if(StringUtils.isEmpty(pkId)) return false;
    	return prdDataResourceDao.deleteByPk(pkId);
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
    	return prdDataResourceDao.updateStatus(params);
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
    	return prdDataResourceDao.updateStatus(params);
	}
}
