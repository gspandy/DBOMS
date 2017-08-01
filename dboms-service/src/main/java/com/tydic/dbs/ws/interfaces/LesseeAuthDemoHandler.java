package com.tydic.dbs.ws.interfaces;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 租户账户权限申请通知接口
 *
 *
 */
@Component
public class LesseeAuthDemoHandler implements Handler {

	private static final Logger log = LoggerFactory.getLogger(Handler.class);

	public Map handle(HttpServletRequest request){
		//处理业务逻辑
		Map<String,String> map = new HashMap<String,String>();
		map.put("result_flag","1");
		map.put("message","SUCCESS");
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
		return null;
	}

	@Override
	public String getServiceName() {
		return SERVICE_NAME;
	}

	public static final String SERVICE_NAME = "LESSEE_ACCOUNT_AUTH_SERVICE";


}