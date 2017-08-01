/**
 * com.sunrise.wcs.web.BusTenantAction.java
 */
package com.tydic.dbs.dbsweb.controller.buyer;
import com.google.gson.Gson;
import com.tydic.commons.utils.ListUtils;
import com.tydic.commons.utils.OrderUtils;
import com.tydic.dbs.buyer.service.BussTenantRoleService;
import com.tydic.dbs.buyer.service.InterfaceLogService;
import com.tydic.dbs.buyer.vo.BussTenant;
import com.tydic.dbs.buyer.vo.BussTenantRole;
import com.tydic.dbs.buyer.vo.InterfaceLog;
import com.tydic.dbs.commons.enums.AuditStatus;
import com.tydic.dbs.commons.enums.Status;
import com.tydic.dbs.dbsweb.common.CommonConfig;
import com.tydic.dbs.commons.enums.OperationType;
import com.tydic.dbs.commons.utils.DateUtil;
import com.tydic.dbs.commons.utils.FileInfgenUtil;
import com.tydic.dbs.commons.vo.LoginMemberVo;
import com.tydic.dbs.dbsweb.common.Constants;
import com.tydic.dbs.dbsweb.httpclient.HttpRemoteService;
import com.tydic.dbs.system.log.mapper.InfFileLog;
import com.tydic.dbs.system.log.mapper.InfIndent;
import com.tydic.dbs.system.log.service.InfFileLogService;
import com.tydic.dbs.system.log.service.InfIndentService;
import com.tydic.dbs.vo.ResultVO;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tydic.commons.web.BaseAction;
import com.tydic.dbs.buyer.service.BussTenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @file  BusTenantAction.java
 * @author Carson
 * @version 0.1
 * @todo	TODO
 * Copyright(C), 2013-2014
 *		 Guangzhou tydic Technology Co., Ltd.
 * History
 *   	1. Date: 2016-07-07 07:53:32
 *      	Author: Carson
 *      	Modification: this file was created
 *   	2. ...
 */
@Controller
@RequestMapping("/author/bussTenant")
public class BusTenantController extends BaseAction{
    private final Log log = LogFactory.getLog(BusTenantController.class);

    @Autowired
    private CommonConfig commonConfig;

    @Autowired
    private BussTenantService bussTenantService;

    @Autowired
    private BussTenantRoleService bussTenantRoleService;
	@Autowired
	private InfIndentService infIndentService;
	@Autowired
	private InfFileLogService infFileLogService;
    @Autowired
    private InterfaceLogService interfaceLogService;

