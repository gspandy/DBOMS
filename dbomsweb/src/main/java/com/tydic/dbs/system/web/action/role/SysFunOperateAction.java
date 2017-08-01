package com.tydic.dbs.system.web.action.role;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fins.gt.util.StringUtils;
import com.tydic.dbs.commons.constant.WcsSessionConstant;
import com.tydic.dbs.commons.web.BaseAnnotationAction;
import com.tydic.dbs.system.menu.mapper.SysFunOperate;
import com.tydic.dbs.system.menu.service.SysFunOperateService;
import com.tydic.dbs.system.operator.mapper.SysOperator;
import com.tydic.dbs.system.role.mapper.SysRoleMenu;
import com.tydic.dbs.system.role.service.SysRoleMenuService;

/**
 * 
 * @ClassName: SysFunOperateAction 
 * @Description: TODO(菜单按钮操作类) 
 * @author huangChuQin
 * @date 2016-7-28 下午8:04:06 
 *
 */
@Controller
@RequestMapping("/funOperate")
public class SysFunOperateAction extends BaseAnnotationAction {
	
	@Autowired
	private SysFunOperateService sysFunOperateService;
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	
	/**
	 * 当前登录用户功能操作权限数据获取
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getFunOperatesByOperator")
	public @ResponseBody Map<String, Boolean> getFunOperatesByOperator(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		SysOperator operator = (SysOperator) session.getAttribute(WcsSessionConstant.SESSION_OPERATOR);
		Map<String, Boolean> result = new HashMap<String, Boolean>();
		/**获取当前用户所拥有的功能操作列表，并加入到session中*/
		List<SysFunOperate> funOperateArray = sysFunOperateService.getHasAuthFunOperateByMenuCodeAndOperId(null, operator.getOperId());
		if (CollectionUtils.isNotEmpty(funOperateArray)) {
			for (SysFunOperate obj : funOperateArray)
				result.put(obj.getMenuCode()+"_"+obj.getOperateType(), Boolean.TRUE);
			/**将可用操作加入到session中*/
			session.setAttribute(WcsSessionConstant.SESSION_FUN_OPERATE, result);
		}
		return result;
	}
	
	/**
	 * 根据菜单编码获取操作数据
	 * @param request
	 * @param menuCode 菜单编码
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getFunOperatesByMenuCode")
	public @ResponseBody List<SysFunOperate> getFunOperatesByMenuCode(HttpServletRequest request,
			String menuCode, String roleCode) throws Exception {
		//获取菜单功能操作数据
		List<SysFunOperate> funOperates = sysFunOperateService.getByMenuCode(menuCode);
		//获取角色菜单功能操作数据
		if (StringUtils.isNotEmpty(roleCode) && CollectionUtils.isNotEmpty(funOperates)) {
			SysRoleMenu roleAuth = new SysRoleMenu();
			roleAuth.setMenuCode(menuCode);
			roleAuth.setRoleCode(roleCode);
			List<SysRoleMenu> roleAuthArray = sysRoleMenuService.getRoleOperat(roleAuth);
			if (CollectionUtils.isNotEmpty(roleAuthArray)) {
				List<SysFunOperate> operates = new ArrayList<SysFunOperate>();
				for (SysFunOperate fun : funOperates) {
					for (SysRoleMenu auth : roleAuthArray) {
						if (StringUtils.isNotEmpty(auth.getOperateCode()) && fun.getOperateCode().equals(auth.getOperateCode())){
							fun.setIsChecked(Boolean.TRUE);
							break;
						} else {
							fun.setIsChecked(Boolean.FALSE);
						}
					}
					operates.add(fun);
				}
				return operates;
			}
		}
		return funOperates;
	}
}
