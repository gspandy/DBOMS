
package com.tydic.dbs.dbsweb.controller.buyer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tydic.commons.utils.ListUtils;
import com.tydic.commons.utils.OrderUtils;
import com.tydic.dbs.buyer.service.*;
import com.tydic.dbs.buyer.vo.*;
import com.tydic.dbs.commons.enums.AuditType;
import com.tydic.dbs.commons.enums.OperationType;
import com.tydic.dbs.commons.enums.Status;
import com.tydic.dbs.dbsweb.common.CommonConfig;
import com.tydic.dbs.dbsweb.common.Constants;
import com.tydic.dbs.commons.enums.AuditStatus;
import com.tydic.dbs.commons.vo.LoginMemberVo;
import com.tydic.dbs.dbsweb.httpclient.HttpRemoteService;
import com.tydic.dbs.vo.ResultVO;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tydic.commons.web.BaseAction;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.print.DocFlavor;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * com.tydic.dbs.dbsweb.controller.buyer.Controller
 * 商户配置
 * zjl
 * 2016-07-20
 */
@Controller
@RequestMapping("/author/buss")

//@RequestMapping("/buss")
public class BussController extends BaseAction{
    private final Log log = LogFactory.getLog(BussController.class);

    @Autowired
    private CommonConfig commonConfig;

    @Autowired
    private BussDataPemissionService bussDataPemissionService;

    @Autowired
    private BussAuditStatusService bussAuditStatusService;

    @Autowired
    private InterfaceLogService interfaceLogService;

    @Autowired
    private DataPermissonCfgService dataPermissonCfgService;



