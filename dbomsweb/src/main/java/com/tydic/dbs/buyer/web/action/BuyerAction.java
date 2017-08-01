package com.tydic.dbs.buyer.web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tydic.dbs.buyer.service.InterfaceLogService;
import com.tydic.dbs.buyer.vo.InterfaceLog;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.tydic.commons.utils.OrderUtils;
import com.tydic.commons.utils.Page;
import com.tydic.dbs.buyer.service.AppInfoService;
import com.tydic.dbs.buyer.service.BussAuditStatusService;
import com.tydic.dbs.buyer.service.BussInfoService;
import com.tydic.dbs.buyer.service.BussTenantService;
import com.tydic.dbs.buyer.vo.AppInfo;
import com.tydic.dbs.buyer.vo.BussAuditStatus;
import com.tydic.dbs.buyer.vo.BussInfo;
import com.tydic.dbs.buyer.vo.BussTenant;
import com.tydic.dbs.common.constant.CommonConfig;
import com.tydic.dbs.common.constant.CommonModuleConstant;
import com.tydic.dbs.commons.Constants;
import com.tydic.dbs.commons.constant.WcsSessionConstant;
import com.tydic.dbs.commons.define.WcsDefinition;
import com.tydic.dbs.commons.enums.AuditType;
import com.tydic.dbs.commons.enums.Status;
import com.tydic.dbs.commons.utils.DateUtil;
import com.tydic.dbs.commons.utils.FileInfgenUtil;
import com.tydic.dbs.commons.utils.FileUtil;
import com.tydic.dbs.commons.vo.LoginMemberVo;
import com.tydic.dbs.commons.web.BaseAnnotationAction;
import com.tydic.dbs.httpclient.HttpRemoteService;
import com.tydic.dbs.system.email.service.SendEmailService;
import com.tydic.dbs.system.log.mapper.InfFileLog;
import com.tydic.dbs.system.log.mapper.InfIndent;
import com.tydic.dbs.system.log.service.InfFileLogService;
import com.tydic.dbs.system.log.service.InfIndentService;
import com.tydic.dbs.system.operator.mapper.SysOperator;
import com.tydic.dbs.system.sms.mapper.SysSms;
import com.tydic.dbs.system.sysUploadFile.mapper.SysUploadFile;
import com.tydic.dbs.system.sysUploadFile.service.SysUploadFileService;
import com.tydic.dbs.vo.BussInfoVo;
import com.tydic.dbs.vo.ResultVO;

/**
 * 
 * @ClassName: BuyerAction 
 * @Description: TODO(后台-商户管理控制器) 
 * @author Michael dengwenjie@tydic.com 
 * @date 2016-7-18 下午2:56:59 
 *
 */
@Controller
@RequestMapping("/buyer")
public class BuyerAction extends BaseAnnotationAction {
	private final Log log = LogFactory.getLog(BuyerAction.class);
	@Autowired
    private CommonConfig commonConfig;
	@Autowired
	private BussInfoService bussInfoService;
	@Autowired
    private SysUploadFileService sysUploadFileService;
	@Autowired
    private BussAuditStatusService bussAuditStatusService;
	@Autowired
	private InfIndentService infIndentService;
	@Autowired
	private InfFileLogService infFileLogService;
	
	@Autowired
	private SendEmailService sendEmailService;

	@Autowired
	private InterfaceLogService interfaceLogService;
	@Autowired
	private AppInfoService appInfoService;
	
    @Autowired
 	private BussTenantService bussTenantService;
	
