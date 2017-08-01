/**
 * com.sunrise.wcs.web.BusInfoAction.java
 */
package com.tydic.dbs.dbsweb.controller.buyer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.tydic.commons.utils.Page;
import com.tydic.commons.web.BaseAction;
import com.tydic.dbs.buyer.service.AppInfoService;
import com.tydic.dbs.buyer.service.BussAuditStatusService;
import com.tydic.dbs.buyer.service.BussInfoService;
import com.tydic.dbs.buyer.service.BussTenantService;
import com.tydic.dbs.buyer.vo.AppInfo;
import com.tydic.dbs.buyer.vo.BussAuditStatus;
import com.tydic.dbs.buyer.vo.BussInfo;
import com.tydic.dbs.buyer.vo.BussTenant;
import com.tydic.dbs.commons.enums.AuditType;
import com.tydic.dbs.commons.enums.Status;
import com.tydic.dbs.commons.exception.BizException;
import com.tydic.dbs.commons.vo.LoginMemberVo;
import com.tydic.dbs.dbsweb.common.Constants;
import com.tydic.dbs.dbsweb.common.FileUnil;
import com.tydic.dbs.system.sysUploadFile.mapper.SysUploadFile;
import com.tydic.dbs.system.sysUploadFile.service.SysUploadFileService;

/** 
 * @ClassName: BusInfoController 
 * @Description: TODO(商户信息控制器) 
 * @author Michael dengwenjie@tydic.com 
 * @date 2016-7-13 下午5:13:29 
 *  
 */
@Controller
@RequestMapping("/author/bussinfo")
public class BusInfoController extends BaseAction{
    private final Log log = LogFactory.getLog(BusInfoController.class);
    @Autowired
	private BussInfoService bussInfoService;
    @Autowired
    private BussAuditStatusService bussAuditStatusService;
    @Autowired
    private SysUploadFileService sysUploadFileService;
    @Autowired
 	private BussTenantService bussTenantService;
    @Autowired
   	private AppInfoService appInfoService;
    @Autowired
   	private FileUnil fileUnil;
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
	public String query(HttpServletRequest request,ModelMap model){
		LoginMemberVo loginMemberVo = (LoginMemberVo) request.getSession().getAttribute(Constants.LOGIN_SESSION_MEMBER_KEY);
		LoginMemberVo vo = new LoginMemberVo();
		String fileId = "";
		String bussId = "";
		BussInfo busInfo = new BussInfo();
		if(loginMemberVo!=null){
			bussId = loginMemberVo.getBussId();
		}
		if(StringUtils.isNotBlank(bussId)){
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
				if (uploadFile != null) {
					vo.setUploadFileName(uploadFile.getFileName());
				}
			} catch (Exception e) {
				log.error("获取商户证件失败");
				e.printStackTrace();
			}
		}
		vo.setLoginTime(loginMemberVo.getLoginTime());
		request.getSession().setAttribute(Constants.LOGIN_SESSION_MEMBER_KEY,vo);
		model.addAttribute("loginMemberVo", vo);
		
