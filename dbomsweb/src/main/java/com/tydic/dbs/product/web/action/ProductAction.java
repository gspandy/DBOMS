package com.tydic.dbs.product.web.action;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.tydic.dbs.buyer.service.BussInfoService;
import com.tydic.dbs.buyer.service.InterfaceLogService;
import com.tydic.dbs.buyer.vo.InterfaceLog;
import com.tydic.dbs.common.constant.CommonConfig;
import com.tydic.dbs.commons.Constants;
import com.tydic.dbs.commons.constant.ConfigConstants;
import com.tydic.dbs.commons.constant.WcsSessionConstant;
import com.tydic.dbs.commons.define.WcsDefinition;
import com.tydic.dbs.commons.utils.DateUtil;
import com.tydic.dbs.commons.utils.FileInfgenUtil;
import com.tydic.dbs.commons.utils.FileUtil;
import com.tydic.dbs.commons.utils.FtpUpFile;
import com.tydic.dbs.commons.web.BaseAnnotationAction;
import com.tydic.dbs.httpclient.HttpRemoteService;
import com.tydic.dbs.product.service.PrdDataResourceRequireService;
import com.tydic.dbs.product.service.PrdInfoService;
import com.tydic.dbs.product.vo.PrdDataResourceRequire;
import com.tydic.dbs.product.vo.PrdInfo;
import com.tydic.dbs.system.log.mapper.InfFileLog;
import com.tydic.dbs.system.log.mapper.InfIndent;
import com.tydic.dbs.system.log.service.InfFileLogService;
import com.tydic.dbs.system.log.service.InfIndentService;
import com.tydic.dbs.system.operator.mapper.SysOperator;
import com.tydic.dbs.system.sysUploadFile.mapper.SysUploadFile;
import com.tydic.dbs.system.sysUploadFile.service.SysUploadFileService;

import com.tydic.dbs.vo.ResultVO;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tydic.commons.utils.DateUtils;
import com.tydic.commons.utils.OrderUtils;
import com.tydic.commons.utils.Page;

/**
 * 
 * @ClassName: ProductAction 
 * @Description: TODO(后台-产品管理控制器) 
 * @author Zhongjialiang zhongjialiang@tydic.com 
 * @date 2016-7-25 下午7:12:28 
 *
 */
@Controller
@RequestMapping("/product")
public class ProductAction extends BaseAnnotationAction {

	@Autowired
	private CommonConfig commonConfig;
	@Autowired
	private PrdInfoService prdInfoService;
	@Autowired
	private BussInfoService bussInfoService;
	@Autowired
    private SysUploadFileService sysUploadFileService;
	@Autowired
	private InfIndentService infIndentService;
	@Autowired
	private InfFileLogService infFileLogService;
	@Autowired
	private InterfaceLogService interfaceLogService;
	@Autowired
	private PrdDataResourceRequireService prdDataResourceRequireService;
	
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
		
