package com.tydic.dbs.commons.utils;

import com.tydic.dbs.commons.constant.ConfigConstants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 调用文件接口生成文件并上传
 * Created by long on 2016/8/10.
 */
public class FileInfgenUtil {
    private final Log log = LogFactory.getLog(FileInfgenUtil.class);

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String,Object> filegenerate(HttpServletRequest request,String data,String bussId,String fileName)throws Exception{
        Map map=new HashMap();
        String urls=request.getSession().getServletContext().getRealPath("/")+"temp";
        log.debug("------------------------------"+urls);
        try {
            File file=new File(urls+"/"+fileName);
            log.debug("------------------------------"+file);
            if (!file.getParentFile().exists()) {
                if (!file.getParentFile().mkdirs()) {
                }
            }

            if (!file.exists()){
                file.createNewFile();
            }
            BufferedWriter output = new BufferedWriter(new FileWriter(file));
            output.write(data);
            output.close();
            InputStream fielin = new FileInputStream(file);

            String ftpRootPath = ConfigConstants.UPLOAD_FILE_PATH_INTERFACE_INFO;//上传文件路径
            char[] ftpPassword = (ConfigConstants.FTP_SERVER_PASSWORD).toCharArray();//服务器密码
            String port = ConfigConstants.FTP_SERVER_PORT;//ftp端口号
            FtpUpFile ftp = new FtpUpFile(ConfigConstants.FTP_SERVER_IP,Integer.parseInt(port),ConfigConstants.FTP_SERVER_USER,ftpPassword,ftpRootPath,null);
            ftp.mkdir(ftpRootPath, bussId, true);
            Boolean ftpisFile =  ftp.ftpPutPrdFile(ftpRootPath+"/"+bussId,fileName,fielin);
            file.delete();
            fielin.close();
            if (ftpisFile) {
                map.put("msg","已生成txt文件，请运维人员发送给一级平台！");
			}else{
	            map.put("flag",false);
	            map.put("msg","txt文件生成失败");
			}
        }catch (Exception e){
            log.error("Error",e);
        }
        return map;
    }
}
