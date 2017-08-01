package com.tydic.dbs.commons.sms;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * @file  SmsLogFactory.java
 * @author caipeimin
 * @version 0.1
 * @短信日志工厂
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-04-09 18:41:49
 *      	Author: caipeimin
 *      	Modification: this file was created
 *   	2. ...
 */
public class SmsLogFactory {
	private static final String DEFAULT_DATE_FORMAT = "yyyyMMdd";
	
	private static Logger logger;

	private SmsLogFactory() {

	}

	public static Logger getLogger(String fileName) {
		if (logger == null) {
			initialize(fileName);
		}
		return logger;
	}

	private static void initialize(String fileName) {
		try {
			if (fileName == null || "".equals(fileName)) {
				throw new NullPointerException("fileName can't null");
			}
			File logFile = new File(SmsConfig.getInstance().getDaemonlogdir() + fileName + ".log."
					+ new SimpleDateFormat(DEFAULT_DATE_FORMAT).format(new Date()));
			if (!logFile.exists()) {
				logFile.createNewFile();
			}

			logger = Logger.getLogger(fileName);
			FileHandler fileHandler = new FileHandler(
					logFile.getAbsolutePath(), true);
			fileHandler.setFormatter(new LogFormatter());
			logger.addHandler(fileHandler);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}

class LogFormatter extends Formatter {
	private SimpleDateFormat dateFormat;

	public LogFormatter() {
		dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}

	@Override
	public String format(LogRecord record) {
		return dateFormat.format(new Date(record.getMillis())) + "|"
				+ record.getLevel() + "|" + record.getMessage() + "\n";
	}
}
