/*
 * com.tydic.dbs.dbsweb.controller  2016-7-11
 *
 * Copyright 2010 Shenzhen TYDIC Information Technology Co.,Ltd.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.tydic.dbs.dbsweb.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.tydic.dbs.commons.vo.LoginMemberVo;
import com.tydic.dbs.dbsweb.common.Constants;
import com.tydic.dbs.order.service.OrdInfoService;
import com.tydic.dbs.order.vo.OrdInfo;
import com.tydic.dbs.product.service.PrdInfoService;
import com.tydic.dbs.product.vo.PrdInfo;

/** 
 * @ClassName: IndexController 
 * @Description: TODO(首页控制器) 
 * @author Michael dengwenjie@tydic.com 
 * @date 2016-7-11 下午5:13:29 
 *  
 */
@Controller
@RequestMapping("/author/buss")
public class IndexController extends BaseController {
	
	@Autowired
	private OrdInfoService ordInfoService;
	@Autowired
	private PrdInfoService prdInfoService;
	
	/**
	 * 
	 * @Title: login 
	 * @Description: TODO(跳转到前端首页) 
	 * @param @param request
	 * @param @param model
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	@RequestMapping("/index")
	public String index(HttpServletRequest request,ModelMap model) throws Exception{
		LoginMemberVo loginMemberVo = (LoginMemberVo) request.getSession().getAttribute(Constants.LOGIN_SESSION_MEMBER_KEY);
		String bussId = "";
		int submitedProductNo = 0 ;
		int auditedProductNo = 0 ;
		int preSubmitOrderNo = 0 ;
		int submitedOrderNo = 0 ;
		int commitedOrderNo = 0 ;
		if (loginMemberVo != null){
			bussId = loginMemberVo.getBussId();
		}
		if (StringUtils.isNotBlank(bussId)){
			try {
				submitedProductNo = getSubmitedProduct(bussId);//已提交产品数量
				auditedProductNo = getAuditedProduct(bussId);//已审核产品数量
				preSubmitOrderNo = getPreSubmitOrder(bussId);//未提交工单数量
				submitedOrderNo = getSubmitedOrder(bussId);//已提交工单数量
				commitedOrderNo = getCommitedOrder(bussId);//已执行工单数量
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		model.addAttribute("submitedProductNo", String.valueOf(submitedProductNo));
		model.addAttribute("auditedProductNo", String.valueOf(auditedProductNo));
		model.addAttribute("preSubmitOrderNo", String.valueOf(preSubmitOrderNo));
		model.addAttribute("submitedOrderNo", String.valueOf(submitedOrderNo));
		model.addAttribute("commitedOrderNo", String.valueOf(commitedOrderNo));
		return "index";
	}
	
	/**
	 * 
	 * @Title: getSubmitedOrder 
	 * @Description: TODO(获取已提交工单的数量) 
	 * @param @param request
	 * @param @param model
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return int    返回类型 
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	private int getSubmitedOrder(String bussId) throws Exception{
		Map<String, Object> params=new HashMap<String, Object>();
		List<OrdInfo> ordInfoList = new ArrayList<>();
		params.put("busId", bussId);
		params.put("ordStatus", Constants.ORDER_SUBMITED);//已提交工单
		ordInfoList = ordInfoService.getPageByParamMap(params).getList();
		if (ordInfoList == null || ordInfoList.isEmpty()){
			return 0;
		}else {
			return ordInfoList.size();
		}
	}
	
	/**
	 * 
	 * @Title: getPreSubmitOrder 
	 * @Description: TODO(获取未提交工单的数量) 
	 * @param @param request
	 * @param @param model
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return int    返回类型 
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	private int getPreSubmitOrder(String bussId) throws Exception{
		Map<String, Object> params=new HashMap<String, Object>();
		List<OrdInfo> ordInfoList = new ArrayList<>();
		params.put("busId", bussId);
		params.put("ordStatus", Constants.ORDER_PRE_SUBMIT);//待提交工单
		ordInfoList = ordInfoService.getPageByParamMap(params).getList();
		if (ordInfoList == null || ordInfoList.isEmpty()){
			return 0;
		}else {
			return ordInfoList.size();
		}
	}
	
	/**
	 * 
	 * @Title: getCommitedOrder 
	 * @Description: TODO(获取已执行工单的数量) 
	 * @param @param request
	 * @param @param model
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return int    返回类型 
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	private int getCommitedOrder(String bussId) throws Exception{
		Map<String, Object> params=new HashMap<String, Object>();
		List<OrdInfo> ordInfoList = new ArrayList<>();
		params.put("busId", bussId);
		params.put("ordStatus", Constants.ORDER_COMMITED);//已执行工单
		ordInfoList = ordInfoService.getPageByParamMap(params).getList();
		if (ordInfoList == null || ordInfoList.isEmpty()){
			return 0;
		}else {
			return ordInfoList.size();
		}
	}
	
	/**
	 * 
	 * @Title: getSubmitedProduct 
	 * @Description: TODO(获取已提交产品的数量) 
	 * @param @param request
	 * @param @param model
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return int    返回类型 
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	private int getSubmitedProduct(String bussId) throws Exception{
		Map<String, Object> params=new HashMap<String, Object>();
		List<PrdInfo> prdInfoList = new ArrayList<PrdInfo>();
		params.put("bussId", bussId);
		//params.put("prdStatus", "2");//已提交产品
		prdInfoList = prdInfoService.getPageByParamMap(params).getList();
		if (prdInfoList == null || prdInfoList.isEmpty()){
			return 0;
		}else {
			return prdInfoList.size();
		}
	}
	
	/**
	 * 
	 * @Title: getAuditedProduct 
	 * @Description: TODO(获取已审核的产品数量) 
	 * @param @param request
	 * @param @param model
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return int    返回类型 
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	private int getAuditedProduct(String bussId) throws Exception{
		Map<String, Object> params=new HashMap<String, Object>();
		List<PrdInfo> prdInfoList = new ArrayList<PrdInfo>();
		params.put("bussId", bussId);
		params.put("prdStatus", Constants.PRODUCT_AUDITED);//已审核产品
		prdInfoList = prdInfoService.getPageByParamMap(params).getList();
		if (prdInfoList == null || prdInfoList.isEmpty()){
			return 0;
		}else {
			return prdInfoList.size();
		}
	}
}
