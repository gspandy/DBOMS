
package com.tydic.dbs.buyer.bo;

import java.util.*;

import com.tydic.dbs.buyer.dao.BussItResourceDao;
import com.tydic.dbs.buyer.service.BussAuditStatusService;
import com.tydic.dbs.buyer.service.BussItResourceService;
import com.tydic.dbs.buyer.vo.BussAuditStatus;
import com.tydic.dbs.buyer.vo.BussItResource;
import com.tydic.dbs.commons.enums.AuditStatus;
import com.tydic.dbs.commons.enums.AuditType;
import com.tydic.dbs.commons.enums.Status;
import org.apache.commons.lang.StringUtils;

import com.tydic.commons.utils.Page;
import com.tydic.dbs.buyer.dao.BussDataPemissionDao;
import com.tydic.dbs.buyer.service.BussDataPemissionService;
import com.tydic.dbs.buyer.vo.BussDataPemission;
import com.tydic.dbs.commons.define.WcsCommonStatus;
/**
 * @file  BussDataPemissionService.java
 * @BussDataPemission数据权限操作接口
 * @author zjl
 * 2016-7-11
 */
public class BussDataPemissionBo implements BussDataPemissionService {
	
	private BussDataPemissionDao bussDataPemissionDao;

	private BussAuditStatusService bussAuditStatusService;

	private BussItResourceService bussItResourceService;


    /**
	 * @param bussDataPemissionDao the bussDataPemissionDao to set
	 */
	public void setBussDataPemissionDao(BussDataPemissionDao bussDataPemissionDao) {
		this.bussDataPemissionDao = bussDataPemissionDao;
	}

	public void setBussAuditStatusService(BussAuditStatusService bussAuditStatusService) {
		this.bussAuditStatusService = bussAuditStatusService;
	}

	public void setBussItResourceService(BussItResourceService bussItResourceService) {
		this.bussItResourceService = bussItResourceService;
	}

	/**
	 * 查找所有数据库记录
     * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
     */
	@SuppressWarnings({ "rawtypes" })
    public List getAll() throws Exception {
		return bussDataPemissionDao.selectAll();
	}
	
	/**
     * 查找符合条件的所有数据库记录
     * @param vo 与数据库中记录对应的值对象
     * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
     * @throws Exception
     */
	@SuppressWarnings({ "rawtypes" })
    public List get(BussDataPemission vo) throws Exception{
		if(vo == null) return null;
        return bussDataPemissionDao.selectByVo(vo);
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
		return bussDataPemissionDao.queryForPage(params);
	}
	
    /**
     * 查找符合条件的数据库记录
     * @param pkId 与数据库中主键对应的值
     * @return type : 返回查询操作符合条件的记录的VO对象，操作失败返回null
     * @throws Exception
     */
	public BussDataPemission get(String pkId) throws Exception{
		if(StringUtils.isEmpty(pkId)) return null;
     	return bussDataPemissionDao.selectByPk(pkId);
	}
	
	/**
     * 向数据库中插入一条记录
     * @param vo 与数据库中记录对应的值对象
     * @return type : RpDir 返回插入操作是否成功
     * @throws Exception
     */
    public BussDataPemission save(BussDataPemission vo) throws Exception{
    	if(vo == null) return null;		
		if(vo.getPemissionId()!=null) {
			return bussDataPemissionDao.updateByVo(vo);
		} else {
			return bussDataPemissionDao.insertByVo(vo);
		}
    }
    
    /**
     * 删除符合条件的数据库记录
     * @param pkId 与数据库中主键对应的值
     * @return type : boolean 返回删除操做是否成功，操作失败返回false
     */
    public boolean delete(String pkId) throws Exception{
    	if(StringUtils.isEmpty(pkId)) return false;
    	return bussDataPemissionDao.deleteByPk(pkId);
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
    	return bussDataPemissionDao.updateStatus(params);
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
    	return bussDataPemissionDao.updateStatus(params);
	}

