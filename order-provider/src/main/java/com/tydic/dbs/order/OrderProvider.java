package com.tydic.dbs.order;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class OrderProvider
{
	static {
		PropertyConfigurator.configure("/home/dboms/conf/order-log4j.properties");
	}
	private static final Logger logger = Logger.getLogger(OrderProvider.class);
	
	public static void main(String[] args) throws Exception
	{
		try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
					new String[]{
							"classpath*:order-datasource.xml",
							"classpath*:buyer-dao.xml",
							"classpath*:buyer-service.xml",
							"classpath*:billing-dao.xml",
							"classpath*:billing-service.xml",
							"classpath*:order-dao.xml",
							"classpath*:order-service.xml",
							"classpath*:order-provider.xml"
					}
			);
			context.start();
			logger.info("Order provider is started ...");
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
