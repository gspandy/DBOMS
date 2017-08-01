package com.tydic.dbs.commons.constant;

import java.util.Properties;

/**、
 * 配置常量
 * @author Administrator
 *
 */
public class ConfigConstants {
	
	private static Properties p = new Properties();
	static{
		try {
			p.load(ConfigConstants.class.getClassLoader().getResourceAsStream("com/tydic/dbs/commons/sms/sms-config.properties"));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("加载config.properties配置文件出错"+e.getMessage());
		}
	}
	
	//短信接口
	public final static String SMS_WEBSERVICE_URL = p.getProperty("SMS_WEBSERVICE_URL");
	public final static String SMS_CUSTOMER_ID = p.getProperty("SMS_CUSTOMER_ID");
	public final static String SMS_CUSTOMER_PASSPORT = p.getProperty("SMS_CUSTOMER_PASSPORT");
	
	//ftp配置
		public final static String SEQ_MODIFY_RECORD_BATCH= "SEQ_MODIFY_RECORD_BATCH";
		public final static String BASE_UPLOAD_FILE_PATH = p.getProperty("BASE_UPLOAD_FILE_PATH");
		public final static String UPLOAD_FILE_PATH_BUSS_INFO = p.getProperty("UPLOAD_FILE_PATH_BUSS_INFO");
		public final static String UPLOAD_FILE_PATH_PRD_INFO = p.getProperty("UPLOAD_FILE_PATH_PRD_INFO");
		public final static String UPLOAD_FILE_PATH_ORD_INFO = p.getProperty("UPLOAD_FILE_PATH_ORD_INFO");
		public final static String UPLOAD_FILE_PATH_INTERFACE_INFO = p.getProperty("UPLOAD_FILE_PATH_INTERFACE_INFO");
		public final static String FTP_SERVER_IP = p.getProperty("FTP_SERVER_IP");//ftpip
		public final static String FTP_SERVER_PORT = p.getProperty("FTP_SERVER_PORT");
		public final static String FTP_SERVER_USER = p.getProperty("FTP_SERVER_USER");
		public final static String FTP_SERVER_PASSWORD = p.getProperty("FTP_SERVER_PASSWORD");
		public final static String FTP_108_ORIGINAL_FILEPATH = p.getProperty("FTP_108_ORIGINAL_FILEPATH");
		public final static String FTP_DATA_ORIGINAL_FILEPATH = p.getProperty("FTP_DATA_ORIGINAL_FILEPATH");
		public final static String GET_FILE_ROWS_COMMAND = p.getProperty("GET_FILE_ROWS_COMMAND");
		public final static String RENAME_FILE_COMMAND = p.getProperty("RENAME_FILE_COMMAND");
		
		//创建用户、密码、目录的shell
		public final static String FTP_CHANGE_PASSWD_SHELL = p.getProperty("FTP_CHANGE_PASSWD_SHELL");
		public final static String FTP_CREATE_USER_SHELL = p.getProperty("FTP_CREATE_USER_SHELL");
		public final static String FTP_CREATE_PATH_SHELL = p.getProperty("FTP_CREATE_PATH_SHELL");
		public final static String FTP_PUSH_FILE_SHELL = p.getProperty("FTP_PUSH_FILE_SHELL");
		//创建虚拟ftp用户和密码
		public final static String VUSER_FTP_CREATE_SHELL = p.getProperty("VUSER_FTP_CREATE_SHELL");
		public final static String VUSER_FTP_UPDATE_SHELL = p.getProperty("VUSER_FTP_UPDATE_SHELL");
		//ftp连接检查脚本
		public final static String FTP_CHECK_SHELL = p.getProperty("FTP_CHECK_SHELL");

		//定时工单文件路径
		public final static String ORD_FILE_PATH = p.getProperty("ORD_FILE_PATH");

		//路径分割符
		public final static String PATH_DECOLLATOR = p.getProperty("PATH_DECOLLATOR");

		//定时任务扫描标识
		public final static String SCAN_FLAG = p.getProperty("SCAN_FLAG");



}