    @RequestMapping("/saveDataPem")
    @ResponseBody
    public  Map<String,String> saveDataPem(@RequestParam(value = "operType", required = true) String operType,
                                     HttpServletRequest request) throws Exception {
        Map<String, String> result = new HashMap<String, String>();
        InterfaceLog interfaceLog=new InterfaceLog();
        String serNum=OrderUtils.generateOutTradeNo();//流水
        Gson gson=new Gson();


        //判断用户是否登录
        LoginMemberVo loginMemberVo = (LoginMemberVo) request.getSession().getAttribute(Constants.LOGIN_SESSION_MEMBER_KEY);
        if (loginMemberVo == null) {
            result = new HashMap<String, String>();
            result.put("flag", "0");
            result.put("message", "用户未登录！");
            return result;
        }
//        获取商户信息
        String bussId = loginMemberVo.getBussId();
        String bussAccount = loginMemberVo.getBussAccount();
        String mobile = loginMemberVo.getBussMobileNo();

//        String bussId = request.getParameter("bussId");
//        String bussAccount = request.getParameter("bussAccount");
//        String mobile = request.getParameter("mobile");
        //获取数据权限信息
        String pemInfo = request.getParameter("pemInfo");
        //获取IT资源（disk）
        String resId = request.getParameter("resId");
        String diskSize = request.getParameter("diskSize");

        if (pemInfo == null || "".equals(pemInfo)) {
            result.put("flag", "false");
            result.put("message", "请选择数据权限！");
            return result;
        }
        if (diskSize == null || "".equals(diskSize)) {
            result.put("flag", "false");
            result.put("message", "请选择存储需求！");
            return result;
        }
        //数据权限
        List<Map<String,String>> pemInfolist = gson.fromJson(pemInfo,new TypeToken<List<Map<String,String>>>() {
        }.getType());

        List<BussDataPemission> pemList = new ArrayList<BussDataPemission>();
        List<String> pemIdList = new ArrayList<>();
        if (pemInfolist != null && pemInfolist.size() > 0) {
            BussDataPemission dataPemission = null;
            String dataStatus = AuditStatus.WAIT.getCode();

            for(Map<String,String>m:pemInfolist){
                dataPemission = new BussDataPemission();
                dataPemission.setBussId(bussId);
                dataPemission.setPemissionId(m.get("pemId"));
                dataPemission.setPemissionName(m.get("pemName"));
                dataPemission.setAppStatus(dataStatus);

                //将pemid放入list集合
                pemIdList.add(m.get("pemId"));

                pemList.add(dataPemission);
            }
        }

        boolean dataPemSendIsRequest = true;

        //查询商户审核状态，如果数据权限处于待审核状态，则不允许修改、提交
        BussAuditStatus auditStatus = new BussAuditStatus();
        auditStatus.setBussId(bussId);
        auditStatus.setStatus(Status.VALID.getCode());
        List<BussAuditStatus> auditStatusList = bussAuditStatusService.get(auditStatus);
        //获取数据权限状态
        String dataPemStatus = null;
        if (auditStatusList != null && auditStatusList.size() > 0) {
            for (BussAuditStatus au : auditStatusList) {
                if (AuditType.DATAPEM.getCode().equals(au.getType())) {
                    dataPemStatus = au.getAuditStatus();
                    if (AuditStatus.WAIT.getCode().equals(au.getAuditStatus())) {
                        result.put("flag", "false");
                        result.put("message", "数据权限正在等待一级平台审核，不能修改！");
                        return result;
                    }
                }
            }
        }

        //修改时，如果数据权限信息没有发生变化，则不调用接口发送数据权限申请
        Map<String, String> pemParam = new HashMap<>();
        pemParam.put("bussId", bussId);
        List<BussDataPemission> oldPemList = bussDataPemissionService.selectPemission(pemParam);
        if (oldPemList != null && oldPemList.size() > 0) {
            List<String> oldPemIdList = new ArrayList<>();
            for (BussDataPemission t : oldPemList) {
                if (AuditStatus.DRAFT.getCode().equals(t.getAppStatus()) || AuditStatus.PASS.getCode().equals(t.getAppStatus())) {
                    oldPemIdList.add(t.getPemissionId());
                }

            }

            if (ListUtils.compare(pemIdList, oldPemIdList)) {
                if (AuditStatus.PASS.equals(dataPemStatus) || AuditStatus.NO_PASS.getCode().equals(dataPemStatus)) {
                    dataPemSendIsRequest = false;
                }
            }
        }


        BussItResource res = new BussItResource();
        res.setResoureId(resId);
        res.setBussId(bussId);
        res.setDiskSize(NumberUtils.toInt(diskSize));

        if (!bussDataPemissionService.saveDataPem(bussId,pemList, dataPemSendIsRequest,res)) {
            result.put("flag", Constants.ERROR);
            result.put("message", "保存数据权限失败！");
            return result;
        }

        if(!dataPemRequest(bussId,bussAccount,diskSize,mobile,pemList,OperationType.ADD.getCode(),serNum)){
            result.put("flag", "0");
            result.put("message", "调用数据权限申请接口异常！");
            return result;
        }
        String message =  "数据权限已提交审核！请耐心等待！";
        result.put("flag", Constants.SUCCESS);
        result.put("message", message);

        interfaceLog.setParamIn(pemList.toString());
        interfaceLog.setSerialNum(serNum);
        interfaceLog.setBusiType("USER_DATAAPLY");
        interfaceLog.setRemark("数据权限申请");
        interfaceLog.setResultContent(gson.toJson(result));
        interfaceLog.setCreateTime(new Date());
        interfaceLogService.save(interfaceLog);
        return result;
    }
    

    /**
     * 数据权限申请
     * @param pemList
     * @return
     * @throws Exception
     */
    private  boolean dataPemRequest(String bussId,String bussAccount,String diskSize,String mobile,List<BussDataPemission> pemList,String serviceOperType,String serNum) throws Exception{
        Map<String, String> result = null;

        //调用接口，发送数据权限申请
        Map<String,String> param = new HashMap<>();
        Map<String, String> map = new HashMap<String, String>();
        map = new HashMap<String, String>();
        map.put("bus_id", bussId);
        map.put("bus_name",bussAccount);
        map.put("phone_no", mobile);
        map.put("disk",diskSize);


        List<Map<String,String>> pemNameList = new ArrayList<>();
        if(pemList!= null ){
            for (BussDataPemission p:pemList){
                Map<String,String> pemMap  = new HashMap<>();
                pemMap.put("data_service_name",p.getPemissionName());
                pemNameList.add(pemMap);

            }
        }
        Gson gson = new Gson();
        map.put("data_resource", gson.toJson(pemNameList));
        map.put("operate_type", serviceOperType);
        map.put("system_id", Constants.SYSTEM_ID);
        map.put("serial_num",serNum);
        try {
            ResultVO resultVO = HttpRemoteService.service(commonConfig.getBussResourceUrl(), map, ResultVO.class);
            if(Constants.SUCCESS.equals(resultVO.getResult_flag())){
                return true;
            }
        } catch (Exception ex) {
            logger.error("调用接口异常！", ex);
        }
        return false;
    }



}
