package com.tydic.dbs.dbsweb.common;

import java.awt.Image;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tydic.dbs.buyer.service.AppInfoService;
import com.tydic.dbs.buyer.vo.AppInfo;
@Controller
@RequestMapping("/author/bussinfo")
public class ImgServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8030155522232258116L;
	private final Log log = LogFactory.getLog(ImgServlet.class);
	@Autowired
   	private AppInfoService appInfoService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//resp.setContentType("image/jpeg");
		resp.setHeader("Content-Type","image/jpeg");
		String appID = req.getParameter("id");
		AppInfo appInfo = new AppInfo();
		try {
			appInfo = appInfoService.get(appID);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		//byte[] bytes = appInfo.getAppImg();
		
		/*if(bytes.length > 0){
			OutputStream os = null;ImageIO.createImageInputStream(bytes);
			try{
				os = resp.getOutputStream();
				os.write(bytes);
				os.flush();
			}catch(IOException e){
				log.info("读取图片出错!"+e.getMessage());
			}finally{
				if(null != os){
					try{
						os.close();
						//return null;
					}catch(IOException e){
						log.info("关闭图片输出流出错!"+e.getMessage());
					}
				}
			}
		}*/
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doPost(req, resp);
	}
	
}