	/**
	 * 
	 * @Title: index 
	 * @Description: TODO(后台-商户列表页) 
	 * @param @param request
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	@RequestMapping("/index")
	public String index(HttpServletRequest request) throws Exception {
				
		return "buyer/buyerList";
	}
	
	/**
	 * 
	 * @Title: index 
	 * @Description: TODO(后台-加载商户列表页) 
	 * @param @param request
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/getBussinfoList")
	@ResponseBody
	public Map<String,Object> getBussinfoList(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		Map<String,Object> params = new HashMap<String,Object>();
		List<BussAuditStatus> auditStatusList = new ArrayList<>();
		List<BussInfoVo> bussInfoVo = new ArrayList<BussInfoVo>();
		String auditType = "";
		String auditStatus = "";
		String bussAccount = request.getParameter("bussAccount");
		String bussCardNo = request.getParameter("bussCardNo");
		String bussTelNo = request.getParameter("bussMobileNo");
		String orgName = request.getParameter("orgName");
		String bussStatus = request.getParameter("bussStatus");
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		
		if (StringUtils.isNotBlank(bussAccount)){
			bussAccount = bussAccount.replaceAll("%", "\\\\%");
			bussAccount = bussAccount.replaceAll("_", "\\\\_");
			params.put("bussAccount", bussAccount.trim());
		}
		if (StringUtils.isNotBlank(bussCardNo)){
			bussCardNo = bussCardNo.replaceAll("%", "\\\\%");
			bussCardNo = bussCardNo.replaceAll("_", "\\\\_");
			params.put("bussCredNo", bussCardNo.trim());
		}
		if (StringUtils.isNotBlank(bussTelNo)){
			bussTelNo = bussTelNo.replaceAll("%", "\\\\%");
			bussTelNo = bussTelNo.replaceAll("_", "\\\\_");
			params.put("bussTeleNo", bussTelNo.trim());
		}
		if (StringUtils.isNotBlank(orgName)){
			orgName = orgName.replaceAll("%", "\\\\%");
			orgName = orgName.replaceAll("_", "\\\\_");
			params.put("orgName", orgName.trim());
		}
		if (StringUtils.isNotBlank(bussStatus)){
			params.put("bussStatus", bussStatus);
		}
		if (StringUtils.isNotBlank(page)){
			params.put(Page.CURR_PAGE, Integer.parseInt(page));
		}
		if (StringUtils.isNotBlank(rows)){
			params.put(Page.PAGE_SIZE, Integer.parseInt(rows));
		}
		
		List<Map<String, Object>> orderBy = new ArrayList<Map<String, Object>>();
		List<BussTenant> bussTenantList = new ArrayList<>();
    	Map<String, Object> hash = new HashMap<String, Object>();
    	hash.put("createTime", "desc");
    	orderBy.add(hash);
    	params.put("orderBy", orderBy);
    	Page bussInfoPage = bussInfoService.getPageByParamMap(params);
    	List<BussInfo> bussInfoList = bussInfoPage.getList();
    	if(bussInfoList !=null && bussInfoList.size() >0){
    		for(BussInfo bussInfo : bussInfoList){
    			String bussId = bussInfo.getBussId();
    			BussInfoVo bussVo = new BussInfoVo();
    			BeanUtils.copyProperties(bussVo,bussInfo);
    			Map<String,Object> param = new HashMap<String,Object>();
    			if (StringUtils.isNotBlank(bussId)){
    				param.put("bussId", bussId);
    				param.put("status", Status.VALID.getCode());
    			}
    			try {
    				auditStatusList = bussAuditStatusService.getPageByParamMap(param).getList();
    				bussTenantList = bussTenantService.getPageByParamMap(param).getList();
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
    			
    			if (auditStatusList != null && auditStatusList.size() > 0){
    				for (BussAuditStatus vo: auditStatusList){
    					auditType = vo.getType();
    					auditStatus = vo.getAuditStatus();
    					if (AuditType.ITRESOURCE.getCode().equals(auditType)) {
    						bussVo.setItresourceStatus(auditStatus);
    					}else if(AuditType.LEEAUTH.getCode().equals(auditType)){
    						bussVo.setLeeauthStatus(auditStatus);
    					}else if(AuditType.DATAPEM.getCode().equals(auditType)){
    						bussVo.setDatapemStatus(auditStatus);
    					}
    				}
    			}
    			
    			int wait = 0,pass= 0,noPass = 0;
    			if (bussTenantList != null && bussTenantList.size() > 0){
    				for (BussTenant vo: bussTenantList){
    					auditStatus = vo.getAuditStatus();
    					if (auditStatus.equals("1")) {
    						wait+=1;
    					} else if (auditStatus.equals("2"))  {
    						pass+=1;
    					} else {
    						noPass+=1;
    					}
    				}
    				if (noPass>0) {
    					auditStatus = "NOPASS";
    				} else if (wait>0) {
    					auditStatus = "WAIT";
    				} else if (pass>0) {
    					auditStatus = "PASS";
    				}
    				bussVo.setLeeauthStatus(auditStatus);
    			}
    			bussInfoVo.add(bussVo);
    		}
    	}
    	
    	Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalNumber", bussInfoPage.getTotalNumber());
		map.put("totalPage", bussInfoPage.getTotalPage());
		map.put("bussInfoList", bussInfoVo);
    	
		return map;
	}
	
	/**
	 * 
	 * @Title: deleteBuyer
	 * @Description: TODO(商户注销) 
	 * @param @param request
	 * @param @param response
	 * @param @param page
	 * @param @param rows
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return Page    返回类型 
	 * @throws
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/deleteBuyer")
	@ResponseBody
	public Map<String,Object> deleteBuyer(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		BussInfo busInfo = new BussInfo();
		String serNum=OrderUtils.generateOutTradeNo();//流水
		Gson gson = new Gson();
		boolean flag = false;
		String bussId = request.getParameter("bussId");
		InterfaceLog interfaceLog=new InterfaceLog();
		SysOperator operator = (SysOperator) request.getSession().getAttribute(WcsSessionConstant.SESSION_OPERATOR);
		if(StringUtils.isEmpty(bussId)) return null;
		else {
				try {
					busInfo = bussInfoService.get(bussId);
					if (busInfo != null){
						InfIndent infIndent=infIndentService.get("USER_BUSDEL");
						if (infIndent.getInfFlag()!=null && "1".equals(infIndent.getInfFlag())){//文件接口
							InfFileLog infFileLog=new InfFileLog();
							infFileLog.setInfCode("USER_BUSDEL");
							infFileLog.setInfName("商户注销接口");
							infFileLog.setStatus("1");
							infFileLog.setCreator(operator.getOperId());
							infFileLog.setCreateTime(new Date());
							infFileLog.setBussId(bussId);
							infFileLog.setOperId(operator.getOperId());
							String dateSting= DateUtil.DateToString2(new Date());
							List<InfFileLog> temp=infFileLogService.getLogCount("USER_BUSDEL");
							long size;
							if (temp==null||temp.size()<=0){
								size=1;
							}else {
								size=temp.get(0).getInfLogId()+1;
							}

							infFileLog.setInfFileName("USER_BUSDEL_"+dateSting+"_"+size+".txt");
							infFileLog.setSerialNum(OrderUtils.generateOutTradeNo());

							//开始上传
							try {
								//String data="{\"bus_id\":" +bussId+",\"phone_no\":" +busInfo.getBussMobileNo()+",\"system_id\":" +system_id+"}";
								Map data=new HashMap<>();
								data.put("bus_id", bussId);
								data.put("phone_no", busInfo.getBussMobileNo());
								data.put("system_id", "0001");
								data.put("serial_num",serNum);
								FileInfgenUtil futil=new FileInfgenUtil();
								map=futil.filegenerate(request,gson.toJson(data), bussId,"USER_BUSDEL_"+dateSting+"_"+size+".txt");
								interfaceLog.setParamIn(gson.toJson(data));
								infFileLogService.save(infFileLog);
								if(!(map.containsKey("flag"))){
									busInfo.setBussStatus(CommonModuleConstant.BUS_STATUS_NO_ACTIVE);
									bussInfoService.save(busInfo);
									flag = true;
								}
							}catch (Exception e){
								log.error("Error",e);
							}
						}else {//http请求
							Map paramIn=new HashMap();
							paramIn.put("bussInfo",busInfo);
							paramIn.put("serNum",serNum);
							interfaceLog.setParamIn(gson.toJson(paramIn));
							if(!cancelBuyerRequest(busInfo,serNum)){
								map.put("msg", "调用商户注销接口异常！");
							}else{
								busInfo.setBussStatus(CommonModuleConstant.BUS_STATUS_NO_ACTIVE);
								bussInfoService.save(busInfo);
								flag = true;
							}
						}
					}
				} catch (Exception e) {
					log.error("获取商户信息失败");
					map.put("msg", "获取商户信息失败！");
					e.printStackTrace();
				}
		}
		map.put("flag", flag);
		interfaceLog.setBusiType("USER_BUSDEL");
		interfaceLog.setResultContent(gson.toJson(map));
		interfaceLog.setSerialNum(serNum);
		interfaceLog.setRemark("商户注销");
		interfaceLogService.save(interfaceLog);
		return map;
	}
	
	/**
	 * 
	 * @Title: query 
	 * @Description: TODO(商户信息查询) 
	 * @param @param request
	 * @param @param model
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	@RequestMapping("/query")
	public String query(HttpServletRequest request) throws Exception{
		String bussId = request.getParameter("bussId");/***商户编码***/
		LoginMemberVo vo = getBuyerInfoByBussId(bussId);
		request.setAttribute("vo", vo);
		
