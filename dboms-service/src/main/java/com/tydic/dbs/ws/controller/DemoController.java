package com.tydic.dbs.ws.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.tydic.dbs.ws.common.Constants;
import com.tydic.dbs.ws.interfaces.DataPemissionNoticeHandler;
import com.tydic.dbs.ws.interfaces.Handler;
import com.tydic.dbs.ws.interfaces.LesseeAuthDemoHandler;
import com.tydic.dbs.ws.interfaces.LesseeAuthNoticeHandler;
import com.tydic.dbs.ws.interfaces.OrderResultHandler;
import com.tydic.dbs.ws.vo.DataResourceNoticeVO;
import com.tydic.dbs.ws.vo.LeeAuthorVO;
import com.tydic.dbs.ws.vo.OrderNoticeVO;

/**
 * Created by dell on 2016/7/12.
 */

@Controller
@RequestMapping("/demo")
public class DemoController extends BaseController{

    @Autowired
    private LesseeAuthDemoHandler lesseeAuthDemoHandler;
    @Autowired
    private OrderResultHandler orderResultHandler;
    
    @Autowired
    private DataPemissionNoticeHandler dataPemissionNoticeHandler;
    
    @Autowired
    private LesseeAuthNoticeHandler lesseeAuthNoticeHandler;

    @RequestMapping(value = "/service")
    @ResponseBody
    public Map<String,String> notice(@RequestBody String json,HttpServletRequest request) {
    	log.info("param:"+json);
    	Map<String,String> map = new HashMap<String,String>();
		map.put("result_flag","1");
		map.put("message","SUCCESS");
		return map;
       
    }
    
    @RequestMapping(value = "/dataResNotice")
    @ResponseBody
    public Map<String,String> dataPemNotice(@RequestBody String param,HttpServletRequest request) {
    	if(param == null||"".equals(param.trim())){
    		 Map<String, String> map = new HashMap();
	        map.put(Constants.RESULT_FLAG,Constants.ERROR);
	        map.put(Constants.MESSAGE, "参数错误！");
	        return map;
    	}
    	Gson gson = new Gson();
    	DataResourceNoticeVO  vo = gson.fromJson(param, DataResourceNoticeVO.class);
    	return dataPemissionNoticeHandler.handle(vo);
       
    }
    
    @RequestMapping(value = "/leeAuthorNotice")
    @ResponseBody
    public Map<String,String> leeAuthorNotice(@RequestBody String param,HttpServletRequest request) {
    	if(param == null||"".equals(param.trim())){
    		 Map<String, String> map = new HashMap();
	        map.put(Constants.RESULT_FLAG,Constants.ERROR);
	        map.put(Constants.MESSAGE, "参数错误！");
	        return map;
    	}
    	Gson gson = new Gson();
    	LeeAuthorVO  vo = gson.fromJson(param, LeeAuthorVO.class);
    	return lesseeAuthNoticeHandler.handle(vo);
    }
    
    @RequestMapping(value = "/orderNotice")
    @ResponseBody
    public Map<String,String> orderNotice(@RequestBody String param,HttpServletRequest request) {
    	if(param == null||"".equals(param.trim())){
    		 Map<String, String> map = new HashMap();
	        map.put(Constants.RESULT_FLAG,Constants.ERROR);
	        map.put(Constants.MESSAGE, "参数错误！");
	        return map;
    	}
    	Gson gson = new Gson();
    	OrderNoticeVO  vo = gson.fromJson(param, OrderNoticeVO.class);
    	return orderResultHandler.handle(vo);
    }
    
    @RequestMapping("/test")
    @ResponseBody
    public  Map<String, String> test(HttpServletRequest request) throws Exception {
        String type = request.getParameter("type");
        Map<String, String> params = getParams(request);
        params.put(Constants.RESULT_FLAG,Constants.SUCCESS);
        params.put(Constants.MESSAGE, "SUCCESS");
        if("1".equals(type)){
            throw new Exception("test Exception");
        }
        return params;

    }


    private Handler getHandler(String service){
        return lesseeAuthDemoHandler;
    }



}
