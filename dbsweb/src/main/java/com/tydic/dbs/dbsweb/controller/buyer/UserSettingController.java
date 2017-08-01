package com.tydic.dbs.dbsweb.controller.buyer;

import com.fins.gt.action.BaseAction;
import com.tydic.dbs.buyer.service.*;
import com.tydic.dbs.buyer.vo.*;
import com.tydic.dbs.commons.constant.CommonConstant;
import com.tydic.dbs.commons.define.WcsDefinition;
import com.tydic.dbs.dbsweb.common.CommonConfig;
import com.tydic.dbs.dbsweb.common.Constants;
import com.tydic.dbs.commons.enums.*;
import com.tydic.dbs.commons.vo.LoginMemberVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by long on 2016/7/18.
 */
@Controller
@RequestMapping("/author/userSetting")
public class UserSettingController extends BaseAction {
    private final Log log = LogFactory.getLog(UserSettingController.class);
    @Autowired
    private BussInfoService bussInfoService;
    @Autowired
    private BussTenantService bussTenantService;
    @Autowired
    private BussItResourceService bussItResourceService;
    @Autowired
    private BussDataPemissionService bussDataPemissionService;
    @Autowired
    private BussTenantRoleService bussTenantRoleService;
    @Autowired
    private CommonConfig commonConfig;
    @Autowired
    private DataPermissonCfgService dataPermissonCfgService;

    
    /**
     * 商户IT资源配置页
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/toUserItSetting")
    public  String toUserItSetting(HttpServletRequest request, ModelMap modelMap)throws  Exception{
        Map<String,Object> map=new HashMap();
        LoginMemberVo loginMemberVo = (LoginMemberVo) request.getSession().getAttribute(Constants.LOGIN_SESSION_MEMBER_KEY);
        String type = request.getParameter("type");
        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("bussId",loginMemberVo.getBussId());
        List bussList=bussInfoService.getDataByMap(paramMap);
        
        Map btMap=new HashMap();
        btMap.put("bussId",loginMemberVo.getBussId());
        btMap.put("status",Constants.ROLE_STATUS_VALID);
        List<BussTenant> bussTenantList=bussTenantService.getBussTenant(btMap);
        modelMap.addAttribute("pageMaxSize",bussTenantList.size());
       
        Map itMap=new HashMap();
        //itMap.put("status",Constants.IT_STATUS_DRAFT);
        itMap.put("bussId",loginMemberVo.getBussId());
        List<BussItResource> itList=bussItResourceService.selectResourceByMap(itMap);
        map.put("roles", Role.values());
        modelMap.addAttribute("cpuTypeMap", WcsDefinition.wcsCpuType.WCS_ROLE_CPU_TYPE_MAP);//cpu
        modelMap.addAttribute("memorySizeMap",WcsDefinition.wcsMemorySize.WCS_ROLE_MEMORY_SIZE_MAP);//内存
        modelMap.addAttribute("diskSizeMap",WcsDefinition.wcsDiskSize.WCS_ROLE_DISK_SIZE_MAP);//存储
        modelMap.addAttribute("ftpSizeMap",WcsDefinition.wcsFtpSize.WCS_ROLE_FTP_SIZE_MAP);//ftp
        modelMap.addAttribute("roleMap",map);
        modelMap.addAttribute("bussList",bussList);
        if(itList.size()>0){//修改
            modelMap.addAttribute("resoureId",itList.get(0).getResoureId());
            modelMap.addAttribute("cpuType",itList.get(0).getCupType());//cpu
            modelMap.addAttribute("memorySize",itList.get(0).getMemorySize());//内存
            modelMap.addAttribute("diskSize",itList.get(0).getDiskSize());//磁盘
            modelMap.addAttribute("ftpSize",itList.get(0).getFtpSize());
        }
        modelMap.addAttribute("bussId",loginMemberVo.getBussId());
        modelMap.addAttribute("statusType",type);//查看标识1 待审核 2 通过3不通过
        if(type.equals("1")){
        	return  "bussInfo/userItSee";
        }else{
        	return  "bussInfo/userItSet";
        }
    }
    /**
     * 商户数据权限配置页
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/toUserDataSetting")
    public  String toUserDataSetting(HttpServletRequest request, ModelMap modelMap)throws  Exception{
        Map<String,Object> map=new HashMap();
        LoginMemberVo loginMemberVo = (LoginMemberVo) request.getSession().getAttribute(Constants.LOGIN_SESSION_MEMBER_KEY);
        
        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("bussId",loginMemberVo.getBussId());
        List bussList=bussInfoService.getDataByMap(paramMap);
        String type = request.getParameter("type");
        
        Map btMap=new HashMap();
        btMap.put("bussId",loginMemberVo.getBussId());
        btMap.put("status",Constants.ROLE_STATUS_VALID);
        List<BussTenant> bussTenantList=bussTenantService.getBussTenant(btMap);
        modelMap.addAttribute("pageMaxSize",bussTenantList.size());
       
        Map itMap=new HashMap();
        //itMap.put("status",Constants.IT_STATUS_DRAFT);
        itMap.put("bussId",loginMemberVo.getBussId());
        List<BussItResource> itList=bussItResourceService.selectResourceByMap(itMap);
        map.put("roles", Role.values());
        modelMap.addAttribute("cpuTypeMap", WcsDefinition.wcsCpuType.WCS_ROLE_CPU_TYPE_MAP);//cpu
        modelMap.addAttribute("memorySizeMap",WcsDefinition.wcsMemorySize.WCS_ROLE_MEMORY_SIZE_MAP);//内存
        modelMap.addAttribute("diskSizeMap",WcsDefinition.wcsDiskSize.WCS_ROLE_DISK_SIZE_MAP);//存储
        modelMap.addAttribute("ftpSizeMap",WcsDefinition.wcsFtpSize.WCS_ROLE_FTP_SIZE_MAP);//ftp
        modelMap.addAttribute("roleMap",map);
        modelMap.addAttribute("bussList",bussList);
        if(itList.size()>0){//修改
            modelMap.addAttribute("resoureId",itList.get(0).getResoureId());
            modelMap.addAttribute("cpuType",itList.get(0).getCupType());//cpu
            modelMap.addAttribute("memorySize",itList.get(0).getMemorySize());//内存
            modelMap.addAttribute("diskSize",itList.get(0).getDiskSize());//磁盘
            modelMap.addAttribute("ftpSize",itList.get(0).getFtpSize());
        }
        modelMap.addAttribute("bussId",loginMemberVo.getBussId());
        if(OperationType.LOOK.getCode().equals(type)){
        	return  "bussInfo/userDataSee";
        }else{
        	modelMap.addAttribute("operType",type);
        	return  "bussInfo/userDataSet";
        }
    }
    /**
     * 商户操作员配置页
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/toUserAuthSetting")
    public  String toUserAuthSetting(HttpServletRequest request, ModelMap modelMap)throws  Exception{
        Map<String,Object> map=new HashMap();
        LoginMemberVo loginMemberVo = (LoginMemberVo) request.getSession().getAttribute(Constants.LOGIN_SESSION_MEMBER_KEY);
        
        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("bussId",loginMemberVo.getBussId());
        List bussList=bussInfoService.getDataByMap(paramMap);
        String type = request.getParameter("type");
        
        Map btMap=new HashMap();
        btMap.put("bussId",loginMemberVo.getBussId());
        btMap.put("status",Constants.ROLE_STATUS_VALID);
        List<BussTenant> bussTenantList=bussTenantService.getBussTenant(btMap);
        modelMap.addAttribute("pageMaxSize",bussTenantList.size());
       
        Map itMap=new HashMap();
        //itMap.put("status",Constants.IT_STATUS_DRAFT);
        itMap.put("bussId",loginMemberVo.getBussId());
        List<BussItResource> itList=bussItResourceService.selectResourceByMap(itMap);
        map.put("roles", Role.values());
        modelMap.addAttribute("cpuTypeMap", WcsDefinition.wcsCpuType.WCS_ROLE_CPU_TYPE_MAP);//cpu
        modelMap.addAttribute("memorySizeMap",WcsDefinition.wcsMemorySize.WCS_ROLE_MEMORY_SIZE_MAP);//内存
        modelMap.addAttribute("diskSizeMap",WcsDefinition.wcsDiskSize.WCS_ROLE_DISK_SIZE_MAP);//存储
        modelMap.addAttribute("ftpSizeMap",WcsDefinition.wcsFtpSize.WCS_ROLE_FTP_SIZE_MAP);//ftp
        modelMap.addAttribute("roleMap",map);
        modelMap.addAttribute("bussList",bussList);
        if(itList.size()>0){//修改
            modelMap.addAttribute("resoureId",itList.get(0).getResoureId());
            modelMap.addAttribute("cpuType",itList.get(0).getCupType());//cpu
            modelMap.addAttribute("memorySize",itList.get(0).getMemorySize());//内存
            modelMap.addAttribute("diskSize",itList.get(0).getDiskSize());//磁盘
            modelMap.addAttribute("ftpSize",itList.get(0).getFtpSize());
        }
        modelMap.addAttribute("bussId",loginMemberVo.getBussId());
        if (type.equals("1")){
        	 return  "bussInfo/userAuthSee";
        }else{
        	return  "bussInfo/userAuthSet";
        }
        
    }
    /**
     * 商户配置页
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/toUserSetting")
    public  String toUserSetting(HttpServletRequest request, ModelMap modelMap)throws  Exception{
        Map<String,Object> map=new HashMap();
        LoginMemberVo loginMemberVo = (LoginMemberVo) request.getSession().getAttribute(Constants.LOGIN_SESSION_MEMBER_KEY);
        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("bussId",loginMemberVo.getBussId());
        List bussList=bussInfoService.getDataByMap(paramMap);
        Map btMap=new HashMap();
        btMap.put("bussId",loginMemberVo.getBussId());
        btMap.put("status",Constants.ROLE_STATUS_VALID);
        List<BussTenant> bussTenantList=bussTenantService.getBussTenant(btMap);
        modelMap.addAttribute("pageMaxSize",bussTenantList.size());
        Map itMap=new HashMap();
        //itMap.put("status",Constants.IT_STATUS_DRAFT);
        itMap.put("bussId",loginMemberVo.getBussId());
        List<BussItResource> itList=bussItResourceService.selectResourceByMap(itMap);
        map.put("roles", Role.values());
        modelMap.addAttribute("cpuTypeMap", WcsDefinition.wcsCpuType.WCS_ROLE_CPU_TYPE_MAP);//cpu
        modelMap.addAttribute("memorySizeMap",WcsDefinition.wcsMemorySize.WCS_ROLE_MEMORY_SIZE_MAP);//内存
        modelMap.addAttribute("diskSizeMap",WcsDefinition.wcsDiskSize.WCS_ROLE_DISK_SIZE_MAP);//存储
        modelMap.addAttribute("ftpSizeMap",WcsDefinition.wcsFtpSize.WCS_ROLE_FTP_SIZE_MAP);//ftp
        modelMap.addAttribute("roleMap",map);
        modelMap.addAttribute("bussList",bussList);
        if(itList.size()>0){
            modelMap.addAttribute("resoureId",itList.get(0).getResoureId());
            modelMap.addAttribute("cpuType",itList.get(0).getCupType());//cpu
            modelMap.addAttribute("memorySize",itList.get(0).getMemorySize());//内存
            modelMap.addAttribute("diskSize",itList.get(0).getDiskSize());//磁盘
            modelMap.addAttribute("ftpSize",itList.get(0).getFtpSize());
        }
        modelMap.addAttribute("bussId",loginMemberVo.getBussId());
        return  "bussInfo/userSetting";
    }

    /**
     * 获取当前用户操作员列表
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/getBussTenant")
    @ResponseBody
    public  Map<String,Object> getBussTenant(HttpServletRequest request)throws  Exception{
        Map<String,Object> map=new HashMap<>();
        LoginMemberVo loginMemberVo=(LoginMemberVo) request.getSession().getAttribute(Constants.LOGIN_SESSION_MEMBER_KEY);
        Map<String,Object> pMap=new HashMap<>();
        String pageSize=request.getParameter("pageSize");
        long minSize=Long.parseLong(pageSize);
        long maxSize=minSize+5;
        pMap.put("bussId",loginMemberVo.getBussId());
        pMap.put("status",Constants.ROLE_STATUS_VALID);
        //pMap.put("orderBy","tenantIdDesc");
        pMap.put("minSize",minSize);
        pMap.put("maxSize",maxSize);
        pMap.put("sign",Constants.ROLE_STATUS_VALID);
        try {
            List<BussTenant> bussTenantList=bussTenantService.getBussTenant(pMap);
            List itList=bussTenantRoleService.selectRoleByMap(pMap);
            map.put("bussTenant",bussTenantList);
            map.put("tenantRole",itList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  map;
    }


    @RequestMapping("/getItResource")
    @ResponseBody
    public  Map<String,Object> getItResource(HttpServletRequest request)throws Exception{
        Map map =new HashMap();
        LoginMemberVo loginMemberVo=(LoginMemberVo) request.getSession().getAttribute(Constants.LOGIN_SESSION_MEMBER_KEY);
        Map itMap=new HashMap();
        try {
            //itMap.put("status",Constants.IT_STATUS_DRAFT);
            itMap.put("bussId",loginMemberVo.getBussId());
            List itList=bussItResourceService.selectResourceByMap(itMap);
            map.put("itList",itList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  map;
    }
    /**
     * 商户配置详情页
     * @param request
     * @param modelMap
     * @return
     * @throws Exception
     */
    @RequestMapping("/toUserSettingDetail")
    public  String toUserSettingDetail(HttpServletRequest request, ModelMap modelMap)throws  Exception{
        LoginMemberVo loginMemberVo=(LoginMemberVo)request.getSession().getAttribute(Constants.LOGIN_SESSION_MEMBER_KEY);
        Map<String,Object> map=new HashMap<>();
        map.put("bussId",loginMemberVo.getBussId());
        try{
            Map btMap=new HashMap();
            btMap.put("bussId",loginMemberVo.getBussId());
            btMap.put("status",Constants.ROLE_STATUS_VALID);
            List<BussTenant> bussTenantList=bussTenantService.getBussTenant(btMap);
            modelMap.addAttribute("pageMaxSize",bussTenantList.size());
            List<BussItResource> itRescource = bussItResourceService.selectResourceByMap(map);//it资源
            if(itRescource.size()>0){
                modelMap.addAttribute("cpuType",itRescource.get(0).getCupType()+"核");//cpu
                if(itRescource.get(0).getMemorySize()>1024){
                    modelMap.addAttribute("memorySize",itRescource.get(0).getMemorySize()/1024+"T");//内存
                }else {
                    modelMap.addAttribute("memorySize",itRescource.get(0).getMemorySize()+"G");//内存
                }
                if (itRescource.get(0).getDiskSize()>1024){
                    modelMap.addAttribute("diskSize",itRescource.get(0).getDiskSize()/1024+"T");//磁盘
                }else{
                    modelMap.addAttribute("diskSize",itRescource.get(0).getDiskSize()+"G");//磁盘
                }
                if (itRescource.get(0).getFtpSize()>1024){
                    modelMap.addAttribute("ftpSize",itRescource.get(0).getFtpSize()/1024+"T");
                }else{
                    modelMap.addAttribute("ftpSize",itRescource.get(0).getFtpSize()+"G");
                }
            }
            List bussList=bussInfoService.getDataByMap(map);//配置状态
            List pemList=bussDataPemissionService.selectPemission(map);//数据资源
            modelMap.addAttribute("pemList",pemList);
            modelMap.addAttribute("itRescource",itRescource);
            modelMap.addAttribute("bussList",bussList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  "bussInfo/userSettingDetail";
    }

    /**
     * 逻辑删除操作员
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("deleteByTenantId")
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
          //  save(tenantId,"","","",roleId,"","","",operType,request);
            map.put("success",true);
        }catch (Exception e){
            map.put("success",false);
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 查询操作员详细信息
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/getRoleMessage")
    @ResponseBody
    public  Map<String,Object> getRoleMessage(HttpServletRequest request)throws  Exception{
        Map map=new HashMap();
        String tenantId=request.getParameter("tenantId");
        try {
            Map<String,Object> pMap=new HashMap<>();
            pMap.put("tenantId",tenantId);
            pMap.put("status",Constants.ROLE_STATUS_VALID);
            List roleList=bussTenantService.getBussTenant(pMap);
            List itList=bussTenantRoleService.selectRoleByMap(pMap);
            map.put("roleMessage",roleList);
            map.put("tenantMessage",itList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  map;
    }

    /**
     * 查询数据权限列表
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/getDatapemissionDict")
    @ResponseBody
    public  Map<String,Object> getDatapemissionDict(HttpServletRequest request)throws Exception{
        Map map=new HashMap();
        List<DataPemissionDict> dataPemissionDictsList=bussTenantService.getDataPemissionDict();
        Map chilMap=new HashMap();
        if (null!=dataPemissionDictsList && dataPemissionDictsList.size()>0){
            List<DataPemissionDict> treeList=new ArrayList<>();
            for (DataPemissionDict dd:dataPemissionDictsList) {
                if(StringUtils.isNotEmpty(dd.getParentCode()) && (dd.getParentCode()).equals(String.valueOf(CommonConstant.PARENT_ID))){
                    treeList.add(dd);
                    fromtData(dataPemissionDictsList,dd);
                }
            }
            map.put("dataPemissionDictsList",treeList);
        }
        return map;
    }

    /**
     * 递归封装数据
     * @param sourceList
     * @param parnetDict
     * @throws Exception
     */
    public void  fromtData(List<DataPemissionDict> sourceList,DataPemissionDict parnetDict)throws Exception{
        for (DataPemissionDict dd:sourceList) {
            if (StringUtils.isNotEmpty(dd.getParentCode()) && dd.getParentCode().compareTo(parnetDict.getCode()) == 0){
                List<DataPemissionDict> child=parnetDict.getChildList();
                if (CollectionUtils.isEmpty(child))
                    child=new ArrayList<>();
                child.add(dd);
                parnetDict.setChildFlag("true");
                parnetDict.setChildList(child);
                //递归进行数据封装
                fromtData(sourceList,dd);
            }
        }
    }

    /**
     * 数据权限详情
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/getPemissionDetail")
    @ResponseBody
    public Map<String,Object> getPemissionDetail(HttpServletRequest request)throws Exception{
        Map map=new HashMap();
        LoginMemberVo loginMemberVo=(LoginMemberVo)request.getSession().getAttribute(Constants.LOGIN_SESSION_MEMBER_KEY);
        String bussId=loginMemberVo.getBussId();
        Map<String,String> paramMap=new HashMap<>();
        paramMap.put("bussId",bussId);
        //paramMap.put("appStatus",Constants.ROLE_STATUS_VALID);
        List<BussDataPemission> list=bussDataPemissionService.selectPemission(paramMap);
        map.put("dictList",list);
        return map;
    }

    public void formatDeatil(List<DataPemissionDict> sourceList,DataPemissionDict parentDict,List<BussDataPemission> pemissionList)throws Exception{
        for (DataPemissionDict dict:sourceList) {
            if (StringUtils.isNotEmpty(dict.getParentCode())&& dict.getParentCode().compareTo(parentDict.getCode()) == 0){
                for (BussDataPemission pemission:pemissionList) {
                    if (pemission.getPemissionId().equals(dict.getCode())){
                        List<DataPemissionDict> child=parentDict.getChildList();
                        if (CollectionUtils.isEmpty(child))
                            child=new ArrayList<>();
                        child.add(dict);
                        parentDict.setChildFlag("true");
                        parentDict.setChildList(child);
                        //递归进行数据封装
                        fromtData(sourceList,dict);
                    }
                }
            }
        }
    }
    
    /**
     * 查询数据权限列表
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/getDataPemssionTree")
    @ResponseBody
    public  Map<String,Object> getDataPemssionTree(HttpServletRequest request)throws Exception{
        Map map=new HashMap();
        List<DataPermissonCfg> dataList=dataPermissonCfgService.getAll();
        Map chilMap=new HashMap();
        if (dataList != null && dataList.size()>0){
            List<DataPermissonCfg> treeList=new ArrayList<>();
            for (DataPermissonCfg dd:dataList) {
                if(StringUtils.isNotEmpty(dd.getParentId()) && (dd.getParentId()).equals(String.valueOf(CommonConstant.PARENT_ID))){
                    treeList.add(dd);
                    getChildData(dataList,dd,true);
                }
            }
            map.put("dataPemssionList",treeList);
        }
        return map;
    }
    
    /**
     * 
     * @param sourceList
     * @param parentCfg
     * @param isRecur
     * @throws Exception
     */
    public void  getChildData(List<DataPermissonCfg> sourceList,DataPermissonCfg parentCfg,boolean isRecur)throws Exception{
        for (DataPermissonCfg dd:sourceList) {
            if (StringUtils.isNotEmpty(dd.getParentId()) && dd.getParentId().compareTo(parentCfg.getId().toString()) == 0){
                List<DataPermissonCfg> child=parentCfg.getChildList();
                if (CollectionUtils.isEmpty(child))
                    child=new ArrayList<>();
                child.add(dd);
                parentCfg.setChildFlag("true");
                parentCfg.setChildList(child);
                if(isRecur){
                	getChildData(sourceList,dd,isRecur);
                }
            }
        }
    }

    
}
