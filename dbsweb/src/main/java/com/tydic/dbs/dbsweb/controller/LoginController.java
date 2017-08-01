/*
 * com.tydic.dbs.obh.controller  2014-12-26
 *
 * Copyright 2010 Shenzhen TYDIC Information Technology Co.,Ltd.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.tydic.dbs.dbsweb.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tydic.commons.utils.Md5;
import com.tydic.commons.utils.OrderUtils;
import com.tydic.commons.utils.WebContextUtils;
import com.tydic.dbs.buyer.service.BussInfoService;
import com.tydic.dbs.buyer.vo.BussInfo;
import com.tydic.dbs.commons.define.WcsDefinition;
import com.tydic.dbs.commons.exception.BizException;
import com.tydic.dbs.dbsweb.common.Constants;
import com.tydic.dbs.commons.enums.SmsType;
import com.tydic.dbs.commons.sms.webService.sms.SmsResponse;
import com.tydic.dbs.commons.vo.LoginMemberVo;
import com.tydic.dbs.system.SysLoginSms.mapper.SysLoginSms;
import com.tydic.dbs.system.SysLoginSms.service.SysLoginSmsService;
import com.tydic.dbs.system.sms.mapper.SysSms;
import com.tydic.dbs.system.sms.service.DBsSmsService;
import com.tydic.dbs.system.sms.service.SysSmsService;
import com.tydic.dbs.system.sysUploadFile.mapper.SysUploadFile;
import com.tydic.dbs.system.sysUploadFile.service.SysUploadFileService;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @ClassName: LoginController
 * @Description: TODO(登录控制器)
 * @author Michael dengwenjie@tydic.com
 * @date 2016-7-12 上午11:45:22
 * 
 */
@Controller
public class LoginController extends BaseController {

	@Autowired
	private BussInfoService bussInfoService;
	 @Autowired 
	 private DBsSmsService dBsSmsService;
	
	@Autowired
	private SysLoginSmsService sysLoginSmsService;
	
	@Autowired
    private SysUploadFileService sysUploadFileService;
	
