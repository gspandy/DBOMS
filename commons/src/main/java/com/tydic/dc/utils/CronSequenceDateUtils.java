/**
 * com.tydic.commons.utils.Utils.java
 */
package com.tydic.dc.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

import org.springframework.scheduling.support.CronSequenceGenerator;

/**
 * @file CronSequenceDateUtils.java
 * @version 0.1
 * @todo 定时任务执行时间字符串跟Date类型的转换类
 */
public class CronSequenceDateUtils {


	/**
	 * 根据 spring cron express 表达式,输入时间获取下次时间
	 * @description TODO
	 * @param express 表达式
	 * @param date 时间
	 * @return 下次时间
	 */
	public static Date getNextDate(String express, Date date) {

		try{
			if(date==null){
				date = new Date();
			}
			// SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			CronSequenceGenerator cg = new CronSequenceGenerator(express,
					TimeZone.getDefault());
			return cg.next(date);
		}catch(Exception e){
			return null;
		}
	}
	
	public static void main(String[] args) {
	
		System.out.println(CronSequenceDateUtils.getNextDate("0 0/3 * * * *", null));
		//System.out.println(CronSequenceDateUtils.getNextDate("0 0/3 * * * *",CronSequenceDateUtils.getNextDate("0 0 0 1 1/6 *", new Date())));
		
	}

}
