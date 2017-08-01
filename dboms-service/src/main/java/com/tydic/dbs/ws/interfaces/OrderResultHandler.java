package com.tydic.dbs.ws.interfaces;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tydic.commons.utils.OrderUtils;
import com.tydic.dbs.buyer.service.InterfaceLogService;
import com.tydic.dbs.buyer.vo.InterfaceLog;
import com.tydic.dbs.commons.constant.ConfigConstants;
import com.tydic.dbs.commons.enums.DealFlag;
import com.tydic.dbs.order.service.OrdResultFileService;
import com.tydic.dbs.order.vo.OrdResultFile;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tydic.dbs.commons.enums.OrderStatus;
import com.tydic.dbs.order.service.OrdInfoService;
import com.tydic.dbs.order.vo.OrdInfo;
import com.tydic.dbs.ws.common.Constants;
import com.tydic.dbs.ws.vo.OrderNoticeVO;

/**
 * 
 * @ClassName: OrderResultHandler 
 * @Description: TODO(工单结果数据已生成通知接口) 
 * @author huangChuQin
 * @date 2016-8-5 下午3:23:20 
 *
 */
@Component
public class OrderResultHandler implements Handler {

	private static final Logger log = LoggerFactory.getLogger(Handler.class);

	@Autowired
	private OrdInfoService ordInfoService;

	@Autowired
	private OrdResultFileService ordResultFileService;

	@Autowired
	private InterfaceLogService interfaceLogService;

	@SuppressWarnings("unchecked")
	public Map handle(HttpServletRequest request){
		Map<String,String> map = new HashMap<String,String>();
		InterfaceLog interfaceLog=new InterfaceLog();
		String fileName = request.getParameter(FILE_NAME);
		String ftpInfo = request.getParameter(FTP_INFO);
		String ordId = request.getParameter(WORK_NO);
		String filePath = ConfigConstants.ORD_FILE_PATH;

		try {
			OrdInfo vo = ordInfoService.get(ordId);
			if (vo == null) {
				map.put(Constants.RESULT_FLAG,Constants.ERROR);
				map.put(Constants.MESSAGE,"获取工单数据出错");
				return map;
			}

			if (OrderStatus.EXECUTED.getCode().equals(vo.getOrdStatus())) {
				map.put(Constants.RESULT_FLAG,Constants.ERROR);
				map.put(Constants.MESSAGE,"该工单已执行");
				return map;
			}

			if (OrderStatus.EXECUTING.getCode().equals(vo.getOrdStatus())) {
				map.put(Constants.RESULT_FLAG,Constants.ERROR);
				map.put(Constants.MESSAGE,"该工单重复提交");
				return map;
			}

			//保存数据到工单文件返回表中
			OrdResultFile ordResultFile = new OrdResultFile();
			ordResultFile.setSerialNum(OrderUtils.generateOutTradeNo());
			ordResultFile.setFileName(fileName);
			ordResultFile.setFileUrl(filePath);

			Gson gson = new Gson();
			Map <String,String> ftpMap = gson.fromJson(ftpInfo,new TypeToken<Map<String,String>>() {
			}.getType());

			if(ftpMap != null){
				ordResultFile.setFtpIp(ftpMap.get("ip"));
				ordResultFile.setFtpPort(NumberUtils.toInt(ftpMap.get("port")));
				ordResultFile.setFtpUser(ftpMap.get("user"));
				ordResultFile.setFtpPass(ftpMap.get("password"));
				ordResultFile.setFtpPath(ftpMap.get("file_path"));
			}

			ordResultFile.setOrdId(ordId);
			ordResultFile.setCreateTime(new Date());
			ordResultFile.setState(DealFlag.WAIT_DEAL.getCode());
			ordResultFileService.save(ordResultFile);

			//变更工单状态为正在执行
			OrdInfo tvo = new OrdInfo();
			tvo.setOrdId(vo.getOrdId());
			tvo.setOrdStatus(OrderStatus.EXECUTING.getCode());
			ordInfoService.update(tvo);

			Map paramIn=new HashMap();
			paramIn.put("fileName",fileName);
			paramIn.put("ordId",ordId);
			paramIn.put("filePath",filePath);
			interfaceLog.setParamIn(gson.toJson(paramIn));
			interfaceLog.setBusiType("DATA_NOTICE");
			interfaceLog.setRemark("工单结果数据已生成通知");
			interfaceLog.setSerialNum(request.getParameter("session_id"));

			map.put(Constants.RESULT_FLAG,Constants.SUCCESS);
			map.put(Constants.MESSAGE,"更新工单状态成功");
			interfaceLog.setResultContent(gson.toJson(map));
			interfaceLog.setCreateTime(new Date());
			interfaceLogService.save(interfaceLog);
		} catch (Exception e1) {
			log.error("Error", e1);
			map.put(Constants.RESULT_FLAG, Constants.ERROR);
			map.put(Constants.MESSAGE, "系统异常");
			return map;
		}

		//返回结果
		return map;
	}
	
