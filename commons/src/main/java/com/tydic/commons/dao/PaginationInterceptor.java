/**
 * com.tydic.commons.dao.PaginationInterceptor.java
 */
package com.tydic.commons.dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tydic.commons.utils.BaseVO;
import com.tydic.commons.utils.Page;

 /**
 * @file  PaginationInterceptor.java
 * @author weishaojia(viscar)
 * @version 0.1
 * @todo 分页拦截插件
 * Copyright(C), 2013-2014
 *			Guangzhou Sunrise Electronics Development Co., Ltd.
 * History
 *   	1. Date: 2013-4-18
 *      	Author: weishaojia(viscar)
 *      	Modification: this file was created
 *   	2. Date: 2014-5-28
 *   		Author: caipeimin
 *   		Modification: add native paging logic
 */
@Intercepts({@Signature(type=StatementHandler.class,method="prepare",args={Connection.class})}) 
public class PaginationInterceptor implements Interceptor {

	protected static Logger log = LoggerFactory.getLogger(PaginationInterceptor.class); 

	/* (non-Javadoc)
	 * @see org.apache.ibatis.plugin.Interceptor#intercept(org.apache.ibatis.plugin.Invocation)
	 */
	@SuppressWarnings("rawtypes")
	public Object intercept(Invocation invocation) throws Exception {
		StatementHandler statementHandler = (StatementHandler)invocation.getTarget();   
        MetaObject metaStatementHandler = MetaObject.forObject(statementHandler);   
           
        RowBounds rowBounds = (RowBounds)metaStatementHandler.getValue("delegate.rowBounds");   
        if(rowBounds == null || rowBounds == RowBounds.DEFAULT){   
            try {
				return invocation.proceed();
			} catch (InvocationTargetException e) {
				throw new Exception(e);
			} catch (IllegalAccessException e) {
				throw new Exception(e);
			}   
        }   
           
        DefaultParameterHandler defaultParameterHandler = (DefaultParameterHandler)metaStatementHandler.getValue("delegate.parameterHandler");   
        Object params= defaultParameterHandler.getParameterObject();
        String originalSql = (String)metaStatementHandler.getValue("delegate.boundSql.sql"); 
        
        if(params instanceof BaseVO){
	        BaseVO vo=null;
	        vo = (BaseVO)defaultParameterHandler.getParameterObject();  
	        if(vo!=null&&vo.getOrderBy() != null && vo.getDescOrAsc() != null ){
	        	originalSql = originalSql + " order by " + vo.getOrderBy() +  " " + vo.getDescOrAsc();   
	        }
        } else if (params instanceof Map){
        	//手动order by
        	Object order =((Map)params).get("p_orderBy");
        	if(order!=null&&(order instanceof String)){
        		originalSql = originalSql + " ORDER BY "+(String)order ; 
        	}
        }
        
        Configuration configuration = (Configuration)metaStatementHandler.getValue("delegate.configuration");
        
        Dialect dialect = Dialect.getDialect(configuration.getVariables().getProperty("dialect").toUpperCase());   
        if (Boolean.TRUE.equals(((Map)params).get(Page.NATIVE_PAGING)))
        	metaStatementHandler.setValue("delegate.boundSql.sql", dialect.getNativeLimitString(originalSql, rowBounds.getOffset(), rowBounds.getLimit()));   
        else
        	metaStatementHandler.setValue("delegate.boundSql.sql", dialect.getLimitString(originalSql, rowBounds.getOffset(), rowBounds.getLimit()));   
        metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET );   
        metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT );   
        if(log.isDebugEnabled()){   
            BoundSql boundSql = statementHandler.getBoundSql();   
            log.debug("生成分页SQL : " + boundSql.getSql());   
        }   
        try {
			return invocation.proceed();
		} catch (InvocationTargetException e) {
			throw new Exception(e);
		} catch (IllegalAccessException e) {
			throw new Exception(e);
		}   

	}

	/* (non-Javadoc)
	 * @see org.apache.ibatis.plugin.Interceptor#plugin(java.lang.Object)
	 */
	public Object plugin(Object target) {
		// TODO Auto-generated method stub
		return Plugin.wrap(target, this);   
	}

	/* (non-Javadoc)
	 * @see org.apache.ibatis.plugin.Interceptor#setProperties(java.util.Properties)
	 */
	public void setProperties(Properties arg0) {
		// TODO Auto-generated method stub
		
	}

}