    /**
     * 逻辑删除操作员
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/deleteByTenantId")
    @ResponseBody
    public Map<String ,Object> deleteByTenantId(HttpServletRequest request)throws  Exception{
        List<String> ids=new ArrayList<>();
        Map<String,Object> map =new HashMap<>();
        String tenantId=request.getParameter("tenantId");
        String operType=request.getParameter("operType");
        ids.add(tenantId);
        LoginMemberVo loginMemberVo=(LoginMemberVo) request.getSession().getAttribute(Constants.LOGIN_SESSION_MEMBER_KEY);
        try {
            Map<String,Object> tempMap=new HashMap<>();
            tempMap.put("tenantId",tenantId);
            List<BussTenantRole> list=bussTenantRoleService.selectRoleByMap(tempMap);
            List<String> roles=new ArrayList();
            if (list.size()>0){
                for (int i=0;i<list.size();i++){
                    roles.add(list.get(i).getRoleId());
                }
            }
            String  roleId= StringUtils.join( roles.toArray(),",");
            save(tenantId,"","","",roleId,"","","",operType,request);
            map.put("success",true);
        }catch (Exception e){
            map.put("success",false);
            e.printStackTrace();
        }
        return map;
    }

    @SuppressWarnings("null")
	@RequestMapping(value = "/save")
    @ResponseBody
    public Map<String,String> save(@RequestParam(value = "tenantId",required = false) String tenantId,
                                   @RequestParam (value = "bussId",required = false) String bussId,
                                   @RequestParam (value = "tenantName",required = false) String tenantName,
                                   @RequestParam (value = "mobile",required = false) String mobile,
                                   @RequestParam (value = "roleId",required = false) String roleId,
                                   @RequestParam (value = "email",required = false) String email,
                                   @RequestParam (value = "remark",required = false) String remark,
                                   @RequestParam (value = "idCard" ,required = false) String idCard,
                                   @RequestParam (value = "operType",required = true)String operType,HttpServletRequest request) throws Exception {
        Map<String,String> result = null;
        BussTenant tenant =  null;
        BussTenantRole tenantRole = null;
        List<BussTenantRole> tenantRoleList = null;
        InterfaceLog interfaceLog=new InterfaceLog();
        String serNum=OrderUtils.generateOutTradeNo();//流水
        Gson gson=new Gson();

        //获取租户账户权限信息
        if(OperationType.ADD.getCode().equals(operType)||OperationType.MODIFY.getCode().equals(operType)){
            tenant =  new BussTenant();
            if(tenantId == null || "".equals(tenantId)){
                tenantId = OrderUtils.getOperId();
            }
            tenant.setTenantId(tenantId);
            if(bussId == null || "".equals(bussId)){
                result = new HashMap<String,String>();
                result.put("flag","false");
                result.put("message","bussId is null");
                return result;
            }
            tenant.setBussId(bussId);
            if(tenantName != null & !"".equals(bussId)){
                tenant.setTenantName(tenantName);
            }
            if(mobile == null || "".equals(mobile)){
                result = new HashMap<String,String>();
                result.put("flag","false");
                result.put("message","mobile is null");
                return result;
            }
            tenant.setMobile(mobile);

            if(null==idCard || "".equals(idCard)){
                result=new HashMap<String,String>();
                result.put("flag","false");
                result.put("message","idCard is null");
                return  result;
            }
            tenant.setIdCard(idCard);
            if(roleId == null || "".equals(roleId)){
                result = new HashMap<String,String>();
                result.put("flag","false");
                result.put("message","role is null");
                return result;
            }else{
                String[] roles = roleId.split(",");

                if(roles != null){
                    tenantRoleList = new ArrayList<>();
                    for (int i=0;i<roles.length;i++){
                        tenantRole = new BussTenantRole();
                        tenantRole.setRoleId(roles[i]);
                        tenantRole.setTenantId(tenantId);
                        tenantRoleList.add(tenantRole);
                    }
                    tenant.setRoleList(tenantRoleList);
                }else{
                    result = new HashMap<String,String>();
                    result.put("flag","false");
                    result.put("message","role is null");
                    return result;
                }
            }


            if(email != null & !"".equals(email)){
                tenant.setEmail(email);
            }
            if(remark != null & !"".equals(remark)){
                tenant.setRemark(remark);
            }

        }else if(OperationType.DEL.getCode().equals(operType)){
            if(tenantId == null || "".equals(tenantId)){
                result = new HashMap<String,String>();
                result.put("flag","false");
                result.put("message","操作员权限ID为空");
                return result;
            }


            tenant =  new BussTenant();
            tenant.setTenantId(tenantId);

            String[] roles = roleId.split(",");

            if(roles != null){
                tenantRoleList = new ArrayList<>();
                for (int i=0;i<roles.length;i++){
                    tenantRole = new BussTenantRole();
                    tenantRole.setRoleId(roles[i]);
                    tenantRole.setTenantId(tenantId);
                    tenantRoleList.add(tenantRole);
                }
                tenant.setRoleList(tenantRoleList);
            }else{
                result = new HashMap<String,String>();
                result.put("flag","false");
                result.put("message","角色为空");
                return result;
            }

        }else{
            result = new HashMap<String,String>();
            result.put("flag","false");
            result.put("message","operType Error");
            return result;
        }

        if(OperationType.MODIFY.getCode().equals(operType)){
            BussTenantRole bussTenantRole = new BussTenantRole();
            bussTenantRole.setTenantId(tenantId);
            List <BussTenantRole> roleList= bussTenantRoleService.get(bussTenantRole);
            List<String> roleIds = new ArrayList<>();
            for(BussTenantRole role :roleList){
                roleIds.add(role.getRoleId());
            }
            List<String> tmproles = new ArrayList<>();
             Collections.addAll(tmproles,roleId.split(","));
            //修改租户权限时，如果角色没有变化，则操作类型变更4（同步手机号码、邮箱到）
            if(ListUtils.compare(tmproles,roleIds)){
                operType = "4";
            }
        }
        if(OperationType.DEL.getCode().equals(operType)){
            tenant = bussTenantService.get(tenantId);
            tenant.setRoleList(tenantRoleList);
        }

        //调用接口
        Map<String,String> map = new HashMap<String,String>();
        map.put("bus_id",tenant.getBussId());
        map.put("user_id",tenant.getTenantId());
        map.put("user_name",tenant.getTenantName());
        map.put("phone_no",tenant.getMobile());
        map.put("email",tenant.getEmail());
        map.put("operate_type", operType);
        map.put("roles", roleId);
        map.put("system_id",Constants.SYSTEM_ID);
        map.put("serial_num",serNum);
//		InfIndent infIndent = infIndentService.get("USER_AUTHAPLY");
//        interfaceLog.setParamIn(gson.toJson(map));
//		if (infIndent.getInfFlag()!=null && "1".equals(infIndent.getInfFlag())){
//			if (!busPemRequestInf(request,map,tenant)) {
//	            result = new HashMap<String,String>();
//	            result.put("flag",Constants.ERROR);
//	            result.put("message","生成接口文件失败");
//	            return result;
//			}
//			result = new HashMap<String,String>();
//	        result.put("message","已生成接口文件，请联系运维人员");
//	        result.put("flag", Constants.ERROR);
//		}else{
        //调用接口
        try{
            ResultVO resultVO = HttpRemoteService.service(commonConfig.getBussTenantUrl(), map, ResultVO.class);
        }catch(Exception ex){
            logger.error("调用接口异常！",ex);
            result = new HashMap<String,String>();
            result.put("flag","0");
            result.put("message","调用接口异常！");
            return result;
        }
        result = new HashMap<String,String>();
        result.put("message","");
        result.put("flag", Constants.SUCCESS);
//		}

        //保存租户账户权限信息
        if(OperationType.ADD.getCode().equals(operType)) {
            tenant.setAuditStatus(AuditStatus.WAIT.getCode());
            bussTenantService.save(tenant);
        }else if(OperationType.MODIFY.getCode().equals(operType)||OperationType.SYNC.getCode().equals(operType)){
            if(OperationType.MODIFY.getCode().equals(operType)){
                tenant.setAuditStatus(AuditStatus.WAIT.getCode());
            }
            bussTenantService.modify(tenant);
        }else if(OperationType.DEL.getCode().equals(operType)){
            //执行逻辑删除
            tenant.setStatus(Status.UNVALID.getCode());
            bussTenantService.modify(tenant);
        }
        interfaceLog.setSerialNum(serNum);
        interfaceLog.setBusiType("USER_AUTHAPLY");
        interfaceLog.setRemark("租户账号权限申请");
        interfaceLog.setCreateTime(new Date());
        interfaceLog.setResultContent(gson.toJson(result));
        try{
            interfaceLogService.save(interfaceLog);//日志
        }catch (Exception e){
            log.error("ERROR",e);
        }
        return result;
    }

    @SuppressWarnings({ "unchecked" })
	private boolean busPemRequestInf(HttpServletRequest request,Map<String,String> map,BussTenant tenant){
    	boolean b = false;
    	InfFileLog infFileLog=new InfFileLog();
		infFileLog.setInfCode("USER_AUTHAPLY");
		infFileLog.setInfName("租户账号权限申请接口");
		infFileLog.setStatus(Constants.ROLE_STATUS_VALID);
		infFileLog.setCreator(tenant.getTenantName());
		infFileLog.setCreateTime(new Date());
		infFileLog.setBussId(tenant.getBussId());
		infFileLog.setOperId(tenant.getTenantId());
		String dateSting= DateUtil.DateToString2(new Date());
		List<InfFileLog> temp = null;
		try {
			temp = infFileLogService.getLogCount("USER_AUTHAPLY");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		long size;
		if (temp==null||temp.size()<=0){
			size=1;
		}else {
			size=temp.get(0).getInfLogId()+1;
		}
		infFileLog.setInfFileName("USER_AUTHAPLY_"+dateSting+"_"+size+".txt");
		infFileLog.setSerialNum(OrderUtils.generateOutTradeNo());

		//开始上传
		try {
			Gson gson = new Gson();
			FileInfgenUtil futil=new FileInfgenUtil();
			Map<String, Object> fileResult = futil.filegenerate(request,gson.toJson(map), tenant.getBussId(),"USER_AUTHAPLY_"+dateSting+"_"+size+".txt");
			if (fileResult.get("flag")==null) {
				infFileLog = infFileLogService.save(infFileLog);
				b = infFileLog!=null;
			}
		}catch (Exception e){
			log.error("生成接口文件异常！",e);
			b = false;
			return b;
		}
    	return b;
    }

}
