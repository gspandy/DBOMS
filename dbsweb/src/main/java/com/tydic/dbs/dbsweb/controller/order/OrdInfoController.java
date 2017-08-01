/**
 * com.sunrise.ord.web.OrdInfoAction.java
 */
package com.tydic.dbs.dbsweb.controller.order;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tydic.commons.utils.Page;
import com.tydic.commons.web.BaseAction;
import com.tydic.dbs.commons.utils.FileUtil;
import com.tydic.dbs.commons.vo.LoginMemberVo;
import com.tydic.dbs.dbsweb.common.Constants;
import com.tydic.dbs.order.service.OrdInfoService;
import com.tydic.dbs.order.service.OrdLogService;
import com.tydic.dbs.order.service.OrdPrdService;
import com.tydic.dbs.order.vo.OrdInfoVo;
/**
 * 
 * @ClassName: OrdInfoController 
 * @Description: TODO(工单管理控制器) 
 * @author Michael dengwenjie@tydic.com 
 * @date 2016-8-1 下午2:32:52 
 *
 */
@Controller
@RequestMapping("/author/order")
public class OrdInfoController extends BaseAction{
    private final Log log = LogFactory.getLog(OrdInfoController.class);
    
    @Autowired
    private OrdInfoService ordInfoService;
    @Autowired
    private OrdPrdService ordPrdService;
    @Autowired
    private OrdLogService ordLogService; 
    /**
     * 
     * @Title: toOrderList 
     * @Description: TODO(工单-进入工单列表页) 
     * @param @param request
     * @param @return
     * @param @throws Exception    设定文件 
     * @return String    返回类型 
     * @throws
     */
    @RequestMapping("/toList")
    public String toOrderList(HttpServletRequest request) throws Exception{
    	
    	return "/order/orderList";
    }
    
    /**
     * 
     * @Title: getOrderList 
     * @Description: TODO(工单-异步加载工单列表数据) 
     * @param @param request
     * @param @param response
     * @param @return
     * @param @throws Exception    设定文件 
     * @return Map<String,Object>    返回类型 
     * @throws
     */
    @SuppressWarnings("unchecked")
	@RequestMapping("/getList")
    @ResponseBody
    public Map<String,Object> getOrderList(HttpServletRequest request,HttpServletResponse response) throws Exception{
    	LoginMemberVo loginMemberVo = (LoginMemberVo)request.getSession().getAttribute(Constants.LOGIN_SESSION_MEMBER_KEY);
    	String bussId = "";
    	if (loginMemberVo != null){
    		bussId = loginMemberVo.getBussId();
    	}
    	Map<String,Object> map = new HashMap<String,Object>();
    	Map<String,Object> params = new HashMap<String,Object>();
    	String ordId = request.getParameter("ordId");
    	String startTime = request.getParameter("startTime");
    	String endTime = request.getParameter("endTime");
    	String ordStatus = request.getParameter("ordStatus");
    	String prdId = request.getParameter("prdId");
    	String prdName = request.getParameter("prdName");
    	String page = request.getParameter("page");
    	String rows = request.getParameter("rows");
    	if (StringUtils.isNotBlank(bussId)){
    		params.put("busId", bussId.trim());
    	}
    	if (StringUtils.isNotBlank(ordId)){
    		ordId = ordId.replaceAll("%", "\\\\%");
    		ordId = ordId.replaceAll("_", "\\\\_");
    		params.put("ordId", ordId.trim());
    	}
    	if (StringUtils.isNotBlank(startTime)){
    		params.put("startTime", startTime.trim());
    	}
    	if (StringUtils.isNotBlank(endTime)){
    		params.put("endTime", endTime.trim());
    	}
    	if (StringUtils.isNotBlank(ordStatus)){
    		params.put("ordStatus", ordStatus.trim());
    	}
    	if (StringUtils.isNotBlank(prdId)){
    		prdId = prdId.replaceAll("%", "\\\\%");
    		prdId = prdId.replaceAll("_", "\\\\_");
    		params.put("prdId", prdId.trim());
    	}
    	if (StringUtils.isNotBlank(prdName)){
    		prdName = prdName.replaceAll("%", "\\\\%");
    		prdName = prdName.replaceAll("_", "\\\\_");
    		params.put("prdNameLike", prdName.trim());
    	}
    	if (StringUtils.isNotBlank(page)){
    		params.put(Page.CURR_PAGE, Integer.parseInt(page));
    	}
    	if (StringUtils.isNotBlank(rows)){
    		params.put(Page.PAGE_SIZE, Integer.parseInt(rows));
    	}
    	List<Map<String,Object>> orderBy = new ArrayList<Map<String,Object>>();
    	Map<String,Object> hash = new HashMap<String,Object>();
    	hash.put("createTime", "desc");
    	orderBy.add(hash);
    	params.put("orderBy", orderBy);
    	List<OrdInfoVo> ordList = new ArrayList<OrdInfoVo>();
    	Page oderPage = new Page();
    	try {
    		oderPage = ordInfoService.getPageByParamMap(params);
    		if (oderPage == null){
    			return null;
    		}
    		ordList = oderPage.getList();
			map.put("totalNumber", oderPage.getTotalNumber());
			map.put("totalPage", oderPage.getTotalPage());
			map.put("rows", ordList);
    	}catch (Exception e) {
			e.printStackTrace();
		}
    	return map;
    }
    