	/**
	 * 查询数据权限文件
	 * @param map
	 * @return
	 * @throws Exception
     */
	public  List selectPemission(Map map)throws Exception{
		if (null==map) return  null;
		return bussDataPemissionDao.selectByParamMap(map);
	}

	/**
	 * 根据商户ID批量更新数据权限
	 * @param list
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean updateByBussId(String bussId,List<BussDataPemission> list) throws Exception {
		if(bussId == null){
			return false;
		}
		if(list == null){
			return true;
		}

		Map<String,Object> param = new HashMap<>();
		param.put("bussId",bussId);
		List<String> appStatusList = new ArrayList();
		appStatusList.add(AuditStatus.PASS.getCode());
		appStatusList.add(AuditStatus.WAIT.getCode());
		param.put("appStatusIn", appStatusList);
		List<BussDataPemission> oldList = bussDataPemissionDao.selectByParamMap(param);
		if(oldList == null || oldList.size()==0){
			for (BussDataPemission b :list){
				bussDataPemissionDao.insertByVo(b);
			}
			return true;
		}

		List<BussDataPemission> addList = new ArrayList<>();
		List<BussDataPemission> modifyList = new ArrayList<>();
		List<BussDataPemission> delList = new ArrayList<>();

		for(BussDataPemission newPem:list){
			boolean isExists = false;
			for (BussDataPemission oldPem : oldList){
				if(oldPem.getPemissionId().equals(newPem.getPemissionId())){
					modifyList.add(newPem);
					isExists = true;
				}
			}
			if(!isExists){
				addList.add(newPem);
			}
		}

		for (BussDataPemission oldPem : oldList){
			boolean isExists = false;
			for(BussDataPemission newPem:list){
				if(oldPem.getPemissionId().equals(newPem.getPemissionId())){
					isExists =true;
				}
			}
			if(!isExists){
				delList.add(oldPem);
			}
		}
		//批量新增
		for (BussDataPemission b :addList){
			bussDataPemissionDao.insertByVo(b);
		}

		for (BussDataPemission b :modifyList){
			bussDataPemissionDao.updateByVoNotNull(b);
		}

		for (BussDataPemission b :delList){
			bussDataPemissionDao.deleteByPk(b.getPemissionId());
		}

		return true;
	}

	/**
	 * 批量更新数据权限
	 * @param
	 * @return
	 * @throws Exception
	 */
	public boolean updateBatch(List<BussDataPemission> list,BussAuditStatus auditStatus) throws Exception{
		if(list == null||list.size()==0||auditStatus == null){
			return false;
		}
		//变更商户审核表状态
		bussAuditStatusService.saveAuditInfo(auditStatus);
		for (BussDataPemission b :list){
			bussDataPemissionDao.updateByVoNotNull(b);
		}

		return true;

	}

	/**
	 * 保存数据权限信息
	 * @param pemList
	 * @return
	 * @throws Exception
	 */
	public boolean saveDataPem(String bussId,List<BussDataPemission> pemList,boolean isModifyDataPem,BussItResource bussItResource) throws Exception {
		if(bussId==null||"".equals(bussId)){
			return false;
		}
		//更新数据权限信息及审核状态
		if(isModifyDataPem){
			//更新数据权限信息
			updateByBussId(bussId,pemList);
			//更新IT资源信息（disk）
			bussItResourceService.save(bussItResource);
			//保存数据权限审核信息
			BussAuditStatus auditStatus = new BussAuditStatus();
			auditStatus.setBussId(bussId);
			auditStatus.setStatus(Status.VALID.getCode());

			auditStatus.setAuditStatus(AuditStatus.WAIT.getCode());

			auditStatus.setType(AuditType.DATAPEM.getCode());
			bussAuditStatusService.saveAuditInfo(auditStatus);

		}
		return true;
	}



}
