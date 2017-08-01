package com.tydic.dbs.system.sms.bo;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

import com.tydic.dbs.commons.constant.ConfigConstants;

import com.tydic.dbs.commons.sms.webService.sms.SmsResponse;
import com.tydic.dbs.commons.sms.webService.sms.UserAppSmsSendWebServiceLocator;
import com.tydic.dbs.commons.sms.webService.sms.UserAppSmsSendWebServicePortType;
import com.tydic.dbs.system.sms.mapper.SysSms;
import com.tydic.dbs.system.sms.service.DBsSmsService;





@Service
public class DBsSmsServiceImpl implements DBsSmsService {
	
	private static final Log logger = LogFactory.getLog(DBsSmsServiceImpl.class);
	
	//发送短信
	public SmsResponse sendSms(SysSms vo) throws Exception{
		List<String> xmlList = createRequestXml(vo);
		SmsResponse smsResponse = null;
		for (String requestXml : xmlList) {
			smsResponse = callService(requestXml);
		}
		return smsResponse;
	}
	
	private List<String> createRequestXml(SysSms vo){//GwSmsVO smsVO短信实体
		List<String> xmlList = new ArrayList<String>();
		String[] mobiles = vo.getCalledNum().split("\\|");
		String strContent = vo.getContent();
		for (int i = 0; i < mobiles.length; i++) {
			Document document = DocumentHelper.createDocument();
			Element root = document.addElement("request");
			root.addElement("userId");
			root.addElement("customerId").addText(ConfigConstants.SMS_CUSTOMER_ID);
			root.addElement("customerPassport").addText(ConfigConstants.SMS_CUSTOMER_PASSPORT);
			root.addElement("senderName").addText("中国联通");
			root.addElement("strmobileNumber").addText(mobiles[i]);
			root.addElement("strContent").addText(strContent);
			
			xmlList.add(root.asXML());
		}
		return xmlList;
	}
	
	private static SmsResponse parseSmsResponse(String responseXml) throws DocumentException{
		Document document = DocumentHelper.parseText(responseXml);
		
		Element root = document.getRootElement();
		Element valueEle = root.element("returnvalue");
		Element operatingEle = root.element("operatingreturn");
		
		SmsResponse smsResponse = new SmsResponse();
		smsResponse.setReturnvalue(valueEle.getText());
		smsResponse.setOperatingreturn(operatingEle.getText());
		return smsResponse;
	}
	
	private SmsResponse callService(String requestXml){
		try {
			UserAppSmsSendWebServiceLocator locator = new UserAppSmsSendWebServiceLocator();
			UserAppSmsSendWebServicePortType smsService = locator.getUserAppSmsSendWebServiceHttpPort(new URL(ConfigConstants.SMS_WEBSERVICE_URL));
			
			logger.info("短信发送接口请求报文："+requestXml);
			String returnText = smsService.userAppSmsSend(requestXml);
			logger.info("短信发送接口返回报文："+returnText);
			return parseSmsResponse(returnText);
		} catch (Exception e) {
			logger.error("调用短信发送接口失败："+e.getMessage(), e);
			e.printStackTrace();
			SmsResponse smsResponse = new SmsResponse();
			smsResponse.setReturnvalue(DBsSmsService.SMS_FAILURE);
			smsResponse.setOperatingreturn("调用短信发送接口失败："+e.getMessage());
			return smsResponse;
		}
	}
}
