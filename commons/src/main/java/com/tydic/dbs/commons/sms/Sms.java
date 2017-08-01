package com.tydic.dbs.commons.sms;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.huawei.insa2.comm.sgip.message.SGIPSubmitMessage;
import com.huawei.insa2.util.Args;
import com.huawei.insa2.util.Cfg;
import com.huawei.smproxy.SGIPSMProxy;

/**
 * @file  Sms.java
 * @author caipeimin
 * @version 0.1
 * @发送短信
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-04-09 18:41:49
 *      	Author: caipeimin
 *      	Modification: this file was created
 *   	2. ...
 */
public class Sms {
	
	private static Properties submitConfig = null;
	
	private static Logger logger = Logger.getLogger(Sms.class);
	
	private static SGIPSMProxy proxy = null;
	
	private static long modifyTime = 0;
	
	private static long proxyModifyTime = 0;
	
	private final static String CONNECT_FILE = SmsConfig.getInstance().getConnectFile();
	
	private final static String CONFIG_FILE = SmsConfig.getInstance().getConfigFile();
	
	//20101009 added by lyz
	private final static int MAX_CONTENT_LENTH = 50;
	
	private static synchronized SGIPSMProxy getProxy(){
		File sFile = new File(CONNECT_FILE);
		if(proxy == null || proxyModifyTime !=sFile.lastModified()) {
			try {
				Args arg = new Cfg(CONNECT_FILE).getArgs("SGIPConnect");
				proxy = new SGIP12SMProxy(arg);
				proxyModifyTime = sFile.lastModified();
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}			
		return proxy;
	}
	
	private static synchronized Properties getProperties() {
		File file = new File(CONFIG_FILE);
		if(submitConfig == null || modifyTime != file.lastModified()){
	        submitConfig = new Properties();
	        FileInputStream is = null;
			try {
				is = new FileInputStream(file);
				submitConfig.load(is);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (is != null) {
					try {
						is.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
	        modifyTime = file.lastModified();
	        logger.info("加载发送配置" + modifyTime);
		}
		return submitConfig;
	}

	/**
	 * @description: <发送短信>
	 * @param:
	 * @return:返回1为成功
	 * @throws:
	 */	
	public  int[] sendMessage(String[] mobiles,String content){
		long sendBefore = System.currentTimeMillis();
		int result[]=new int[mobiles.length];
		boolean connect = false;
		try {
			connect = getProxy().connect(null, null);
			long connEnd = System.currentTimeMillis();
			logger.info("创建连接耗时：" + (connEnd - sendBefore) + "ms");
			
			if(!connect){
				logger.error("连接错误消息");
				return result;
			}
			String SPNumber = changeString(getProperties().getProperty("SPNumber"));//SP的接入号码
			String ChargeNumber = changeString(getProperties().getProperty("ChargeNumber"));//付费号码
			String[] UserNumber = mobiles;//接收该短消息的手机号，最多100个号码
			String CorpId = changeString(getProperties().getProperty("CorpId"));//企业代码，取值范围0-99999
			String ServiceType = changeString(getProperties().getProperty("ServiceType"));//业务代码，由SP定义
			int FeeType = Integer.parseInt(changeString(getProperties().getProperty("FeeType")));//计费类型，1：对“计费用户号码”免费
			String FeeValue = changeString(getProperties().getProperty("FeeValue"));//该条短消息的收费值,以分为单位
			String GivenValue = changeString(getProperties().getProperty("GivenValue"));//赠送用户的话费
			int AgentFlag = Integer.parseInt(changeString(getProperties().getProperty("AgentFlag")));//代收费标志，0：应收；1：实收
			int MorelatetoMTFlag = Integer.parseInt(changeString(getProperties().getProperty("MorelatetoMTFlag")));//引起MT消息的原因
			int Priority = Integer.parseInt(changeString(getProperties().getProperty("Priority")));//优先级0-9从低到高，默认为0
			Date ExpireTime = null;//短消息寿命的终止时间
			Date ScheduleTime = null;//短消息定时发送的时间
			int ReportFlag = Integer.parseInt(changeString(getProperties().getProperty("ReportFlag")));//状态报告标记
			int TP_pid = Integer.parseInt(changeString(getProperties().getProperty("TP_pid")));//GSM协议类型
			//20101009 要支持长短信必须将此属性设置为1
			int TP_udhi = Integer.parseInt(changeString(getProperties().getProperty("TP_udhi")));//GSM协议类型
			int MessageCoding = Integer.parseInt(changeString(getProperties().getProperty("MessageCoding")));//短消息的编码格式
			int MessageType = Integer.parseInt(changeString(getProperties().getProperty("MessageType")));//信息类型
			String  reserve = changeString(getProperties().getProperty("reserve"));//保留，扩展用
	
			List<String> contentList = splitContent(content, MAX_CONTENT_LENTH);
			List<byte[]> byteList = byteContent(contentList, changeString(getProperties().getProperty("Coding3")));
			
			//网关不支持群发，只能循环发送
			for (int i = 0; i < UserNumber.length; i++) {
				String[] newUserNumber = {"86"+UserNumber[i]};
				for (int j = 0; j < byteList.size(); j++) {
					SGIPSubmitMessage submitMsg = new SGIPSubmitMessage(SPNumber,ChargeNumber,newUserNumber,CorpId,ServiceType,FeeType,
							FeeValue,GivenValue,AgentFlag,MorelatetoMTFlag,Priority,ExpireTime,ScheduleTime,ReportFlag,TP_pid,TP_udhi,
							MessageCoding,MessageType,byteList.get(j).length,byteList.get(j),reserve);
					try {
						//20110415 hide by lyz
						//SGIPSubmitRepMessage message = (SGIPSubmitRepMessage)proxy.send(submitMsg);
						proxy.send(submitMsg);
						logger.info("发送短信队列：" + getSendNum() + "; 接收号码："+ newUserNumber[0] + ",短信内容" + (j+1) + ": " + new String(byteList.get(j), changeString(submitConfig.getProperty("Coding3")))+"[发送：成功]");
						//20110415 hide by lyz
						//result[i]=message.getResult()== 0?1:0;
						result[i] = 1;
					} catch (IOException e) {
						e.printStackTrace();
						logger.error("连接状态："+proxy.getConnState()+",错误消息：" + e.toString());
					}
				}
			}		
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(proxy !=null){
				proxy.close();
			}
		}	
//		logger.info(currCount+"短信发送耗时：" + (System.currentTimeMillis() - sendBefore) + "ms");
		return result;
	}
	
	private static long sendNum;
	
	private static synchronized long getSendNum() {
		return sendNum++;
	}
	
	public static String changeString(String input){
		if(null == input || "null".equalsIgnoreCase(input)){
			return null;
		}else{
			return input.trim();
		}
	}
	
	/**
	 * 拆分短信内容
	 * 
	 * @param content 要拆分的短信内容
	 * @param maxLen 拆分后内容的最大长度
	 * @return
	 */
	public static List<String> splitContent(String content, int maxLen) {
		List<String> messages = new ArrayList<String>();
		if (content.length() <= maxLen) { //短信内容总长度小于等于拆分内容最大长度,不用拆分直接返回
			messages.add(content);
		} else { //短信内容大于拆分内容总长度, 每次截取maxLen个字符并保存在List中
			int count = (int)Math.round((double)content.length()/(double)maxLen + 0.5f); //最多需要拆分的短信条数(取整数)
			//循环count次拆分短信内容
			for (int i = 0; i < count; i++) {
				if ((i+1)*maxLen > content.length()) {
					messages.add(content.substring(i*maxLen));
				} else {
					messages.add(content.substring(i*maxLen, (i+1)*maxLen));
				}
			}
		}
		return messages;
	}
	
	private static final int TP_UDHI_HEAD_LENGTH = 7;
	/**
	 * 为每条短信加上7字节头信息
	 * 
	 * @param contents
	 * @param charset
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static List<byte[]> byteContent(List<String> contents, String charset) throws UnsupportedEncodingException {
		List<byte[]> byteContentList = new ArrayList<byte[]>();
		byte[] tmp = null;
		for (int i = 0; i < contents.size(); i++) {
			byte[] content = contents.get(i).getBytes(charset);
			tmp = new byte[TP_UDHI_HEAD_LENGTH + content.length];
			//7字节TP_udhi头, 格式06 08 04 XX XX MM NN
			tmp[0] = 6;  //6, 表示剩余协议头的长度
			tmp[1] = 8;  //8, 在GSM 03.40规范9.2.3.24.1中规定，表示随后的这批超长短信的标识位长度为2(格式中的XX值)
			tmp[2] = 4;  //4, 表示剩下短信标识的长度
			tmp[3] = 0;  // XX
			tmp[4] = 39; // XX 这批短信的唯一标志，事实上，SME(手机或者SP)把消息合并完之后，就重新记录，所以这个标志是否唯 一并不是很重要
			tmp[5] = (byte)contents.size(); //MM, 短信的数量。如果一个超长短信总共5条，这里的值就是5
			tmp[6] = (byte)(i+1);           //NN, 短信的数量。如果当前短信是这批短信中的第一条的值是1，第二条的值是2...
			System.arraycopy(content, 0, tmp, TP_UDHI_HEAD_LENGTH, content.length);
			byteContentList.add(tmp);
		}
		return byteContentList;
	}

	public static void main(String[] args){		
//		Sms.sendPurchase(new String[]{"18611111111"} ,"123");
	}
}
