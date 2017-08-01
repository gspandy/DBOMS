package com.tydic.dbs.ws.interfaces;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by gonghongrui on 15/1/29.
 */
public interface Handler {

    public Map handle(HttpServletRequest request);

    /**
     * 检查参数是否包含了必要的参数
     * 如果有异常，返回错误信息
     * 没有异常，返回空
     * @return
     */
    public String checkParams(HttpServletRequest request);

    public String getServiceName();
}
