/*
 * com.tydic.dbs.buyer.web.action.order  2016-8-1
 *
 * Copyright 2010 Shenzhen TYDIC Information Technology Co.,Ltd.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.tydic.dbs.system.web.action.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tydic.commons.utils.Page;
import com.tydic.dbs.buyer.service.BussInfoService;
import com.tydic.dbs.buyer.vo.BussInfo;
import com.tydic.dbs.commons.utils.FileUtil;
import com.tydic.dbs.commons.web.BaseAnnotationAction;
import com.tydic.dbs.order.service.OrdInfoService;
import com.tydic.dbs.order.service.OrdLogService;
import com.tydic.dbs.order.service.OrdRusultService;
import com.tydic.dbs.order.vo.OrdInfoVo;
import com.tydic.dbs.order.vo.OrdLog;
import com.tydic.dbs.order.vo.OrdRusult;

/** 
 * @ClassName: SysOrderAction 
 * @Description: TODO(工单管理操作类) 
 * @author huangChuQin
 * @date 2016-8-1 下午3:59:44 
 *  
 */
@Controller
@RequestMapping("/sysOrder")
public class SysOrderAction extends BaseAnnotationAction {
	
	@Autowired
	private OrdInfoService ordInfoService;
	@Autowired
	private OrdLogService ordLogService;
	@Autowired
	private OrdRusultService ordRusultService;
	@Autowired
	private BussInfoService bussInfoService;
	
	@RequestMapping("/toOrderList")
	public String toOderList(HttpServletRequest request, HttpServletResponse response){
		return "system/order/orderList";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/getOrderList")
	public @ResponseBody Map<String, Object> getOrderList(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> params = new HashMap<String, Object>();
		String ordId1 = request.getParameter("ordId");
		if (StringUtils.isNotEmpty(ordId1)){
			ordId1 = ordId1.replaceAll("%", "\\\\%");
			ordId1 = ordId1.replaceAll("_", "\\\\_");
		}
		String prdId1 = request.getParameter("prdId");
		if (StringUtils.isNotEmpty(prdId1)){
			prdId1 = prdId1.replaceAll("%", "\\\\%");
			prdId1 = prdId1.replaceAll("_", "\\\\_");
		}
		String prdName1 = request.getParameter("prdName");
		if (StringUtils.isNotEmpty(prdName1)){
			prdName1 = prdName1.replaceAll("%", "\\\\%");
			prdName1 = prdName1.replaceAll("_", "\\\\_");
		}
		String startTime1 = request.getParameter("startTime");
		String endTime1 = request.getParameter("endTime");
		final String ordId = ordId1;
		final String prdId = prdId1;
		final String prdName = prdName1;
		final String startTime = startTime1;
		final String endTime = endTime1;
		final String ordStatus = request.getParameter("ordStatus");
		final String page = request.getParameter("page");
		params.put(Page.CURR_PAGE, Integer.parseInt(page));
		params.put(Page.PAGE_SIZE, 6);
		if (StringUtils.isNotEmpty(ordId))
			params.put("ordId", ordId);
		if (StringUtils.isNotEmpty(prdId))
			params.put("prdId", prdId);
		if (StringUtils.isNotEmpty(prdName))
			params.put("prdNameLike", prdName);
		if (StringUtils.isNotEmpty(ordStatus))
			params.put("ordStatus", ordStatus);
		if (startTime!=null&&!"".equals(startTime)) 
			params.put("startTime", startTime+" 00:00:00");
		if (endTime!=null&&!"".equals(endTime)) 
			params.put("endTime", endTime+" 23:59:59");
		List<Map<String, Object>> orderBy = new ArrayList<Map<String, Object>>();
    	Map<String, Object> hash = new HashMap<String, Object>();
    	hash.put("createTime", "desc");
    	orderBy.add(hash);
    	params.put("orderBy", orderBy);
		Map<String, Object> map = new HashMap<String, Object>();
    	List<OrdInfoVo> ordList = new ArrayList<OrdInfoVo>();
    	Page sysOrderPage = new Page();
    	try {
			sysOrderPage = ordInfoService.getPageByParamMap(params);
			if (sysOrderPage==null) {
				return map;
			}
			ordList = sysOrderPage.getList();
			map.put("totalNumber", sysOrderPage.getTotalNumber());
			map.put("totalPage", sysOrderPage.getTotalPage());
			map.put("rows", ordList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/toOrderDetail")
	public String toOrderDetail(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String ordId = request.getParameter("ordIdTemp");
		if (ordId==null||"".equals(ordId)) {
			throw new Exception("工单查询失败，工单号为空。");
		}
		
		//关联的工单基础信息
		OrdInfoVo ordInfoVo = ordInfoService.getByPkId(ordId);
		
		//工单操作日志信息
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ordId", ordId);
		Page ordLogPage = ordLogService.getPageByParamMap(params);
		List<OrdLog> ordLogList = ordLogPage.getList();
		
		//数据结果信息
		OrdRusult ordRusult = ordRusultService.get(ordInfoVo.getOrdRusultId());
		
		//商户信息
		BussInfo bussInfo = bussInfoService.get(ordInfoVo.getBusId());

		request.setAttribute("ordInfoVo", ordInfoVo);
		request.setAttribute("ordLogList", ordLogList);
		request.setAttribute("ordRusult", ordRusult);
		request.setAttribute("bussInfo", bussInfo);
		return "system/order/orderDetail";
	}

	@RequestMapping("/fileDown")
	public @ResponseBody String fileDown(HttpServletRequest request,HttpServletResponse response){
		String filePath = request.getParameter("filePath");
		String fileName = request.getParameter("fileName");
		FileUtil file = new FileUtil();
		return file.fileDown(request, response, filePath, fileName);
	}
}
