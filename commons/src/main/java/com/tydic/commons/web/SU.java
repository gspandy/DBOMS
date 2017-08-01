/**
 * com.tydic.commons.web.SU.java
 */
package com.tydic.commons.web;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.fins.gt.model.PageInfo;
import com.fins.gt.server.GridServerHandler;
import com.tydic.commons.utils.BaseVO;
import com.tydic.commons.utils.Grid;
import com.tydic.commons.utils.Page;

 /**
 * @file  SU.java
 * @author weishaojia(viscar)
 * @version 0.1
 * @todo	TODO
 * Copyright(C), 2013-2014
 *			Guangzhou Sunrise Electronics Development Co., Ltd.
 * History
 *   	1. Date: 2013-4-23
 *      	Author: weishaojia(viscar)
 *      	Modification: this file was created
 *   	2. ...
 */
public class SU {
	
	/**
	 * 从页面取得查询数据
	 * @param retMap
	 * @param vo
	 * @param req
	 * @return
	 */
	public static Map Get(Map retMap,BaseVO vo,HttpServletRequest req,HttpServletResponse rep){
		/**
		 BeanUtils.copyProperties(req.getParameterMap(), vo);  
		 retMap.put("Vo", vo); //需要查找的条件
		 int pageIndex = 1;
		 String strPage = req.getParameter("pageIndex");
		 if (strPage != null && strPage.trim().length() > 0){
			pageIndex = Integer.parseInt(strPage);
		 }
		 retMap.put("currPage",new Integer(pageIndex)); //当前在第几页
		 int perPageSize=10;
		 String strPerPageSize = req.getParameter("pageSize");
		 if (strPerPageSize != null && strPerPageSize.trim().length() > 0){
			perPageSize = Integer.parseInt(strPerPageSize);
		 }
		 retMap.put("pageSize",new Integer(perPageSize)); //每页显示的记录条数
		 **/

		 GridServerHandler gridServerHandler=new GridServerHandler(req,rep);

		 PageInfo pi=gridServerHandler.getPageInfo();
		 int pageIndex = 1;
		 if (pi != null && pi.getPageNum() > 1){
			pageIndex = pi.getPageNum();
		 }
		 int perPageSize=10;
		 if (pi != null && pi.getPageSize()> 0){
			perPageSize = pi.getPageSize();
		 }
		 retMap.put("currPage",new Integer(pageIndex)); //当前在第几页
		 retMap.put("pageSize",new Integer(perPageSize)); //每页显示的记录条数
		 retMap.put("Vo", vo); //需要查找的条件
		 return retMap;
	}
	
	/**
	 * 把GT-GRID转成本地GRID信息，以防前台不用GT-GRID时好处理
	 * @param req
	 * @param rep
	 * @return
	 */
	public static Grid Grid(HttpServletRequest req,HttpServletResponse rep){
		Grid grid=new Grid();
		GridServerHandler gridServerHandler=new GridServerHandler(req,rep);
		grid.setId(gridServerHandler.getGridInfo().getId());
		grid.setColumnInfo(gridServerHandler.getColumnInfo());
		grid.setSortInfo(gridServerHandler.getSortInfo());
		grid.setFieldsName(gridServerHandler.getFieldsName());
		grid.setFilterInfo(gridServerHandler.getFilterInfo());
		
		Page page=new Page();
		PageInfo pi=gridServerHandler.getPageInfo();
		page.setCurrentPage(pi.getPageNum());
		page.setEndNum(pi.getEndRowNum());
		page.setPageSize(pi.getPageSize());
		page.setStartNum(pi.getStartRowNum());
		page.setTotalNumber(pi.getTotalRowNum());
		page.setTotalPage(pi.getTotalPageNum());
		grid.setPageInfo(page);
		
		grid.setData(gridServerHandler.getData());
		grid.setInsertRecords(gridServerHandler.getInsertedRecords());
		grid.setUpdateRecords(gridServerHandler.getUpdatedRecords());
		grid.setDeleteRecords(gridServerHandler.getDeletedRecords());
		return grid;
	}
	
	/**
	 *  通过GT-GRID的输出封装
	 * @param page
	 * @param req
	 * @param rep
	 * @return
	 */
	public static boolean Put(Page page,BaseVO vo,HttpServletRequest req,HttpServletResponse rep){
		boolean ret=true;
		try {
			GridServerHandler gridServerHandler=new GridServerHandler(req,rep);
			gridServerHandler.setTotalRowNum(page.getTotalNumber());
//			List outList=new ArrayList();
//			for(int i=0;i<page.getList().size();i++){
//				outList.add(((BaseVO)page.getList().get(i)).toMap());
//			}
//			gridServerHandler.setData(outList);
			gridServerHandler.setData(page.getList(),vo.getClass());
			rep.setContentType("text/json;charset=UTF-8");
			PrintWriter out=null;
			out = rep.getWriter();
			out.print(gridServerHandler.getLoadResponseText());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	
	/**
	 * 保存成功返回
	 * @param flag
	 * @param req
	 * @param rep
	 * @return
	 */
	public static boolean Echo(boolean flag,HttpServletRequest req,HttpServletResponse rep){
		boolean ret=true;
		//设置该次操作是否成功.
		GridServerHandler gridServerHandler=new GridServerHandler(req,rep);
		gridServerHandler.setSuccess(flag);
		return ret;
	}
	
	/**
	 * 把字符串写向前端
	 * @param rep
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static boolean OutHtml(HttpServletResponse rep,String data) throws Exception{
		boolean ret=true;
		rep.setContentType("text/html;charset=UTF-8");
		PrintWriter out=null;
		out = rep.getWriter();
		out.print(data);
		return ret;
	}
	
	/**
	 * 把字符串写向前端
	 * @param rep
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static boolean OutText(HttpServletResponse rep,String data) throws Exception{
		boolean ret=true;
		rep.setContentType("text/text;charset=UTF-8");
		PrintWriter out=null;
		out = rep.getWriter();
		out.print(data);
		return ret;
	}
	
	/**
	 * 输出JSON数据
	 * @param data
	 * @param rep
	 * @return
	 */
	public static boolean OutJson(Map<String,Object> data,HttpServletResponse rep){
		boolean ret=true;
		try {
			Map<String,Object> map = new HashMap<String, Object>();	
			map.put("data", data);
			JSONArray jsonArray = JSONArray.fromObject(map);
			String str = jsonArray.toString();
			String modifyed_str = str.substring(1,str.length()-1);
			System.out.println(modifyed_str);
			rep.setContentType("text/json;charset=utf-8");
			PrintWriter out = rep.getWriter();
			out.print(modifyed_str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	
	/**
	 * 向前台输出XML
	 * @param rep
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static boolean OutXml(HttpServletResponse rep,String data) throws Exception{
		boolean ret=true;
		rep.setContentType("text/xml;charset=UTF-8");
		PrintWriter out=null;
		out = rep.getWriter();
		out.print(data);
		return ret;
	}

}
