package com.tydic.dbs.commons.sms;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @file  SmsConfig.java
 * @author caipeimin
 * @version 0.1
 * @发送短信配置类
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-04-09 18:41:49
 *      	Author: caipeimin
 *      	Modification: this file was created
 *   	2. ...
 */
public class SmsConfig {
	private static Properties properties = new Properties();
	
	private static SmsConfig instance;
	
	public static synchronized SmsConfig getInstance(){
		if(instance==null){
			instance = new SmsConfig();
			InputStream is = SmsConfig.class.getClassLoader().getResourceAsStream("com/sunrise/wcs/commons/sms/sms-config.properties");
			try {
				properties.load(is);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			instance.setDriver(properties.getProperty("jdbc.driverClassName"));
			instance.setUrl(properties.getProperty("jdbc.url"));
			instance.setUser(properties.getProperty("jdbc.username"));
			instance.setPassword(properties.getProperty("jdbc.password"));
			instance.setInitconns(properties.getProperty("jdbc.initconns"));
			instance.setMaxconns(properties.getProperty("jdbc.maxconns"));
			instance.setLogintimeout(properties.getProperty("jdbc.logintimeout"));
			instance.setDaemonlogdir(properties.getProperty("daemon.log.dir"));
			instance.setConnectFile(properties.getProperty("connect_file"));
			instance.setConfigFile(properties.getProperty("config_file"));
		}
		return instance;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getInitconns() {
		return initconns;
	}

	public void setInitconns(String initconns) {
		this.initconns = initconns;
	}

	public String getLogintimeout() {
		return logintimeout;
	}

	public void setLogintimeout(String logintimeout) {
		this.logintimeout = logintimeout;
	}

	public String getMaxconns() {
		return maxconns;
	}

	public void setMaxconns(String maxconns) {
		this.maxconns = maxconns;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getAdlogdir() {
		return adlogdir;
	}

	public void setAdlogdir(String adlogdir) {
		this.adlogdir = adlogdir;
	}
	
	public String getDaemonlogdir() {
		return daemonlogdir;
	}

	public void setDaemonlogdir(String daemonlogdir) {
		this.daemonlogdir = daemonlogdir;
	}

	public String getConnectFile() {
		return connectFile;
	}

	public void setConnectFile(String connectFile) {
		this.connectFile = connectFile;
	}

	public String getConfigFile() {
		return configFile;
	}

	public void setConfigFile(String configFile) {
		this.configFile = configFile;
	}

	private String driver;

	private String url;

	private String user;

	private String password;

	private String initconns;

	private String maxconns;

	private String logintimeout;
	
	private String adlogdir;
	
	private String daemonlogdir;
	
	private String connectFile;
	
	private String configFile;
}
