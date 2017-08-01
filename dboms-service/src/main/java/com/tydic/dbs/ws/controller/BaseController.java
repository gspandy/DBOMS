package com.tydic.dbs.ws.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tydic.dbs.ws.common.CommonConfig;
import com.tydic.dbs.ws.sign.SignUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2016/7/4.
 */
public class BaseController {

    protected Logger log = LoggerFactory.getLogger(this.getClass());


    public HashMap<String, String> getParams(HttpServletRequest request){

        HashMap<String, String> _params = new HashMap<>();

        Enumeration<String> names = request.getParameterNames();

        String _name;
        while(names.hasMoreElements()){
            _name = names.nextElement();
            _params.put(_name,request.getParameter(_name));
        }
        return _params;
    }
    public HashMap<String, Object> getParams(String jsonStr){
        Gson gson = new Gson();
        return gson.fromJson(jsonStr,new TypeToken<Map<String,Object>>() {
        }.getType());
    }





}
