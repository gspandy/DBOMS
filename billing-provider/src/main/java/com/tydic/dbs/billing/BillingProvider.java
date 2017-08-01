package com.tydic.dbs.billing;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class BillingProvider
{
	static {
		PropertyConfigurator.configure("/home/dboms/conf/billing-log4j.properties");
	}
	private static final Logger logger = Logger.getLogger(BillingProvider.class);
	
	public static void main(String[] args) throws Exception
	{
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
			new String[] {
					"classpath*:billing-datasource.xml",
					"classpath*:billing-dao.xml",
					"classpath*:billing-service.xml",
					"classpath*:billing-provider.xml"
				}
		);
		context.start();
		logger.info("Billing provider is started ...");
//		System.in.read();
		while(true) {
			Thread.sleep(60000);
			logger.info("...");
		}
	}
}
