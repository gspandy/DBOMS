/**
 * com.tydic.commons.dao.dialect.OracleDialect.java
 */
package com.tydic.commons.dao.dialect;

import com.tydic.commons.dao.Dialect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class MysqlDialect extends Dialect {

   private static final Logger log = LoggerFactory.getLogger( MysqlDialect.class );

   public String getLimitString(String sql, int offset, int limit) {
       sql = sql.trim();
       StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);

       pagingSelect.append(sql);
       pagingSelect.append(" limit ").append(offset).append(",").append(limit);

       if(log.isDebugEnabled()){
           log.debug(pagingSelect.toString());
       }
       return pagingSelect.toString();
   }

   public String getNativeLimitString(String sql, int offset, int limit) {
       sql = sql.trim();
       StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);

       pagingSelect.append(sql);
       pagingSelect.append(" limit ").append(offset).append(",").append(limit);

       if(log.isDebugEnabled()){
           log.debug(pagingSelect.toString());
       }
       return pagingSelect.toString();
   }

   public static void main(String...args){
       MysqlDialect db=new MysqlDialect();
       System.out.println(db.getLimitString("select * from test", 10, 5));
   }

}
