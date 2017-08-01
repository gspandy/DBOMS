package com.tydic.dbs.ws.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.tydic.dbs.buyer.service.InterfaceLogService;
import com.tydic.dbs.buyer.vo.InterfaceLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tydic.dbs.ws.common.CommonConfig;
import com.tydic.dbs.ws.common.Constants;
import com.tydic.dbs.ws.interfaces.DataPemissionNoticeHandler;
import com.tydic.dbs.ws.interfaces.DataServiceNoticeHandler;
import com.tydic.dbs.ws.interfaces.Handler;
import com.tydic.dbs.ws.interfaces.ITResourceNoticeHandler;
import com.tydic.dbs.ws.interfaces.LesseeAuthNoticeHandler;
import com.tydic.dbs.ws.interfaces.OrderResultHandler;
import com.tydic.dbs.ws.sign.SignUtil;

/**
 * Created by dell on 2016/7/12.
 */

@Controller
@RequestMapping("/service")
public class DispatcherController  extends BaseController{

    @Autowired
    private LesseeAuthNoticeHandler lesseeAuthNoticeHandler;

    @Autowired
    private ITResourceNoticeHandler itResourceNoticeHandler;

    @Autowired
    private DataPemissionNoticeHandler dataPemissionNoticeHandler;

    @Autowired
    private DataServiceNoticeHandler dataServiceNoticeHandler;
    
    @Autowired
    private OrderResultHandler orderResultHandler;
    
    @Autowired
    private CommonConfig commonConfig;

    @Autowired
    private InterfaceLogService interfaceLogService;

    @RequestMapping(value = "/notice",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> notice(@RequestBody String json,HttpServletRequest request) {

        Map<String, String> params = getParams(request);
//        Map<String, Object> params = getParams(json);

        log.info("The parameters enter into GatewayController 1:" + params.toString());
        ModelAndView mav = null;
        try {

            boolean isSign = SignUtil.getSignVeryfy(params, commonConfig.getHttpKey());
//            boolean isSign = true;
            //签名错误
            if (!isSign) {
                Map<String, String> sParams = new HashMap<String, String>();
                sParams.put("result_flag", "0");
                sParams.put("message", "error sign");
                return sParams;
            } else {

                //查找对应的服务开始处理
                String service = request.getParameter(Constants.SERVICE_KEY);

                log.info(params.toString());
                Handler handler = this.getHandler(service);
                if(handler == null){
                    log.info("*************rquest service Error");
                    Map<String, String> sParams = new HashMap<String, String>();
                    sParams.put("result_flag", "0");
                    sParams.put("message","请求服务错误!请检查service name");
                    return sParams;

                }
                String result = handler.checkParams(request);

                if (result != null) {
                    log.info("*************request param  error");
                    Map<String, String> sParams = new HashMap<String, String>();
                    sParams.put("result_flag", "0");
                    sParams.put("message",result+ " is null !");
                    return sParams;
                }
                log.info("The service name:" + service);
                log.info("The parameters enter into GatewayController 2:" + params.toString());
                return handler.handle(request);
            }
        }catch (Exception e){
            log.info("*************Error occur while enter into GatewayController", e);
            Map<String, String> sParams = new HashMap<String, String>();
            sParams.put(Constants.RESULT_FLAG,Constants.ERROR);
            sParams.put(Constants.MESSAGE, "调用接口异常");
            return sParams;
        }

    }

    private Handler getHandler(String service){
        Handler handler = null;
        if(lesseeAuthNoticeHandler.getServiceName().equals(service)){
            handler = lesseeAuthNoticeHandler;
        }else if(itResourceNoticeHandler.getServiceName().equals(service)){
            handler = itResourceNoticeHandler;
        }else if(dataPemissionNoticeHandler.getServiceName().equals(service)){
            handler = dataPemissionNoticeHandler;
        }else if(dataServiceNoticeHandler.getServiceName().equals(service)){
            handler = dataServiceNoticeHandler;
        }else if (orderResultHandler.getServiceName().equals(service)) {
        	handler = orderResultHandler;
		}
        return handler;
    }

    public void saveLog(String serilaNum,String busiType,String paramIn,String outParam){
        InterfaceLog interfaceLog = new InterfaceLog();
        interfaceLog.setSerialNum(serilaNum);
        interfaceLog.setBusiType(busiType);
        interfaceLog.setParamIn(paramIn);
        interfaceLog.setResultContent(outParam);
        try {
            interfaceLogService.save(interfaceLog);
        } catch (Exception e) {
            log.error("",e);
        }
    }

    public void modifyLog(String serilaNum,String busiType,String paramIn,String outParam){
        InterfaceLog interfaceLog = new InterfaceLog();
        interfaceLog.setSerialNum(serilaNum);
        interfaceLog.setBusiType(busiType);
        interfaceLog.setParamIn(paramIn);
        try {
            interfaceLogService.save(interfaceLog);
        } catch (Exception e) {
            log.error("",e);
        }
    }



}