		return "buyer/buyerDetail";
	}
	
	/**
	 * 
	 * @Title: audit 
	 * @Description: TODO(进入商户审核页面) 
	 * @param @param request
	 * @param @param model
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	@RequestMapping("/audit")
	public String audit(HttpServletRequest request, ModelMap modelMap) throws Exception{
		String bussId = request.getParameter("bussId");/***商户编码***/	
		LoginMemberVo vo = getBuyerInfoByBussId(bussId);
		modelMap.addAttribute("bussDateType", WcsDefinition.wcsBussDataType.WCS_BUSS_DATA_TYPE);//cpu
		request.setAttribute("vo", vo);
		
		return "buyer/buyerAudit";
	}
	
	/**
	 * 
	 * @Title: doAudit 
	 * @Description: TODO(商户审核操作) 
	 * @param @param request
	 * @param @param model
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	@RequestMapping("/doAudit")
	@ResponseBody
	public Map<String, Object> doAudit(HttpServletRequest request , ModelMap modelMap) throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		Boolean flag = false;
		String bussId = request.getParameter("bussId");/***商户编码***/
		String bussDataType = request.getParameter("bussDataType");/***商户数据类型***/
		String suggetion = request.getParameter("suggetion");/***审核意见***/
		String auditType = request.getParameter("auditType");/***审核操作类型(02:通过；03:不通过)***/
		BussInfo busInfo = new BussInfo();
		try {
			busInfo = bussInfoService.get(bussId);
		}catch (Exception e){
			log.error("获取商户信息失败");
			e.printStackTrace();
		}
		if (busInfo != null) {
			try {
				busInfo.setBussStatus(auditType);
				busInfo.setSuggestion(suggetion);
				busInfo.setBussDataType(bussDataType);
				bussInfoService.save(busInfo);
				flag = true;
				//调用SOA接口发送随机短信
				SysSms vo = new SysSms();
				vo.setCalledNum(busInfo.getBussMobileNo());
				vo.setContent("您提交的审核已在一级平台审核通过【大数据研发平台】");
				vo.setCreateTime(new Date());
				//dBsSmsService.sendSms(vo);暂时注释掉
				//下发邮件通知商户
				log.info("SendEmail Begin :"+new Date().toString());
				if (Constants.AUDIT_AGREE.equals(auditType)){
					sendEmailService.sendEmail(busInfo.getBussEmail(), "【大数据研发平台】", "您提交的审核已在一级平台通过【大数据研发平台】");
				}else{
					sendEmailService.sendEmail(busInfo.getBussEmail(), "【大数据研发平台】", "您提交的审核在一级平台审核不通过【大数据研发平台】");
				}
				log.info("SendEmail End :"+new Date().toString());
			}catch (Exception e){
				log.error("商户审核失败");
				e.printStackTrace();
				map.put("msg", e);
			}
			
		}
		
		map.put("flag", flag);
		return map;
	}
	
	/**
	 * 
	 * @Title: getBuyerInfoByBussId 
	 * @Description: TODO(根据商户编码获取商户信息) 
	 * @param @param bussId
	 * @param @return    设定文件 
	 * @return LoginMemberVo    返回类型 
	 * @throws
	 */
	public LoginMemberVo getBuyerInfoByBussId(String bussId){
		LoginMemberVo vo = new LoginMemberVo();
		BussInfo busInfo = new BussInfo();
		String fileId = "";
		if(StringUtils.isEmpty(bussId)) return null;
		else {
				try {
					busInfo = bussInfoService.get(bussId);
					if (busInfo != null){
						BeanUtils.copyProperties(vo, busInfo);
						fileId = busInfo.getFileId();
					}			
				} catch (Exception e) {
					log.error("获取商户信息失败");
					e.printStackTrace();
				}
		}
		if(StringUtils.isNotBlank(fileId)){
			try {
				SysUploadFile uploadFile = sysUploadFileService.get(Long.valueOf(fileId));
				if (uploadFile != null){
					vo.setUploadFileName(uploadFile.getFileName());
				}
			} catch (Exception e){
				log.error("获取商户证件失败");
				e.printStackTrace();
			}
		}
		
		return vo;
	}
	
	@RequestMapping("/downFile")
	public String downFile(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String fileId = request.getParameter("fileCode");
		if (fileId==null||fileId.equals("")) {
			return null;
		}
		SysUploadFile sysuploadFile = sysUploadFileService.get(Long.parseLong(fileId));
		if (sysuploadFile==null) {
			return null;
		}
		String ftpPath = sysuploadFile.getFilePath();
		String fileName = sysuploadFile.getFileName();
		FileUtil down = new FileUtil();
		down.fileDown(request, response, ftpPath, fileName);
		return null;
	}
	
	private boolean cancelBuyerRequest(BussInfo busInfo,String serNum) throws Exception{
		//封装请求入参
		Map<String,String> map = new HashMap<String,String>();
		map.put("bus_id", busInfo.getBussId());
		map.put("phone_no", busInfo.getBussMobileNo());
		map.put("system_id", "0001");
		map.put("serial_num",serNum);
		boolean flag = false;
		log.info("commonConfig.getBussCancelUrl()入参:"+commonConfig.getBussCancelUrl());
		try {
			ResultVO resultVo = HttpRemoteService.service(commonConfig.getBussCancelUrl(), map, ResultVO.class);
			if (CommonModuleConstant.SUCCESS.equals(resultVo.getResult_flag())){
				flag = true;
			}
		} catch (Exception e) {
			log.error("调用接口异常！", e);
			e.printStackTrace();
		}
		return flag;
	}
	/**
	 * 
	 * @Title: index 
	 * @Description: TODO(后台-商户列表页) 
	 * @param @param request
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	@RequestMapping("/toAppList")
	public String toAppList(HttpServletRequest request) throws Exception {
		return "buyer/appList";
	}
	/**
	 * 
	 * @Title: index 
	 * @Description: TODO(后台-加载商户列表页) 
	 * @param @param request
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/getBussAppList")
		public @ResponseBody Page getBussAppList(HttpServletRequest request)throws Exception{
		Map<String,Object> params = new HashMap<String,Object>();
		String bussAccount = request.getParameter("bussAccount");
		String appName = request.getParameter("appName");
		String pageNo = request.getParameter("pageNo");
		pageNo = pageNo==null||pageNo.equals("")?"1":pageNo;
		params.put(Page.CURR_PAGE, Integer.parseInt(pageNo));
		List<Map<String, Object>> orderBy = new ArrayList<Map<String, Object>>();
    	Map<String, Object> hash = new HashMap<String, Object>();
    	hash.put("createTime", "desc");
    	orderBy.add(hash);
    	params.put(Page.PAGE_SIZE, Page.DEFAULT_PAGE_SIZE);
    	params.put("tenantName",""==bussAccount?null :bussAccount);
    	params.put("appName",""==appName?null :appName);
    	params.put("orderBy", orderBy);
    	try {
    		Page appInfoPage = appInfoService.getPageByParamMap(params);
    		if(appInfoPage == null){
    			appInfoPage = Page.EMPTY_PAGE;
	    	}
			return appInfoPage;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	/*Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalNumber", appInfoPage.getTotalNumber());
		map.put("totalPage", appInfoPage.getTotalPage());
		map.put("bussInfoList/appInfoList", appInfo);*/
    	
		return null;
	}
	
	/**
	 * 
	 * @Title: query 
	 * @Description: TODO(商户信息查询) 
	 * @param @param request
	 * @param @param model
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	@RequestMapping("/appDetail")
	public String appDetail(HttpServletRequest request) throws Exception{
		String bussId = request.getParameter("bussId");/***商户编码***/
		String appId = request.getParameter("appId");/***商户app编码***/
		LoginMemberVo vo = getBuyerInfoByBussId(bussId);
		AppInfo appInfo=appInfoService.get(appId);
		request.setAttribute("vo", vo);
		request.setAttribute("appInfo", appInfo);
		
		return "buyer/appDetail";
	}
	
}


