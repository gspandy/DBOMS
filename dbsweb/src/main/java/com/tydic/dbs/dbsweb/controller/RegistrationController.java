package com.tydic.dbs.dbsweb.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.tydic.commons.utils.DateUtils;
import com.tydic.commons.utils.Md5;
import com.tydic.commons.utils.OrderUtils;
import com.tydic.dbs.buyer.service.BussInfoService;
import com.tydic.dbs.buyer.vo.BussInfo;
import com.tydic.dbs.commons.constant.ConfigConstants;
import com.tydic.dbs.commons.define.WcsDefinition;
import com.tydic.dbs.commons.utils.FtpUpFile;
import com.tydic.dbs.commons.vo.DataFileFTPVO;
import com.tydic.dbs.system.sysUploadFile.mapper.SysUploadFile;
import com.tydic.dbs.system.sysUploadFile.service.SysUploadFileService;

/**
 * @ClassName: RegistrationController 
 * @Description: 注册控制器
 * @author zhongjialiang zhongjialiang@tydic.com 
 * @date 2016-7-11 下午15:14:47 
 *  
 */
@Controller
@RequestMapping("/registration")
public class RegistrationController  extends BaseController {
	
	@Autowired
	/**商户注册*/
	private BussInfoService bussInfoService; 
	@Autowired
	/**商户注册*/
	private SysUploadFileService sysUploadFileService; 
	
	/**
	 * 
	 * @Title: index 
	 * @Description: TODO(商户注册) 
	 * @param @param request
	 * @param @param response
	 * @param @param model
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	@RequestMapping("/index")
	public String index(HttpServletRequest request, HttpServletResponse response,ModelMap model) throws Exception{
	    return "Businessregistration/Businessregistration";
	}
	/**
	 * @throws IOException 
	 * 
	 * @Title: registrationSave 
	 * @Description: TODO(商户保存方法) 
	 * @param @param request
	 * @param @param response
	 * @param @param model
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	@RequestMapping("registrationSave")
	@ResponseBody
	public  Map<String, Object> registrationSave(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		Map<String, Object> map = new HashMap<>();
		BussInfo info = new BussInfo();
		/*String realPath = request.getParameter("realPath");*/
		String bussAccou = request.getParameter("UserName");
		String password = request.getParameter("UserPassword");
		String name = request.getParameter("Name");
		String mobileNo = request.getParameter("Mobile");///手机号码
		String IdCard = request.getParameter("IdCard");
		String Address = request.getParameter("Address");//联系地址
		String Email = request.getParameter("Email");
		String orgName = request.getParameter("orgName");
		String orgAddress = request.getParameter("orgAddress");//机构地址
		String bussNUM = request.getParameter("BUSSINESS_NUM");//机构编码
		String bussteleNo = request.getParameter("BUSS_TELE_NO");//联系号码
		String file = request.getParameter("uploadfile1");//复印件
		info.setBussId(OrderUtils.getBussId());//商户编号
		info.setBussAccount(bussAccou);//用户名
		info.setBussPasswd(Md5.En(password));//商户密码
		info.setBussType(WcsDefinition.wcsBussType.BUSINESS_ORGANIZATIONS);//商户类型（1：个人商户，2：机构商户）
		info.setBussName(name);//姓名
		info.setBussMobileNo(mobileNo);//移动电话
		info.setBussCredNo(IdCard);//证件号码
		info.setBussAddress(Address);//联系地址
		info.setBussEmail(Email);//EMAIL
		info.setOrgName(orgName);//机构名称
		info.setOrgAddress(orgAddress);//机构地址
		info.setBussinessNum(bussNUM);//工商编码
		info.setBussTeleNo(bussteleNo);//固定电话
		info.setCreateTime(info.getCreateTime());//创建时间
		info.setConfigureStatus(WcsDefinition.wcsConfigureStatus.NOT_CONFIGURED);
		info.setBussStatus(WcsDefinition.wcsBussStatus.PENDING);//商户状态
		
		//系统文件记录表
		SysUploadFile  info1= new SysUploadFile();
		/*info1.setFileId(info.getFileId());//复印件
		*/int par = file.lastIndexOf("\\");// 对最后一个“.”结束的文件定位
	    String fin = file.substring(par+1);// 截取文件名
	    fin = new DateUtils().getYmdhmsByDate(new Date())+"_"+fin;
	    String fin1 = file.substring(0,par+1);// 截取目录
	    String fin2= fin1.replace("\\", "\\\\"); //转义识别
		info1.setFileName(fin);//文件名
		info1.setFileType(WcsDefinition.wcsFileType.BUSSFILE);//表示类型：1商户文件2：产品需求文件
		info1.setFileStatus(WcsDefinition.wcsFileStatus.EFFECTIVE);//文件狀態
		info1.setCreateTime(new Date());//创建时间
		info1.setFilePath(ConfigConstants.UPLOAD_FILE_PATH_BUSS_INFO+"/"+info.getBussId());//文件路径
		info1.setModifyTime(new Date());
	  
