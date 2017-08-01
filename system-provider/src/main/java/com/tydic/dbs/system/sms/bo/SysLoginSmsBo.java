/**
 * com.sunrise.wss.bo.SysLoginSmsBo.java
 */
package com.tydic.dbs.system.sms.bo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import com.tydic.commons.utils.Page;
import com.tydic.dbs.commons.define.WcsCommonStatus;
import com.tydic.dbs.system.SysLoginSms.dao.SysLoginSmsDao;
import com.tydic.dbs.system.SysLoginSms.mapper.SysLoginSms;
import com.tydic.dbs.system.SysLoginSms.service.SysLoginSmsService;

/**
 * 
 * @ClassName: SysLoginSmsBo 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author Michael dengwenjie@tydic.com 
 * @date 2016-7-20 下午4:24:10 
 *
 */
public class SysLoginSmsBo implements SysLoginSmsService {
	
	private SysLoginSmsDao sysLoginSmsDao;
	

    /**
	 * @param sysLoginSmsDao the sysLoginSmsDao to set
	 */
	public void setSysLoginSmsDao(SysLoginSmsDao sysLoginSmsDao) {
		this.sysLoginSmsDao = sysLoginSmsDao;
	}


	/**
	 * 查找所有数据库记录
     * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
     */
	@SuppressWarnings({ "rawtypes" })
    public List getAll() throws Exception {
		return sysLoginSmsDao.selectAll();
	}
	
	/**
     * 查找符合条件的所有数据库记录
     * @param vo 与数据库中记录对应的值对象
     * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
     * @throws Exception
     */
	@SuppressWarnings({ "rawtypes" })
    public List get(SysLoginSms vo) throws Exception{
		if(vo == null) return null;
        return sysLoginSmsDao.selectByVo(vo);
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
		return sysLoginSmsDao.queryForPage(params);
	}
	
    /**
     * 查找符合条件的数据库记录
     * @param pkid 与数据库中主键对应的值
     * @return type : 返回查询操作符合条件的记录的VO对象，操作失败返回null
     * @throws Exception
     */
	public SysLoginSms get(String pkId) throws Exception{
		if(StringUtils.isEmpty(pkId)) return null;
     	return sysLoginSmsDao.selectByPk(pkId);
	}
	
	/**
     * 向数据库中插入一条记录
     * @param vo 与数据库中记录对应的值对象
     * @return type : RpDir 返回插入操作是否成功
     * @throws Exception
     */
    public SysLoginSms save(SysLoginSms vo) throws Exception{
    	if(vo == null) return null;		
		if(vo.getLoginSmsId()!=null) {
			return sysLoginSmsDao.updateByVo(vo);
		} else {
			return sysLoginSmsDao.insertByVo(vo);
		}
    }
    
    /**
     * 删除符合条件的数据库记录
     * @param pkid 与数据库中主键对应的值
     * @return type : boolean 返回删除操做是否成功，操作失败返回false
     */
    public boolean delete(String pkId) throws Exception{
    	if(StringUtils.isEmpty(pkId)) return false;
    	return sysLoginSmsDao.deleteByPk(pkId);
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
    	return sysLoginSmsDao.updateStatus(params);
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
    	return sysLoginSmsDao.updateStatus(params);
	}	
	
	@SuppressWarnings("unchecked")
	public boolean isRightSms(String mobile, String smsCode, Integer type)
			throws Exception {
		boolean flag=false;
		SysLoginSms sms=null;
		Map<String, Object> params = new HashMap<String, Object>();
		List<Map<String, Object>> orderBy = new ArrayList<Map<String, Object>>();
		Map<String, Object> orderParam=new HashMap<>();		
		orderParam.put("createTime", "desc");
		orderBy.add(orderParam);
		params.put("orderBy", orderBy);
		params.put("mobile", mobile);
		params.put("smsId", smsCode);
		params.put("smsType", type);
		params.put("expireTimeGte",new Timestamp(new Date().getTime()));//判断时效性
		List<SysLoginSms> list=(List<SysLoginSms>)sysLoginSmsDao.selectByParamMap(params);
		if (list==null || list.isEmpty() || list.size() <= 0) return flag;
		else {
			sms=list.get(0);
		}
		//能查询到,说明验证码正确并有效，那么可以删除
		if(sms!=null){
			sysLoginSmsDao.deleteByVo(sms);
			flag=true;
		}
				
		return flag;
	}
}
