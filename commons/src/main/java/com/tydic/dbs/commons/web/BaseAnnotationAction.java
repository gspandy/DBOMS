/**
 * com.tydic.commons.web.BaseAction.java
 */
package com.tydic.dbs.commons.web;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fins.gt.export.CsvReader;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.tydic.dbs.commons.constant.ConfigConstants;
import com.tydic.dbs.commons.constant.PageObject;
import com.tydic.dbs.commons.vo.GwUploadFileVO;

/**
* @file  BaseAnnotationAction.java
* @author caipeimin
* @version 0.1
* @控制器基类
* Copyright(C), 2014-2015
*			Guangzhou Sunrise Technology Development Co., Ltd.
* History
*   	1. Date: 2014-2-17
*      	Author: caipeimin
*      	Modification: this file was created
*   	2. ...
*/
public abstract class BaseAnnotationAction {
	protected final Log log = LogFactory.getLog(BaseAnnotationAction.class);
	protected PageObject pageObject;
	public void asyncReturnResult(HttpServletResponse response, String msg, String...charsets) {
		try {
			String charset = "UTF-8";
			if (ArrayUtils.isNotEmpty(charsets))
				charset = charsets[0];
			response.setHeader("Cache-Control", "no-cache");
			response.setContentType("text/html;charset="+charset);//注意返回的内容格式
			PrintWriter pw = response.getWriter();
			pw.print(msg);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void asyncReturnResult(HttpServletResponse response, JSONObject json, String...charsets) {
		try {
			String charset = "UTF-8";
			if (ArrayUtils.isNotEmpty(charsets))
				charset = charsets[0];
			response.setHeader("Cache-Control", "no-cache");
			response.setContentType("text/html;charset="+charset);//注意返回的内容格式
			PrintWriter pw = response.getWriter();
			pw.print(json);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    /**
     * 把图片路径回写到富文本编辑器(从SaleGoodsAction类提取出来封闭到父类)
     * @return
     * @author shuyongfu
     */
    public void write(String contentType, String encode, String content, HttpServletResponse response) {
        if (content != null) {
            response.setContentType(contentType);
            response.setHeader("Cache-Control", "no-cache");
//            response.setCharacterEncoding(encode);
            try {
                response.getWriter().write(content);
            } catch (IOException e) {
                log.error("向客户端输出内容[" + content + "]出错" + e);
            }
        }
    }
    
    /***
     * title:upLoadFile(上传文件)
     * @param fileType
     * @param userId
     * @return
     * @throws Exception
     */
   
  /*	public GwUploadFileVO upLoadFile(String fileType,String userId) throws Exception{
  		GwUploadFileVO  documentsInfoVO=new GwUploadFileVO();
  		if(this.getUpload() != null){
  			//不同模块用途文件存放不同目录
  			String fileContent = "system";
              if("01".equals(fileType)){//用户管理模块
              	fileContent = "userManage";
              }else if("02".equals(fileType)){
              	fileContent = "desenManage";
              }
  			int par = uploadFileName.lastIndexOf(".");// 对最后一个“.”结束的文件定位
  			String fin = uploadFileName.substring(par);// 截取扩展名
  			imageFileName = new Date().getTime() + fin;// 以时间命名
  			String filePath = ConfigConstants.BASE_UPLOAD_FILE_PATH+"/uploadFile/"+fileContent+"/";
  			
  			if(!new File(filePath).exists())
  				new File(filePath).mkdirs();
  			
  			FileUtils.copyFile(upload, new File(filePath + File.separator + imageFileName));
  			String savePath = "/uploadFile/"+fileContent+"/"+imageFileName;
  			
  			documentsInfoVO.setCreateTime(new Date());
  			documentsInfoVO.setCreateUser(userId);
  			documentsInfoVO.setFileType(fileType);
  			documentsInfoVO.setRealName(uploadFileName);
  			documentsInfoVO.setFilePath(savePath);
  		}
  		return documentsInfoVO;
  	}*/
  	/***
     * title:downLoadFile(下载文件)
     * @param fileType
     * @param userId
     * @return
     * @throws Exception
     */
    /***
  	public void downLoadFile(HttpServletRequest request) throws Exception{
		String fileId=request.getParameter("fileId");
		GwUploadFileVO fileVo  = uploadFileService.findById(Long.parseLong(fileId));
		//关于文件下载时采用文件流输出的方式处理：
		response.reset();//可以加也可以不加
		response.setContentType("application/x-download");
		//application.getRealPath("/main/mvplayer/CapSetup.msi");获取的物理路径
		String filePath = fileVo.getFilePath();
		//String filenamedownload = application.getRealPath(System.getProperty("FileRoot.Path")+filePath);//即将下载的文件的相对路径 
		String filenamedownload = ConfigConstants.BASE_UPLOAD_FILE_PATH+filePath;//绝对路径
		//根据不同浏览器区分解决乱码
		String filenamedisplay;
		String agent=request.getHeader("USER-AGENT");
		if(null != agent && -1 != agent.indexOf("Firefox")){//Firefox
			filenamedisplay = new String(fileVo.getRealName().getBytes("UTF-8"),"iso-8859-1");
		}else{//其他
			filenamedisplay = URLEncoder.encode(fileVo.getRealName(), "UTF-8");
		}
		response.addHeader("Content-Disposition","attachment;filename=" + filenamedisplay);
		java.io.OutputStream out = null;
		java.io.FileInputStream in = null;
		try{
			out = response.getOutputStream();
			in = new FileInputStream(filenamedownload);

			byte[] b = new byte[1024];
			int i = 0;

			while((i = in.read(b)) > 0){
				out.write(b, 0, i);
			}
			out.flush(); 
		}catch(Exception e){
			e.printStackTrace();
			String sms="系统后台错误:操作人("+getLoginUser().getLoginName()+"),操作功能(文件下载),原因:"+e.getMessage()+"【数据服务网关】";
			GwWorkPlanVO workPlan=workPlanService.saveWorkPlan("系统后台错误", WorkPlanConstent.SYSTEM_BACKSTAGE_ERROR, "文件下载时发生错误！原因"+e.getMessage(), WorkPlanConstent.WAIT_FOR_DEAL, null, null, null, null, null,sms,null);
			Map map=new HashMap();
			map.put("userId",String.valueOf(getLoginUser().getUserId()));
			map.put("operFun", "文件下载");
			workPlanParamService.saveParamMap(workPlan.getPlanId(), map);
			throw new Exception("文件下载出错："+e.getMessage());
		}
		finally{
			if(in != null){
				in.close();
				in = null;
			}
		}
	}***/
  	
  	/***
     * title:downLoadFile(读取excel文件内容)
     * @param fileType
     * @param userId
     * @return
     * @throws Exception
     */
  	public List readExcel(File ruleCheckFile) throws Exception{
		try {
            Workbook workbook = null;
	  		try {
	  			workbook = new HSSFWorkbook(new FileInputStream(ruleCheckFile));	//office2003
	  		} catch (Exception e) {
	  			try {
	  				workbook = new XSSFWorkbook(new FileInputStream(ruleCheckFile)); //office2007
	  			} catch (Exception e1) {
	  				e1.printStackTrace();
	  				throw e1;
	  			}
	  		}
	  		int rowCount = 0;
            Sheet sheet = workbook.getSheetAt(0);
            rowCount = sheet.getLastRowNum();
            if(rowCount < 1){
            	throw new RuntimeException("合规检查规则文件数据必须大于1行");
            }            
            List list = new ArrayList<>();
          	for(int i=1;i<=rowCount;i++){//行
          		String reorder = getCellValue(sheet.getRow(i).getCell(0));//序号, 也是字段编码
          		String fieldName = getCellValue(sheet.getRow(i).getCell(1)); //变量定义，字段中文名称
          		String fieldType = getCellValue(sheet.getRow(i).getCell(2)); //字段类型
          		String checkRule = getCellValue(sheet.getRow(i).getCell(4));//检查规则
          	}
          	return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("读取excel文件失败:"+e.getMessage());
		}
	}
  	
  	public String getCellValue(Cell cell){
		if(cell==null) return "";
		if(cell.getCellType() != HSSFCell.CELL_TYPE_STRING){
			cell.setCellType(org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING);
        } 
		String value = cell.getStringCellValue();
		return value != null ? value.trim() : "";
	}

	/***
	 * title:downLoadFile(读取CSV文件内容)
	 * @return
	 * @throws Exception
	 */
	public String readCSV(File file) throws Exception{
		StringBuffer buffer = new StringBuffer();
		try {
			//BufferedReader reader = new BufferedReader(new FileReader(file));
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"utf-8"));
			CsvReader creader = new CsvReader(reader, '\n');

			while (creader.readRecord()) {
				buffer.append(creader.getRawRecord()).append(",");
			}
			creader.close();
		}catch(Exception ex){
			log.error("读取文件错误：",ex);
		}
		String content = buffer.toString();
		if(content.endsWith(",")){
			content = content.substring(0,content.length()-1);
		}
		return content;
	}
  	
  	public PageObject getPageObject() {
		return pageObject;
	}
	public void setPageObject(PageObject pageObject) {
		this.pageObject = pageObject;
	}
}
