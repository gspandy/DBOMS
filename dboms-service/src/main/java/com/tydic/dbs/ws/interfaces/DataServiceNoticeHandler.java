package com.tydic.dbs.ws.interfaces;


import com.google.gson.Gson;
import com.tydic.dbs.buyer.service.InterfaceLogService;
import com.tydic.dbs.buyer.vo.InterfaceLog;
import com.tydic.dbs.product.service.PrdDataResourceService;
import com.tydic.dbs.product.vo.PrdDataResource;
import com.tydic.dbs.ws.common.Constants;
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
public class DataServiceNoticeHandler implements Handler {

	private static final Logger log = LoggerFactory.getLogger(Handler.class);

	@Autowired
	private PrdDataResourceService prdDataResourceService;
	@Autowired
	private InterfaceLogService interfaceLogService;


	public Map handle(HttpServletRequest request){
		Map<String,String> map = new HashMap<String,String>(); //处理业务逻辑
		String serId =request.getParameter(DATA_SERVICE_ID_KEY);
		String serName =request.getParameter(DATA_SERVICE_NAME_KEY);
		String prdId = request.getParameter(PRODUCT_ID_KEY);
		InterfaceLog interfaceLog=new InterfaceLog();

		if(serId==null||"".equals(serId)){
			map.put(Constants.RESULT_FLAG,Constants.ERROR);
			map.put(Constants.MESSAGE,"数据服务ID为空");
			return map;
		}
		if(serName==null||"".equals(serName)){
			map.put(Constants.RESULT_FLAG,Constants.ERROR);
			map.put(Constants.MESSAGE,"数据服务名称为空");
			return map;
		}

		if(prdId==null||"".equals(prdId)){
			map.put(Constants.RESULT_FLAG,Constants.ERROR);
			map.put(Constants.MESSAGE,"产品ID为空");
			return map;
		}

		//
		PrdDataResource prdDataResource = new PrdDataResource();
		prdDataResource.setDataResourceId(serId);
		prdDataResource.setDataResourceName(serName);
		prdDataResource.setPrdId(prdId);

		Map paramIn=new HashMap();
		Gson gson=new Gson();
		paramIn.put("serId",serId);
		paramIn.put("serName",serName);
		paramIn.put("prdId",prdId);
		interfaceLog.setParamIn(gson.toJson(paramIn));
		interfaceLog.setBusiType("PRODUCT_DATASYN");
		interfaceLog.setRemark("数据服务信息同步");
		interfaceLog.setSerialNum(request.getParameter("session_id"));

		try {
			prdDataResourceService.save(prdDataResource);
			map.put(Constants.RESULT_FLAG, Constants.SUCCESS);
			map.put(Constants.MESSAGE,"成功");
			interfaceLog.setSerialNum(request.getParameter("session_id"));
			interfaceLog.setCreateTime(new Date());
			interfaceLog.setResultContent(gson.toJson(map));
			interfaceLogService.save(interfaceLog);
		} catch (Exception e) {
			log.error("Error",e);
			map.put(Constants.RESULT_FLAG, Constants.ERROR);
			map.put(Constants.MESSAGE,"");
		}
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
		}if(!params.containsKey(DATA_SERVICE_ID_KEY)){
			result = DATA_SERVICE_ID_KEY;
		}if(!params.containsKey(DATA_SERVICE_NAME_KEY)){
			result = DATA_SERVICE_NAME_KEY;
		}if(!params.containsKey(PRODUCT_ID_KEY)){
			result = PRODUCT_ID_KEY;
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
	public static final String DATA_SERVICE_ID_KEY = "data_service_id";
	public static final String DATA_SERVICE_NAME_KEY = "data_service_name";
	public static final String PRODUCT_ID_KEY = "product_id";

	public static final String SERVICE_NAME = "DATA_SYNC_SERVICE";


	public static void main(String[] args){
		Double double1 = Double.valueOf("100.1");
		System.out.println(double1.longValue());
	}

}