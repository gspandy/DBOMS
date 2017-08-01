package com.tydic.dbs.system;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class SystemProvider 
{
	static {
		PropertyConfigurator.configure("/home/dboms/conf/system-log4j.properties");
	}
	private static final Logger logger = Logger.getLogger(SystemProvider.class);
	
	public static void main(String[] args) throws Exception
	{
		try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
					new String[]{
							"classpath*:system-datasource.xml",
							"classpath*:system-dao.xml",
							"classpath*:commons-dao.xml",
							"classpath*:system-service.xml",
							"classpath*:system-provider.xml"
					}
			);
			context.start();
			logger.info("System provider is started ...");
//		System.in.read();
			while (true) {
				Thread.sleep(60000);
				logger.info("...");
			}
		}catch(Exception e){
			logger.error("ERROR:",e);
		}

	}
}
