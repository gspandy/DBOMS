package com.tydic.dbs.dbsweb.common;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

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
		String viewName=determineViewName(ex, request);
		if(StringUtils.isBlank(viewName)){
			return null;
		}
		//如果不是异步请求
		if(!(request.getHeader("accept").indexOf("application/json") > -1 || (request  
                .getHeader("X-Requested-With")!= null && request  
                .getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))){
			Integer statusCode = determineStatusCode(request, viewName);  
            if (statusCode != null) {  
                applyStatusCodeIfPossible(request, response, statusCode);  
            }  
            return getModelAndView(viewName, ex, request); 
		}else{//json格式返回
			try {  
                PrintWriter writer = response.getWriter();  
                writer.write(ex.getMessage());  
                writer.flush();  
            } catch (Exception e) {
            	log.error("[CustomSimpleMappingExceptionResolver.doResolveException] - 异步请求返回数据异常",e);
                //e.printStackTrace();  
            }  
			return null;
		}
	}
	
}
