package com.tydic.dbs.ws.interfaces;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tydic.dbs.buyer.service.BussAuditStatusService;
import com.tydic.dbs.buyer.service.BussTenantRoleService;
import com.tydic.dbs.buyer.service.BussTenantService;
import com.tydic.dbs.buyer.service.InterfaceLogService;
import com.tydic.dbs.buyer.vo.BussAuditStatus;
import com.tydic.dbs.buyer.vo.BussTenant;
import com.tydic.dbs.buyer.vo.BussTenantRole;
import com.tydic.dbs.buyer.vo.InterfaceLog;
import com.tydic.dbs.commons.enums.AuditStatus;
import com.tydic.dbs.commons.enums.AuditType;
import com.tydic.dbs.ws.common.Constants;
import com.tydic.dbs.ws.vo.LeeAuthorVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 租户账户权限申请通知接口
 *
 *
 */
@Component
public class LesseeAuthNoticeHandler implements Handler {

	private static final Logger log = LoggerFactory.getLogger(Handler.class);

	@Autowired
	private BussTenantService bussTenantService;

	@Autowired
	private InterfaceLogService interfaceLogService;

	public Map handle(HttpServletRequest request){
		Map<String,String> map = new HashMap<String,String>();
		InterfaceLog interfaceLog=new InterfaceLog();
		//处理业务逻辑
		String userId =request.getParameter("user_id");
		String resultFlag =request.getParameter("result_flag");
		String message = request.getParameter("message");
		String roles = request.getParameter("result");

		//根据操作员id获取操作员信息
		BussTenant bussTenant = null;
		try {
			bussTenant = bussTenantService.get(userId);
		} catch (Exception e) {
			log.error("获取操作员信息出错：",e);
			map.put(Constants.RESULT_FLAG,Constants.ERROR);
			map.put(Constants.MESSAGE,"获取操作员信息出错");
			return map;
		}
		if(bussTenant == null){
			map.put(Constants.RESULT_FLAG,Constants.ERROR);
			map.put(Constants.MESSAGE,"操作员Id错误！查询不到操作员信息");
			return map;
		}

		if(!Constants.SUCCESS.equals(resultFlag)){
			try {
				bussTenant.setAuditStatus(AuditStatus.NO_PASS.getCode());
				bussTenant.setAuditReason(message);
				bussTenantService.syncTenantRoleInfo(bussTenant);
			} catch (Exception e) {
				log.error("Error",e);
				map.put(Constants.RESULT_FLAG,Constants.ERROR);
				map.put(Constants.MESSAGE,"更新状态出错");
				return map;
			}

			map.put(Constants.RESULT_FLAG,Constants.SUCCESS);
			map.put(Constants.MESSAGE,"SUCCESS");
			return map;
		}


		Gson gson = new Gson();
		List<Map<String,String>> list = gson.fromJson(roles, new TypeToken<List<Map<String,String>>>() {
		}.getType());
		List<BussTenantRole> roleList = new ArrayList<>();

		for (Map<String,String> m :list){
			String roleId = m.get("role");
			String account = m.get("account");
			String url = m.get("url");
			if(roleId==null||"".equals(roleId)||account==null||"".equals(account)||url==null||"".equals(url)){
				map.put(Constants.RESULT_FLAG,Constants.ERROR);
				map.put(Constants.MESSAGE,"权限账户信息错误！");
				return map;
			}
			BussTenantRole role = new BussTenantRole();
			role.setTenantId(userId);
			role.setRoleId(roleId);
			role.setLoginId(account);
			role.setUrl(url);
			roleList.add(role);
		}
		bussTenant.setRoleList(roleList);
		Map paramIn=new HashMap();
		paramIn.put("userId",userId);
		paramIn.put("resultFlag",resultFlag);
		paramIn.put("message",message);
		paramIn.put("roles",roles);
		interfaceLog.setParamIn(gson.toJson(paramIn));
		interfaceLog.setBusiType("USER_AUTHAPLYN");
		interfaceLog.setRemark("租户账号权限申请结果");
		interfaceLog.setSerialNum(request.getParameter("session_id"));
		try {
			bussTenant.setAuditStatus(AuditStatus.PASS.getCode());
			bussTenantService.modify(bussTenant);
			//返回结果
			map.put(Constants.RESULT_FLAG,Constants.SUCCESS);
			map.put(Constants.MESSAGE,"SUCCESS");
			interfaceLog.setResultContent(gson.toJson(map));
			interfaceLog.setCreateTime(new Date());
			interfaceLogService.save(interfaceLog);
		} catch (Exception e) {
			log.error("更新权限信息报错",e);
			map.put(Constants.RESULT_FLAG,Constants.ERROR);
			map.put(Constants.MESSAGE,"更新权限数据报错");
		}
		//返回结果
		return map;
	}
	
	
	public Map handle(LeeAuthorVO leeAuthorVO){
		Map<String,String> map = new HashMap<String,String>();
		InterfaceLog interfaceLog=new InterfaceLog();
		//处理业务逻辑
		String userId =leeAuthorVO.getUser_id();
		String resultFlag =leeAuthorVO.getResult_flag();
		String message = leeAuthorVO.getMessage();
		String roles = leeAuthorVO.getResult();

		//根据操作员id获取操作员信息
		BussTenant bussTenant = null;
		try {
			bussTenant = bussTenantService.get(userId);
		} catch (Exception e) {
			log.error("获取操作员信息出错：",e);
			map.put(Constants.RESULT_FLAG,Constants.ERROR);
			map.put(Constants.MESSAGE,"获取操作员信息出错");
			return map;
		}
		if(bussTenant == null){
			map.put(Constants.RESULT_FLAG,Constants.ERROR);
			map.put(Constants.MESSAGE,"操作员Id错误！查询不到操作员信息");
			return map;
		}

		if(!Constants.SUCCESS.equals(resultFlag)){
			try {
				bussTenant.setAuditStatus(AuditStatus.NO_PASS.getCode());
				bussTenant.setAuditReason(message);
				bussTenantService.syncTenantRoleInfo(bussTenant);
			} catch (Exception e) {
				log.error("Error",e);
				map.put(Constants.RESULT_FLAG,Constants.ERROR);
				map.put(Constants.MESSAGE,"更新状态出错");
				return map;
			}

			map.put(Constants.RESULT_FLAG,Constants.SUCCESS);
			map.put(Constants.MESSAGE,"SUCCESS");
			return map;
		}


		Gson gson = new Gson();
		List<Map<String,String>> list = gson.fromJson(roles, new TypeToken<List<Map<String,String>>>() {
		}.getType());
		List<BussTenantRole> roleList = new ArrayList<>();

		for (Map<String,String> m :list){
			String roleId = m.get("role");
			String account = m.get("account");
			String url = m.get("url");
			if(roleId==null||"".equals(roleId)||account==null||"".equals(account)||url==null||"".equals(url)){
				map.put(Constants.RESULT_FLAG,Constants.ERROR);
				map.put(Constants.MESSAGE,"权限账户信息错误！");
				return map;
			}
			BussTenantRole role = new BussTenantRole();
			role.setTenantId(userId);
			role.setRoleId(roleId);
			role.setLoginId(account);
			role.setUrl(url);
			roleList.add(role);
		}
		bussTenant.setRoleList(roleList);
		Map paramIn=new HashMap();
		paramIn.put("userId",userId);
		paramIn.put("resultFlag",resultFlag);
		paramIn.put("message",message);
		paramIn.put("roles",roles);
		interfaceLog.setParamIn(gson.toJson(paramIn));
		interfaceLog.setBusiType("USER_AUTHAPLYN");
		interfaceLog.setRemark("租户账号权限申请结果");
		interfaceLog.setSerialNum(leeAuthorVO.getSession_id());
		try {
			bussTenant.setAuditStatus(AuditStatus.PASS.getCode());
			bussTenantService.modify(bussTenant);
			//返回结果
			map.put(Constants.RESULT_FLAG,Constants.SUCCESS);
			map.put(Constants.MESSAGE,"SUCCESS");
			interfaceLog.setResultContent(gson.toJson(map));
			interfaceLog.setCreateTime(new Date());
			interfaceLogService.save(interfaceLog);
		} catch (Exception e) {
			log.error("更新权限信息报错",e);
			map.put(Constants.RESULT_FLAG,Constants.ERROR);
			map.put(Constants.MESSAGE,"更新权限数据报错");
		}
		//返回结果
		return map;
	}



