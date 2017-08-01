/**
 * com.tydic.commons.dao.Dialect.java
 */
package com.tydic.commons.dao;

import com.tydic.commons.dao.dialect.MysqlDialect;
import com.tydic.commons.dao.dialect.OracleDialect;
import com.tydic.commons.dao.dialect.PostgresqlDialect;

 /**
 * @file  Dialect.java
 * @author weishaojia(viscar)
 * @version 0.1
 * @todo 数据库方言基类
 * Copyright(C), 2013-2014
 *			Guangzhou Sunrise Electronics Development Co., Ltd.
 * History
 *   	1. Date: 2013-4-18
 *      	Author: weishaojia(viscar)
 *      	Modification: this file was created
 *   	2. Date: 2014-5-28
 *   		Author: caipeimin
 *   		Modification: add method getNativeLimitString
 */
public abstract class Dialect {
    private static Dialect dal=null; 
	
    public abstract String getLimitString(String sql, int offset, int limit);
    
    public abstract String getNativeLimitString(String sql, int offset, int limit);
    
    public static Dialect getDialect(String dalStr){
		if(dal==null && "ORACLE".equalsIgnoreCase(dalStr)){
			dal=new OracleDialect();
		}else if("POSTGRESQL".equalsIgnoreCase(dalStr)){
			dal=new PostgresqlDialect();
		}else if("MYSQL".equalsIgnoreCase(dalStr)){
			dal=new MysqlDialect();
		}
		return dal;
	}	
}
