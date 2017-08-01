/*
 * com.tydic.dbs.system.web.action.bill  2016-8-8
 *
 * Copyright 2010 Shenzhen TYDIC Information Technology Co.,Ltd.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.tydic.dbs.system.web.action.bill;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
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

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tydic.commons.utils.Page;
import com.tydic.dbs.billing.service.ChaBillService;
import com.tydic.dbs.billing.vo.ChaBill;
import com.tydic.dbs.billing.vo.ChaBillVo;
import com.tydic.dbs.commons.utils.DateUtil;
import com.tydic.dbs.commons.web.BaseAnnotationAction;

/** 
 * @ClassName: BillAction 
 * @Description: TODO(计费管理) 
 * @author huangChuQin
 * @date 2016-8-8 下午4:00:55 
 *  
 */
@Controller
@RequestMapping("/bill")
public class BillAction extends BaseAnnotationAction{
	
	@Autowired
	private ChaBillService chaBillService;

	@RequestMapping("/toBillList")
	public String toRoleList(HttpServletRequest request) {
		return "bill/billList";
	}
	
	@RequestMapping("/getList")
	public @ResponseBody Map<String, Object> getList(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> params = new HashMap<String, Object>();
		Page billPage = new Page();
		String bussAccount=request.getParameter("bussAccount");
		String startMonthId=request.getParameter("startMonthId");
		String endMonthId=request.getParameter("endMonthId");
		String page=request.getParameter("page");
		if (StringUtils.isNotEmpty(bussAccount)) {
			params.put("bussAccountLike", bussAccount);
		}
		if (StringUtils.isNotEmpty(startMonthId)) {
			params.put("monthIdStart", startMonthId.replaceAll("-", ""));
		}
		if (StringUtils.isNotEmpty(endMonthId)) {
			params.put("monthIdEnd", endMonthId.replaceAll("-", ""));
		}
		if (StringUtils.isNotEmpty(page)) {
			params.put(Page.CURR_PAGE, Integer.parseInt(page));
		}
		params.put(Page.PAGE_SIZE, Page.DEFAULT_PAGE_SIZE);
		List<Map<String, Object>> orderBy = new ArrayList<Map<String, Object>>();
    	Map<String, Object> hash = new HashMap<String, Object>();
    	hash.put("billTime", "desc");
    	orderBy.add(hash);
    	params.put("orderBy", orderBy);
    	double billSum = 0.0;
		try {
			billSum = chaBillService.getPageByParamsSum(params);
			billPage = chaBillService.getPageByParamsMap(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalNumber", billPage.getTotalNumber());
		map.put("totalPage", billPage.getTotalPage());
		map.put("rows", billPage.getList());
		map.put("billSum", billSum);
		return map;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/doExport")
    public @ResponseBody String doExport(HttpServletRequest request,HttpServletResponse response) throws Exception{
        List<ChaBill> list = new ArrayList<ChaBill>();
        String fileName_exp = formatName("ChaBill");
        response.addHeader("content-disposition", "attachment; filename="+fileName_exp);
        response.setContentType("application/x-xls");
        OutputStream os = new BufferedOutputStream(response.getOutputStream());
        WritableWorkbook workbook = Workbook.createWorkbook(os);
        
        Map<String, Object> params = new HashMap<String, Object>();
		String bussAccount=request.getParameter("bussAccount");
		String startMonthId=request.getParameter("startMonthId");
		String endMonthId=request.getParameter("endMonthId");
		String page=request.getParameter("page");
		if (StringUtils.isNotEmpty(bussAccount)) {
			params.put("bussAccountLike", bussAccount);
		}
		if (StringUtils.isNotEmpty(startMonthId)) {
			params.put("monthIdStart", startMonthId.replaceAll("-", ""));
		}
		if (StringUtils.isNotEmpty(endMonthId)) {
			params.put("monthIdEnd", endMonthId.replaceAll("-", ""));
		}
		if (StringUtils.isNotEmpty(page)) {
			params.put(Page.CURR_PAGE, Integer.parseInt(page));
		}
		params.put(Page.NO_PAGING, true);
		List<Map<String, Object>> orderBy = new ArrayList<Map<String, Object>>();
    	Map<String, Object> hash = new HashMap<String, Object>();
    	hash.put("billTime", "desc");
    	orderBy.add(hash);
    	params.put("orderBy", orderBy);
    	
        try {
            //要导出的所有记录
            Page billPage = chaBillService.getPageByParamsMap(params);
            list = billPage.getList();
            int size = 100;
            String[] head = {"计费月份","商户名称","计费金额(元)"};
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
    public void createExpCell(List<ChaBill> list,WritableWorkbook book,int size,String [] head)throws Exception{
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
                        	ChaBill chaBill = (ChaBill) list.get(i);
                            Label xm = new Label(0, row, chaBill.getMonthId().toString());
                            sheet.addCell(xm);
                            xm = new Label(1, row, chaBill.getBussAccount());
                            sheet.addCell(xm);
                            xm = new Label(2, row, chaBill.getBillAmount().toString());
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
    
    @RequestMapping("/toDetailList")
    public String toDetailList(HttpServletRequest request){
    	final String monthId = request.getParameter("monthId");
    	final String bussAccount = request.getParameter("bussAccount");
    	request.setAttribute("monthId", monthId);
    	request.setAttribute("bussAccount", bussAccount);
    	return "bill/detailList";
    }
    
    @RequestMapping("/getDetailList")
	public @ResponseBody Map<String, Object> getDetailList(HttpServletRequest request, HttpServletResponse response){
    	Map<String, Object> params = new HashMap<String, Object>();
		Page billPage = new Page();
    	final String monthId = request.getParameter("monthId");
    	final String bussAccount = request.getParameter("bussAccount");
    	final String page=request.getParameter("page");
		if (StringUtils.isNotEmpty(bussAccount)) {
			params.put("bussAccount", bussAccount);
		}
		if (StringUtils.isNotEmpty(monthId)) {
			params.put("monthId", monthId.replaceAll("-", ""));
		}
		if (StringUtils.isNotEmpty(page)) {
			params.put(Page.CURR_PAGE, Integer.parseInt(page));
		}
		params.put(Page.PAGE_SIZE, 6);
		List<Map<String, Object>> orderBy = new ArrayList<Map<String, Object>>();
    	Map<String, Object> hash = new HashMap<String, Object>();
    	hash.put("billTime", "desc");
    	orderBy.add(hash);
    	params.put("orderBy", orderBy);
		try {
			billPage = chaBillService.getPageByParamMap(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalNumber", billPage.getTotalNumber());
		map.put("totalPage", billPage.getTotalPage());
		map.put("rows", billPage.getList());
		return map;
    }
    
    @SuppressWarnings("unchecked")
	@RequestMapping("/doExportDetail")
    public @ResponseBody String doExportDetail(HttpServletRequest request,HttpServletResponse response) throws Exception{
        List<ChaBill> list = new ArrayList<ChaBill>();
        String fileName_exp = formatName("ChaBillDetail");
        response.addHeader("content-disposition", "attachment; filename="+fileName_exp);
        response.setContentType("application/x-xls");
        OutputStream os = new BufferedOutputStream(response.getOutputStream());
        WritableWorkbook workbook = Workbook.createWorkbook(os);
        
        Map<String, Object> params = new HashMap<String, Object>();
    	final String monthId = request.getParameter("monthId");
    	final String bussAccount = request.getParameter("bussAccount");
		if (StringUtils.isNotEmpty(bussAccount)) {
			params.put("bussAccountLike", bussAccount);
		}
		if (StringUtils.isNotEmpty(monthId)) {
			params.put("monthId", monthId.replaceAll("-", ""));
		}
		params.put(Page.NO_PAGING, true);
		List<Map<String, Object>> orderBy = new ArrayList<Map<String, Object>>();
    	Map<String, Object> hash = new HashMap<String, Object>();
    	hash.put("billTime", "desc");
    	orderBy.add(hash);
    	params.put("orderBy", orderBy);

        try {
            //要导出的所有记录
            Page billPage = chaBillService.getPageByParamMap(params);
            list = billPage.getList();
            int size = 100;
            String[] head = {"工单号","商户名称","产品ID","产品名称","产品计量单位","产品单位价格(元)","工单执行时间","数据结果计量","计费金额"};
            createExpCellDetail(list,workbook,size,head);
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
    @SuppressWarnings("static-access")
	public void createExpCellDetail(List<ChaBill> list,WritableWorkbook book,int size,String [] head)throws Exception{
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
                        	ChaBillVo chaBillVo = (ChaBillVo) list.get(i);
                            Label xm = new Label(0, row, chaBillVo.getOrdId());
                            sheet.addCell(xm);
                            xm = new Label(1, row, chaBillVo.getBussAccount());
                            sheet.addCell(xm);
                            xm = new Label(2, row, chaBillVo.getPrdId());
                            sheet.addCell(xm);
                            xm = new Label(3, row, chaBillVo.getPrdName());
                            sheet.addCell(xm);
                            String prdUnit = chaBillVo.getPrdUnit();
                            String prdPrice = prdUnit.equals("1")?"条数":"MB";
                            prdUnit = prdUnit.equals("1")?"￥"+chaBillVo.getPrdPrice()+"/条":"￥"+chaBillVo.getPrdPrice()+"/MB";
                            xm = new Label(4, row, prdUnit);
                            sheet.addCell(xm);
                            xm = new Label(5, row, prdPrice);
                            sheet.addCell(xm);
                            String timeVal = new DateUtil().DateToString5(chaBillVo.getOrdDateBegin());
                            xm = new Label(6, row, timeVal);
                            sheet.addCell(xm);
                            xm = new Label(7, row, chaBillVo.getConsumAmout());
                            sheet.addCell(xm);
                            xm = new Label(8, row, "￥"+chaBillVo.getPayAmout());
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
}
