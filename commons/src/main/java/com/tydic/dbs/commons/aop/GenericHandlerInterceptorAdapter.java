package com.tydic.dbs.commons.aop;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.tydic.commons.web.BaseAction;

public class GenericHandlerInterceptorAdapter extends HandlerInterceptorAdapter {
	Logger logger = Logger.getLogger(GenericHandlerInterceptorAdapter.class);
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof BaseAction) {
			BaseAction action = (BaseAction) handler;
			String methodName = action.getMethodNameResolver().getHandlerMethodName(request);
			logger.debug("Begin of " + action.getClass().getCanonicalName() + "." + methodName + "()");
			logger.debug("The request is: " + request.getRequestURL());
			StringBuilder stringBuilder = new StringBuilder();
			String paramKey = null;
			Iterator<String> iterator = request.getParameterMap().keySet().iterator();
			while (iterator.hasNext()) {
				paramKey = iterator.next();
				if (stringBuilder.length() > 0)
					stringBuilder.append(",");
				stringBuilder.append(paramKey + ":" + request.getParameter(paramKey).toString());
			}
			logger.debug("The parameters are: " + stringBuilder.toString());
		}
		return true;
	}
	
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if (handler instanceof BaseAction) {
			BaseAction action = (BaseAction) handler;
			String methodName = action.getMethodNameResolver().getHandlerMethodName(request);
			logger.debug("End of " + action.getClass().getCanonicalName() + "." + methodName + "()");
			logger.debug("The model is: " + modelAndView.toString());
		}
	}
}