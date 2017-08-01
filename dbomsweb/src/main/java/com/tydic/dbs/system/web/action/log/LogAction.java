/**
 * 
 */
package com.tydic.dbs.system.web.action.log;

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

import com.tydic.commons.utils.DataTransUtils;
import com.tydic.commons.utils.Page;
import com.tydic.dbs.commons.web.BaseAnnotationAction;
import com.tydic.dbs.system.log.mapper.SysLoginLog;
import com.tydic.dbs.system.log.service.SysLoginLogService;

/**
 * 登录日志 LogAction
 * @author yangziran
 * @version 1.0 2014年4月3日
 */
@Controller
@RequestMapping("/log")
public class LogAction extends BaseAnnotationAction {

    @Autowired
    private SysLoginLogService sysLoginLogService;

    /**
     * 页面初始加载
     * @param request
     * @param response
     * @return
     * @author yangziran
     */
    @RequestMapping("/index.do")
    public String index(HttpServletRequest request, HttpServletResponse response) {

        return "system/log/managerLog";
    }

    /**
     * 获取登录日志列表
     * @param request
     * @param response
     * @param page
     * @param rows
     * @return
     * @throws Exception
     * @author yangziran
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/getList.do")
    public @ResponseBody
    Map<String, Object> getLogList(HttpServletRequest request, HttpServletResponse response, int page, int rows)
            throws Exception {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put(Page.CURR_PAGE, page);
        params.put(Page.PAGE_SIZE, rows > 0 ? rows : Page.DEFAULT_PAGE_SIZE);

        // 操作员工号
        String operId = request.getParameter("operId");
        if (StringUtils.isNotEmpty(operId)) {
            params.put("operIdLike", operId);
        }
        String startLoginTime = request.getParameter("startLoginTime");
        if (StringUtils.isNotEmpty(startLoginTime)) {
            params.put("loginTimeGte", DataTransUtils.str2Date(startLoginTime + " 00:00:00", "yyyy-MM-dd HH:mm:ss"));
        }
        String endLoginTime = request.getParameter("endLoginTime");
        if (StringUtils.isNotEmpty(endLoginTime)) {
            params.put("loginTimeLte", DataTransUtils.str2Date(endLoginTime + " 23:59:59", "yyyy-MM-dd HH:mm:ss"));
        }
        String startLogoutTime = request.getParameter("startLogoutTime");
        if (StringUtils.isNotEmpty(startLogoutTime)) {
            params.put("logoutTimeGte", DataTransUtils.str2Date(startLogoutTime + " 00:00:00", "yyyy-MM-dd HH:mm:ss"));
        }
        String endLogoutTime = request.getParameter("endLogoutTime");
        if (StringUtils.isNotEmpty(endLogoutTime)) {
            params.put("logoutTimeLte", DataTransUtils.str2Date(endLogoutTime + " 23:59:59", "yyyy-MM-dd HH:mm:ss"));
        }

        Page sysLoginLogPage = sysLoginLogService.getPageByParamMap(params);
        List<SysLoginLog> sysLoginLog = sysLoginLogPage.getList();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", sysLoginLogPage.getTotalNumber());
        map.put("rows", sysLoginLog);

        return map;
    }

    /**
     * 登录日志详情
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @author yangziran
     */
    @RequestMapping("/getDetail.do")
    public @ResponseBody
    SysLoginLog getOrganizeDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String logIdTmp = request.getParameter("logId");
        Long logId = null;
        if (StringUtils.isNotEmpty(logIdTmp)) {
            logId = Long.valueOf(logIdTmp);
        }

        SysLoginLog sysLoginLog = sysLoginLogService.get(logId);

        return sysLoginLog;
    }
}
