package com.tydic.dbs.system.web.action.user;

import com.thoughtworks.xstream.XStream;
import com.tydic.commons.utils.Md5;
import com.tydic.commons.utils.OrderUtils;
import com.tydic.commons.utils.Page;
import com.tydic.dbs.commons.Constants;
import com.tydic.dbs.commons.constant.WcsSessionConstant;
import com.tydic.dbs.commons.web.BaseAnnotationAction;
import com.tydic.dbs.sign.MD5;
import com.tydic.dbs.system.operator.mapper.SysOperator;
import com.tydic.dbs.system.operator.service.SysOperatorService;
import com.tydic.dbs.system.organize.mapper.SysOrganize;
import com.tydic.dbs.system.organize.service.SysOrganizeService;
import com.tydic.dbs.system.role.mapper.SysOperRole;
import com.tydic.dbs.system.role.service.SysRoleService;
import com.tydic.dbs.system.web.action.operator.vo.SysOperateVo;
import com.tydic.dbs.system.web.action.organize.vo.SysOrganizeVo;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.*;

/**
 * 用户管理
 * Created by long on 2016/7/28.
 */
@Controller
@RequestMapping("/sysUser")
public class sysUserAction extends BaseAnnotationAction {

    @Resource
    SysOperatorService sysOperatorService;
    @Resource
    SysRoleService sysRoleService;
    @Resource
    SysOrganizeService sysOrganizeService;

