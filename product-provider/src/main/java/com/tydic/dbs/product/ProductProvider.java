package com.tydic.dbs.product;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class ProductProvider
{
	static {
		PropertyConfigurator.configure("/home/dboms/conf/product-log4j.properties");
	}
	private static final Logger logger = Logger.getLogger(ProductProvider.class);
	
	public static void main(String[] args) throws Exception
	{
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
			new String[] {
					"classpath*:product-datasource.xml",
					"classpath*:product-dao.xml",
					"classpath*:product-service.xml",
					"classpath*:product-provider.xml"
				}
		);
		context.start();
		logger.info("Product provider is started ...");
//		System.in.read();
		while(true) {
			Thread.sleep(60000);
			logger.info("...");
		}
	}
}
