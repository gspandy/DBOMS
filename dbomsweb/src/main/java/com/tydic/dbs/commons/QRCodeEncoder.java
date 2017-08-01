package com.tydic.dbs.commons;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

/**
 * 生成二维码工具类
 * @author X230
 *
 */
public class QRCodeEncoder {
	
	/**
	 * @param url  生成二维码的路径
	 * @param filePath 存放二维码的地址
	 * @param fileName 文件名称
	 */
	public static boolean qrcode(String url,String filePath,String fileName){
	    ByteArrayOutputStream out = QRCode.from(url).to(ImageType.PNG).stream();
	    boolean flag=true;
	    try {
	    	File f = new File(filePath);
	    	if(!f.exists())	f.mkdir();
	        FileOutputStream fout = new FileOutputStream(new File(filePath+"/"+fileName+".jpg"));
	        fout.write(out.toByteArray());
	        fout.flush();
	        fout.close();
	        QRCode.from(url).to(ImageType.GIF).file();
	        QRCode.from(url).to(ImageType.GIF).stream();
	        QRCode.from(url).withSize(250, 250).file();
	        QRCode.from(url).withSize(250, 250).stream();
	        QRCode.from(url).to(ImageType.GIF).withSize(250, 250).file();
	        QRCode.from(url).to(ImageType.GIF).withSize(250, 250).stream();
	    }catch (Exception e) {
	        e.printStackTrace();
	        flag=false;
	    }
	    return flag;
    }
}
