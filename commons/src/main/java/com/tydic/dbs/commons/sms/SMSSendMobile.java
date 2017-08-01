  package com.tydic.dbs.commons.sms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.lang3.ArrayUtils;

import com.tydic.dbs.commons.define.WcsDefinition;

/**
 * @file SMSSendMobile.java
 * @author caipeimin
 * @version 0.1
 * @定时发送短信
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-04-09 18:41:49
 *      	Author: caipeimin
 *      	Modification: this file was created
 *   	2. ...
 */
public class SMSSendMobile {
	
	private static final Logger logger = SmsLogFactory.getLogger(SMSMobile.class.getSimpleName());

	private static final Integer SMS_MOBILES=60;
	
	private static Integer priority = 1;
	private static Integer interval = 30000;	//毫秒
	
	/**
	 * @description: <方法主入口>
	 * @param:
	 * @return:
	 * @throws: 
	 */
	public static void main(String[] args) {
		System.setProperty("file.encoding", "UTF-8");
		if (ArrayUtils.isNotEmpty(args)) {
			priority = Integer.valueOf(args[0]);
			interval = Integer.valueOf(args[1]);
		}
		
		while(true) {
			execute();
			try {
				Thread.sleep(interval);
				logger.info("SMS poller is alive...");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	 /***
		 * 
		 * @description: <>
		 * @param:
		 * @return:
		 * @throws:
		 */
		
		private static ArrayList<String> konghao = new ArrayList<String>();
		static{
			konghao.add("18600000000");
			konghao.add("18611111111");
			konghao.add("15644444444");
			konghao.add("13000000000");
			konghao.add("15600000000");
			konghao.add("15611111111");
			konghao.add("13111111111");
			konghao.add("18602030610");
			konghao.add("18688283537");//佛山黄维号码
			konghao.add("18676659792");//函总的老号码
			konghao.add("15602695742");//柏涛提供的老号码
			konghao.add("18675987789");//柏涛提供的老号码
			konghao.add("18675998813");//柏涛提供的老号码
			konghao.add("18688249911");//柏涛提供的老号码
			konghao.add("18676275325");//柏涛提供的老号码
			konghao.add("18688237764");//柏涛提供的老号码
			konghao.add("13138181926");//柏涛提供的号码
			konghao.add("18620018829");//柏涛提供的号码
			konghao.add("15602600000");//柏涛提供的号码
			konghao.add("18620018655");//柏涛提供的号码
		}
	
		@SuppressWarnings("static-access")
		private static void execute(){
			Connection conn=null;		
			List<SMSMobile> mobileList;
			try{
				conn=SmsConnPool.getConnection();
				mobileList=findSMSMobileList(conn, priority);
				int count=mobileList.size();
				logger.info("本次短信发送开始........"+count);
				if(count>0){
			       	for(int i=0;i<count;i++){
			       		SMSMobile smsMobile = mobileList.get(i);
			       		if(konghao.contains(smsMobile.getMobileNumber())){
			       			logger.info("请不要给空号发短信，发送号码："+smsMobile.getMobileNumber());
			       			//删除空号的发送任务
			       			deleteSendTask(smsMobile, conn);
			       			continue;			       			
			       		}
			       		Sms sms = new Sms();
			       		int[] smsSendResult=sms.sendMessage(new String[]{smsMobile.getMobileNumber()},smsMobile.getSmsContent());
						for(int k=0;k<smsSendResult.length;k++){
							//发送失败
							if(smsSendResult[k]==0){
								logger.info("发送失败，号码"+smsMobile.getMobileNumber() + "，编号：" + smsMobile.getSendTaskId());
								//更新重试次数
								if (smsMobile.getHaveRepeatTimes() == null) {
									smsMobile.setHaveRepeatTimes(0);
								} else {
									smsMobile.setHaveRepeatTimes(smsMobile.getHaveRepeatTimes() + 1);
								}
								//如果重试次数已经达到预设值，则标记该短信发送失败，删除发送任务，新建发送结果
								if (smsMobile.getHaveRepeatTimes().equals(smsMobile.getRepeatTimes())) {
									smsMobile.setSendResult(WcsDefinition.WcsSmsSendResult.WCS_FAILURE);
									smsMobile.setFinishTime(new Date());
									insertSendResult(smsMobile, conn);
									deleteSendTask(smsMobile, conn);
								} else {
									updateSendTask(smsMobile, conn);
								}
							}
							//发送成功
							if(smsSendResult[k]==1){
								//删除发送任务，新建发送结果
								smsMobile.setFinishTime(new Date());
								insertSendResult(smsMobile, conn);
								deleteSendTask(smsMobile, conn);
							}
						}
						logger.info("中间休息80 ms ........"+smsMobile.getMobileNumber());
						Thread.sleep(80);
			        }
				}
				logger.info("本次短信发送结束********"+count);
			}catch(Exception e){
				e.printStackTrace();
				logger.info("系统错误，定时群发短信失败"+e);
			}finally{
				SmsConnPool.closeConnection(conn, null, null);
			}
		} 
	
	/****
	 * 
	 * @description: <查询需要发送订单的号码>
	 * @param:
	 * @return:
	 * @throws:
	 */
	private static List<SMSMobile> findSMSMobileList(Connection conn, Integer interval){
		List<SMSMobile> list=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="SELECT S.*, S2.TEMPLATE_CONTENT, S2.STATUS FROM SMS_SEND_TASK S, SMS_TEMPLATE S2 WHERE S.PRIORITY_LEVEL = ? AND S.FINISH_TIME IS NULL AND ROWNUM <= ? AND S.SMS_TEMPLATE_CODE = S2.SMS_TEMPLATE_CODE(+)";
		try{
			list=new ArrayList<SMSMobile>();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, interval);
			pstmt.setInt(2, SMSSendMobile.SMS_MOBILES);
			rs=pstmt.executeQuery();
			while(rs.next()){
				SMSMobile mobile = new SMSMobile();
				mobile.setSendTaskId(rs.getLong("SEND_TASK_ID"));
				mobile.setSmsTemplateCode(rs.getString("SMS_TEMPLATE_CODE"));
				mobile.setSmsContent(rs.getString("SMS_CONTENT"));
				mobile.setSendTime(rs.getTimestamp("SEND_TIME"));
				mobile.setMobileNumber(rs.getString("MOBILE_NUMBER"));
				mobile.setPriorityLevel(rs.getInt("PRIORITY_LEVEL"));
				mobile.setSourceSystem(rs.getString("SOURCE_SYSTEM"));
				mobile.setRepeatTimes(rs.getInt("REPEAT_TIMES"));
				mobile.setHaveRepeatTimes(rs.getInt("HAVE_REPEAT_TIMES"));
				mobile.setRepeatSpace(rs.getInt("REPEAT_SPACE"));
				mobile.setRiseTime(rs.getTimestamp("RISE_TIME"));
				mobile.setFinishTime(rs.getTimestamp("FINISH_TIME"));
				mobile.setCreater(rs.getString("CREATER"));
				mobile.setCreateTime(rs.getTimestamp("CREATE_TIME"));
				mobile.setModifier(rs.getString("MODIFIER"));
				mobile.setModifyTime(rs.getTimestamp("MODIFY_TIME"));
				
				mobile.setTemplateContent(rs.getString("TEMPLATE_CONTENT"));
				mobile.setTemplateStatus(rs.getString("STATUS"));
				list.add(mobile);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			SmsConnPool.closeConnection(null, pstmt, rs);
		}
		return list;
	}
	
	/***
	 * 
	 * @description: <发送短信成功，修改号码状态为1>
	 * @param:
	 * @return:
	 * @throws:
	 */
	private static boolean updateSendTask(SMSMobile mobile,Connection conn){
		boolean flag=false;
		PreparedStatement pstmt=null;
		String sql="UPDATE SMS_SEND_TASK SET HAVE_REPEAT_TIMES = ? WHERE SEND_TASK_ID = ?";
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, mobile.getHaveRepeatTimes());
			pstmt.setLong(2, mobile.getSendTaskId());
			pstmt.executeUpdate();
			flag=true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			SmsConnPool.closeConnection(null, pstmt, null);
		}
		return flag;
	}
	
	/***
	 * 
	 * @description: <发送短信成功，删除发送任务记录>
	 * @param:
	 * @return:
	 * @throws:
	 */
	private static boolean deleteSendTask(SMSMobile mobile,Connection conn){
		boolean flag=false;
		PreparedStatement pstmt=null;
		String sql="DELETE FROM SMS_SEND_TASK WHERE SEND_TASK_ID = ?";
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setLong(1, mobile.getSendTaskId());
			pstmt.execute();
			flag=true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			SmsConnPool.closeConnection(null, pstmt, null);
		}
		return flag;
	}
	
