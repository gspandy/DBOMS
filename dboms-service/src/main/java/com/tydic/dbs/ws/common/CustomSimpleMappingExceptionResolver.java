package com.tydic.dbs.ws.common;

import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**

 * @description: 统一异常处理类
 * @author zjl
 * @since 2016-7-29
 * @log:
 */
public class CustomSimpleMappingExceptionResolver extends SimpleMappingExceptionResolver{
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {

			try {
				Map<String, String> sParams = new HashMap<String, String>();
				sParams.put("result_flag", "0");
				sParams.put("message",ex.getMessage());
				Gson gson = new Gson();
				String s = gson.toJson(sParams);
                PrintWriter writer = response.getWriter();
                writer.write(s);
                writer.flush();  
            } catch (Exception e) {
            	log.error("[CustomSimpleMappingExceptionResolver.doResolveException] 返回数据异常",e);
				Map<String, String> sParams = new HashMap<String, String>();
				sParams.put("result_flag", "0");
				sParams.put("message",ex.getMessage());
				Gson gson = new Gson();
				String s = gson.toJson(sParams);
				PrintWriter writer = null;
				try {
					writer = response.getWriter();
				} catch (IOException e1) {
					log.error("[CustomSimpleMappingExceptionResolver.doResolveException]请求返回数据异常",e);
				}
				writer.write(s);
				writer.flush();
			}
			return null;
//		}
	}
	
}
