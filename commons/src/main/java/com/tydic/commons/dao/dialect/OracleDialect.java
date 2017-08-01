/**
 * com.tydic.commons.dao.dialect.OracleDialect.java
 */
package com.tydic.commons.dao.dialect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tydic.commons.dao.Dialect;

 /**
 * @file  OracleDialect.java
 * @author weishaojia(viscar)
 * @version 0.1
 * @todo Oracle方言处理
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
public class OracleDialect extends Dialect {

	private static final Logger log = LoggerFactory.getLogger( OracleDialect.class );

	public String getLimitString(String sql, int offset, int limit) {
		sql = sql.trim();   
        StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);   
           
        pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");  
        pagingSelect.append(sql);
        pagingSelect.append(" ) row_ ) temp_ where rownum_ > ").append(offset).append(" and rownum_ <= ").append(offset + limit);   
        if(log.isDebugEnabled()){
        	log.debug(pagingSelect.toString());
        }
        return pagingSelect.toString(); 
	}
	
	public String getNativeLimitString(String sql, int offset, int limit) {
		sql = sql.trim();
        StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);
        
        pagingSelect.append("select * from (select row_.*, rownum rownum_ from ( ");
        pagingSelect.append(sql);
        pagingSelect.append(") row_ where rownum <= ").append(offset + limit).append(" ) temp_ where rownum_ > ").append(offset);
        if(log.isDebugEnabled()){
        	log.debug(pagingSelect.toString());
        }
        return pagingSelect.toString();
	}

	public static void main(String...args){
		OracleDialect db=new OracleDialect();
		System.out.println(db.getLimitString("select * from test", 10, 5));
	}

}
