package com.tydic.dbs.commons.sms;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @file  SmsConnPool.java
 * @author caipeimin
 * @version 0.1
 * @短信数据库连接池
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-04-09 18:41:49
 *      	Author: caipeimin
 *      	Modification: this file was created
 *   	2. ...
 */
public class SmsConnPool {
	
	public static Connection getConnection() throws SQLException {
		SmsConfig config = SmsConfig.getInstance();
		try {
			Driver driver = (Driver) Class.forName(config.getDriver()).newInstance();
			DriverManager.registerDriver(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return DriverManager.getConnection(config.getUrl(), config.getUser(), config.getPassword());
	}
	
	public static void closeConnection(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (SQLException sqle) {
			System.out.println(sqle.getMessage());;
		}
	}
}