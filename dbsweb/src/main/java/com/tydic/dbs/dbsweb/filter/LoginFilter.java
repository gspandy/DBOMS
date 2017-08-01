/*
 * com.tydic.dbs.obh.filter  2014-12-26
 *
 * Copyright 2010 Shenzhen TYDIC Information Technology Co.,Ltd.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.tydic.dbs.dbsweb.filter;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Enumeration;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.tydic.dbs.commons.vo.LoginMemberVo;
import com.tydic.dbs.dbsweb.common.Constants;

import org.springframework.web.filter.OncePerRequestFilter;



/**
 * 
 * @ClassName: LoginFilter 
 * @Description: TODO(登录过滤器) 
 * @author Michael dengwenjie@tydic.com 
 * @date 2016-7-25 下午2:42:43 
 *
 */
public class LoginFilter extends OncePerRequestFilter{
	/**   
	* 需要排除的页面   
	*/
	private String[] excludedPageArray={"login","/registration"};
	
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String uri = request.getRequestURI();
		//获取请求参数
		StringBuilder sb=new StringBuilder(uri);
		Enumeration<String> enu=request.getParameterNames();
		if(enu!=null){
			sb.append("?");
			while(enu.hasMoreElements()){
				String paraName=(String)enu.nextElement();
				String paraValue=request.getParameter(paraName);
				sb.append(paraName+"=");
				sb.append(paraValue);
				sb.append("&");
			}
		}
		String fromUrl=URLEncoder.encode(sb.toString(), "UTF-8");
		LoginMemberVo member=(LoginMemberVo)request.getSession().getAttribute(Constants.LOGIN_SESSION_MEMBER_KEY);
		//Object member = null;
    	if(member==null){
    		boolean isAjax=isAjaxRequest(request);
    		if(isAjax){
    			 response.setCharacterEncoding("UTF-8");  
                 response.sendError(500, "登录超时！");
                 return ; 
    		}
    		//获取网站的根目录
    		String path=request.getContextPath();
        	response.sendRedirect(path+"/login?from="+fromUrl);
        	return;
    	} 
    	filterChain.doFilter(request, response);
	}

	private boolean isAjaxRequest(HttpServletRequest request){
		if(!(request.getHeader("accept").indexOf("application/json") > -1 || (request  
                .getHeader("X-Requested-With")!= null && request  
                .getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))){
			return false;
		}else{
			return true;
		}
	}
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request)
			throws ServletException {
		// TODO Auto-generated method stub
		for (String page : excludedPageArray) {//判断是否在过滤url之外  
			if(request.getRequestURI().equals(page)){
				return true;
			}
		}
		return false;
	}     

}
