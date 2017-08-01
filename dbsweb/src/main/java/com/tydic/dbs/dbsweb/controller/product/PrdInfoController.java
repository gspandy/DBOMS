/**
 * com.sunrise.pro.web.PrdInfoAction.java
 */
package com.tydic.dbs.dbsweb.controller.product;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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

import com.alibaba.dubbo.common.json.JSON;
import com.tydic.commons.utils.DateUtils;
import com.tydic.commons.utils.OrderUtils;
import com.tydic.commons.utils.Page;
import com.tydic.commons.web.BaseAction;
import com.tydic.dbs.buyer.service.BussAuditStatusService;
import com.tydic.dbs.buyer.service.BussTenantService;
import com.tydic.dbs.buyer.vo.BussAuditStatus;
import com.tydic.dbs.buyer.vo.BussTenant;
import com.tydic.dbs.commons.constant.ConfigConstants;
import com.tydic.dbs.commons.define.WcsDefinition;
import com.tydic.dbs.commons.enums.Status;
import com.tydic.dbs.commons.utils.FileUtil;
import com.tydic.dbs.commons.utils.FtpUpFile;
import com.tydic.dbs.commons.vo.DataFileFTPVO;
import com.tydic.dbs.commons.vo.LoginMemberVo;
import com.tydic.dbs.dbsweb.common.Constants;
import com.tydic.dbs.order.service.OrdRusultService;
import com.tydic.dbs.order.vo.OrdRusult;
import com.tydic.dbs.product.service.PrdDataResourceRequireService;
import com.tydic.dbs.product.service.PrdInfoService;
import com.tydic.dbs.product.vo.PrdDataResourceRequire;
import com.tydic.dbs.product.vo.PrdInfo;
import com.tydic.dbs.system.sysUploadFile.mapper.SysUploadFile;
import com.tydic.dbs.system.sysUploadFile.service.SysUploadFileService;

/**
 * 
 * @ClassName: PrdInfoController 
 * @Description: TODO(产品控制) 
 * @author huangChuQindownFile
 * @date 2016-7-15 下午6:02:32 
 *
 */
@Controller
@RequestMapping("/author/product")
public class PrdInfoController extends BaseAction{
    private final Log log = LogFactory.getLog(PrdInfoController.class);