    /**
     * 
     * @Title: query 
     * @Description: TODO(工单-根据工单编码查询工单详情) 
     * @param @param request
     * @param @return
     * @param @throws Exception    设定文件 
     * @return String    返回类型 
     * @throws
     */
    @RequestMapping("/detail")
	public String query(HttpServletRequest request,ModelMap model) throws Exception{
    	LoginMemberVo loginMemberVo = (LoginMemberVo)request.getSession().getAttribute(Constants.LOGIN_SESSION_MEMBER_KEY);
    	String orderId = request.getParameter("orderId");/***工单编码***/
		OrdInfoVo vo = new OrdInfoVo();
		Page ordLogPage = new Page();
		if (StringUtils.isNotBlank(orderId)){
			vo = ordInfoService.getByPkId(orderId);
			//todo查询工单操作信息记录
			Map<String,Object> params = new HashMap<String,Object>();
	    	List<Map<String,Object>> orderBy = new ArrayList<Map<String,Object>>();
	    	Map<String,Object> hash = new HashMap<String,Object>();
	    	hash.put("ordLogTime", "asc");
	    	orderBy.add(hash);
	    	params.put("orderBy", orderBy);
			params.put("ordId", orderId);
			ordLogPage = ordLogService.getPageByParamMap(params);
		}
		
		if (vo != null){
			model.addAttribute("vo", vo);
		}
		if (loginMemberVo != null){
			model.addAttribute("loginMemberVo", loginMemberVo);
		}
		
		if(ordLogPage == null || ordLogPage.getTotalNumber()<=0){
			ordLogPage = Page.EMPTY_PAGE;
		}else {
			model.addAttribute("ordLogList", ordLogPage.getList());
		}
		
		return "/order/orderDetail";
	}    
        
    /**
     * 
     * @Title: deleteBuyer 
     * @Description: TODO(工单-删除工单操作) 
     * @param @param request
     * @param @param response
     * @param @return
     * @param @throws Exception    设定文件 
     * @return Map<String,Object>    返回类型 
     * @throws
     */
    @RequestMapping("/deleteOrder")
	@ResponseBody
	public Map<String,Object> deleteOrder(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		boolean flag = false;
		boolean deleteOrd = false;
		boolean deleteOrdPrd = false;
		String orderId = request.getParameter("orderId");/***工单编码***/
	   	if (StringUtils.isNotBlank(orderId)){
	   		try {
	   			deleteOrd = ordInfoService.delete(orderId);
	   			//级联删除工单产品表中数据
	   			deleteOrdPrd = ordPrdService.deleteByFkId(orderId);
	   			if (deleteOrd){
	   				if (deleteOrdPrd){   	   				
	   					flag = true;
	   					map.put("msg", "删除工单成功!");
	   	   			}
	   			}
	   		}catch (Exception e){
	   			log.error("删除工单失败!");
	   			map.put("msg", "删除工单失败!");
	   			e.printStackTrace();
	   		}
		}
	   	map.put("flag", flag);
		return map;
    }
    
    @RequestMapping("/download")
	public @ResponseBody String download(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String ftpPath = request.getParameter("ftpPath");
		String fileName = request.getParameter("fileName");
		if (StringUtils.isBlank(ftpPath)) {
			return null;
		}
		if (StringUtils.isBlank(ftpPath)) {
			return null;
		}
		
		FileUtil down = new FileUtil();
		return down.fileDown(request, ftpPath, fileName);
	}
}
