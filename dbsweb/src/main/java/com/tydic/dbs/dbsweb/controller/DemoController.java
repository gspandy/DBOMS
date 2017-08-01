package com.tydic.dbs.dbsweb.controller;

import com.google.gson.Gson;
import com.tydic.commons.utils.OrderUtils;
import com.tydic.dbs.buyer.service.BussTenantService;
import com.tydic.dbs.buyer.vo.BussTenant;
import com.tydic.dbs.dbsweb.common.CommonConfig;
import com.tydic.dbs.dbsweb.httpclient.HttpRemoteService;
import com.tydic.dbs.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2016/7/7.
 */
@Controller
@RequestMapping("/demo")
public class DemoController extends BaseController{


    @Autowired
    private CommonConfig commonConfig;

    @RequestMapping(value = "/leeAuthNotice")
    @ResponseBody
    public ResultVO leeAuthNotice(HttpServletRequest request, HttpServletResponse response) throws Exception {

        ResultVO resultVO = new ResultVO();
        String resultFlag  = request.getParameter("result_flag")==null?"1":request.getParameter("result_flag");
        String message  = request.getParameter("message")==null?"success":request.getParameter("message");

        String operId  = request.getParameter("user_id");

        String roles=  request.getParameter("roles");
        String accounts = request.getParameter("accounts");
        String urls = request.getParameter("urls");

        if(roles == null||"".equals(roles.trim())
                ||urls == null||"".equals(urls.trim())
                ||accounts == null||"".equals(accounts.trim())){
            resultVO.setResult_flag("0");
            resultVO.setMessage("user_id、roles,accounts,urls不能为空！请检查参数！");
            return resultVO;
        }

        String [] myRoles = roles.split(",");
        String [] myAccounts = accounts.split(",");
        String [] myUrls = urls.split(",");


        if(myRoles.length != myAccounts.length||myRoles.length != myUrls.length||myUrls.length != myAccounts.length){
            resultVO.setResult_flag("0");
            resultVO.setMessage("、roles,accounts,urls个数不匹配！");
            return resultVO;
        }



        Map<String,String> map = new HashMap<String,String>();
        map.put("service_name","LESSEE_ACCOUNT_AUTH_NOICE_SERVICE");
        map.put("session_id","20160713000001");
        map.put("result_flag",resultFlag);
        map.put("message",message);
        map.put("user_id",operId);
//        map.put("user_id","O2016072819521981276");
        List l = new ArrayList();
        for(int i=0;i<myRoles.length;i++){
            Map <String,String> m = new HashMap<String,String>();
            m.put("role",myRoles[i]);
            m.put("account",myAccounts[i]);
            m.put("url",myUrls[i]);
            l.add(m);
        }


        Gson gson = new Gson();
        map.put("result",gson.toJson(l));

        map.put("email","th6001@163.com");
        map.put("system_id","DBOMS");
        try {
            log.info("CommonConfig.httpServicePath:"+commonConfig.getHttpServicePath());
            resultVO = HttpRemoteService.service(commonConfig.getHttpServicePath()+"/leeAuthorNotice", map, ResultVO.class);
        }catch (Exception ex){
            log.error("调用接口异常",ex);
        }
        return resultVO;

    }

    @RequestMapping(value = "/dataNotice")
    @ResponseBody
    public ResultVO dataNotice(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ResultVO resultVO = new ResultVO();
        String bussId  = request.getParameter("user_id");

        String resultFlag  = request.getParameter("result_flag")==null?"1":request.getParameter("result_flag");
        String message  = request.getParameter("message")==null?"success":request.getParameter("message");

        String dataKey=  request.getParameter("data_pem_ids");
        String dataValue = request.getParameter("result_flags");

        if(dataKey == null||"".equals(dataKey.trim())||dataValue == null||"".equals(dataValue.trim())){
            resultVO.setResult_flag("0");
            resultVO.setMessage("user_id、data_pem_ids,result_flags不能为空！请检查参数！");
            return resultVO;
        }

        String [] dataKeys = dataKey.split(",");
        String [] dataValues = dataValue.split(",");

        if(dataKeys.length != dataValues.length){
            resultVO.setResult_flag("0");
            resultVO.setMessage("data_pem_ids,result_flags个数不匹配！");
            return resultVO;
        }

        Map<String,String> map = new HashMap<String,String>();
        map.put("service_name","LESSEE_DATA_AUTH_NOICE_SERVICE");
        map.put("session_id","20160713000001");
        map.put("result_flag",resultFlag);
        map.put("message",message);
        map.put("user_id",bussId);

        List l = new ArrayList();
        for(int i=0;i<dataKeys.length;i++){
            Map <String,String> m = new HashMap<String,String>();
            m.put("data_service_name",dataKeys[i]);
            m.put("result_flag",dataValues[i]);
            l.add(m);
        }

        Gson gson = new Gson();

        map.put("pem_info",gson.toJson(l));
        try {
            log.info("CommonConfig.httpServicePath:"+commonConfig.getHttpServicePath());
            resultVO = HttpRemoteService.service(commonConfig.getHttpServicePath()+"/dataResNotice", map, ResultVO.class);
        }catch (Exception ex){
            log.error("调用接口异常",ex);
        }
        return resultVO;
    }

   
    @RequestMapping(value = "/ordNotice")
    @ResponseBody
    public ResultVO ordNotice(HttpServletRequest request) throws Exception {
        ResultVO vo = null;
        String ordId =request.getParameter("work_no");
        String fileName =request.getParameter("file_name");
        String ip =request.getParameter("ip")==null?"192.168.0.214":request.getParameter("ip");
        String port =request.getParameter("port")==null?"21":request.getParameter("port");
        String user =request.getParameter("user");
        String password =request.getParameter("password");
        String file_path =request.getParameter("file_path");
        
        try {
            Map <String,String>param = new HashMap<String,String>();
            param.put("service_name", "ORDER_RESULT_NOTICE_SERVICE");
            param.put("work_no", ordId);
            param.put("file_name", fileName);
            Map <String,String>tmp = new HashMap<>();
            tmp.put("ip", ip);
            tmp.put("port", port);
            tmp.put("user", user);
            tmp.put("password", password);
            tmp.put("file_path", file_path);
            Gson gson = new Gson();
            param.put("ftp_info", gson.toJson(tmp));
            param.put("session_id", "18627909900");
            vo = HttpRemoteService.service(
            		commonConfig.getHttpServicePath()+"/orderNotice",
                    param, ResultVO.class);
        } catch (Exception ex) {
            log.error("调用接口异常", ex);
        }
        return vo;
    }

    @RequestMapping(value = "/test1")
    @ResponseBody
    public Map<String,String> test(HttpServletRequest request, @RequestParam(value = "operType", required = true) String operType) throws Exception {

        if("0".equals(operType)){
            throw new Exception("系统错误");
        }else if("1".equals(operType)){
            throw new NullPointerException("123");
        }else{

        }
        return null;

    }





}