		try {
			//上传功能,再保存到系統文件記錄表
			boolean flag = false;//开始默认标识符为false，用作判断能否保存的标识符
			Long maxFileSize = 5242880L;//设置传输文件最大为5*1024*1024= 5M
			if(file!=null||file.equals("")){
				//保存信息
				log.info("------------------------------------"+info1.toString());
				SysUploadFile sysuplosdFile = sysUploadFileService.save(info1);
				info.setFileId(Integer.toString(sysuplosdFile.getFileId()));//商户信息文件id
				//新建上传文件对象，用于放置一些常量
				DataFileFTPVO dataFileVO = new DataFileFTPVO();
				dataFileVO.setFtpIp(ConfigConstants.FTP_SERVER_IP);
				dataFileVO.setFtpUser(ConfigConstants.FTP_SERVER_USER);
				dataFileVO.setFtpPassword(ConfigConstants.FTP_SERVER_PASSWORD);
				dataFileVO.setFilePath(fin2);
				dataFileVO.setFileName(fin);
				dataFileVO.setFileId(Long.valueOf(sysuplosdFile.getFileId()));
			}else{
				request.setAttribute("saveSys","保存失败，请选择上传文件!");
			}
			
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
	                Long fileSize = file1.getSize();
	                if (fileSize>maxFileSize){
	                	map.put("FileOverSize", flag);
	                	return map;
	                }else{
	                	boolean flag1 = true;
	                	map.put("FileOverSize", flag1);
	                }
	                String ftpRootPath =info1.getFilePath();//上传文件路径
	                char[]  ftpPassword = (ConfigConstants.FTP_SERVER_PASSWORD).toCharArray();//服务器密码
	                String port = ConfigConstants.FTP_SERVER_PORT;//ftp端口号
	                FtpUpFile ftp = new FtpUpFile(ConfigConstants.FTP_SERVER_IP,Integer.parseInt(port),ConfigConstants.FTP_SERVER_USER,ftpPassword,ftpRootPath,file1);
	                ftp.mkdir(ConfigConstants.UPLOAD_FILE_PATH_BUSS_INFO,info.getBussId(),true);
	                Boolean ftpisFile =  ftp.ftpPutPrdFile(ftpRootPath,fin,file1);
	                System.out.println("----------"+ftpisFile);
	                if (ftpisFile){//上传文件标识符
	                	boolean flag1 = true;
	                	map.put("upFileflag", flag1);
	                	//保存用户信息
	            		BussInfo	saveinfo = bussInfoService.saveBuss(info);
	            		if(saveinfo!=null){//保存商户标识符
	            			boolean flag2 = true;
	            			map.put("bussSave", flag2);
	            		}else{
	            			map.put("bussSave", flag);
	            		}
	                } else {
	                	map.put("upFileflag", flag);
	                }
	            }  
	        }  
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message","文件超出指定大小!");
			System.out.println("保存失败");
		}
		return map;
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
	@RequestMapping("registrationUserSame")
	@ResponseBody
	public  Map<String, Object> registrationUserSame(HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<>();
		String flag = "false";
		//在保存方法之前首先要查询用户名是否存在
		BussInfo info = new BussInfo();
		String userName = request.getParameter("UserName");
		info.setBussAccount(userName);//商户编号
		BussInfo bussexist =bussInfoService.getaccId(info.getBussAccount());
		if(bussexist==null){//未被注册
			map.put("flag", flag);
			return map;
		} else{//已经被注册
			String flag1 ="true";
			map.put("flag", flag1);
			return map;
		}
	
	}
	
	@RequestMapping(value="/upload2")  
    public String upload2(HttpServletRequest request,HttpServletResponse response) throws IllegalStateException, IOException {  
		BussInfo info = new BussInfo();
		String realPath = request.getParameter("realPath");
		String bussAccou = request.getParameter("UserName");
		String password = request.getParameter("UserPassword");
		String name = request.getParameter("Name");
		String mobileNo = request.getParameter("Mobile");///手机号码
		//创建一个通用的多部分解析器  
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
        //判断 request 是否有文件上传,即多部分请求  
        if(multipartResolver.isMultipart(request)){  
            //转换成多部分request    
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
            //取得request中的所有文件名  
            Iterator<String> iter = multiRequest.getFileNames();  
            while(iter.hasNext()){  
                //记录上传过程起始时的时间，用来计算上传时间  
                int pre = (int) System.currentTimeMillis();  
                //取得上传文件  
                MultipartFile file = multiRequest.getFile(iter.next());
                String ftpRootPath =ConfigConstants.BASE_UPLOAD_FILE_PATH;//上传文件路径
                char[]  ftpPassword = ("123456").toCharArray();//服务器密码
               FtpUpFile ftp = new FtpUpFile(ConfigConstants.FTP_SERVER_IP,21,ConfigConstants.FTP_SERVER_USER,ftpPassword,ftpRootPath,file);
                Boolean ftpisFile =  ftp.ftpPutFile1(ftpRootPath,file);
                System.out.println("----------"+ftpisFile);
                if(file != null){  
                    //取得当前上传文件的文件名称  
                    String myFileName = file.getOriginalFilename();  
                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
                    if(myFileName.trim() !=""){  
                        System.out.println(myFileName);  
                        //重命名上传后的文件名  
                        String fileName = "demoUpload" + file.getOriginalFilename();  
                        //定义上传路径  
                        String path = "H:/" + fileName;  
                        File localFile = new File(path);  
                        file.transferTo(localFile);  
                    }  
                }  
                //记录上传该文件后的时间  
                int finaltime = (int) System.currentTimeMillis();  
                System.out.println(finaltime - pre);  
            }  
              
        }  
        return "";  
    }  
      
    @RequestMapping("/toUpload" )   
    public String toUpload() {  
          
        return "/upload";  
    }  
      
}  
	
	
