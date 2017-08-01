package com.tydic.dbs.system.email.bo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tydic.dbs.system.email.service.SendEmailService;

public class SendEmailServiceBo implements SendEmailService {
	private final Log log = LogFactory.getLog(SendEmailServiceBo.class);
	//邮件发送参数信息
	private static Properties prop = new Properties();
	private static String user;
	private static String fromEmail;
	private static String pwd;

	public static String getUser() {
		return user;
	}

	public static void setUser(String user) {
		SendEmailServiceBo.user = user;
	}

	public static String getFromEmail() {
		return fromEmail;
	}

	public static void setFromEmail(String fromEmail) {
		SendEmailServiceBo.fromEmail = fromEmail;
	}

	public static String getPwd() {
		return pwd;
	}

	public static void setPwd(String pwd) {
		SendEmailServiceBo.pwd = pwd;
	}

	public boolean sendEmail(String toEmail, String subject, String content)
			throws Exception {

		InputStream in = null ;
		try {
			in = SendEmailServiceBo.class.getResourceAsStream("/send-email.properties");  
			prop.load(in);
			user = prop.getProperty("user").trim();   
			fromEmail = prop.getProperty("fromEmail").trim();   
			pwd = prop.getProperty("pwd").trim();   
			log.info("fromEmail : "+fromEmail); 
			log.info("toEmail : "+toEmail);
			//创建会话对象
			Session session = Session.getDefaultInstance(prop, new MyAuthenticator(user,pwd));
			//邮件对象
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromEmail));
			message.setRecipient(RecipientType.TO, new InternetAddress(toEmail));
			message.setSentDate(new Date());
			message.setSubject(subject);
			message.setContent(content, "text/html;charset=utf-8");
			message.saveChanges();
			
			log.info(message.getFrom()+" - "+message.getRecipients(RecipientType.TO)+" - "+message.getSubject()+" - "+message.getContent());

			log.info("Send Email Begin : "+new Date().toString()); 
			// 发送
			Transport.send(message);
			log.info("Send Email End : "+new Date().toString());
			
			if (in!=null) {
				in.close();
			}
		} catch (Exception e) {
			log.error("ERROR :"+e);
			e.printStackTrace();
			return false;
		} finally {
			if (in!=null) {
				in.close();
			}
		}
		return true;
	}

	// 邮箱认证
	static class MyAuthenticator extends Authenticator{
		private String user;
		private String pwd;
		public MyAuthenticator(String user, String pwd) {
			super();
			this.user = user;
			this.pwd = pwd;
		}
		public MyAuthenticator() {}
		
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(user, pwd);
		}
	}
}
