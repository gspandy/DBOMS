package com.tydic.dbs.system.sms.service;

import com.tydic.dbs.commons.sms.webService.sms.SmsResponse;
import com.tydic.dbs.system.sms.mapper.SysSms;

/**
 * DBsFileService:(发送短信接口)
 * @author Michael-Deng
 *
 */
public interface DBsSmsService {
	
	public final static String SMS_SUCCESS = "1";
	public final static String SMS_FAILURE = "0";

	public SmsResponse sendSms(SysSms vo) throws Exception;//GwSmsVO smsVO短信实体
}