	/**
	 * 检查参数是否合法
	 * 如果有异常，返回错误信息
	 * 没有异常，返回空
	 *
	 * @return
	 */
	@Override
	public String checkParams(HttpServletRequest request){
		String result = null;
		Map params = request.getParameterMap();

		//TODO 只检查了参数是否存在，没检查参数格式
		if(!params.containsKey(SERVICE_NAME_KEY)){
			result = SERVICE_NAME_KEY;
		}else if(!params.containsKey(SESSION_ID_KEY)){
			result = SESSION_ID_KEY;
		}if(!params.containsKey(INPUT_CHARSET_KEY)){
			result = INPUT_CHARSET_KEY;
		}if(!params.containsKey(SIGN)){
			result = SIGN;
		}if(!params.containsKey(USER_ID_KEY)){
			result = USER_ID_KEY;
		}if(!params.containsKey(RESULT_FLAG_KEY)){
			result = RESULT_FLAG_KEY;
		}if(!params.containsKey(SYSTEM_ID_KEY)){
			result = SYSTEM_ID_KEY;
		}

		return result;
	}

	@Override
	public String getServiceName() {
		return SERVICE_NAME;
	}

	public static final String SERVICE_NAME_KEY	 = "service_name";
	public static final String SESSION_ID_KEY = "session_id";
	public static final String INPUT_CHARSET_KEY = "input_charset";
	public static final String SIGN = "sign_key";
	public static final String USER_ID_KEY = "user_id";
	public static final String RESULT_FLAG_KEY = "result_flag";
	public static final String MESSAGE_KEY = "message";
	public static final String RESULT_KEY = "result";
	public static final String SYSTEM_ID_KEY = "system_id";
	public static final String SERVICE_NAME = "LESSEE_ACCOUNT_AUTH_NOICE_SERVICE";


	public static void main(String[] args){
		Double double1 = Double.valueOf("100.1");
		System.out.println(double1.longValue());
	}

}