	/***
	 * 
	 * @description: <发送短信成功，保持发送结果>
	 * @param:
	 * @return:
	 * @throws:
	 */
	private static boolean insertSendResult(SMSMobile mobile,Connection conn){
		boolean flag=false;
		PreparedStatement pstmt=null;
		String sql="INSERT INTO SMS_SEND_RESULT " +
				"(SEND_RESULT_ID, SMS_TEMPLATE_CODE, SMS_CONTENT, SEND_TIME, MOBILE_NUMBER, PRIORITY_LEVEL, SOURCE_SYSTEM, RISE_TIME, FINISH_TIME, SEND_RESULT, " +
				"CREATE_TIME, CREATER, MODIFY_TIME, MODIFIER) VALUES (SEQ_SMS_SEND_RESULT.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, mobile.getSmsTemplateCode());
			pstmt.setString(2, mobile.getSmsContent());
			if (mobile.getSendTime() != null)
				pstmt.setTimestamp(3, new Timestamp(mobile.getSendTime().getTime()));
			else
				pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			pstmt.setString(4, mobile.getMobileNumber());
			pstmt.setInt(5, mobile.getPriorityLevel());
			pstmt.setString(6, mobile.getSourceSystem());
			pstmt.setTimestamp(7, new Timestamp(mobile.getRiseTime().getTime()));
			pstmt.setTimestamp(8, new Timestamp(mobile.getFinishTime().getTime()));
			pstmt.setString(9, mobile.getSendResult());
			pstmt.setTimestamp(10, new Timestamp(mobile.getCreateTime().getTime()));
			pstmt.setString(11, mobile.getCreater());
			pstmt.setTimestamp(12, new Timestamp(mobile.getModifyTime().getTime()));
			pstmt.setString(13, mobile.getModifier());
			pstmt.execute();
			flag=true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			SmsConnPool.closeConnection(null, pstmt, null);
		}
		return flag;
	}
}
