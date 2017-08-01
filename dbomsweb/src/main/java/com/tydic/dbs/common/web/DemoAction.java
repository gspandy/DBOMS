/**
 * 
 */
package com.tydic.dbs.common.web;


import com.google.gson.Gson;
import com.tydic.dbs.common.constant.CommonConfig;
import com.tydic.dbs.commons.Constants;
import com.tydic.dbs.commons.utils.NumberUtil;
import com.tydic.dbs.commons.vo.LoginMemberVo;
import com.tydic.dbs.commons.web.BaseAnnotationAction;
import com.tydic.dbs.httpclient.HttpRemoteService;
import com.tydic.dbs.product.service.PrdInfoService;
import com.tydic.dbs.product.vo.PrdInfo;
import com.tydic.dbs.system.sysUploadFile.mapper.SysUploadFile;
import com.tydic.dbs.system.sysUploadFile.service.SysUploadFileService;
import com.tydic.dbs.vo.ResultVO;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录日志 LogAction
 * @author yangziran
 * @version 1.0 2014年4月3日
 */
@Controller
@RequestMapping("/demo")
public class DemoAction extends BaseAnnotationAction {

    @Autowired
    private PrdInfoService prdInfoService;

    @Autowired
    private SysUploadFileService sysUploadFileService;

    @Autowired
    private CommonConfig commonConfig;

    /**
     * 产品审核申请
     * @return
     */
    @RequestMapping("/test.do")
    @ResponseBody
    public ResultVO demo(String prdId) throws Exception {
        ResultVO vo = new ResultVO();
            prdAuditRequest("d://a.csv");
            vo.setResult_flag(Constants.SUCCESS);
            vo.setMessage("ok");
            return vo;


    }
    private  boolean prdAuditRequest(String filePath)throws Exception{

        if(filePath== null){
            log.error("文件路径为空！");
            return false;
        }
       // String prdId = prdInfo.getPrdId();


        String content ="";
        try {
            File file = new File(filePath);
            if(file.exists()){
                content = readCSV(file);
            }else{
               // log.error(dataFile.getFilePath() + "/" + dataFile.getFileName()+"不存在！");
                return false;
            }
        }catch (Exception ex){
            log.error("文件读取失败！",ex);
            return false;
        }

        if(content == null||"".equals(content.trim())){
          //  log.error("产品【"+prdInfo.getPrdName()+"】对应的数据资源文件为空");
            return false;
        }

        //调用接口，发送IT资源请求
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_id", "11");
        map.put("product_id", "123");
        map.put("product_name", "zhao");

        Gson gson = new Gson();
        Map m = new HashMap();
        m.put("requst",content);
        map.put("request", gson.toJson(m));

        try {
            ResultVO resultVO = HttpRemoteService.service(CommonConfig.httpPath, map, ResultVO.class);
            if(Constants.SUCCESS.equals(resultVO.getResult_flag())){
                return true;
            }
        } catch (Exception ex) {
            log.error("调用接口异常！", ex);
        }
        return false;
    }


}