	public Map handle(OrderNoticeVO orderNoticeVO){
		Map<String,String> map = new HashMap<String,String>();
		InterfaceLog interfaceLog=new InterfaceLog();
		String fileName = orderNoticeVO.getFile_name();
		String ftpInfo = orderNoticeVO.getFtp_info();
		String ordId = orderNoticeVO.getWork_no();
		String filePath = ConfigConstants.ORD_FILE_PATH;

		try {
			OrdInfo vo = ordInfoService.get(ordId);
			if (vo == null) {
				map.put(Constants.RESULT_FLAG,Constants.ERROR);
				map.put(Constants.MESSAGE,"获取工单数据出错");
				return map;
			}

			if (OrderStatus.EXECUTED.getCode().equals(vo.getOrdStatus())) {
				map.put(Constants.RESULT_FLAG,Constants.ERROR);
				map.put(Constants.MESSAGE,"该工单已执行");
				return map;
			}

			if (OrderStatus.EXECUTING.getCode().equals(vo.getOrdStatus())) {
				map.put(Constants.RESULT_FLAG,Constants.ERROR);
				map.put(Constants.MESSAGE,"该工单重复提交");
				return map;
			}

			//保存数据到工单文件返回表中
			OrdResultFile ordResultFile = new OrdResultFile();
			ordResultFile.setSerialNum(OrderUtils.generateOutTradeNo());
			ordResultFile.setFileName(fileName);
			ordResultFile.setFileUrl(filePath);

			Gson gson = new Gson();
			Map <String,String> ftpMap = gson.fromJson(ftpInfo,new TypeToken<Map<String,String>>() {
			}.getType());

			if(ftpMap != null){
				ordResultFile.setFtpIp(ftpMap.get("ip"));
				ordResultFile.setFtpPort(NumberUtils.toInt(ftpMap.get("port")));
				ordResultFile.setFtpUser(ftpMap.get("user"));
				ordResultFile.setFtpPass(ftpMap.get("password"));
				ordResultFile.setFtpPath(ftpMap.get("file_path"));
			}

			ordResultFile.setOrdId(ordId);
			ordResultFile.setCreateTime(new Date());
			ordResultFile.setState(DealFlag.WAIT_DEAL.getCode());
			ordResultFileService.save(ordResultFile);

			//变更工单状态为正在执行
			OrdInfo tvo = new OrdInfo();
			tvo.setOrdId(vo.getOrdId());
			tvo.setOrdStatus(OrderStatus.EXECUTING.getCode());
			ordInfoService.update(tvo);

			Map paramIn=new HashMap();
			paramIn.put("fileName",fileName);
			paramIn.put("ordId",ordId);
			paramIn.put("filePath",filePath);
			interfaceLog.setParamIn(gson.toJson(paramIn));
			interfaceLog.setBusiType("DATA_NOTICE");
			interfaceLog.setRemark("工单结果数据已生成通知");
			interfaceLog.setSerialNum(orderNoticeVO.getSession_id());

			map.put(Constants.RESULT_FLAG,Constants.SUCCESS);
			map.put(Constants.MESSAGE,"更新工单状态成功");
			interfaceLog.setResultContent(gson.toJson(map));
			interfaceLog.setCreateTime(new Date());
			interfaceLogService.save(interfaceLog);
		} catch (Exception e1) {
			log.error("Error", e1);
			map.put(Constants.RESULT_FLAG, Constants.ERROR);
			map.put(Constants.MESSAGE, "系统异常");
			return map;
		}

		//返回结果
		return map;
	}



	/**
	 * 检查参数是否合法
	 * 如果有异常，返回错误信息
	 * 没有异常，返回空
	 *
	 * @return
	 */
	@Override
	public String checkParams(HttpServletRequest request){
		String result = null;
		Map params = request.getParameterMap();

		//TODO 只检查了参数是否存在，没检查参数格式
		if(!params.containsKey(SERVICE_NAME_KEY)){
			result = SERVICE_NAME_KEY;
		}else if(!params.containsKey(SESSION_ID_KEY)){
			result = SESSION_ID_KEY;
		}if(!params.containsKey(INPUT_CHARSET_KEY)){
			result = INPUT_CHARSET_KEY;
		}if(!params.containsKey(SIGN)){
			result = SIGN;
		}if(!params.containsKey(WORK_NO)){
			result = WORK_NO;
		}if(!params.containsKey(FILE_NAME)){
			result = FILE_NAME;
		}if(!params.containsKey(FTP_INFO)){
			result = FTP_INFO;
		}

		return result;
	}

	@Override
	public String getServiceName() {
		return SERVICE_NAME;
	}

	public static final String SERVICE_NAME_KEY	 = "service_name";
	public static final String SESSION_ID_KEY = "session_id";
	public static final String INPUT_CHARSET_KEY = "input_charset";
	public static final String SIGN = "sign_key";
	public static final String WORK_NO = "work_no";
	public static final String FILE_NAME = "file_name";
	public static final String FTP_INFO="ftp_info";
	public static final String SERVICE_NAME = "ORDER_RESULT_NOTICE_SERVICE";


	public static void main(String[] args){
		Double double1 = Double.valueOf("100.1");
		System.out.println(double1.longValue());
	}

}