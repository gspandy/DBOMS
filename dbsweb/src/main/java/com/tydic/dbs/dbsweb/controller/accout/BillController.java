package com.tydic.dbs.dbsweb.controller.accout;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

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
import com.tydic.dbs.billing.service.ChaBillService;
import com.tydic.dbs.billing.vo.ChaBill;
import com.tydic.dbs.billing.vo.ChaBillVo;
import com.tydic.dbs.commons.vo.LoginMemberVo;
import com.tydic.dbs.dbsweb.common.Constants;

/**
 * 
 * @ClassName: BillController 
 * @Description: TODO(账单控制器) 
 * @author Michael dengwenjie@tydic.com 
 * @date 2016-8-15 上午10:19:11 
 *
 */
@Controller
@RequestMapping("/author/bill")
public class BillController extends BaseAction{
    private final Log log = LogFactory.getLog(BillController.class);
    
    @Autowired
    private ChaBillService chaBillService;
    @RequestMapping("/toMonthBillList")
    public String toMonthBillPage(ModelMap model) throws Exception {
    	
    	return "/bill/monthBillList";
    }
    
    @SuppressWarnings("unchecked")
	@RequestMapping("/getMonthBillList")
    @ResponseBody
    public Map<String,Object> getMonthBillList(HttpServletRequest request,ModelMap model) throws Exception{
    	LoginMemberVo loginMemberVo = (LoginMemberVo)request.getSession().getAttribute(Constants.LOGIN_SESSION_MEMBER_KEY);
    	Map<String,Object> map = new HashMap<String,Object>();
    	Map<String,Object> params = new HashMap<String,Object>();
    	String startMonth = request.getParameter("startMonth");
    	String endMonth = request.getParameter("endMonth");
    	String page = request.getParameter("page");
    	String rows = request.getParameter("rows");
    	String bussId = "";
    	double totalAmount = 0d;
    	if (loginMemberVo != null){
    		bussId = loginMemberVo.getBussId();
    	}
    	if (StringUtils.isNotBlank(bussId)){
    		params.put("bussId", bussId.trim());
    	}
    	if (StringUtils.isNotBlank(startMonth)){
    		params.put("monthIdGTe", startMonth.replaceAll("-", "").trim());
    	}
    	if (StringUtils.isNotBlank(endMonth)){
    		params.put("monthIdLTe", endMonth.replaceAll("-", "").trim());
    	}
    	if (StringUtils.isNotBlank(page)){
    		params.put(Page.CURR_PAGE, Integer.parseInt(page));
    	}
    	if (StringUtils.isNotBlank(rows)){
    		params.put(Page.PAGE_SIZE, Integer.parseInt(rows));
    	}
    	List<Map<String,Object>> orderBy = new ArrayList<Map<String,Object>>();
    	Map<String,Object> hash = new HashMap<String,Object>();
    	hash.put("monthId", "desc");
    	orderBy.add(hash);
    	params.put("orderBy", orderBy);
    	Page mBillPage = new Page();
    	List<ChaBill> mBillList = new ArrayList<ChaBill>();
    	try{
    		mBillPage = chaBillService.getPagesByParamMap(params);
    		totalAmount = chaBillService.getPageByParamsSum(params);
    		if (mBillPage == null){
    			return null;
    		}
    		mBillList = mBillPage.getList();
    		map.put("totalNumber", mBillPage.getTotalNumber());
			map.put("totalPage", mBillPage.getTotalPage());
			map.put("rows", mBillList);
			map.put("totalAmount", totalAmount);
    	}catch (Exception e){
    		log.info("获取月账单列表失败");
    		map.put("msg", "获取月账单列表失败");
    		e.printStackTrace();
    	}
    	return map;
    }
    
    @RequestMapping("/toMonthBillDetail")
    public String toMonthBillDetail(HttpServletRequest request,ModelMap model) throws Exception {
    	String monthId = request.getParameter("monthId");
    	if (StringUtils.isNotBlank(monthId)){
    		model.addAttribute("monthId", monthId);
    		model.addAttribute("year", monthId.substring(0, 4));
    		model.addAttribute("month", monthId.substring(4, 6));
    	}
    	return "/bill/monthBillDetail";
    }
    
