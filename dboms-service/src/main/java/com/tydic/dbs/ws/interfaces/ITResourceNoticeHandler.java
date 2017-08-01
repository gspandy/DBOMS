package com.tydic.dbs.ws.interfaces;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tydic.dbs.buyer.service.BussAuditStatusService;
import com.tydic.dbs.buyer.service.BussItResourceService;
import com.tydic.dbs.buyer.service.BussTenantRoleService;
import com.tydic.dbs.buyer.service.InterfaceLogService;
import com.tydic.dbs.buyer.vo.BussAuditStatus;
import com.tydic.dbs.buyer.vo.BussItResource;
import com.tydic.dbs.buyer.vo.BussTenantRole;
import com.tydic.dbs.buyer.vo.InterfaceLog;
import com.tydic.dbs.commons.enums.AuditStatus;
import com.tydic.dbs.commons.enums.AuditType;
import com.tydic.dbs.ws.common.Constants;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 租户账户权限申请通知接口
 *
 *
 */
@Component
public class ITResourceNoticeHandler implements Handler {

	private static final Logger log = LoggerFactory.getLogger(Handler.class);

	@Autowired
	private BussItResourceService bussItResourceService;

	@Autowired
	private BussAuditStatusService bussAuditStatusService;

	@Autowired
	private InterfaceLogService interfaceLogService;

	public Map handle(HttpServletRequest request){
		Map<String,String> map = new HashMap<String,String>();
		InterfaceLog interfaceLog=new InterfaceLog();
		//处理业务逻辑
		String userId =request.getParameter("user_id");
		String resultFlag =request.getParameter("result_flag");
		String message = request.getParameter("message");
		String ftpInfo  = request.getParameter("ftp_info");

		//更新审核状态
		BussAuditStatus auditStatus = new BussAuditStatus();
		auditStatus.setBussId(userId);
		auditStatus.setType(AuditType.ITRESOURCE.getCode());

		if(!Constants.SUCCESS.equals(resultFlag)){
			auditStatus.setAuditStatus(AuditStatus.NO_PASS.getCode());
			try {
				bussAuditStatusService.saveAuditInfo(auditStatus);
			} catch (Exception e) {
				log.error("Error",e);
			}
			map.put(Constants.RESULT_FLAG,Constants.ERROR);
			map.put(Constants.MESSAGE,message);
			return map;
		}

		Gson gson = new Gson();
		Map<String,String> resultMap = gson.fromJson(ftpInfo, new TypeToken<Map<String,String>>() {
		}.getType());

		String ip = resultMap.get("ip");
		String port = resultMap.get("port");
		String user = resultMap.get("user");

		String password = resultMap.get("password");
		String filePath = resultMap.get("file_path");

		if(ip==null||"".equals(ip)){
			map.put(Constants.RESULT_FLAG,Constants.ERROR);
			map.put(Constants.MESSAGE,"ip为空！");
			return map;
		}
		if(port==null||"".equals(port)){
			map.put(Constants.RESULT_FLAG,Constants.ERROR);
			map.put(Constants.MESSAGE,"port为空！");
			return map;
		}if(user==null||"".equals(user)){
			map.put(Constants.RESULT_FLAG,Constants.ERROR);
			map.put(Constants.MESSAGE,"user为空！");
			return map;
		}
		if(password==null||"".equals(password)){
			map.put(Constants.RESULT_FLAG,Constants.ERROR);
			map.put(Constants.MESSAGE,"password为空！");
			return map;
		}
		if(filePath==null||"".equals(filePath)){
			map.put(Constants.RESULT_FLAG,Constants.ERROR);
			map.put(Constants.MESSAGE,"filePath为空！");
			return map;
		}


		BussItResource resource = new BussItResource();
		resource.setFtpIp(ip);
		resource.setFtpPort(NumberUtils.toInt(port));
		resource.setFtpUser(user);
		resource.setFtpPass(password);
		resource.setFtpPath(filePath);
		resource.setBussId(userId);
		resource.setStatus(AuditStatus.PASS.getCode());

		auditStatus.setAuditStatus(AuditStatus.PASS.getCode());

		Map paramIn=new HashMap();
		paramIn.put("userId",userId);
		paramIn.put("resultFlag",resultFlag);
		paramIn.put("message",message);
		paramIn.put("ftpInfo",ftpInfo);
		interfaceLog.setParamIn(gson.toJson(paramIn));
		interfaceLog.setBusiType("USER_SOUAPLYN");
		interfaceLog.setRemark("租户IT资源申请结果");
		interfaceLog.setSerialNum(request.getParameter("session_id"));

		try {
			bussItResourceService.modifyFtpInfoByBussId(resource,auditStatus);
			//返回结果
			map.put(Constants.RESULT_FLAG,Constants.SUCCESS);
			map.put(Constants.MESSAGE,"SUCCESS");
			interfaceLog.setCreateTime(new Date());
			interfaceLog.setResultContent(gson.toJson(map));
			interfaceLogService.save(interfaceLog);
		} catch (Exception e) {
			log.error("更新IT资源信息报错",e);
			map.put(Constants.RESULT_FLAG,Constants.ERROR);
			map.put(Constants.MESSAGE,"更新IT资源信息报错");
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
	public static final String SERVICE_NAME = "IT_RESOURCE_NOTICE_SERVICE";


	public static void main(String[] args){
		Double double1 = Double.valueOf("100.1");
		System.out.println(double1.longValue());
	}

}