		return "/bussInfo/bussInfo";
	}
	
	/**
	 * 
	 * @Title: guide 
	 * @Description: TODO(商户配置导航) 
	 * @param @param request
	 * @param @param model
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/management/guide")
	public String guide(HttpServletRequest request,ModelMap model) throws Exception{
		LoginMemberVo loginMemberVo = (LoginMemberVo) request.getSession().getAttribute(Constants.LOGIN_SESSION_MEMBER_KEY);
		Map<String, Object> params=new HashMap<String, Object>();
		Map<String, Object> param=new HashMap<String, Object>();
		List<BussAuditStatus> auditStatusList = new ArrayList<>();
		List<BussTenant> bussTenantList = new ArrayList<>();
		String auditType = "";
		String auditStatus = "";
		String bussId = "";
		if(loginMemberVo != null){
			bussId = loginMemberVo.getBussId();
		}
		if (StringUtils.isNotBlank(bussId)){
			params.put("bussId", bussId);
			param.put("bussId", bussId);
			param.put("status", Status.VALID.getCode());
		}
		try {
			auditStatusList = bussAuditStatusService.getPageByParamMap(params).getList();
			bussTenantList = bussTenantService.getPageByParamMap(param).getList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (auditStatusList != null && auditStatusList.size() > 0){
			for (BussAuditStatus vo: auditStatusList){
				auditType = vo.getType();
				auditStatus = vo.getAuditStatus();
				if (auditType.equals("ITRESOURCE")) {
					model.addAttribute("ITRESOURCE", auditType);
					model.addAttribute("ITStatus", auditStatus);
				}else if(auditType.equals("LEEAUTH")){
					model.addAttribute("LEEAUTH", auditType);
					model.addAttribute("AUTHStatus", auditStatus);
				}else if(auditType.equals("DATAPEM")){
					model.addAttribute("DATAPEM", auditType);
					model.addAttribute("DATAStatus", auditStatus);
				}
			}
			model.addAttribute("auditStatusList", auditStatusList);
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
			model.addAttribute("AUTHStatus", auditStatus);
			model.addAttribute("bussTenantList", bussTenantList);
		}
		return "/bussInfo/bussGuide";
	}
	
	/***
	 * 
	 * @Title: management 
	 * @Description: TODO(商户配置) 
	 * @param @param request
	 * @param @param model
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	@RequestMapping("/management")
	public String management(HttpServletRequest request,ModelMap model){
		LoginMemberVo loginMemberVo = (LoginMemberVo) request.getSession().getAttribute(Constants.LOGIN_SESSION_MEMBER_KEY);
		
		
		model.addAttribute("loginMemberVo", loginMemberVo);	
		return "/bussInfo/bussInfo";
	}	
	
	/**
	 * 
	 * @Title: updateBuyer 
	 * @Description: TODO(跳转到商户修改页面) 
	 * @param @param request
	 * @param @param model
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	@RequestMapping("/updateBuyer")
	public String updateBuyer(HttpServletRequest request,ModelMap model) throws Exception{
		LoginMemberVo loginMemberVo = (LoginMemberVo) request.getSession().getAttribute(Constants.LOGIN_SESSION_MEMBER_KEY);
		model.addAttribute("loginMemberVo", loginMemberVo);
				
		return "bussInfo/bussUpdate";
	}
	
	/**
	 * 
	 * @Title: doUpdateBuyer 
	 * @Description: TODO(商户修改操作) 
	 * @param @param request
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	@RequestMapping("/doUpdateBuyer")
	@ResponseBody
	public Map<String, Object> doUpdateBuyer(HttpServletRequest request) throws Exception{
		
		Map<String, Object> map=new HashMap<String, Object>();
		BussInfo vo = new BussInfo();
		boolean flag = false;
		String bussId = request.getParameter("bussId");
		String bussMobileNo = request.getParameter("bussMobileNo");
		String bussAddress = request.getParameter("bussAddress");
		String bussEmail = request.getParameter("bussEmail");
		String bussTeleNo = request.getParameter("bussTeleNo");
		if(StringUtils.isBlank(bussId)){
			throw new BizException(0, "用户编号不存在！");
		}
		if(StringUtils.isBlank(bussMobileNo)){
			throw new BizException(0, "手机号码不能为空！");
		}
		if(StringUtils.isBlank(bussAddress)){
			throw new BizException(0, "联系地址不能为空！");
		}
		if(StringUtils.isBlank(bussEmail)){
			throw new BizException(0, "邮箱不能为空！");
		}
		if(StringUtils.isBlank(bussTeleNo)){
			throw new BizException(0, "联系电话不能为空！");
		}
		
		vo = bussInfoService.get(bussId);
		
		if(vo != null){
			vo.setBussMobileNo(bussMobileNo);
			vo.setBussAddress(bussAddress);
			vo.setBussEmail(bussEmail);
			vo.setBussTeleNo(bussTeleNo);
			try{
				bussInfoService.save(vo);
				flag = true;
				map.put("msg", "商户修改成功！");
			}catch (Exception e){
				map.put("msg", "商户修改失败！");
				e.printStackTrace();
			}
		}
		
		map.put("flag", flag);
		return map;
		
	}
	/**
	 * 
	 * @Title: appList
	 * @Description: TODO(商户app列表页) 
	 * @param @param request
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return 
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/management/appInfo")
	public String appInfo(HttpServletRequest request,ModelMap model) throws Exception{
		LoginMemberVo loginMemberVo = (LoginMemberVo) request.getSession().getAttribute(Constants.LOGIN_SESSION_MEMBER_KEY);
	
		return "/bussInfo/bussAppList";
	}
	/**
	 * 
	 * @Title: appList
	 * @Description: TODO(商户app列表) 
	 * @param @param request
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return 
	 * @throws
	 */
	@RequestMapping("/getAppList")
    public @ResponseBody Page getAppList(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		LoginMemberVo vo = (LoginMemberVo) session.getAttribute(Constants.LOGIN_SESSION_MEMBER_KEY);
		String appName = request.getParameter("appName");
		String pageNo = request.getParameter("pageNo");
		pageNo = pageNo==null||pageNo.equals("")?"1":pageNo;
		Map<String, Object> param = new HashMap<String, Object>();
		if (appName!=null&&!"".equals(appName)) {
			param.put("appName", appName);
		}
		param.put("bussId", vo.getBussId());
		param.put(Page.CURR_PAGE, Integer.parseInt(pageNo));
		param.put(Page.PAGE_SIZE, 8);
		List<Map<String, Object>> orderBy = new ArrayList<Map<String, Object>>();
    	Map<String, Object> hash = new HashMap<String, Object>();
    	hash.put("createTime", "desc");
    	orderBy.add(hash);
    	param.put("orderBy", orderBy);
		param.put("creater", vo.getBussAccount());
		try {
			Page page = appInfoService.getPageByParamMap(param);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    }
	
	/**
	 * 
	 * @Title: registrationUserSame 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param request
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	@RequestMapping("/appSame")
	@ResponseBody
	public  Map<String, Object> appSame(HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<>();
		String flag = "true";
		//在保存方法之前首先要查询用户名是否存在
		AppInfo appInfo = new AppInfo();
		String appName = request.getParameter("appName");
		appInfo.setAppName(appName);//商户编号
		AppInfo appexit =appInfoService.getAppName(appName);
		if(appexit==null){//未被注册
			map.put("flag", flag);
			return map;
		} else{//已经被注册
			String flag1 ="false";
			map.put("flag", flag1);
			return map;
		}
	
	}
	
	/**
	 * 
	 * @Title: appAdd 
	 * @Description: TODO(商户应用新增) 
	 * @param @param request
	 * @param @param model
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	@RequestMapping("/appAdd")
	public String appAdd(HttpServletRequest request,ModelMap model) throws Exception{
		LoginMemberVo loginMemberVo = (LoginMemberVo) request.getSession().getAttribute(Constants.LOGIN_SESSION_MEMBER_KEY);
		
		String type = request.getParameter("type");
		model.addAttribute("type", type);
		
		return "/bussInfo/bussAppAdd";
	}
	
	/**
	 * @throws Exception 
	 * @throws IOException 
	 * 
	 * @Title: appSave 
	 * @Description: TODO(商户app保存方法) 
	 * @param @param request
	 * @param @param response
	 * @param @param model
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	@RequestMapping("appSave")
	@ResponseBody
	public  Map<String, Object> appSave(HttpServletRequest request, HttpServletResponse response) throws Exception{
		LoginMemberVo loginMemberVo = (LoginMemberVo) request.getSession().getAttribute(Constants.LOGIN_SESSION_MEMBER_KEY);
		Map<String, Object> map = new HashMap<>();
		String appName = request.getParameter("appName");
		String appDes = request.getParameter("appDes");
		String appUrl = request.getParameter("appUrl");
		String appId = request.getParameter("appId");
		String type = request.getParameter("type");
		String file = request.getParameter("uploadfile1");//复印件
		AppInfo appInfo = new AppInfo();
		if(null!=appName){appInfo.setAppName(appName);}
		if(null!=appDes){appInfo.setAppDes(appDes);}		
		if(null!=appUrl){appInfo.setAppurl(appUrl);}
		appInfo.setBussId(loginMemberVo.getBussId());
		appInfo.setCreater(loginMemberVo.getBussAccount());
		appInfo.setTenantName(loginMemberVo.getBussAccount());
		appInfo.setBussName(loginMemberVo.getBussName());
		appInfo.setCreateTime(new Date());
		if(type.equals("2")&&appId!=null){
			appInfo.setAppId(appId);
		}
		//先将图片保存到服务器上
		
		//创建一个通用的多部分解析器  
		boolean flag = false;//开始默认标识符为false，用作判断能否保存的标识符
		try {
			Map<String, String> fileMap = null;
			 CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
		        //判断 request 是否有文件上传,即多部分请求  
		        if(multipartResolver.isMultipart(request)){  
		            //转换成多部分request    
		            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
		            //取得request中的所有文件名  
		            Iterator<String> iter = multiRequest.getFileNames();  
		            while(iter.hasNext()){  
		                //取得上传文件  
		            	int[] sizes = {Constants.PHOTO_SMALL_PHOTO_124, Constants.PHOTO_SMALL_PHOTO_400};
		                MultipartFile file1 = multiRequest.getFile(iter.next());
		                
		                if(file1!=null){
		                	fileMap = fileUnil.savePhoto(file1, sizes);
		                	appInfo.setAppImg(fileMap.get("filePath"));
		                	fileMap.put("imgflag", "true");
		                }else {
		                	  fileMap = new HashMap<String, String>();
		                	  
		                      fileMap.put("imgflag", "false");
		                }
		            }  
		        }  
		        AppInfo Info =  appInfoService.save(appInfo);
		        if (null!=Info){
		        	boolean flag1 = true;
		        	map.put("appSave", flag1);
		        } else{
		        	map.put("appSave", flag);
		        }
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message","系统出错!");
			System.out.println("保存失败");
		}
       
		return map;
	}
	@SuppressWarnings("static-access")
	@RequestMapping("/toAppDetial")
    public String toAppDetial(HttpServletRequest request, ModelMap modelMap,HttpServletResponse response) throws Exception {
		String appID = request.getParameter("appId");
		String type = request.getParameter("type");


		AppInfo appInfo = new AppInfo();
	
		if (appID!=null&&!"".equals(appID)) {
			appInfo = appInfoService.get(appID);
		}
	/*	String createTime = "";
		if (null!=appInfo){
			if (!appInfo.getCreateTime().equals("")&&appInfo.getCreateTime()!=null)
				createTime = new DateUtils().getDateString(appInfo.getCreateTime());
		}
		request.setAttribute("createTime", createTime);*/
		request.setAttribute("type", type);
		request.setAttribute("appInfo", appInfo);
		//将图片转为输出流
		/*response.setContentType("img/png");
		BufferedImage iamge = new BufferedImage(60, 20, BufferedImage.TYPE_INT_RGB);
		 //字节数组输出流
        ByteArrayOutputStream bout=new ByteArrayOutputStream();
        bout.write(appInfo.getAppImg());
        
		*/
		if (type.equals("2")){
	    	return "/bussInfo/bussAppAdd"; //新增产品和编辑产品在同一个页面
		}else if (type.equals("0")){
			return "/bussInfo/bussAppAdd"; //新增产品和编辑产品在同一个页面
		}
    	return "/bussInfo/bussAppDetail";
    }
	@RequestMapping("/delApp")
	public @ResponseBody Boolean delApp(HttpServletRequest request) throws Exception{
		String appID = request.getParameter("appId");
		String fileId = request.getParameter("fileCode");
	/*	if (fileId!=null&&!"".equals(fileId)) {
			try {
				SysUploadFile sysuploadFile = sysUploadFileService.get(Long.parseLong(fileId));
				String ftpPath = sysuploadFile.getFilePath();
				String fileName = sysuploadFile.getFileName();
				sysUploadFileService.delete(Long.parseLong(fileId));
				String port = ConfigConstants.FTP_SERVER_PORT;//ftp端口号
				String ip = ConfigConstants.FTP_SERVER_IP;
				String user = ConfigConstants.FTP_SERVER_USER;
				char[] ftpPassword = (ConfigConstants.FTP_SERVER_PASSWORD).toCharArray();//服务器密码
				FtpUpFile ftp = new FtpUpFile(ip,Integer.parseInt(port),user,ftpPassword);
				ftp.DelFile(ftpPath, fileName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}*/
		if (appID!=null&&!"".equals(appID)) {
			return appInfoService.delete(appID);
		}
		return false;
	}
	
	/**
     * 检查商户是否配置数据资源
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/checkResource")
    @ResponseBody
    public  Map<String,Object> checkResource(HttpServletRequest request)throws Exception{
        Map map =new HashMap();
        LoginMemberVo loginMemberVo=(LoginMemberVo) request.getSession().getAttribute(Constants.LOGIN_SESSION_MEMBER_KEY);
       
        Map<String, Object> params=new HashMap<String, Object>();
        params.put("bussId",loginMemberVo.getBussId());
    	params.put("type", AuditType.DATAPEM.getCode());
    	params.put("status", Status.VALID.getCode());
    	
    	List auditStatusList= null;
        try {
            auditStatusList = bussAuditStatusService.getPageByParamMap(params).getList();;
        }catch (Exception e){
            e.printStackTrace();
        }
        if(auditStatusList != null && auditStatusList.size()>0){
        	map.put("flag", true);
        }else{
        	map.put("flag", false);
        }
        return  map;
    }
}
