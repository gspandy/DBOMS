package com.tydic.dbs.dbsweb.controller.order;

import com.fins.gt.action.BaseAction;
import com.google.gson.Gson;
import com.tydic.commons.utils.DateUtils;
import com.tydic.commons.utils.OrderUtils;
import com.tydic.commons.utils.Page;
import com.tydic.dbs.buyer.service.BussItResourceService;
import com.tydic.dbs.buyer.service.InterfaceLogService;
import com.tydic.dbs.buyer.vo.BussItResource;
import com.tydic.dbs.buyer.vo.InterfaceLog;
import com.tydic.dbs.commons.define.WcsDefinition;
import com.tydic.dbs.commons.utils.DateUtil;
import com.tydic.dbs.commons.utils.FileInfgenUtil;
import com.tydic.dbs.commons.vo.LoginMemberVo;
import com.tydic.dbs.dbsweb.common.CommonConfig;
import com.tydic.dbs.dbsweb.common.Constants;
import com.tydic.dbs.dbsweb.httpclient.HttpRemoteService;
import com.tydic.dbs.order.service.OrdInfoService;
import com.tydic.dbs.order.service.OrdLogService;
import com.tydic.dbs.order.service.OrdPrdService;
import com.tydic.dbs.order.vo.OrdPrd;
import com.tydic.dbs.order.vo.OrdPrdAndData;
import com.tydic.dbs.product.service.PrdDataResourceRequireService;
import com.tydic.dbs.product.service.PrdInfoService;
import com.tydic.dbs.product.vo.PrdDataResource;
import com.tydic.dbs.product.vo.PrdDataResourceRequire;
import com.tydic.dbs.system.log.mapper.InfFileLog;
import com.tydic.dbs.system.log.mapper.InfIndent;
import com.tydic.dbs.system.log.service.InfFileLogService;
import com.tydic.dbs.system.log.service.InfIndentService;
import com.tydic.dbs.vo.ResultVO;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工单申请接口
 * Created by long on 2016/8/3.
 */
@Controller
@RequestMapping("/author/ordApply")
public class OrdApplyController extends BaseAction{

    @Resource
    CommonConfig commonConfig;
    @Resource
    PrdInfoService prdInfoService;
    @Autowired
    private OrdPrdService ordPrdService;
    @Resource
    OrdLogService ordLogService;
    @Resource
    BussItResourceService bussItResourceService;
    @Resource
    OrdInfoService ordInfoService;
	@Autowired
	private InfIndentService infIndentService;
	@Autowired
	private InfFileLogService infFileLogService;
    @Autowired
    private InterfaceLogService interfaceLogService;
    @Autowired
    private PrdDataResourceRequireService prdDataResourceRequireService;
    
    private final Log logger = LogFactory.getLog(OrdApplyController.class);

