package com.tydic.dbs.system.web.action.interfaceFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.tydic.dbs.buyer.service.*;
import com.tydic.dbs.buyer.vo.*;
import com.tydic.dbs.commons.Constants;
import net.sf.json.JSONObject;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tydic.commons.utils.OrderUtils;
import com.tydic.dbs.commons.constant.ConfigConstants;
import com.tydic.dbs.commons.constant.WcsSessionConstant;
import com.tydic.dbs.commons.enums.AuditStatus;
import com.tydic.dbs.commons.enums.AuditType;
import com.tydic.dbs.commons.enums.DealFlag;
import com.tydic.dbs.commons.enums.OrderStatus;
import com.tydic.dbs.commons.utils.FtpUpFile;
import com.tydic.dbs.commons.web.BaseAnnotationAction;
import com.tydic.dbs.order.service.OrdInfoService;
import com.tydic.dbs.order.service.OrdResultFileService;
import com.tydic.dbs.order.vo.OrdInfo;
import com.tydic.dbs.order.vo.OrdResultFile;
import com.tydic.dbs.product.service.PrdDataResourceService;
import com.tydic.dbs.product.service.PrdInfoService;
import com.tydic.dbs.product.vo.PrdDataResource;
import com.tydic.dbs.product.vo.PrdInfo;
import com.tydic.dbs.system.log.mapper.InfFileLog;
import com.tydic.dbs.system.log.mapper.UploadFile;
import com.tydic.dbs.system.log.service.InfFileLogService;
import com.tydic.dbs.system.log.service.UploadFileService;
import com.tydic.dbs.system.operator.mapper.SysOperator;

/**
 * （后台文件接口上传）
 * Created by long on 2016/8/9.
 */
@Controller
@RequestMapping("/InfFile")
public class InfFileAction extends BaseAnnotationAction {

    private final Log log= LogFactory.getLog(InfFileAction.class);
    
