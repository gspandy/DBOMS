package com.tydic.dbs.buyer;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class BuyerProvider
{
	static {
		PropertyConfigurator.configure("/home/dboms/conf/buyer-log4j.properties");
	}
	private static final Logger logger = Logger.getLogger(BuyerProvider.class);
	
	public static void main(String[] args) throws Exception
	{
		try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
					new String[]{
							"classpath*:buyer-datasource.xml",
							"classpath*:buyer-dao.xml",
							"classpath*:buyer-service.xml",
							"classpath*:buyer-provider.xml"
					}
			);
			context.start();
			logger.info("Buyer provider is started ...");
//		System.in.read();
			while (true) {
				Thread.sleep(60000);
				logger.info("...");
			}
		}catch (Exception e){
			logger.error("ERROR",e);
		}
	}
}
