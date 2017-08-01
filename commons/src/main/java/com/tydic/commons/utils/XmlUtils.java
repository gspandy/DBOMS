/**
 * com.tydic.commons.utils.XmlUtils.java
 */
package com.tydic.commons.utils;

import java.io.File;
import java.io.FileWriter;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

 /**
 * @file  XmlUtils.java
 * @author weishaojia(viscar)
 * @version 0.1
 * @todo XML处理工具在
 * Copyright(C), 2013-2014
 *			Guangzhou Sunrise Electronics Development Co., Ltd.
 * History
 *   	1. Date: 2013-4-24
 *      	Author: weishaojia(viscar)
 *      	Modification: this file was created
 *   	2. ...
 */
public class XmlUtils {

	/**
	 * 把字符串转为Dom4j的XML对象
	 * @param xml
	 * @return
	 * @throws DocumentException
	 */
	public static Document getDom(String xml) throws DocumentException{
		return DocumentHelper.parseText(xml);
	}
	
	/**
	 * 创一个空XML，返回Dom4j的XML对象
	 * @return
	 * @throws DocumentException
	 */
	public static Document createXml() throws DocumentException{
		return DocumentHelper.createDocument();
	}
	
	
	/**
	 * 把一个XML对象写入文件
	 * @param xml
	 * @param filePath
	 * @throws Exception
	 */
	public static void writeXml(Document xml,String filePath) throws Exception{
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");// 设置XML文件的编码格式
        File file = new File(filePath);
        XMLWriter writer = new XMLWriter(new FileWriter(file), format);
        writer.write(xml);
        writer.close();
	}
	
	/**
	 * 从文件读入一个XML，返回Dom4j的XML对象
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public static Document readXml(String filePath) throws Exception{
		SAXReader reader = new SAXReader();
		return  reader.read(new File(filePath));
	}
}