    @Resource
    PrdInfoService prdInfoService;
    @Resource
    OrdInfoService ordInfoService;
    @Resource
    InfFileLogService infFileLogService;
    @Resource
    UploadFileService uploadFileService;
    @Resource
    BussAuditStatusService bussAuditStatusService;
    @Resource
    BussItResourceService bussItResourceService;
    @Resource
    BussDataPemissionService bussDataPemissionService;
    @Resource
    BussTenantService bussTenantService;
    @Resource
    BussTenantRoleService bussTenantRoleService;
    @Resource
    PrdDataResourceService prdDataResourceService;
    @Resource
    OrdResultFileService ordResultFileService;
	@Resource
	InterfaceLogService interfaceLogService;
    /**
     * 上传文件页
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/toUpload")
    public String toUpload(HttpServletRequest request)throws Exception{
        return "system/interfaceFile/uploadfile";
    }

    @SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	@RequestMapping("/uploadFile")
    @ResponseBody
    public Map<String,Object> uploadFile(HttpServletRequest request)throws Exception{
        Map map=new HashMap();
        Map<String,Object> fileMap=new HashMap();//上传文件内容
        InfFileLog infFileLog=new InfFileLog();
        String upload=request.getParameter("uploadfile");
        String interfacefName=request.getParameter("infName");
        String serialNum=OrderUtils.generateOutTradeNo();
        String fileName=upload.substring(upload.lastIndexOf("\\")+1);//接口文件名
        SysOperator operator = (SysOperator) request.getSession().getAttribute(WcsSessionConstant.SESSION_OPERATOR);
        String[] tempArr=fileName.split("_");
        String flag= tempArr[0]+"_"+tempArr[1];
        //记录日志
        infFileLog.setCreater(operator.getOperId());
        infFileLog.setCreateTime(new Date());
        infFileLog.setStatus("1");
        //解析参数
        try{
            log.debug("解析参数sart------------");
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
                    InputStream inputStream=file1.getInputStream();
                    byte b[]=new byte[(int)file1.getSize()];
                    inputStream.read(b);
                    inputStream.close();
                    String temp=new String(b);
                    log.debug(temp);
                    JSONObject  jasonObject = JSONObject.fromObject(temp);
                    fileMap = (Map)jasonObject;
                    String[]fileNames=fileName.split("_");
                    String infName=fileNames[0]+"_"+fileNames[1];
                    
                    String bussId="";
                    if("USER_AUTHAPLYN".equals(infName)){//租户账号权限申请结果接口
                    	bussId=fileMap.get("user_id").toString();
                    	if(StringUtils.isEmpty(bussId)){
                    		map.put("success", false);
                    		map.put("message", "商户不存在，请检查上传文件内容！");
                    		return map;
                    	}
                    	infFileLog.setBussId(bussId);
                    }else if("USER_SOUAPLYN".equals(infName)){//租户IT资源申请结果接口
                    	bussId=fileMap.get("user_id").toString();
                    	if(StringUtils.isEmpty(bussId)){
                    		map.put("success", false);
                    		map.put("message", "商户不存在，请检查上传文件内容！");
                    		return map;
                    	}
                    	infFileLog.setBussId(bussId);
                    }else if("USER_DATAAPLYN".equals(infName)){//租户数据权限申请结果接口
                    	bussId=fileMap.get("user_id").toString();
                    	if(StringUtils.isEmpty(bussId)){
                    		map.put("success", false);
                    		map.put("message", "商户不存在，请检查上传文件内容！");
                    		return map;
                    	}
                    	infFileLog.setBussId(bussId);
                    }else if("PRODUCT_DATASYN".equals(infName)){//数据服务信息同步接口
                    	String prdId=fileMap.get("product_id").toString();//产品id
                    	PrdInfo prdInfo=prdInfoService.get(prdId);
                    	if(prdInfo==null || StringUtils.isEmpty(prdInfo.getBussId())){
                    		log.error(prdId+"商户不存在！");
                    		map.put("success", false);
                    		map.put("message", prdId+"商户不存在！");
                    		return map;
                    	}
                    	bussId=prdInfo.getBussId();
                    	infFileLog.setBussId(bussId);
                    }else if("DATA_NOTICE".equals(infName)){//工单结果数据已生成通知接口
                    	String ordId=fileMap.get("work_no").toString();//工单号
                    	OrdInfo ordInfo=ordInfoService.get(ordId);
                    	if(ordInfo==null || StringUtils.isEmpty(ordInfo.getBusId())){
                    		log.error(ordId+"商户不存在！");
                    		map.put("success", false);
                    		map.put("message", ordId+"商户不存在！");
                    		return map;
                    	}
                    	bussId=ordInfo.getBusId();
                    	infFileLog.setBussId(bussId);
                    }else{
                    	log.error("错误的接口名称："+infName+"----请检查！");
                    	map.put("success", false);
                    	map.put("message", "请核实检查上传文件！");
                    	return map;
                    }
                    log.debug("上传文件内容："+fileMap);
                    String ftpRootPath = ConfigConstants.UPLOAD_FILE_PATH_INTERFACE_INFO;//上传文件路径
                    char[] ftpPassword = (ConfigConstants.FTP_SERVER_PASSWORD).toCharArray();//服务器密码
                    String port = ConfigConstants.FTP_SERVER_PORT;//ftp端口号
                    FtpUpFile ftp = new FtpUpFile(ConfigConstants.FTP_SERVER_IP,Integer.parseInt(port),ConfigConstants.FTP_SERVER_USER,ftpPassword,ftpRootPath,file1);
                    ftp.mkdir(ftpRootPath, bussId, true);
                    Boolean ftpisFile =  ftp.ftpPutPrdFile(ftpRootPath+"/"+bussId,"upload_"+fileName,file1);
                    if (!ftpisFile){//上传文件标识符
                    	map.put("success", false);
                    	map.put("message", "上传失败，请稍后再试！");
                    	return map;
                    }
                    infFileLog.setOperId(operator.getOperId());
                    infFileLog.setInfFileName("upload_"+fileName);
                    infFileLog.setInfCode(infName);
                    infFileLog.setInfName(interfacefName);
                    infFileLog.setSerialNum(serialNum);
                    infFileLog.setCreator(operator.getOperId());
                    infFileLog.setRemark("上传接口文件!");
                    
                    UploadFile upFile=new UploadFile();
                    upFile.setSerialNum(serialNum);
                    upFile.setStatus("1");
                    upFile.setFileUrl(ftpRootPath+"/"+bussId);
                    upFile.setFileName("upload_"+fileName);
                    upFile.setCreater(operator.getOperId());
                    upFile.setCreateTime(new Date());
                    upFile.setOperId(operator.getOperId());
                    upFile.setCreater(operator.getOperId());
                    try{
                    	infFileLogService.save(infFileLog);
                    	uploadFileService.save(upFile);
                    }catch(Exception e){
                    	log.error("Error:",e);
                    	map.put("success", false);
                    	map.put("message", "插入记录表失败！");
                    	return map;
                    }
                }
            }
        }catch (Exception e){
            log.error("Error:",e);
            map.put("success",true);
            map.put("message","上传文件失败！");
            return map;
        }

        List<String> keys=new ArrayList<>();
        for(String key:fileMap.keySet()){
            keys.add(key);
        }
        log.debug("上传文件内容参数名:"+keys);
        
        //业务处理
        switch (flag){
            case "USER_AUTHAPLYN":
                map=userAuthaplyn(request,fileMap,keys);
                break;
            case "USER_SOUAPLYN":
            	map=userSouaplyn(request, fileMap, keys);
                break;
            case "USER_DATAAPLYN":
            	map=userDataaplyn(request, fileMap, keys);
                break;
            case "PRODUCT_DATASYN":
            	map=productDatasyn(request, fileMap, keys);
                break;
            case "DATA_NOTICE":
            	map=dataNotice(request, fileMap, keys);
                break;
            default:
            	map.put("success", false);
            	map.put("message", "请检查上传文件!");
            	return map;
        }
        //更新状态
        if(map.get("success").toString()=="false"){
        	return map;
        }
        try{
        	uploadFileService.updateByserNum(serialNum);
        	map.put("success", true);
        	map.put("message", "上传成功！");
        }catch (Exception e) {
			log.error("更新状态出错",e);
			map.put("success", false);
			map.put("message", "更新状态出错");
		}
        return map;
    }

    /**
     * 租户账号权限申请结果接口
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/UserAuthaplyn")
    public Map<String,Object> userAuthaplyn(HttpServletRequest request,Map map,List<String> keyList)throws Exception{
        Map result=new HashMap();
        List<String> keyParam=new ArrayList<>();
		InterfaceLog interfaceLog=new InterfaceLog();
        keyParam.add("service_name");
        keyParam.add("session_id");
        keyParam.add("input_charset");
        keyParam.add("sign_key");
        keyParam.add("user_id");
        keyParam.add("result_flag");
        keyParam.add("message");
        keyParam.add("result");
        keyParam.add("system_id");
        Collections.sort(keyParam);
        Collections.sort(keyList);
        if(!keyParam.containsAll(keyList)){
        	result.put("success", false);
        	result.put("message", "参数有误，请核对后再上传！");
        	return result;
        }
        Map checkMap=dataCheck(keyParam, map);
        if(checkMap.get("flag").toString()=="false"){
        	result.put("success", false);
        	result.put("message", "参数有误，请核对后再上传！---["+checkMap.get("checkList").toString()+"]为空");
        	return result;
        }
      //处理业务逻辑
      		String userId =map.get("user_id").toString();
      		String resultFlag =map.get("result_flag").toString();
      		String message = map.get("message").toString();
      		String roles = map.get("result").toString();

      		//根据操作员id获取操作员信息
      		BussTenant bussTenant = null;
      		try {
      			bussTenant = bussTenantService.get(userId);
      		} catch (Exception e) {
      			log.error("获取操作员信息出错：",e);
      			result.put("success",false);
      			result.put("message","获取操作员信息出错");
      			return result;
      		}
      		if(bussTenant == null){
      			result.put("success",false);
      			result.put("message","操作员Id错误！查询不到操作员信息");
      			return result;
      		}
      		//更新审核状态
      		BussAuditStatus auditStatus = new BussAuditStatus();
      		auditStatus.setBussId(bussTenant.getBussId());
      		auditStatus.setType(AuditType.LEEAUTH.getCode());

      		if(!"1".equals(resultFlag)){
      			auditStatus.setAuditStatus(AuditStatus.NO_PASS.getCode());
      			try {
      				bussAuditStatusService.saveAuditInfo(auditStatus);
      			} catch (Exception e) {
      				log.error("Error",e);
      				result.put("success",false);
      				result.put("message","更新状态出错");
      				return result;
      			}

      			result.put("success",false);
      			result.put("message",message);
      			return result;
      		}


      		Gson gson = new Gson();
      		List<Map<String,String>> list = gson.fromJson(roles, new TypeToken<List<Map<String,String>>>() {
      		}.getType());
      		List<BussTenantRole> roleList = new ArrayList<>();

      		for (Map<String,String> m :list){
      			String roleId = m.get("role");
      			String account = m.get("account");
      			String url = m.get("url");
      			if(roleId==null||"".equals(roleId)||account==null||"".equals(account)||url==null||"".equals(url)){
      				result.put("success",false);
      				result.put("message","权限账户信息错误！");
      				return result;
      			}
      			BussTenantRole role = new BussTenantRole();
      			role.setTenantId(userId);
      			role.setRoleId(roleId);
      			role.setLoginId(account);
      			role.setUrl(url);
      			roleList.add(role);
      		}

			Map paramIn=new HashMap();
			paramIn.put("userId",userId);
			paramIn.put("resultFlag",resultFlag);
			paramIn.put("message",message);
			paramIn.put("roles",roles);
			interfaceLog.setParamIn(gson.toJson(paramIn));
			interfaceLog.setBusiType("USER_AUTHAPLYN");
			interfaceLog.setRemark("租户账号权限申请结果");
			interfaceLog.setSerialNum(map.get("session_id").toString());
      		try {
      			auditStatus.setAuditStatus(AuditStatus.PASS.getCode());
      			bussTenantRoleService.modify(roleList,auditStatus);
      			//返回结果
      			result.put("success",true);
      			result.put("message","SUCCESS");
				interfaceLog.setResultContent(gson.toJson(result));
				interfaceLog.setCreateTime(new Date());
				interfaceLogService.save(interfaceLog);
      		} catch (Exception e) {
      			log.error("更新权限信息报错",e);
      			result.put("success",false);
      			result.put("message","更新权限数据报错");
      		}
      		//返回结果
        return  result;
    }
    
    /**
     * 租户IT资源申请结果接口
     * @param request
     * @param map
     * @param list
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String,Object> userSouaplyn(HttpServletRequest request,Map map,List<String> list)throws Exception{
    	Map result=new HashMap();
		InterfaceLog interfaceLog=new InterfaceLog();
    	List<String> keyParam=new ArrayList<>();
        keyParam.add("service_name");
        keyParam.add("session_id");
		keyParam.add("input_charset");
        keyParam.add("sign_key");
        keyParam.add("user_id");
        keyParam.add("result_flag");
        keyParam.add("message");
        keyParam.add("ftp_info");
        keyParam.add("system_id");
        Collections.sort(keyParam);
        Collections.sort(list);
		log.debug(list);
		log.debug(keyParam);
        if(!keyParam.containsAll(list)){
        	result.put("success", false);
        	result.put("message", "参数有误，请核对后再上传！");
        	return result;
        }
        Map checkMap=dataCheck(keyParam, map);
        if(checkMap.get("flag").toString()=="false"){
        	result.put("success", false);
        	result.put("message", "参数有误，请核对后再上传！---["+checkMap.get("checkList").toString()+"]为空");
        	return result;
        }
        
    	BussAuditStatus auditStatus = new BussAuditStatus();
    	auditStatus.setBussId(map.get("user_id").toString());
		auditStatus.setType(AuditType.ITRESOURCE.getCode());
		if(!"1".equals(map.get("result_flag").toString())){
			auditStatus.setAuditStatus(AuditStatus.NO_PASS.getCode());
			try {
				bussAuditStatusService.saveAuditInfo(auditStatus);
			} catch (Exception e) {
				log.error("Error",e);
			}
			result.put("success",false);
			result.put("message",map.get("message").toString());
			return result;
		}
		
		Gson gson = new Gson();
		Map<String,String> resultMap = gson.fromJson(map.get("ftp_info").toString(), new TypeToken<Map<String,String>>() {
		}.getType());

		String ip = resultMap.get("ip");
		String port = resultMap.get("port");
		String user = resultMap.get("user");

		String password = resultMap.get("password");
		String filePath = resultMap.get("file_path");

		if(ip==null||"".equals(ip)){
			result.put("success",false);
			result.put("message","ip为空！");
			return result;
		}
		if(port==null||"".equals(port)){
			result.put("success",false);
			result.put("message","port为空！");
			return result;
		}if(user==null||"".equals(user)){
			result.put("success",false);
			result.put("message","user为空！");
			return result;
		}
		if(password==null||"".equals(password)){
			result.put("success",false);
			result.put("message","password为空！");
			return result;
		}
		if(filePath==null||"".equals(filePath)){
			result.put("success",false);
			result.put("message","filePath为空！");
			return result;
		}
		
		BussItResource resource = new BussItResource();
		resource.setFtpIp(ip);
		resource.setFtpPort(NumberUtils.toInt(port));
		resource.setFtpUser(user);
		resource.setFtpPass(password);
		resource.setFtpPath(filePath);
		resource.setBussId(map.get("user_id").toString());
		resource.setStatus(AuditStatus.PASS.getCode());
		resource.setAppTime(new Date());
		auditStatus.setAuditStatus(AuditStatus.PASS.getCode());

		Map paramIn=new HashMap();
		paramIn.put("userId",map.get("user_id").toString());
		paramIn.put("resultFlag",map.get("result_flag").toString());
		paramIn.put("message",map.get("message").toString());
		paramIn.put("ftp_info",map.get("ftp_info"));
		interfaceLog.setParamIn(gson.toJson(paramIn));
		interfaceLog.setBusiType("USER_SOUAPLYN");
		interfaceLog.setRemark("IT资源申请结果");
		interfaceLog.setSerialNum(map.get("session_id").toString());

		try {
			bussItResourceService.modifyFtpInfoByBussId(resource,auditStatus);
			//返回结果
			result.put("success",true);
			result.put("message","SUCCESS");
			interfaceLog.setResultContent(gson.toJson(result));
			interfaceLog.setCreateTime(new Date());
			interfaceLogService.save(interfaceLog);
		} catch (Exception e) {
			log.error("更新IT资源信息报错",e);
			result.put("success",true);
			result.put("message","更新IT资源信息报错");
		}
    	return  result;
    }
    
    /**
     * 租户数据权限申请结果接口
     * @param request
     * @param map
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String,Object> userDataaplyn(HttpServletRequest request,Map map,List<String> keyList)throws Exception{
    	Map result=new HashMap();
		InterfaceLog interfaceLog=new InterfaceLog();
    	List<String> keyParam=new ArrayList<>();
        keyParam.add("service_name");
        keyParam.add("session_id");
        keyParam.add("input_charset");
        keyParam.add("sign_key");
        keyParam.add("user_id");
        keyParam.add("result_flag");
        keyParam.add("message");
        keyParam.add("pem_info");
        keyParam.add("system_id");
        Collections.sort(keyParam);
        Collections.sort(keyList);
        if(!keyParam.containsAll(keyList)){
        	result.put("success", false);
        	result.put("message", "参数有误，请核对后再上传！");
        	return result;
        }
        Map checkMap=dataCheck(keyParam, map);
        if(checkMap.get("flag").toString()=="false"){
        	result.put("success", false);
        	result.put("message", "参数有误，请核对后再上传！---["+checkMap.get("checkList").toString()+"]为空");
        	return result;
        }
        //处理业务逻辑
		String userId =map.get("user_id").toString();
		String resultFlag =map.get("result_flag").toString();
		String message = map.get("message").toString();
		String pems = map.get("pem_info").toString();
	
		//更新审核状态
		BussAuditStatus auditStatus = new BussAuditStatus();
		auditStatus.setBussId(userId);
		auditStatus.setType(AuditType.DATAPEM.getCode());
	
		if(!"1".equals(resultFlag)){
			auditStatus.setAuditStatus(AuditStatus.NO_PASS.getCode());
			try {
				bussAuditStatusService.saveAuditInfo(auditStatus);
			} catch (Exception e) {
				log.error("Error",e);
			}
			result.put("success",false);
			result.put("message",message);
			return result;
		}
	
		Gson gson = new Gson();
		List<Map<String,String>> list = gson.fromJson(pems, new TypeToken<List<Map<String,String>>>() {
		}.getType());
		List<BussDataPemission> pemList = new ArrayList<>();
	
		for (Map<String,String> m :list){
			String pemId = m.get("data_pem_id");
			String flag = m.get("result_flag");
			if(pemId==null||"".equals(pemId)||flag==null||"".equals(flag)){
				result.put("success",false);
				result.put("message","权限信息错误！");
				return result;
			}
			BussDataPemission dataPemission = new BussDataPemission();
			dataPemission.setPemissionId(pemId);
			dataPemission.setBussId(userId);
			dataPemission.setAppStatus(AuditStatus.PASS.getCode());
	
			pemList.add(dataPemission);
		}

		Map paramIn=new HashMap();
		paramIn.put("userId",userId);
		paramIn.put("resultFlag",resultFlag);
		paramIn.put("message",message);
		paramIn.put("pems",pems);
		interfaceLog.setParamIn(gson.toJson(paramIn));
		interfaceLog.setBusiType("USER_DATAAPLYN");
		interfaceLog.setRemark("租户数据权限申请结果");
		interfaceLog.setSerialNum(map.get("session_id").toString());

		try {
			auditStatus.setAuditStatus(AuditStatus.PASS.getCode());
			bussDataPemissionService.updateBatch(pemList,auditStatus);
			//返回结果
			result.put("success",false);
			result.put("message","SUCCESS");
			interfaceLog.setResultContent(gson.toJson(result));
			interfaceLog.setCreateTime(new Date());
			interfaceLogService.save(interfaceLog);
			return result;
		} catch (Exception e) {
			log.error("更新权限信息报错",e);
			result.put("success",true);
			result.put("message","更新权限数据报错");
		}
    	return  result;
    }
    
    /**
     * 数据服务信息同步接口
     * @param request
     * @param map
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<String,Object> productDatasyn(HttpServletRequest request,Map map,List<String> keyList)throws Exception{
    	Map result=new HashMap();
		Gson gson=new Gson();
		InterfaceLog interfaceLog=new InterfaceLog();
    	List<String> keyParam=new ArrayList<>();
        keyParam.add("service_name");
        keyParam.add("session_id");
        keyParam.add("input_charset");
        keyParam.add("sign_key");
        keyParam.add("data_service_id");
        keyParam.add("data_service_name");
        keyParam.add("product_id");
        Collections.sort(keyParam);
        Collections.sort(keyList);
        if(!keyParam.containsAll(keyList)){
        	result.put("success", false);
        	result.put("message", "参数有误，请核对后再上传！");
        	return result;
        }
        Map checkMap=dataCheck(keyParam, map);
        if(checkMap.get("flag").toString()=="false"){
        	result.put("success", false);
        	result.put("message", "参数有误，请核对后再上传！---["+checkMap.get("checkList").toString()+"]为空");
        	return result;
        }
        //业务处理
        String serId = map.get("data_service_id").toString();
		String serName = map.get("data_service_name").toString();
		String prdId = map.get("product_id").toString();

		if(serId==null||"".equals(serId)){
			result.put("success",false);
			result.put("message","数据服务ID为空");
			return result;
		}
		if(serName==null||"".equals(serName)){
			result.put("success",false);
			result.put("message","数据服务名称为空");
			return result;
		}

		if(prdId==null||"".equals(prdId)){
			result.put("success",false);
			result.put("message","产品ID为空");
			return result;
		}

		//
		PrdDataResource prdDataResource = new PrdDataResource();
		prdDataResource.setDataResourceId(serId);
		prdDataResource.setDataResourceName(serName);
		prdDataResource.setPrdId(prdId);
		PrdInfo prdInfo=new PrdInfo();
		prdInfo.setPrdStatus(Constants.PRD_STATUS);
		prdInfo.setPrdId(prdId);

		Map paramIn=new HashMap();
		paramIn.put("serId",serId);
		paramIn.put("serName",prdId);
		paramIn.put("serName", serName);
		interfaceLog.setParamIn(gson.toJson(paramIn));
		interfaceLog.setBusiType("PRODUCT_DATASYN");
		interfaceLog.setRemark("数据服务信息同步接口");
		interfaceLog.setSerialNum(map.get("session_id").toString());
		try {
			prdDataResourceService.save(prdDataResource);
			prdInfoService.save(prdInfo);
			result.put("success",true);
			result.put("message","成功");
			interfaceLog.setResultContent(gson.toJson(result));
			interfaceLog.setCreateTime(new Date());
			interfaceLogService.save(interfaceLog);
		} catch (Exception e) {
			log.error("Error",e);
			result.put("success",false);
			result.put("message","数据信息同步失败！");
		}
    	return  result;
    }
    
    /**
     * 工单结果数据已生成通知接口
     * @param request
     * @param map
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<String,Object> dataNotice(HttpServletRequest request,Map map,List<String> keyList)throws Exception{
    	Map result=new HashMap();
		InterfaceLog interfaceLog=new InterfaceLog();
		Gson gson=new Gson();
    	List<String> keyParam=new ArrayList<>();
        keyParam.add("service_name");
        keyParam.add("session_id");
        keyParam.add("input_charset");
        keyParam.add("sign_key");
        keyParam.add("work_no");
        keyParam.add("file_name");
        Collections.sort(keyParam);
        Collections.sort(keyList);
        if(!keyParam.containsAll(keyList)){
        	result.put("success", false);
        	result.put("message", "参数有误，请核对后再上传！");
        	return result;
        }
        Map checkMap=dataCheck(keyParam, map);
        if(checkMap.get("flag").toString()=="false"){
        	result.put("success", false);
        	result.put("message", "参数有误，请核对后再上传！---["+checkMap.get("checkList").toString()+"]为空");
        	return result;
        }
    	String fileName = map.get("file_name").toString();
		String ordId = map.get("work_no").toString();
		String filePath = "";

		try {
			OrdInfo vo = ordInfoService.get(ordId);
			if (vo == null) {
				result.put("success",false);
				result.put("message","获取工单数据出错");
				return result;
			}

			if (OrderStatus.EXECUTED.getCode().equals(vo.getOrdStatus())) {
				result.put("success",false);
				result.put("message","该工单已执行");
				return result;
			}

			Map paramIn=new HashMap();
			paramIn.put("fileName",fileName);
			paramIn.put("ordId",ordId);
			paramIn.put("filePath", ConfigConstants.ORD_FILE_PATH);
			interfaceLog.setParamIn(gson.toJson(paramIn));
			interfaceLog.setBusiType("PRODUCT_DATASYN");
			interfaceLog.setRemark("数据服务信息同步接口");
			interfaceLog.setSerialNum(map.get("session_id").toString());

			//保存数据到工单文件返回表中
			OrdResultFile ordResultFile = new OrdResultFile();
			ordResultFile.setSerialNum(OrderUtils.generateOutTradeNo());
			ordResultFile.setFileName(fileName);
			ordResultFile.setFileUrl(ConfigConstants.ORD_FILE_PATH);
			ordResultFile.setOrdId(ordId);
			ordResultFile.setState(DealFlag.WAIT_DEAL.getCode());
			ordResultFile.setCreateTime(new Date());

			ordResultFileService.save(ordResultFile);

			//变更工单状态为正在执行
			OrdInfo tvo = new OrdInfo();
			tvo.setOrdId(vo.getOrdId());
			tvo.setOrdStatus(OrderStatus.EXECUTING.getCode());
			ordInfoService.update(tvo);
			result.put("success",true);
			result.put("message","更新工单状态成功");
			interfaceLog.setResultContent(gson.toJson(result));
			interfaceLog.setCreateTime(new Date());
			interfaceLogService.save(interfaceLog);

		} catch (Exception e1) {
			log.error("Error",e1);
			result.put("success",false);
			result.put("message","系统异常");
			return result;
		}

    	return  result;
    }
    
    /**
     * 校验数据
     * @param list
     * @param map
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public Map dataCheck(List<String> list,Map map )throws Exception{
    	boolean flag=true;
    	Map checkMap=new HashMap<>();
    	List<String> checkList=new ArrayList<>();
		int index=list.indexOf("sign_key");
		list.remove(index);
    	for (String string : list) {
			if(map.get(string).toString()==null || "".equals(map.get(string).toString())){
				checkList.add(string);
				flag=false;
			}
		}
    	checkMap.put("flag", flag);
    	checkMap.put("checkList", checkList);
    	return checkMap;
    }
}