    /**
     * 工单新增页
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/toOrderApply")
    public String toOrderApply(HttpServletRequest request, ModelMap modelMap)throws Exception{
    	request.setAttribute("unitMap", WcsDefinition.wcsPrdUnit.WCS_PRD_UNIT_MAP);//计量单位
        String operType=request.getParameter("operType");
        String orderId=request.getParameter("orderId");
        String prdId=request.getParameter("prdId");
        OrdPrd ordPrd=new OrdPrd();
        OrdPrdAndData ordPrdAndData=new OrdPrdAndData();
        List<PrdDataResourceRequire> dataList = null;
        if(Constants.NEW_STATUS.equals(operType)){//新增
            request.setAttribute("flag",Constants.NEW_STATUS);
        }else if (Constants.UPDATE_STATUS.equals(operType)){//修改状态
        	request.setAttribute("flag",Constants.UPDATE_STATUS);
            try {
                if (StringUtils.isNotEmpty(orderId)){
                    ordPrd=ordPrdService.getByOrdId(orderId);
                }
                if(StringUtils.isNotEmpty(prdId)){
                	ordPrdAndData=ordPrdService.selectPrdByOrderId(prdId);
                	/***added by andy 获取产品数据资源需求 0927--start****/
                	dataList = prdDataResourceRequireService.getList(prdId);
                	/***added by andy 获取产品数据资源需求 0927--end****/
                }
            }catch (Exception e){
                logger.error("查询错误：",e);
            }
            //modelMap.addAttribute("ordPrdAndData",ordPrdAndData);
        }
        request.setAttribute("ordPrd", ordPrd);
        request.setAttribute("ordPrdAndData", ordPrdAndData);
        request.setAttribute("orderId", orderId);
        request.setAttribute("proDataList", dataList);
        return "order/orderAdd";
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/orderConfig")
    @ResponseBody
    public Map<String,Object> orderConfig(HttpServletRequest request)throws Exception{
    	Map result=new HashMap<>();
    	LoginMemberVo loginMemberVo=(LoginMemberVo)request.getSession().getAttribute(Constants.LOGIN_SESSION_MEMBER_KEY);
        String operType=request.getParameter("operType");//操作类型:新增（保存，提交）修改（保存，提交）
        String prdId=request.getParameter("prdId");//产品Id
        String startRow=request.getParameter("startRow");//数据抽取开始行
        String endRow=request.getParameter("endRow");//数据抽取结束行
        String ordId=request.getParameter("orderId");//工单编号
        String resId=request.getParameter("resId");//数据需求ID
        String ordDateBegin=request.getParameter("startTime");//开始时间
        String ordDateEnd=request.getParameter("endTime");//结束时间
        BussItResource bussItResource=new BussItResource();
        InterfaceLog interfaceLog=new InterfaceLog();
        Gson gson = new Gson();
        String serNum=OrderUtils.generateOutTradeNo();//流水号
        PrdDataResource prdDataResource=new PrdDataResource();
        int max_num=Integer.parseInt(endRow)-Integer.parseInt(startRow);
        
        OrdPrd ordPrd=new OrdPrd();
        ordPrd.setPrdId(prdId);
        ordPrd.setResoureId(resId);
        if(Constants.ORDER_APPLY_SAVE.equals(operType) || Constants.ORDER_APPLY_SUBMIT.equals(operType)){//新增
        	ordId=OrderUtils.getOrderId();
        	ordPrd.setOrdId(ordId);
        }else{
        	if(StringUtils.isEmpty(ordId)){
        		result.put("flag", Constants.ERROR);
        		result.put("message", "工单编号为空！");
        		return result;
        	}
        	ordPrd.setOrdId(ordId);
        }
        
        ordPrd.setStartRow(Integer.parseInt(startRow));
        ordPrd.setEndRow(Integer.parseInt(endRow));
        ordPrd.setOrdDatebegin(DateUtils.getHymdsByString(ordDateBegin));
        ordPrd.setOrdDateend(DateUtils.getHymdsByString(ordDateEnd));
        ordPrd.setBussId(loginMemberVo.getBussId());
        ordPrd.setOperType(operType);
        
        //如果是提交按钮，则调用接口
        if(Constants.ORDER_APPLY_SUBMIT.equals(operType) || Constants.ORDER_APPLY_UPDATE_SUBMIT.equals(operType) || Constants.ORDER_SUBMIT.equals(operType)){

	    	try{
	    		prdDataResource=prdInfoService.getResouceByPrdId(prdId);
	    	}catch (Exception e) {
	    		 logger.error("查询数据服务",e);
	             result.put("message","查询数据服务失败！");
	         	return result;
			}
	        Map<String,String> map = new HashMap<String,String>();
	        map.put("bus_id",loginMemberVo.getBussId());//申请用户ID
	        map.put("work_no",ordId);//工单号
	        map.put("product_id", prdId);
	        map.put("max_num",Integer.toString(max_num));//最大输出行数，0表示全部输出，大于零整数表示
	        map.put("times", ordDateBegin+"|"+ordDateEnd);//时间段，格式：yyyy-mm-dd|yyyy-mm-dd
	        map.put("system_id", Constants.SYSTEM_ID);//来源应用平台ID
	        map.put("serial_num",serNum);
	      
	        try{
	            interfaceLog.setParamIn(gson.toJson(map));
	            ResultVO resultVO = HttpRemoteService.service(commonConfig.getOrderApplyUrl(), map, ResultVO.class);
	            result.put("flag",resultVO.getResult_flag());
	            result.put("message",resultVO.getMessage());
	        }catch(Exception ex) {
	            logger.error("调用接口异常！", ex);
	            result.put("flag",Constants.ERROR);
	            result.put("message","调用接口异常！");
	        }
       }
        try{
        	if(Constants.ORDER_SUBMIT.equals(operType)){
        		ordInfoService.updateByOrdId(ordId,loginMemberVo.getBussId());
        		result.put("flag", Constants.SUCCESS);
        		result.put("message", "提交成功！");
        	}else{
        		ordPrdService.save(ordPrd);
        		if(Constants.ORDER_APPLY_SAVE.equals(operType) || Constants.ORDER_APPLY_UPDATE_SAVE.equals(operType)){
        			result.put("flag",Constants.SUCCESS);
        			result.put("message", "保存成功！");
        		}else{
        			result.put("flag",Constants.SUCCESS);
        			result.put("message", "提交成功！");
        		}
        	}
        }catch (Exception e) {
			logger.error("失败信息：",e);
			if(Constants.ORDER_APPLY_SAVE.equals(operType) || Constants.ORDER_APPLY_UPDATE_SAVE.equals(operType)){
        		result.put("flag",Constants.SUCCESS);
        		result.put("message", "保存失败！");
        	}else{
        		result.put("flag",Constants.SUCCESS);
        		result.put("message", "提交失败！");
        	}
			
		}
        interfaceLog.setBusiType("ORDER_APPLY");
        interfaceLog.setSerialNum(serNum);
        interfaceLog.setResultContent(gson.toJson(result));
        interfaceLog.setRemark("工单申请");
        interfaceLogService.save(interfaceLog);
    	return result;
    }
    /**
     * 获取产品列表
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/getPrdInfoList")
    @ResponseBody
    public Page getPrdInfoList(HttpServletRequest request)throws Exception{
        try{
            Map param=new HashMap();
            LoginMemberVo loginMemberVo=(LoginMemberVo)request.getSession().getAttribute(Constants.LOGIN_SESSION_MEMBER_KEY);
            String prdId=request.getParameter("prdId");
            String prdName=request.getParameter("prdName");
            param.put("prdId",""==prdId?null:prdId);
            param.put("prdStatus",Constants.PRD_STATUS);
            param.put("prdName",""==prdName?null:prdName);
            param.put("bussId",loginMemberVo.getBussId());
            String pageNo = request.getParameter("pageNo");
            pageNo = pageNo==null||pageNo.equals("")?"1":pageNo;
            param.put(Page.CURR_PAGE, Integer.parseInt(pageNo));
            param.put(Page.PAGE_SIZE, 6);
            //Page page=prdInfoService.selectPrdByParam(param);
            Page page=prdInfoService.selectPrdByParams(param);
            return page;
        }catch (Exception e){
            logger.error("查询失败：",e);
        }
        return null;
    }
    
    /**
     * 获取产品列表
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/getPrdDataList")
    @ResponseBody
    public Page getPrdDataList(HttpServletRequest request)throws Exception{
        try{
            String prdId=request.getParameter("prdId");
            List<PrdDataResourceRequire> proDataList = null;
            Page page = new Page();
            if (StringUtils.isNotBlank(prdId)){
            	proDataList = prdDataResourceRequireService.getList(prdId);
            }
            if (!proDataList.isEmpty() && proDataList.size() > 0){
            	page.setList(proDataList);
            }
            return page;
        }catch (Exception e){
            logger.error("查询产品数据资源需求失败：",e);
        }
        return null;
    }
}
