package com.tydic.dbs.system.email.service;

/**
 * DBsEmailService:(发邮件接口)
 * @author huangChuQin
 *
 */
public interface SendEmailService {
	/**
	 * 
	 * @param toEmail 收件邮箱地址
	 * @param subject 邮件主题
	 * @param content 邮件内容
	 * @return
	 */
	public boolean sendEmail(String toEmail,String subject,String content) throws Exception;
}