    @SuppressWarnings("unchecked")
	@RequestMapping("/getMonthBillDetail")
    @ResponseBody
    public Map<String,Object> getMonthBillDetail(HttpServletRequest request) throws Exception{
    	LoginMemberVo loginMemberVo = (LoginMemberVo)request.getSession().getAttribute(Constants.LOGIN_SESSION_MEMBER_KEY);
    	Map<String,Object> map = new HashMap<String,Object>();
    	Map<String,Object> params = new HashMap<String,Object>();
    	String monthId = request.getParameter("monthId");
    	String page = request.getParameter("page");
    	String rows = request.getParameter("rows");
    	String bussId = "";
    	if (loginMemberVo != null){
    		bussId = loginMemberVo.getBussId();
    	}
    	if (StringUtils.isNotBlank(bussId)){
    		params.put("bussId", bussId.trim());
    	}
    	if (StringUtils.isNotBlank(monthId)){
    		params.put("monthId", monthId.trim());
    	}
    	if (StringUtils.isNotBlank(page)){
    		params.put(Page.CURR_PAGE, Integer.parseInt(page));
    	}
    	if (StringUtils.isNotBlank(rows)){
    		params.put(Page.PAGE_SIZE, Integer.parseInt(rows));
    	}
    	List<Map<String,Object>> orderBy = new ArrayList<Map<String,Object>>();
    	Map<String,Object> hash = new HashMap<String,Object>();
    	hash.put("billTime", "desc");
    	orderBy.add(hash);
    	params.put("orderBy", orderBy);
    	Page mBillPage = new Page();
    	List<ChaBillVo> mBillList = new ArrayList<ChaBillVo>();
    	try{
    		mBillPage = chaBillService.getPageByParamMap(params);
    		if (mBillPage == null){
    			return null;
    		}
    		mBillList = mBillPage.getList();
    		map.put("totalNumber", mBillPage.getTotalNumber());
			map.put("totalPage", mBillPage.getTotalPage());
			map.put("rows", mBillList);
    	}catch (Exception e){
    		log.info("获取月账单明细失败");
    		map.put("msg", "获取月账单明细失败");
    		e.printStackTrace();
    	}
    	
    	return map;
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/doMBListExport")
    public String doExport(HttpServletRequest request,HttpServletResponse response) throws Exception{
        List list = new ArrayList();
        String fileName_exp = formatName("ChaBill");
        response.addHeader("content-disposition", "attachment; filename="+fileName_exp);
        response.setContentType("application/x-xls");
        OutputStream os = new BufferedOutputStream(response.getOutputStream());
        WritableWorkbook workbook = Workbook.createWorkbook(os);
        LoginMemberVo loginMemberVo=(LoginMemberVo) request.getSession().getAttribute(Constants.LOGIN_SESSION_MEMBER_KEY);
        Map map=new HashMap();
        map.put("bussId",loginMemberVo.getBussId());
        String startMonth=request.getParameter("startTime");
        String endMonth=request.getParameter("endTime");
        if (StringUtils.isNotBlank(startMonth)){
    		map.put("monthIdGTe", startMonth.replaceAll("-", "").trim());
    	}
    	if (StringUtils.isNotBlank(endMonth)){
    		map.put("monthIdLTe", endMonth.replaceAll("-", "").trim());
    	}
    	List<Map<String,Object>> orderBy = new ArrayList<Map<String,Object>>();
    	Map<String,Object> hash = new HashMap<String,Object>();
    	hash.put("billTime", "desc");
    	orderBy.add(hash);
    	map.put("orderBy", orderBy);
        try {
            //要导出的所有记录
            list = chaBillService.selectByMap(map);
            int size = 100;
            String[] head = {"计费月份","计费金额(元)"};
            createExpCell(list,workbook,size,head);
        } catch (Exception e) {
            log.error("数据导出报错", e);
            e.printStackTrace();
        }finally{
            os.flush();
            os.close();
        }
        return null;
    }

    /**
     * 导出
     * @param list 数据列表
     * @param book 生成excal
     * @param size 一页最大数
     * @param head 页头
     * */
    @SuppressWarnings("rawtypes")
	public void createExpCell(List list,WritableWorkbook book,int size,String [] head)throws Exception{
        int length = list.size();
        int k = length%size>0?length/size+1:length/size;
        try {
            for(int l=0;l<=k;l++) {
                WritableSheet sheet = null;
                if(l==0||(k>0&&l!=k)){
                    sheet=book.createSheet("月账单("+l+")", l);
                    Label label;
                    for (int i = 0; i < head.length; i++) {
                        sheet.setColumnView(i, 15);
                        label= new Label(i, 0, head[i]);
                        sheet.addCell(label);
                    }
                }
                if(k>0){
                    int row = 1;
                    for(int i = l*size; i< (l+1)*size ; i++ ) {
                        if(i<length) {                        	
                            Map map = (Map) list.get(i);
                            Label xm = new Label(0, row, map.get("MONTH_ID").toString());
                            sheet.addCell(xm);
                            double temp=Double.parseDouble(map.get("BILL_AMOUNT").toString());
                            DecimalFormat df=new DecimalFormat(".##");
                            xm = new Label(1, row, df.format(temp));
                            sheet.addCell(xm);
                        }
                        row++;
                    }
                }
            }
            book.write();
            book.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/doMBListDetailExport")
    public String doMBListDetailExport(HttpServletRequest request,HttpServletResponse response) throws Exception{
        List list = new ArrayList();
        String fileName_exp = formatName("ChaBillDetail");
        response.addHeader("content-disposition", "attachment; filename="+fileName_exp);
        response.setContentType("application/x-xls");
        OutputStream os = new BufferedOutputStream(response.getOutputStream());
        WritableWorkbook workbook = Workbook.createWorkbook(os);
        LoginMemberVo loginMemberVo=(LoginMemberVo) request.getSession().getAttribute(Constants.LOGIN_SESSION_MEMBER_KEY);
        Map map=new HashMap();
        map.put("bussId",loginMemberVo.getBussId());
        String monthId=request.getParameter("monthId");
        if (StringUtils.isNotBlank(monthId)){
    		map.put("monthId", monthId.replaceAll("-", "").trim());
    	}
        List<Map<String,Object>> orderBy = new ArrayList<Map<String,Object>>();
    	Map<String,Object> hash = new HashMap<String,Object>();
    	hash.put("billTime", "desc");
    	orderBy.add(hash);
    	map.put("orderBy", orderBy);
        try {
            //要导出的所有记录
            list = chaBillService.selectMBDByMap(map);
            int size = 100;
            String[] head = {"工单号","产品ID","产品名称","产品计量单位","产品单位价格（元）","工单执行时间","数据结果计量","计费金额（元）"};
            createMDExpCell(list,workbook,size,head);
        } catch (Exception e) {
            log.error("数据导出报错", e);
            e.printStackTrace();
        }finally{
            os.flush();
            os.close();
        }
        return null;
    }

    /**
     * 导出
     * @param list 数据列表
     * @param book 生成excal
     * @param size 一页最大数
     * @param head 页头
     * */
    @SuppressWarnings("rawtypes")
	public void createMDExpCell(List list,WritableWorkbook book,int size,String [] head)throws Exception{
        int length = list.size();
        int k = length%size>0?length/size+1:length/size;
        try {
            for(int l=0;l<=k;l++) {
                WritableSheet sheet = null;
                if(l==0||(k>0&&l!=k)){
                    sheet=book.createSheet("月账单明细("+l+")", l);
                    Label label;
                    for (int i = 0; i < head.length; i++) {
                        sheet.setColumnView(i, 15);
                        label= new Label(i, 0, head[i]);
                        sheet.addCell(label);
                    }
                }
                if(k>0){
                    int row = 1;
                    for(int i = l*size; i< (l+1)*size ; i++ ) {
                        if(i<length) {
                            Map map = (Map) list.get(i);
                            Label xm = new Label(0, row, map.get("ORD_ID").toString());
                            sheet.addCell(xm);
                            xm = new Label(1, row, map.get("PRD_ID").toString());
                            sheet.addCell(xm);
                            xm = new Label(2, row, map.get("PRD_NAME").toString());
                            sheet.addCell(xm);
                            if("0".equals(map.get("PRD_UNIT").toString())){
                                xm = new Label(3, row, "MB");
                                sheet.addCell(xm);
                            }else {
                                xm = new Label(3, row, "条");
                                sheet.addCell(xm);
                            }
                            xm = new Label(4, row, map.get("PRD_PRICE").toString());
                            sheet.addCell(xm);
                            xm = new Label(5, row, map.get("RUN_TIME").toString());
                            sheet.addCell(xm);
                            double temp=0.00;
                            if ("1".equals(map.get("PRD_UNIT").toString())){
                                xm = new Label(6, row, map.get("ROW_NUM").toString());
                                sheet.addCell(xm);
                                //temp=Double.parseDouble(map.get("PRD_PRICE").toString())*Double.parseDouble(map.get("ROW_NUM").toString());
                                
                            }else {
                                xm = new Label(6, row, map.get("DATA_SIZE").toString());
                                sheet.addCell(xm);
                                //temp=Double.parseDouble(map.get("PRD_PRICE").toString())*Double.parseDouble(map.get("DATA_SIZE").toString());
                            }
                            temp=Double.parseDouble(map.get("CONSUM_AMOUNT").toString());
                            DecimalFormat df=new DecimalFormat(".##");
                            xm = new Label(7, row, df.format(temp));
                            sheet.addCell(xm);
                        }
                        row++;
                    }
                }
            }
            book.write();
            book.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static String formatName(String modelName){
        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
        return modelName+"_"+dateFormater.format(new Date())+".xls";
    }
}
