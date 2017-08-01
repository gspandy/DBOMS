package com.tydic.dbs.ws.interfaces;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tydic.commons.utils.OrderUtils;
import com.tydic.dbs.buyer.service.BussAuditStatusService;
import com.tydic.dbs.buyer.service.BussDataPemissionService;
import com.tydic.dbs.buyer.service.InterfaceLogService;
import com.tydic.dbs.buyer.vo.BussAuditStatus;
import com.tydic.dbs.buyer.vo.BussDataPemission;
import com.tydic.dbs.buyer.vo.InterfaceLog;
import com.tydic.dbs.commons.enums.AuditStatus;
import com.tydic.dbs.commons.enums.AuditType;
import com.tydic.dbs.ws.common.Constants;
import com.tydic.dbs.ws.vo.LeeAuthorVO;
import com.tydic.dbs.ws.vo.DataResourceNoticeVO;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 数据权限通知接口
 *
 *
 */
@Component
public class DataPemissionNoticeHandler implements Handler {

	private static final org.slf4j.Logger log = LoggerFactory.getLogger(Handler.class);

	@Autowired
	private BussDataPemissionService bussDataPemissionService;

	@Autowired
	private BussAuditStatusService bussAuditStatusService;

	@Autowired
	private InterfaceLogService interfaceLogService;

	public Map handle(HttpServletRequest request){
		Map<String,String> map = new HashMap<String,String>();
		//处理业务逻辑
		String userId =request.getParameter(USER_ID_KEY);
		String resultFlag =request.getParameter(RESULT_FLAG_KEY);
		String message = request.getParameter(MESSAGE_KEY);
		String pems = request.getParameter(PEM_INFO_KEY);

		//更新审核状态
		BussAuditStatus auditStatus = new BussAuditStatus();
		auditStatus.setBussId(userId);
		auditStatus.setType(AuditType.DATAPEM.getCode());

		if(!Constants.SUCCESS.equals(resultFlag)){
			auditStatus.setAuditStatus(AuditStatus.NO_PASS.getCode());
			try {
				bussAuditStatusService.saveAuditInfo(auditStatus);
			} catch (Exception e) {
				log.error("Error",e);
			}
			map.put(Constants.RESULT_FLAG,Constants.SUCCESS);
			map.put(Constants.MESSAGE,"SUCCESS");
			return map;
		}

		Gson gson = new Gson();
		List<Map<String,String>> list = gson.fromJson(pems, new TypeToken<List<Map<String,String>>>() {
		}.getType());
		List<BussDataPemission> pemList = new ArrayList<>();

		for (Map<String,String> m :list){
			String pemName = m.get("data_service_name");
			String flag = m.get("result_flag");
			if(pemName==null||"".equals(pemName)||flag==null||"".equals(flag)){
				map.put(Constants.RESULT_FLAG,Constants.ERROR);
				map.put(Constants.MESSAGE,"权限信息错误！");
				return map;
			}
			BussDataPemission dataPemission = new BussDataPemission();
			dataPemission.setPemissionName(pemName);
			dataPemission.setBussId(userId);
			if("1".equals(flag)){
				dataPemission.setAppStatus(AuditStatus.PASS.getCode());
			}else if("2".equals(flag)){
				dataPemission.setAppStatus(AuditStatus.NO_PASS.getCode());
			}
		
			pemList.add(dataPemission);

		}
		InterfaceLog interfaceLog=new InterfaceLog();
		Map paramIn=new HashMap();
		paramIn.put("userId",userId);
		paramIn.put("resultFlag",resultFlag);
		paramIn.put("message",message);
		paramIn.put("pems",pems);
		interfaceLog.setParamIn(gson.toJson(paramIn));
		interfaceLog.setBusiType("USER_AUTHAPLYN");
		interfaceLog.setRemark("租户账号权限申请结果");
		interfaceLog.setSerialNum(request.getParameter("session_id"));
		try {
			auditStatus.setAuditStatus(AuditStatus.PASS.getCode());
			bussDataPemissionService.updateBatch(pemList,auditStatus);
			//返回结果
			map.put(Constants.RESULT_FLAG, Constants.SUCCESS);
			map.put(Constants.MESSAGE, "SUCCESS");
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
	
	public Map handle(DataResourceNoticeVO vo){
		Gson gson = new Gson();
		Map<String,String> map = new HashMap<String,String>();
		//处理业务逻辑
		String userId =vo.getUser_id();
		String resultFlag =vo.getResult_flag();
		String message = vo.getMessage();
		String pems = vo.getPem_info();

		//更新审核状态
		BussAuditStatus auditStatus = new BussAuditStatus();
		auditStatus.setBussId(userId);
		auditStatus.setType(AuditType.DATAPEM.getCode());

		if(!Constants.SUCCESS.equals(resultFlag)){
			auditStatus.setAuditStatus(AuditStatus.NO_PASS.getCode());
			try {
				bussAuditStatusService.saveAuditInfo(auditStatus);
			} catch (Exception e) {
				log.error("Error",e);
			}
			map.put(Constants.RESULT_FLAG,Constants.SUCCESS);
			map.put(Constants.MESSAGE,"SUCCESS");
			return map;
		}

		List<Map<String,String>> list = gson.fromJson(pems, new TypeToken<List<Map<String,String>>>() {
		}.getType());
		List<BussDataPemission> pemList = new ArrayList<>();

		for (Map<String,String> m :list){
			String pemName = m.get("data_service_name");
			String flag = m.get("result_flag");
			if(pemName==null||"".equals(pemName)||flag==null||"".equals(flag)){
				map.put(Constants.RESULT_FLAG,Constants.ERROR);
				map.put(Constants.MESSAGE,"权限信息错误！");
				return map;
			}
			BussDataPemission dataPemission = new BussDataPemission();
			dataPemission.setPemissionName(pemName);
			dataPemission.setBussId(userId);
			if("1".equals(flag)){
				dataPemission.setAppStatus(AuditStatus.PASS.getCode());
			}else if("2".equals(flag)){
				dataPemission.setAppStatus(AuditStatus.NO_PASS.getCode());
			}
		
			pemList.add(dataPemission);

		}
		InterfaceLog interfaceLog=new InterfaceLog();
		Map paramIn=new HashMap();
		paramIn.put("userId",userId);
		paramIn.put("resultFlag",resultFlag);
		paramIn.put("message",message);
		paramIn.put("pems",pemList);
		interfaceLog.setParamIn(gson.toJson(paramIn));
		interfaceLog.setBusiType("USER_AUTHAPLYN");
		interfaceLog.setRemark("租户账号权限申请结果");
		interfaceLog.setSerialNum(vo.getSession_id());
		try {
			auditStatus.setAuditStatus(AuditStatus.PASS.getCode());
			bussDataPemissionService.updateBatch(pemList,auditStatus);
			//返回结果
			map.put(Constants.RESULT_FLAG, Constants.SUCCESS);
			map.put(Constants.MESSAGE, "SUCCESS");
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
		}if(!params.containsKey(PEM_INFO_KEY)){
			result = PEM_INFO_KEY;
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
	public static final String PEM_INFO_KEY = "pem_info";
	public static final String SERVICE_NAME = "LESSEE_DATA_AUTH_NOTICE_SERVICE";


}