		return "product/productList";
	}
	/**
	 * 查询产品列表
	 * @param request
	 * @return
	 * @throws Exception
     */
	@RequestMapping("/getProductList")
	public @ResponseBody Page getMenuList(HttpServletRequest request)throws Exception{
		Map<String,Object>paramMap=new HashMap<>();
		String prdId = request.getParameter("prdId");
		String prdName  = request.getParameter("prdName");
		String prdType  = request.getParameter("prdType");
		String prdStatus = request.getParameter("prdStatus");
		String pageNo = request.getParameter("pageNo");
		pageNo = pageNo==null||pageNo.equals("")?"1":pageNo;
		paramMap.put(Page.CURR_PAGE, Integer.parseInt(pageNo));
		List<Map<String, Object>> orderBy = new ArrayList<Map<String, Object>>();
		Map<String, Object> hash = new HashMap<String, Object>();
		hash.put("createTime", "desc");
		orderBy.add(hash);
		paramMap.put(Page.PAGE_SIZE, Page.DEFAULT_PAGE_SIZE);
		paramMap.put("prdId",""==prdId?null :prdId);
		paramMap.put("prdNameLike",""==prdName?null :prdName);
		paramMap.put("prdType",""==prdType?null :prdType);
		paramMap.put("prdStatus",""==prdStatus?null :prdStatus);
		paramMap.put("orderBy",orderBy);
		
    	/*pageObject.setData(productPage.getList());
    	request.setAttribute("pageObject", pageObject);*/
		try {
			Page page=prdInfoService.getPageByParamBussMap(paramMap);
			if(page == null){
				page = Page.EMPTY_PAGE;
	    	}
			return page;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	
	/**
	 * 
	 * @Title: query 
	 * @Description: TODO(产品详情信息) 
	 * @param @param request
	 * @param @param model
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */

	@SuppressWarnings("static-access")
	@RequestMapping("/query")
    public String query(HttpServletRequest request) throws Exception {
		String prdID = request.getParameter("prdId1");
		String type = request.getParameter("type");


		PrdInfo productInfo = new PrdInfo();
		if (type.equals("0")) {//类型：0新增产品 1 查看产品 2审核
			request.setAttribute("type", type);
			request.setAttribute("prdInfo", productInfo);
			return "product/productAdd";
		}
		if (prdID!=null&&!"".equals(prdID)) {
			productInfo = prdInfoService.get(prdID);
		}
		String createTime = "";
		if (!productInfo.getCreateTime().equals("")&&productInfo.getCreateTime()!=null)
			createTime = new DateUtils().getDateString(productInfo.getCreateTime());
		request.setAttribute("createTime", createTime);
		request.setAttribute("type", type);
		request.setAttribute("prdInfo", productInfo);
		request.setAttribute("WCS_PRD_UNIT_MAP", WcsDefinition.wcsPrdUnit.WCS_PRD_UNIT_MAP);
		List prdData=  prdDataResourceRequireService.getList(prdID);//查询出产品需求
		request.setAttribute("prdDataList", prdData);
		if (type.equals("2")){
	    	return "product/productModify"; //审核产品
		}
    	return "product/productDetail";
    }
	
	/**
	 * 
	 * @Title: doAudit 
	 * @Description: TODO(产品审核操作) 
	 * @param @param request
	 * @param @param model
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	@RequestMapping("/doAudit")
	@ResponseBody
	public Map<String, Object> doAudit(HttpServletRequest request) throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		HttpSession session = request.getSession();
		Gson gson=new Gson();
		SysOperator operator = (SysOperator) session.getAttribute(WcsSessionConstant.SESSION_OPERATOR);
		String serNum=OrderUtils.generateOutTradeNo();//流水号
		InterfaceLog interfaceLog=new InterfaceLog();
		Boolean flag = false;
		String prdId = request.getParameter("prdId");/***产品编码***/
		String suggetion = request.getParameter("suggetion");/***审核意见***/
		String prdUnit = request.getParameter("prdUnit");/***产品计量单位***/
		String prdPrice = request.getParameter("prdPrice");/***产品计量单价***/
		String Type = request.getParameter("Type");/***审核操作类型(2:通过；3:不通过)***/
		String prdType = request.getParameter("prdType");/***产品类型(1:商户结果数据；2：联通原始数据3:应用数据)***/
		String dataIp = request.getParameter("dataIp");/***数据库ip***/
		String dataPort = request.getParameter("dataPort");/***数据库端口***/
		String dataName = request.getParameter("dataName");/***数据库用户***/
		String dataPass = request.getParameter("dataPass");/***数据库密码***/
		PrdInfo prdInfo = new PrdInfo();
		try {
			prdInfo = prdInfoService.get(prdId);
		}catch (Exception e){
			map.put("msg", "审核失败");
			log.error("审核失败");
			e.printStackTrace();
		}
		if (prdInfo != null) {
			try {
				if(null!=Type&&""!=Type){
					prdInfo.setPrdStatus(Type);
				}
				if(null!=suggetion&&""!=suggetion){
					prdInfo.setCheckOpin(suggetion);
				}
				if(null!=prdUnit&&""!=prdUnit){
					prdInfo.setPrdUnit(prdUnit);
				}
				if(null!=prdPrice&&""!=prdPrice){
					prdInfo.setPrdPrice(new BigDecimal(prdPrice));
				}
				if(null!=prdType&&""!=prdType){
					prdInfo.setPrdType(prdType);
				}
				if(null!=dataIp&&""!=dataIp){
					prdInfo.setDataBaseIp(dataIp);
				}
				if(null!=dataPort&&""!=dataPort){
					prdInfo.setDataBasePort(dataPort);
				}
				if(null!=dataName&&""!=dataName){
					prdInfo.setDataBaseUserName(dataName);
				}
				if(null!=dataPass&&""!=dataPass){
					prdInfo.setDataBaseUserPwd(dataPass);
				}
				prdInfo.setChecker(operator.getOperAccount());
				prdInfo.setCheckTime(new Date());
				if (Type.equals("2")||Type.equals("1")){//审核通过才需要调用接口
					PrdInfo prdInfo1 = prdInfoService.get(prdId);
					InfIndent infIndent=infIndentService.get("PRODUCT_REVIEW");
					if (infIndent.getInfFlag()!=null && "1".equals(infIndent.getInfFlag())){//文件接口
						InfFileLog infFileLog=new InfFileLog();
						infFileLog.setInfCode("PRODUCT_REVIEW");
						infFileLog.setInfName("产品审核申请接口");
						infFileLog.setStatus("1");
						infFileLog.setCreator(operator.getOperId());
						infFileLog.setCreateTime(new Date());
						infFileLog.setBussId(prdInfo1.getBussId());
						infFileLog.setOperId(operator.getOperId());
						String dateSting= DateUtil.DateToString2(new Date());
						List<InfFileLog> temp=infFileLogService.getLogCount("PRODUCT_REVIEW");
						long size;
						if (temp==null||temp.size()<=0){
							size=1;
						}else {
							size=temp.get(0).getInfLogId()+1;
						}
						
						infFileLog.setInfFileName("PRODUCT_REVIEW"+dateSting+"_"+size+".txt");
						infFileLog.setSerialNum(OrderUtils.generateOutTradeNo());
						
						//开始上传
						try {
							String urls=request.getSession().getServletContext().getRealPath("/")+"temp";
							Map param=new HashMap<>();
							param.put("product_id", prdInfo1.getPrdId());
							param.put("product_name", prdInfo1.getPrdName());
							param.put("user_id", prdInfo1.getBussId());
							//根据产品Id获取数据资源文件路径
							SysUploadFile dataFile = sysUploadFileService.get(NumberUtils.toLong(prdInfo1.getPrdDatafile()));
							if(dataFile == null){
								log.error("产品【"+prdInfo1.getPrdName()+"】没有对应的数据资源文件");
								map.put("flag", false);
								map.put("msg", "产品【"+prdInfo1.getPrdName()+"】没有对应的数据资源文件");
								return map;
							}
							String fileName = dataFile.getFileName();
							if(fileName==null||!Constants.CSV.equalsIgnoreCase(fileName.substring(fileName.lastIndexOf(".")+1,fileName.length()).trim())){
								log.error("产品【"+prdInfo1.getPrdName()+"】数据资源文件格式错误！必须是csv格式");
								map.put("flag", false);
								map.put("msg", "产品【"+prdInfo1.getPrdName()+"】数据资源文件格式错误！必须是csv格式");
								return map;
							}
							String content ="";
							try {
								//File file = new File(dataFile.getFilePath() + "/" + dataFile.getFileName());
								char[]  ftpPassword = (ConfigConstants.FTP_SERVER_PASSWORD).toCharArray();//服务器密码
								FtpUpFile ftp = new FtpUpFile(ConfigConstants.FTP_SERVER_IP,Integer.parseInt(ConfigConstants.FTP_SERVER_PORT),
										ConfigConstants.FTP_SERVER_USER,ftpPassword);

								content = ftp.readCSV(dataFile.getFilePath(),dataFile.getFileName());
							}catch (Exception ex){
								log.error("文件读取失败！",ex);
								map.put("flag", false);
								map.put("msg", "文件读取失败！");
								return map;

							}

							if(content == null||"".equals(content.trim())){
								log.error("产品【"+prdInfo.getPrdName()+"】对应的数据资源文件为空");
								map.put("flag", false);
								map.put("msg", "产品【"+prdInfo.getPrdName()+"】对应的数据资源文件为空");
								return map;
							}
							Map m=new HashMap<>();
							m.put("requirement", content);
							param.put("data_resources", gson.toJson(m));
							param.put("serial_num",serNum);
							FileInfgenUtil futil=new FileInfgenUtil();
							flag = false;
							interfaceLog.setParamIn(gson.toJson(param));
							map=futil.filegenerate(request,gson.toJson(param), prdInfo1.getBussId(),"PRODUCT_REVIEW"+dateSting+"_"+size+".txt");
							infFileLogService.save(infFileLog);
						}catch (Exception e){
							log.error("Error",e);
						}
					}else{
						Map paramIn=new HashMap();
						paramIn.put("prdInfo",prdInfo1);
						paramIn.put("serial_num",serNum);
						interfaceLog.setParamIn(gson.toJson(paramIn));
						ResultVO vo = prdAuditRequest(prdInfo1,serNum);
						if(Constants.SUCCESS.equals(vo.getResult_flag())){
							prdInfo.setPrdStatus("2");
							prdInfoService.save(prdInfo);
							flag = true;
							map.put("msg", "审核成功");
						}else {
							flag = false;
							map.put("msg", vo.getMessage());
						}
					}
				}else{
					flag = true;
					prdInfoService.save(prdInfo);
					//map.put("msg", "审核失败");
				}

			}catch (Exception e){
			
				log.error("审核失败");
				e.printStackTrace();
			}
			
		}
		
		map.put("flag", flag);
		interfaceLog.setSerialNum(serNum);
		interfaceLog.setBusiType("PRODUCT_REVIEW");
		interfaceLog.setResultContent(gson.toJson(map));
		interfaceLog.setRemark("产品审核");
		interfaceLogService.save(interfaceLog);
		return map;
	}
	
	/**
	 * 
	 * @Title: download 
	 * @Description: TODO(产品需求文件下载) 
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	@RequestMapping("/download")
	public String download(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String fileId = request.getParameter("fileCode");
		if (fileId==null||fileId.equals("")) {
			return null;
		}
		SysUploadFile sysuploadFile = sysUploadFileService.get(Long.parseLong(fileId));
		if (sysuploadFile==null) {
			return null;
		}
		String url = request.getSession().getServletContext().getRealPath("/temp");
		String ftpPath = sysuploadFile.getFilePath();
		String fileName = sysuploadFile.getFileName();
		FileUtil fileUtil = new FileUtil();
		fileUtil.fileDown(request, response, ftpPath, fileName);
		return null;
	}

	/**
	 *
	 * @param prdInfo
	 * @return
	 * @throws Exception
	 */
	private  ResultVO prdAuditRequest(PrdInfo prdInfo,String serNum)throws Exception{

		ResultVO vo = new ResultVO();
		if(prdInfo== null){
			log.error("产品信息为空！");
			vo.setResult_flag(Constants.ERROR);
			vo.setMessage("产品信息为空！");
			return vo;
		}
		String prdId = prdInfo.getPrdId();
		//根据产品Id获取产品需求
		  List  <PrdDataResourceRequire> prdData=  prdDataResourceRequireService.getList(prdId);
		//根据产品Id获取数据资源文件路径
		/*SysUploadFile dataFile = sysUploadFileService.get(NumberUtils.toLong(prdInfo.getPrdDatafile()));
		if(dataFile == null){
			log.error("产品【"+prdInfo.getPrdName()+"】没有对应的数据资源文件");
			vo.setResult_flag(Constants.ERROR);
			vo.setMessage("产品【"+prdInfo.getPrdName()+"】没有对应的数据资源文件");
			return vo;
		}
		String fileName = dataFile.getFileName();
		if(fileName==null||!Constants.CSV.equalsIgnoreCase(fileName.substring(fileName.lastIndexOf(".")+1,fileName.length()).trim())){
			log.error("产品【"+prdInfo.getPrdName()+"】数据资源文件格式错误！必须是csv格式");
			vo.setResult_flag(Constants.ERROR);
			vo.setMessage("产品【"+prdInfo.getPrdName()+"】数据资源文件格式错误！必须是csv格式");
			return vo;
		}
		String content ="";
		try {
//			File file = new File(dataFile.getFilePath() + ConfigConstants.PATH_DECOLLATOR + dataFile.getFileName());
			char[]  ftpPassword = (ConfigConstants.FTP_SERVER_PASSWORD).toCharArray();//服务器密码
			FtpUpFile ftp = new FtpUpFile(ConfigConstants.FTP_SERVER_IP,Integer.parseInt(ConfigConstants.FTP_SERVER_PORT),
					ConfigConstants.FTP_SERVER_USER,ftpPassword);

			content = ftp.readCSV(dataFile.getFilePath(),dataFile.getFileName());
		}catch (Exception ex){
			log.error("文件读取失败！",ex);
			vo.setResult_flag(Constants.ERROR);
			vo.setMessage("文件读取失败！");
			return vo;

		}

		if(content == null||"".equals(content.trim())){
			log.error("产品【"+prdInfo.getPrdName()+"】对应的数据资源文件为空");
			vo.setResult_flag(Constants.ERROR);
			vo.setMessage("产品【"+prdInfo.getPrdName()+"】对应的数据资源文件为空");
			return vo;
		}*/

		//调用接口，发送IT资源请求
		Map<String, String> map = new HashMap<String, String>();
		map.put("bus_id", prdInfo.getBussId());
		map.put("product_id", prdId);
		map.put("product_type", prdInfo.getPrdType());
		map.put("product_name", prdInfo.getPrdName());
		map.put("serial_num",serNum);
		map.put("other_requirement",prdInfo.getPrdOthers());

		if(prdData!=null){
			List<Map> list  = new ArrayList<>();
			for(PrdDataResourceRequire prdDataResourceRequire :prdData){
				Map tmap = new HashMap();
				tmap.put("field_name",prdDataResourceRequire.getColumnName());
				tmap.put("field_desc",prdDataResourceRequire.getColumnDesc());
				tmap.put("field_type",prdDataResourceRequire.getColumnType());
				list.add(tmap);
			}
			Gson gson = new Gson();
			System.out.println("--------------"+gson.toJson(list));
			map.put("data_resources", gson.toJson(list));
		}

		try {
			log.info("service:"+commonConfig.getPrdAuditUrl());
			ResultVO resultVO = HttpRemoteService.service(commonConfig.getPrdAuditUrl(), map, ResultVO.class);

			if(resultVO != null && Constants.SUCCESS.equals(resultVO.getResult_flag())){
				vo.setResult_flag(Constants.SUCCESS);
				vo.setMessage("产品已发送至一级平台审核！请耐心等待！");
				return vo;
			}else {
				log.error("调用接口异常！");
				vo.setResult_flag(Constants.ERROR);
				vo.setMessage("调用接口异常！");
			}
		} catch (Exception ex) {
			log.error("调用接口异常！", ex);
			vo.setResult_flag(Constants.ERROR);
			vo.setMessage("调用接口异常！");
		}
		return vo;
	}
	
}