	@Autowired
	private PrdInfoService prdInfoService;
	@Autowired
	private SysUploadFileService sysUploadFileService; 
    @Autowired
    private BussAuditStatusService bussAuditStatusService;
    @Autowired
 	private BussTenantService bussTenantService;
    @Autowired
    private PrdDataResourceRequireService prdDataResourceRequireService;
    @Autowired
    private OrdRusultService ordRusultService;
    
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/toPrdList")
    public String toPrdList(HttpServletRequest request,ModelMap model) throws Exception {
		LoginMemberVo loginMemberVo = (LoginMemberVo) request.getSession().getAttribute(Constants.LOGIN_SESSION_MEMBER_KEY);
		Map<String, Object> params=new HashMap<String, Object>();
		Map<String, Object> param=new HashMap<String, Object>();
		List<BussAuditStatus> auditStatusList = new ArrayList<>();
		List<BussTenant> bussTenantList = new ArrayList<>();
		String bussId = "";
		String auditType = "";
		String auditStatus = "";
		boolean LEEAUTH = false;
		boolean DATAPEM = false;
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
				if(auditType.equals("DATAPEM")){
					if (auditStatus.endsWith("2")) {
						DATAPEM = true;
					}
				}
			}
		}
		if (bussTenantList != null && bussTenantList.size() > 0){
			for (BussTenant vo: bussTenantList){
				auditStatus = vo.getAuditStatus();
				if (auditStatus.equals("2")) {
					LEEAUTH = true;
					break;
				} else {
					LEEAUTH = false;
				}
			}
		}
		auditStatus = "";
		if (LEEAUTH&&DATAPEM) {
			auditStatus = "2";
		}
		if (auditStatusList != null && auditStatusList.size() > 0 && bussTenantList != null && bussTenantList.size() > 0){
			model.addAttribute("auditStatusList", "0");
		}
		model.addAttribute("auditStatus", auditStatus);
    	return "product/productList";
    }
	
	@SuppressWarnings("static-access")
	@RequestMapping("/toPrdDetial")
    public String toPrdDetial(HttpServletRequest request, ModelMap modelMap) throws Exception {
		String prdID = request.getParameter("prdId");
		String type = request.getParameter("type");


		PrdInfo productInfo = new PrdInfo();
		modelMap.addAttribute("prdTypeMap", WcsDefinition.wcsPrdType.WCS_PRD_TYPE_MAP);//cpu
		if (type.equals("0")) {//类型：0新增产品 1 查看产品 2编辑产品
			request.setAttribute("type", type);
			request.setAttribute("prdInfo", productInfo);
			return "product/productAdd";
		}
		if (prdID!=null&&!"".equals(prdID)) {
			productInfo = prdInfoService.get(prdID);
			/***added andy 获取产品数据资源需求---start****/
			List<PrdDataResourceRequire>  dataList = prdDataResourceRequireService.getList(prdID);
			request.setAttribute("proDataList", dataList);
			/***added andy 获取产品数据资源需求---end****/
		}
		String createTime = "";
		if (!productInfo.getCreateTime().equals("")&&productInfo.getCreateTime()!=null)
			createTime = new DateUtils().getDateString(productInfo.getCreateTime());
		request.setAttribute("createTime", createTime);
		request.setAttribute("type", type);
		request.setAttribute("prdInfo", productInfo);
		if (type.equals("2")){
	    	return "product/productAdd"; //新增产品和编辑产品在同一个页面
		}
    	return "product/productDetial";
    }
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/checkName")
	public @ResponseBody Boolean checkName(HttpServletRequest request) throws Exception{
		Boolean flag = false;
		Map<String, Object> param = new HashMap<String, Object>();
		String prdName = request.getParameter("prdName");
		String prdId = request.getParameter("prdId");
		if (prdName!=null&&!"".equals(prdName)) {
			param.put("prdName", prdName);
		}
		if (prdId!=null&&!"".equals(prdId)) {
			param.put("prdIdNotIn", prdId);
		}
		Page page = prdInfoService.getPageByParamBussMap(param);
		List<PrdInfo> prdList = page.getList();
		if (prdList.isEmpty()) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}
	
	@SuppressWarnings("static-access")
	@RequestMapping("/savePrd")
	public @ResponseBody Boolean savePrd(HttpServletRequest request, PrdInfo prdinfo, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		Boolean b = false;
		LoginMemberVo vo = (LoginMemberVo) session.getAttribute(Constants.LOGIN_SESSION_MEMBER_KEY);
		String file = request.getParameter("uploadfile");
		String prdType = request.getParameter("prdType");
		List<PrdDataResourceRequire> datasList = null;
		if (vo==null) {
			return false;
		}
		if (prdinfo==null) {
			return false;
		}
		if (prdinfo.getPrdId()==null||prdinfo.getPrdId().equals("")) {
			prdinfo.setPrdStatus("1");
			prdinfo.setBussId(vo.getBussId());
			prdinfo.setCreater(vo.getBussAccount());
			prdinfo.setCreateTime(new Date());
			prdinfo.setPrdType(prdType);
		}else{
			prdinfo.setPrdStatus("1");
			prdinfo.setBussId(vo.getBussId());
			prdinfo.setModifier(vo.getBussAccount());
			prdinfo.setModifyTime(new Date());
			prdinfo.setPrdType(prdType);
		}
		
		//String file = prdinfo.getPrdDatafile();
//		if (file.equals(prdinfo.getPrdDataName())){
//			file = null;
//		}
		if (file==null||"".equals(file)) {
			PrdInfo product = prdInfoService.save(prdinfo);
			if(product!=null){
				/***added andy 解析数据资源需求--start***/
				String data_resources = request.getParameter("prdDataResource");
				if(StringUtils.isNotBlank(data_resources)){
					JSONObject dataList = JSONObject.fromObject(data_resources);
					JSONArray jsonArray = dataList.getJSONArray("prd_data");

					datasList = prdDataResourceRequireService.getList(product.getPrdId());
					if (!datasList.isEmpty() && datasList.size() > 0){
						prdDataResourceRequireService.deleteByPrdId(product.getPrdId());
					}//删除动作主要是为了区别开是新增产品还是修改产品
					for (int i=0;i<jsonArray.size();i++ ){
					
						PrdDataResourceRequire prdDataResourceRequire = new PrdDataResourceRequire();
						prdDataResourceRequire.setPrdId(product.getPrdId());
						prdDataResourceRequire.setColumnName(jsonArray.getJSONObject(i).get("columnName").toString());
						prdDataResourceRequire.setColumnType(jsonArray.getJSONObject(i).get("columnType").toString());
						prdDataResourceRequire.setColumnDesc(jsonArray.getJSONObject(i).get("columnDesc").toString());
						prdDataResourceRequire.setCreater(vo.getBussAccount());
						prdDataResourceRequire.setCreateTime(new Date());
						prdDataResourceRequireService.save(prdDataResourceRequire);
						
					}
				}
				/***added andy 解析数据资源需求--end***/
				return true;
			}
			return false;
		}
		//系统文件记录表
		SysUploadFile info1= new SysUploadFile();
		if (prdinfo.getPrdDatafile()!=null&&!prdinfo.getPrdDatafile().equals("")) {
			info1 = sysUploadFileService.get(Long.parseLong(prdinfo.getPrdDatafile()));
		}
		final String filePathTemp = info1.getFilePath();
		final String fileNameTemp = info1.getFileName();
		int par = file.lastIndexOf("\\");// 对最后一个“.”结束的文件定位
	    String fin = file.substring(par+1);// 截取文件名
	    fin = new DateUtils().getYmdhmsByDate(new Date())+"_"+fin;
	    String fin1 = file.substring(0,par+1);// 截取目录
	    String fin2= fin1.replace("\\", "\\\\"); //转义识别
		info1.setFileName(fin);//文件名
		info1.setFileType("2");//表示类型：1商户文件2：产品需求文件
		info1.setFileStatus("1");//文件狀態
		info1.setCreater(vo.getBussAccount());
		info1.setCreateTime(new Date());//创建时间
		info1.setFilePath(ConfigConstants.UPLOAD_FILE_PATH_PRD_INFO+"/"+vo.getBussId());//文件路径
	  
		try {
			//上传功能,再保存到系統文件記錄表
			SysUploadFile sysuplosdFile = sysUploadFileService.save(info1);
			prdinfo.setPrdDatafile(String.valueOf(sysuplosdFile.getFileId()));
			//新建上传文件对象，用于放置一些常量
			DataFileFTPVO dataFileVO = new DataFileFTPVO();
			dataFileVO.setFtpIp(ConfigConstants.FTP_SERVER_IP);
			dataFileVO.setFtpUser(ConfigConstants.FTP_SERVER_USER);
			dataFileVO.setFtpPassword(ConfigConstants.FTP_SERVER_PASSWORD);
			dataFileVO.setFilePath(fin2);
			dataFileVO.setFileName(fin);
			dataFileVO.setFileId(Long.valueOf(sysuplosdFile.getFileId()));
			
			//创建一个通用的多部分解析器  
	        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
	        //判断 request 是否有文件上传,即多部分请求  
	        if(multipartResolver.isMultipart(request)){  
	            //转换成多部分request    
	            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
	            //取得request中的所有文件名  
	            Iterator<String> iter = multiRequest.getFileNames();  
	            while(iter.hasNext()){  
	                //取得上传文件  
	                MultipartFile file1 = multiRequest.getFile(iter.next());
	                String ftpRootPath =ConfigConstants.UPLOAD_FILE_PATH_PRD_INFO;//上传文件路径
	                char[] ftpPassword = (ConfigConstants.FTP_SERVER_PASSWORD).toCharArray();//服务器密码
	                String port = ConfigConstants.FTP_SERVER_PORT;//ftp端口号
	                FtpUpFile ftp = new FtpUpFile(ConfigConstants.FTP_SERVER_IP,Integer.parseInt(port),ConfigConstants.FTP_SERVER_USER,ftpPassword,ftpRootPath,file1);
	                ftp.mkdir(ftpRootPath, vo.getBussId(), true);
	                Boolean ftpisFile =  ftp.ftpPutPrdFile(ftpRootPath+"/"+vo.getBussId(),fin,file1);
	                if (ftpisFile){//上传文件标识符
	                	//保存用户信息
	            		PrdInfo saveinfo = prdInfoService.save(prdinfo);
	            		if(saveinfo!=null){//保存产品标识符
	            			b = true;
	            		}else{
	            			b = false;
	            		}
	                } else {
	                	sysUploadFileService.delete(Long.parseLong(sysuplosdFile.getFileId().toString()));
	                	b = false;
	                }
	                //删除之前的文件
	                if (!filePathTemp.isEmpty()&&!fileNameTemp.isEmpty()) {
		                ftp.DelFile(filePathTemp, fileNameTemp);
					}
	            }  
	        }  
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}
	
	@RequestMapping("/delPrd")
	public @ResponseBody Boolean delPrd(HttpServletRequest request) throws Exception{
		String prdID = request.getParameter("prdId");
		String fileId = request.getParameter("fileCode");
		if (fileId!=null&&!"".equals(fileId)) {
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
		}
		if (prdID!=null&&!"".equals(prdID)) {
			return prdInfoService.delete(prdID);
		}
		return false;
	}

	@RequestMapping("/downFile")
	public String downFile(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String ordId = request.getParameter("fileCode");
		if (ordId==null||ordId.equals("")) {
			return null;
		}
		OrdRusult ordRusult = new OrdRusult();
		ordRusult.setWorkNo(ordId);
		List <OrdRusult> ordRusultList = ordRusultService.get(ordRusult);
		if(ordRusultList == null||ordRusultList.size()==0){
			return null;
		}else{
			ordRusult = ordRusultList.get(0);
			String ftpPath = ordRusult.getFilePath();
			String fileName = ordRusult.getFileName();
			FileUtil down = new FileUtil();
			down.fileDown(request, response, ftpPath, fileName);
			return null;
		}
		
		
	}
	
	@RequestMapping("/getPrdList")
    public @ResponseBody Page getPrdList(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		LoginMemberVo vo = (LoginMemberVo) session.getAttribute(Constants.LOGIN_SESSION_MEMBER_KEY);
		String prdID = request.getParameter("prdID");
		String prdName = request.getParameter("prdName");
		String prdStatus = request.getParameter("prdStatus");
		String pageNo = request.getParameter("pageNo");
		pageNo = pageNo==null||pageNo.equals("")?"1":pageNo;
		Map<String, Object> param = new HashMap<String, Object>();
		if (prdID!=null&&!"".equals(prdID)) {
			param.put("prdId", prdID);
		}
		if (prdName!=null&&!"".equals(prdName)) {
			param.put("prdNameLike", prdName);
		}
		if (prdStatus!=null&&!"".equals(prdStatus)) {
			param.put("prdStatus", prdStatus);
		}
		param.put(Page.CURR_PAGE, Integer.parseInt(pageNo));
		param.put(Page.PAGE_SIZE, 8);
		List<Map<String, Object>> orderBy = new ArrayList<Map<String, Object>>();
    	Map<String, Object> hash = new HashMap<String, Object>();
    	hash.put("createTime", "desc");
    	orderBy.add(hash);
    	param.put("orderBy", orderBy);
		param.put("creater", vo.getBussAccount());
		try {
			Page page = prdInfoService.getPageByParamBussMap(param);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    }
}