	@Autowired
    private SysSmsService sysSmsService;
	/****
	 * 
	 * @Title: login
	 * @Description: 跳转到登录页面
	 * @param @param request
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping("/login")
	public String login(
			@RequestParam(value = "from", required = false) String from,
			ModelMap model, HttpServletRequest request) {
		LoginMemberVo loginMemberVo = (LoginMemberVo) request.getSession()
				.getAttribute(Constants.LOGIN_SESSION_MEMBER_KEY);

		if (from != null && loginMemberVo != null) {
			return "redirect:/author/buss/index";
		}
		model.addAttribute("from", from);

		return "login";
	}

	/***
	 * 
	 * @Title: doLogin
	 * @Description: 提交登录
	 * @param @param mobile
	 * @param @param type
	 * @param @param password
	 * @param @param sms
	 * @param @param valcode
	 * @param @param request
	 * @param @return
	 * @param @throws BizException
	 * @param @throws Exception 设定文件
	 * @return Map<String,Object> 返回类型
	 * @throws
	 */
	@SuppressWarnings({ "unchecked", "null", "static-access" })
	@RequestMapping("/doLogin")
	@ResponseBody
	public Map<String, Object> doLogin(
			@RequestParam(value = "account", required = false) String account,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "loginType", required = false) Integer loginType,
			@RequestParam(value = "sms", required = false) String sms,
			@RequestParam(value = "smsValCode", required = false) String smsValCode,
			@RequestParam(value = "isValcode", required = false) Integer isValcode,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		Boolean flag = false;
		List<LoginMemberVo> accountList = new ArrayList<>();
		LoginMemberVo vo = new LoginMemberVo();
		String realPwd = Md5.En(password);
		// 未审核商户状态不允许登陆
		BussInfo bussInfo = bussInfoService.getaccId(account);
		String bussStatus = WcsDefinition.wcsBussStatus.PENDING;
		if (bussInfo != null && bussStatus.equals(bussInfo.getBussStatus())) {
			map.put("msg", "商户尚在审核中，请耐心等候，审核通过后我们会尽快短信、邮件通知您！");
			return map;
		} else {
			if (loginType.equals(Constants.LOGIN_TYPE_SMS)) {// 以手机号，短信验证码方式登录
				// 先查询当前手机号账户是否存在
				params.put("bussMobileNo", account);
				accountList = bussInfoService.getPageByParamMap(params)
						.getList();
				if (accountList.isEmpty() || accountList.size() <=0) {
					map.put("msg", "用户账户不存在");
				} else {
					BeanUtils.copyProperties(vo, accountList.get(0));
					// 再次判断商户状态
					String accountStatus = vo.getBussStatus();
					if (WcsDefinition.wcsBussStatus.Fail.equals(accountStatus)) {
						map.put("msg", "用户账户已注销");
					}else if(WcsDefinition.wcsBussStatus.EXAMINATION_NO_PASSED.equals(accountStatus)){
						map.put("msg", "商户信息未通过审核，请联系管理员处理");
					}
					else {
						if (realPwd.equals(vo.getBussPasswd())) {
							// 再次判断输入的短信验证码是否正确
							boolean sflag = sysLoginSmsService.isRightSms(
									account, sms,
									SmsType.LOGIN_VALIDATE.getCode());
							if (sflag) {
								flag = true;
								// 保存登录日志信息
							} else {
								map.put("msg", "您输入的短信验证码不正确！");
							}
						} else {
							map.put("msg", "登录失败，用户名或密码错误！");
						}
					}
				}

			} else if (loginType.equals(Constants.LOGIN_TYPE_MAIL)) {// 以邮箱，密码方式登录
				params.put("bussEmail", account);
				accountList = bussInfoService.getPageByParamMap(params)
						.getList();
				if (accountList.isEmpty() || accountList.size() <=0) {
					map.put("msg", "用户账户不存在");
				} else {
					BeanUtils.copyProperties(vo, accountList.get(0));
					// 再次判断商户状态
					String accountStatus = vo.getBussStatus();
					if (WcsDefinition.wcsBussStatus.Fail.equals(accountStatus)) {
						map.put("msg", "用户账户已注销");
					}else if(WcsDefinition.wcsBussStatus.EXAMINATION_NO_PASSED.equals(accountStatus)){
						map.put("msg", "商户信息未通过审核，请联系管理员处理");
					}else {
						if (realPwd.equals(vo.getBussPasswd())) {
							flag = true;
							// 保存登录日志信息
						} else {
							map.put("msg", "登录失败，用户名或密码错误！");
						}
					}
				}

			} else if (loginType.equals(Constants.LOGIN_TYPE_SERVICE)) {// 以账户，密码方式登录
				params.put("bussAccount", account);
				accountList = bussInfoService.getPageByParamMap(params)
						.getList();
				if (accountList.isEmpty() || accountList.size() <=0) {
					map.put("msg", "用户账户不存在");
				} else {
					BeanUtils.copyProperties(vo, accountList.get(0));
					// 再次判断商户状态
					String accountStatus = vo.getBussStatus();
					if (WcsDefinition.wcsBussStatus.Fail.equals(accountStatus)) {
						map.put("msg", "用户账户已注销");
					}else if(WcsDefinition.wcsBussStatus.EXAMINATION_NO_PASSED.equals(accountStatus)){
						map.put("msg", "商户信息未通过审核，请联系管理员处理");
					}else {
						if (realPwd.equals(vo.getBussPasswd())) {
							// 保存登录日志信息
							flag = true;
						} else {
							map.put("msg", "登录失败，用户名或密码错误！");
						}
					}
				}
			}
		}
		/*** 将商户信息保存到cookie中-start ***/
		vo.setLoginTime(new Date());
		String fileId = "";
		String bussId = "";
		BussInfo busInfo = new BussInfo();
		if(vo!=null){
			bussId = vo.getBussId();
		}
		if(StringUtils.isNotBlank(bussId)){
			try {
				busInfo = bussInfoService.get(bussId);
				if (busInfo != null){
					BeanUtils.copyProperties(vo, busInfo);
					fileId = busInfo.getFileId();
				}			
			} catch (Exception e) {
				log.error("获取商户信息失败");
				e.printStackTrace();
			}
		}
		if(StringUtils.isNotBlank(fileId)){
			try {
				SysUploadFile uploadFile = sysUploadFileService.get(Long.valueOf(fileId));
				if (uploadFile != null) {
					vo.setUploadFileName(uploadFile.getFileName());
				}
			} catch (Exception e) {
				log.error("获取商户证件失败");
				e.printStackTrace();
			}
		}
		request.getSession().setAttribute(Constants.LOGIN_SESSION_MEMBER_KEY,
				vo);
		/*** 将商户信息保存到cookie中-end ***/
		map.put("flag", flag);
		return map;
	}

	/***
	 * 
	 * @Title: sendSms
	 * @Description: TODO(发送短信验证码)
	 * @param @param mobile
	 * @param @return
	 * @param @throws BizException 设定文件
	 * @return Map<String,Object> 返回类型
	 * @throws
	 */
	@RequestMapping("/sms/send")
	@ResponseBody
	public Map<String, Object> sendSms(
			@RequestParam(value = "mobile", required = false) String mobile,
			@RequestParam(value = "smsType", required = false) Integer smsType)
			throws BizException {
		if (StringUtils.isBlank(mobile)) {
			throw new BizException(0, "手机号不能为空");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		Boolean flag = false;
		try {
			// 保存短信随机码进库
			SysLoginSms sms = new SysLoginSms();
			Date createdate = new Date();
			Date Expiredate = new Date();
			Expiredate.setTime((createdate.getTime() + 1 * 60 * 1000));// 设置短信有效时间为1分钟
			sms.setMobile(mobile);
			sms.setVeriCode(OrderUtils.getFixLenthString(5));
			if (SmsType.LOGIN_VALIDATE.equals(smsType)){
				sms.setSmsType(SmsType.LOGIN_VALIDATE.getCode());
			}else{
				sms.setSmsType(SmsType.MODIFY_PWD.getCode());
			}
			sms.setCreateTime(createdate);
			sms.setExpireTime(Expiredate);
			//发送短信内容
			String sendMessage = WebContextUtils.getLocaleMessage("短信验证码为", "")
					.replace("{}", sms.getVeriCode());
			//保存信息到sys_sms表
			SysSms smsvo = new SysSms();
			smsvo.setCalledNum(mobile);
			smsvo.setContent(sendMessage);
			smsvo.setCreateTime(createdate);
			smsvo = sysSmsService.save(smsvo);
			sms.setSmsId(smsvo.getSmsId());
			sms = sysLoginSmsService.save(sms);
			// 调用SOA接口发送随机短信
			SmsResponse smsResponse = dBsSmsService.sendSms(smsvo);//暂时注释掉
			//更新sys_sms表信息
			smsvo.setStatus(smsResponse.getReturnvalue());
			smsvo.setResult(smsResponse.getOperatingreturn());
			flag = true;
			map.put("msg", "发送短信验证码成功!");
			
		} catch (Exception e) {
			log.error(">>>>>>>>send sms code error!!<<<<<<<<<", e);
			map.put("msg", "发送短信验证码失败!");
			throw new BizException(0, "发送短信验证码失败");

		}

		map.put("flag", flag);
		return map;
	}

	/**
	 * 
	 * @Title: logout
	 * @Description: TODO(商户退出共功能)
	 * @param @param request
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return Map<String,Object> 返回类型
	 * @throws
	 */
	@RequestMapping("/logout")
	@ResponseBody
	public Map<String, Object> logout(HttpServletRequest request)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		request.getSession()
				.removeAttribute(Constants.LOGIN_SESSION_MEMBER_KEY);
		request.getSession().invalidate();
		map.put("flag", true);
		map.put("msg", "您已安全退出!");

		return map;
	}
	
	/**
	 * 
	 * @Title: forgetPwd 
	 * @Description: TODO(商户忘记登录密码跳转) 
	 * @param @param request
	 * @param @param model
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	@RequestMapping("/forgetPwd")
	public String forgetPwd() throws Exception{
		
		return "bussInfo/forgotPwd";
	}
	
	
	/**
	 * 
	 * @Title: doForgetPwd 
	 * @Description: TODO(忘记密码-验证短信验证码输入正确与否) 
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	@SuppressWarnings({ "unchecked", "null", "static-access" })
	@RequestMapping("/doForgetPwd")
	@ResponseBody
	public Map<String, Object> doForgetPwd(HttpServletRequest request) throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		Map<String, Object> params=new HashMap<String, Object>();
		List<LoginMemberVo> accountList = new ArrayList<>();
		LoginMemberVo vo=new LoginMemberVo();
		Boolean flag= false;
		String account = request.getParameter("userName");
		String smsCode = request.getParameter("smsCode");
		if(StringUtils.isBlank(account)){
			throw new BizException(0, "账号不能为空");
		}
		if(StringUtils.isBlank(smsCode)){
			throw new BizException(0, "验证码不能为空");
		}
		//以账户，密码方式登录
		params.put("bussAccount", account);
		accountList = bussInfoService.getPageByParamMap(params).getList();
		if (accountList.isEmpty() || accountList.size() <=0){
			map.put("msg", "用户账户不存在");
		} else {
			BeanUtils.copyProperties(vo, accountList.get(0));
			//再次判断商户状态
			String accountStatus = vo.getBussStatus();
			if (WcsDefinition.wcsBussStatus.Fail.equals(accountStatus)) {
				map.put("msg", "用户账户已注销");
			}else if(WcsDefinition.wcsBussStatus.EXAMINATION_NO_PASSED.equals(accountStatus)){
				map.put("msg", "商户信息未通过审核，请联系管理员处理");
			}else{
				//再次判断输入的短信验证码是否正确
				boolean sflag = sysLoginSmsService.isRightSms(vo.getBussMobileNo(), smsCode, SmsType.LOGIN_VALIDATE.getCode());
				if(sflag){
					flag = true;
				}else{
					map.put("msg", "您输入的短信验证码不正确！");
				}
			}
		}
		
		request.getSession().setAttribute(Constants.VISIT_SESSION_MEMBER_KEY, vo);
		map.put("flag", flag);
		return map;
		
	}
	
	/**
	 * 
	 * @Title: resetPwd 
	 * @Description: TODO(商户重置登录密码跳转) 
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	@RequestMapping("/resetPwd")
	public String resetPwd() throws Exception{
		return "bussInfo/resetPwd";
	}
	
	/**
	 * 
	 * @Title: doResetPwd 
	 * @Description: TODO(商户重置登录密码) 
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	@RequestMapping("/doResetPwd")
	@ResponseBody
	public Map<String, Object> doResetPwd(HttpServletRequest request) throws Exception{
		LoginMemberVo loginMemberVo = (LoginMemberVo) request.getSession().getAttribute(Constants.VISIT_SESSION_MEMBER_KEY);
		Map<String, Object> map=new HashMap<String, Object>();
		String newPwd = request.getParameter("newPwd");
		String cnewPwd = request.getParameter("cnewPwd");
		Boolean flag= false;
		if(StringUtils.isBlank(newPwd)){
			throw new BizException(0, "新密码不能为空！");
		}
		if(StringUtils.isBlank(cnewPwd)){
			throw new BizException(0, "确认新密码不能为空！");
		}
		if(!newPwd.equals(cnewPwd)){
			throw new BizException(0, "两次输入的新密码不相等！");
		}
		if (loginMemberVo != null){
			BussInfo vo = bussInfoService.get(loginMemberVo.getBussId());
			if (vo != null) {
				vo.setBussPasswd(Md5.En(cnewPwd.trim()));
				try{
					bussInfoService.save(vo);
					flag = true;
					map.put("msg", "重置密码成功！");
				}catch (Exception e){
					log.error("重置密码失败！");
					e.printStackTrace();
				}
			}			
		}
		
		map.put("flag", flag);
		return map;
		
	}
	
	/***
	 * 
	 * @Title: queryAccount
	 * @Description: TODO(根据输入账号查询账号是否存在)
	 * @param @param mobile
	 * @param @return
	 * @param @throws BizException 设定文件
	 * @return Map<String,Object> 返回类型
	 * @throws
	 */
	@SuppressWarnings({ "unchecked" })
	@RequestMapping("/queryAccount")
	@ResponseBody
	public Map<String, Object> queryAccount(HttpServletRequest request,ModelMap model)	throws Exception {
		String account = request.getParameter("account");
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		List<BussInfo> accountList = new ArrayList<>();
		Boolean flag = false;
		if (StringUtils.isBlank(account)) {
			throw new BizException(0, "账号不能为空!");
		}
		params.put("bussAccount", account);
		try {
			accountList = bussInfoService.getPageByParamMap(params).getList();
		} catch (Exception e) {
			map.put("msg", "查询账户失败");
			e.printStackTrace();
		}
		if(accountList.isEmpty() || accountList.size() <=0){
			map.put("msg", "用户账户不存在");
		}else{
			flag=true;
		}

		map.put("flag", flag);
		return map;
	}
	
	/**
	 * 
	 * @Title: resetPwd 
	 * @Description: TODO(商户修改密码跳转) 
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	@RequestMapping("/author/modifyPwd")
	public String modifyPwd() throws Exception{
		return "bussInfo/modifyPwd";
	}
	
	/**
	 * 
	 * @Title: doModifyPwd 
	 * @Description: TODO(商户修改密码操作) 
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	@RequestMapping("/author/doModifyPwd")
	@ResponseBody
	public Map<String, Object> doModifyPwd(HttpServletRequest request) throws Exception{
		LoginMemberVo loginMemberVo = (LoginMemberVo) request.getSession().getAttribute(Constants.LOGIN_SESSION_MEMBER_KEY);
		Map<String, Object> map=new HashMap<String, Object>();
		String oldPwd = request.getParameter("oldPwd");
		String newPwd = request.getParameter("newPwd");
		String cnewPwd = request.getParameter("cnewPwd");
		Boolean flag= false;
		
		if(StringUtils.isBlank(oldPwd)){
			throw new BizException(0, "旧密码不能为空！");
		}
		if(StringUtils.isBlank(newPwd)){
			throw new BizException(0, "新密码不能为空！");
		}
		if(StringUtils.isBlank(cnewPwd)){
			throw new BizException(0, "确认新密码不能为空！");
		}
		if(!newPwd.equals(cnewPwd)){
			throw new BizException(0, "两次输入的新密码不相等！");
		}
		
		if(!Md5.En(oldPwd.trim()).equals(loginMemberVo.getBussPasswd())){
			map.put("msg", "旧密码输入不正确！");
			return map;
		}
		if (loginMemberVo != null){
			BussInfo vo = bussInfoService.get(loginMemberVo.getBussId());
			if (vo != null) {
				vo.setBussPasswd(Md5.En(cnewPwd.trim()));
				try{
					bussInfoService.save(vo);
					flag = true;
					map.put("msg", "修改密码成功！");
				}catch (Exception e){
					log.error("修改密码失败！");
					map.put("msg", "修改密码失败！");
					e.printStackTrace();
				}
			}			
		}
		
		map.put("flag", flag);
		return map;
		
	}
}
