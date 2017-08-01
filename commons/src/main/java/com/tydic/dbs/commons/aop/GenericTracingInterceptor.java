package com.tydic.dbs.commons.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;

public class GenericTracingInterceptor implements MethodInterceptor {
	Logger logger = Logger.getLogger(GenericTracingInterceptor.class);

	@Override
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		String methodName = methodInvocation.getMethod().getName();
		String className = methodInvocation.getMethod().getDeclaringClass().getName();
		logger.debug("Begin of method: " + methodName + " on class of: " + className);
		logger.debug("and the parameters are: " + methodInvocation.getArguments().toString());
		Object obj = methodInvocation.getMethod().invoke(methodInvocation.getThis(), methodInvocation.getArguments());
		logger.debug("End of method: " + methodName + " on class of: " + className);
		return obj;
	}

}