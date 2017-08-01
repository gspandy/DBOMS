/**
 * 
 */
package com.tydic.dbs.commons.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * <p>Title: </p>
 * <p>Description:   </p>
 * <p>Company: 从兴技术有限公司 </p>
 * @author: yuchanghong
 * @version 1.0
 * @date: 2014-3-21 下午1:17:09
 */

public class DbCommon {
	private static Properties properties = new Properties();  
	
	private static String driverClass;
	private static String driveUrl;
	private static String user;
	private static String password;
	
	static {
		InputStream is = DbCommon.class.getClassLoader().getResourceAsStream("com/tydic/dbs/commons/utils/druid.properties");
		try {
			properties.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driverClass = properties.getProperty("jdbc.druid.driverClassName");
		driveUrl = properties.getProperty("jdbc.druid.url");
		user = properties.getProperty("jdbc.druid.username");
		password = properties.getProperty("jdbc.druid.password");
	}
	
	public static Connection getConnection() {
		Connection conn = null;

		try {
			Class.forName(driverClass);

			conn =DriverManager.getConnection(driveUrl, user, password); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static void closeConnection(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