    /**
     * 用户列表
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/toUserList")
    public String toUserList(HttpServletRequest request)throws  Exception{
        return "system/user/userManage";
    }

    /**
     * 获取用户列表
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/getUserList")
    public @ResponseBody Page getUserList(HttpServletRequest request)throws Exception{
        Map<String,Object>paramMap=new HashMap<>();
        String operAccount=request.getParameter("operAccount");
        String  operName=request.getParameter("operName");
        String status=request.getParameter("status");
        String pageNo = request.getParameter("pageNo");
        pageNo = pageNo==null||pageNo.equals("")?Constants.PAGE_NO:pageNo;
        paramMap.put(Page.CURR_PAGE, Integer.parseInt(pageNo));
        List<Map<String, Object>> orderBy = new ArrayList<Map<String, Object>>();
        Map<String, Object> hash = new HashMap<String, Object>();
        hash.put("createTime", "desc");
        orderBy.add(hash);
        paramMap.put(Page.PAGE_SIZE, Page.DEFAULT_PAGE_SIZE);
        paramMap.put("operAccount","" ==operAccount ? null:operAccount);
        paramMap.put("operNameLike","" ==operName ? null:operName);
        paramMap.put("status","" ==status ? null:status);
        paramMap.put("orderBy",orderBy);
        log.debug(paramMap);
        try {
            log.debug("开始查询！");
            Page page=sysOperatorService.getPageByParamMap(paramMap);
            log.debug("查询列表结束");
            return page;
        }catch (Exception e){
            log.error("ERROR:",e);
        }
        return null;
    }

    /**
     * 新增/修改用户
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/toUserAdd")
    public String toUserAdd(HttpServletRequest request)throws Exception{
        String operId=request.getParameter("operId");
        String operType=request.getParameter("operType");
        if(Constants.OPER_TYPE_UPDATE.equals(operType)){
            log.debug("修改信息：>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            SysOperator sysOperator=sysOperatorService.get(operId);
            SysOrganize sysOrganize=sysOrganizeService.get(sysOperator.getOrgCode());
            List<SysOperRole> list=sysRoleService.selectOperRole(operId);
            List<String> orgCode=new ArrayList<>();
            for (SysOperRole role:list) {
                orgCode.add(role.getRoleCode());
            }
            request.setAttribute("orgCode",orgCode);
            request.setAttribute("sysOperator",sysOperator);
            request.setAttribute("sysOrganize",sysOrganize);
            request.setAttribute("list",list);
            request.setAttribute("flag",Constants.OPER_TYPE_UPDATE);
        }else {
            request.setAttribute("flag",Constants.OPER_TYPE_SAVE);
        }
        return "system/user/userAdd";
    }

    /**
     * 获取角色列表
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/getRoleList")
    public @ResponseBody Page getRoleList(HttpServletRequest request)throws Exception{
        Map map=new HashMap();
        String roleCode=request.getParameter("roleCode");
        String roleName=request.getParameter("roleName");
        String pageNo = request.getParameter("pageNo");
        pageNo = pageNo==null||pageNo.equals("")?Constants.PAGE_NO:pageNo;
        map.put(Page.CURR_PAGE, Integer.parseInt(pageNo));
        List<Map<String, Object>> orderBy = new ArrayList<Map<String, Object>>();
        Map<String, Object> hash = new HashMap<String, Object>();
        hash.put("createTime", "desc");
        orderBy.add(hash);
        map.put(Page.PAGE_SIZE, Constants.PAGE_SIZE);
        map.put("roleCode",""==roleCode?null : roleCode);
        map.put("roleName",""==roleName?null : roleName);
        map.put("orderBy",orderBy);
        log.debug(map);
        try {
            Page page=sysRoleService.getPageByParamMap(map);
            return page;
        }catch (Exception e){
            log.error("ERROR:",e);
        }
        return null;
    }

    /**
     * 获取用户详细信息
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @author zhongjialiang
     */
    @RequestMapping("/getOperateDetail.do")
    public @ResponseBody
    SysOperator getOperateDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String operId = request.getParameter("operId");//获取用户id
        SysOperator sysOperator = sysOperatorService.get(operId);//获取用户信息
        SysOperator sysOperate = new SysOperator();
        BeanUtils.copyProperties(sysOperator, sysOperate);//copy操作
        if(sysOperator.getOrgCode()!=null){
        	SysOrganize sysOrganize=sysOrganizeService.get(sysOperator.getOrgCode());//获取组织机构信息
            if(sysOrganize==null || sysOrganize.getOrgName()==null || "".equals(sysOrganize.getOrgName())){
                sysOperate.setOrgName("");
            }else{
                sysOperate.setOrgName(sysOrganize.getOrgName());
            }
        }
        if(operId!=null){
        	   List<SysOperRole> list=sysRoleService.selectOperRole(operId);
        	   sysOperate.setSysOperRole(list);
        }
        return sysOperate;
    }

    /**
     * 新增或者修改用户
     * @param request
     * @return
     */
    @RequestMapping("/saveOrUpdateUser")
    @ResponseBody
    public Map<String,Object> saveOrUpdateUser(HttpServletRequest request){
        Map map=new HashMap();
        SysOperator sysOperator=new SysOperator();
        String operType=request.getParameter("operType");
        String roleCodes=request.getParameter("roleCode");
        SysOperator operator = (SysOperator) request.getSession().getAttribute(WcsSessionConstant.SESSION_OPERATOR);
        String[] roles=roleCodes.split(",");
        Set<String> hs=new HashSet<String>();
        for (int i=0;i<roles.length;i++){
            hs.add(roles[i]);
        }
        String[] arr=new String[hs.size()];
        try {
            if (Constants.OPER_TYPE_UPDATE.equals(operType)){
                sysOperator.setOperId(request.getParameter("oldOperId"));
                /*List<SysOperRole> list=sysRoleService.selectOperRole(request.getParameter("oldOperId"));
                for (SysOperRole role:list) {
                    hs.add(role.getRoleCode());
                }*/
                sysOperator.setModifier(operator.getOperId());
            }else {
                sysOperator.setCreater(operator.getOperId());
            }
            String operPwd=request.getParameter("operPwd");
            sysOperator.setOperAccount(request.getParameter("operAccount"));
            sysOperator.setOperPwd(""==operPwd?null:operPwd);
            sysOperator.setOperName(request.getParameter("operName"));
            sysOperator.setOrgCode(request.getParameter("orgCode"));
            sysOperator.setOperCardNo(request.getParameter("idCard"));
            sysOperator.setOperMobile(request.getParameter("mobile"));
            sysOperator.setOperAddr(request.getParameter("operAddr"));
            sysOperator.setPosition(request.getParameter("position"));
            sysOperator.setOperEmail(request.getParameter("email"));
            sysOperator.setStatus(Constants.ROLE_STATUS_NORMAL);
            sysOperatorService.save(sysOperator,hs.toArray(arr),Integer.parseInt(operType));
            map.put("success",true);
            if(Constants.OPER_TYPE_UPDATE.equals(operType)){
               map.put("message","更新成功！");
            }else {
                map.put("message","新增成功！");
            }
        }catch (Exception e){
            log.error("Error:",e);
            map.put("success",false);
            if(Constants.OPER_TYPE_UPDATE.equals(operType)){
                map.put("message","更新失败！");
            }else {
                map.put("message","新增失败！");
            }
        }
        return map;
    }
    
    /**
     * 
     * @Title: changStatus 
     * @Description: TODO(逻辑删除用户) 
     * @param @param request
     * @param @param response
     * @param @return
     * @param @throws Exception    设定文件 
     * @return SysOperateVo    返回类型 
     * @throws
     */
    @RequestMapping("/changStatus.do")
    public @ResponseBody SysOperateVo changStatus(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		SysOperator operator = (SysOperator) session.getAttribute(WcsSessionConstant.SESSION_OPERATOR);
    	SysOperateVo vo = new SysOperateVo();
    	String operId = request.getParameter("operId");
    	String operType = request.getParameter("operType");
    	if (operId!=null&&!"".equals(operId)) {
    		if (operType!=null&&!"".equals(operType)) {
        		List<String> ids = new ArrayList<String>();
        		ids.add(operId);
        		if (operType.equals("0"))
        			sysOperatorService.deleteLogical(ids, operator.getOperId());
        		else
        			sysOperatorService.recover(ids, operator.getOperId());
			}
		}
		return vo;
    }
}
