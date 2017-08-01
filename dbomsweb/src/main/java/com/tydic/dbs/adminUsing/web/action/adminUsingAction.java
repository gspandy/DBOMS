package com.tydic.dbs.adminUsing.web.action;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tydic.commons.utils.Page;
import com.tydic.dbs.commons.vo.LoginMemberVo;
import com.tydic.dbs.commons.web.BaseAnnotationAction;
import com.tydic.dbs.order.service.OrdInfoService;

/**
 * 
 * @ClassName: adminUsingAction 
 * @Description: TODO(后台用户使用量查询) 
 * @author Zhongjialiang zhongjialiang@tydic.com 
 * @date 2016-8-8 上午11:02:06 
 *
 */
@Controller
@RequestMapping("/adminUsing")
public class adminUsingAction extends BaseAnnotationAction {
	
	@Autowired
	private OrdInfoService ordInfoService;
	
	/**
	 * 
	 * @Title: index 
	 * @Description: TODO(跳转到查询页面) 
	 * @param @param request
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	@RequestMapping("/index")
	public String index (HttpServletRequest request){
		
		return "adminUsing/using";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/getUsingList")
	public @ResponseBody Page getUsingList(HttpServletRequest request) throws Exception{
		
		Map<String,Object> map =new HashMap<>();
		String ordId = request.getParameter("ordId");
		String bussName = request.getParameter("bussName");
		String prdId = request.getParameter("prdId");
		String prdName = request.getParameter("prdName");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String pageNo = request.getParameter("pageNo");
		//需要对前台传递的时间进行格式转换
		
		pageNo = pageNo==null||pageNo.equals("")?"1":pageNo;
		map.put(Page.CURR_PAGE,Integer.parseInt(pageNo));
		map.put(Page.PAGE_SIZE,6);
		
		if(null!=ordId&&""!=ordId){
			map.put("ordId", ordId);
		}
		if(null!=bussName&&""!=bussName){
			map.put("bussName", bussName);
		}
		if(null!=prdId&&""!=prdId){
			map.put("prdId", prdId);
		}
		if(null!=prdName&&""!=prdName){
			map.put("prdName", prdName);
		}
		if(startTime ==null){
	        map.put("startTime",startTime);
	    }else {
	         map.put("startTime",""==startTime?null:startTime+" 00:00:00");
	    }

	    if (endTime==null){
	        map.put("endTime",endTime);
	    }else {
	        map.put("endTime",""==endTime?null:endTime+" 23:59:59");
	    }
	    map.put("runTimeDesc","desc");
		//排序
//		List<Map<String,Object>> orderBy =new ArrayList<Map<String,Object>>();
//		Map<String,Object> hash = new HashMap<String, Object>();
//		hash.put("runTimeDesc","desc");
//		orderBy.add(hash);
//		map.put("ordeBy", orderBy);
		
		try {
			Page page = ordInfoService.getPageByMap(map);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	/**
	 * 
	 * @Title: doExport 
	 * @Description: TODO(导出) 
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	 @RequestMapping("/doExport")
	    public String doExport(HttpServletRequest request,HttpServletResponse response) throws Exception{
	        List list = new ArrayList();
	        Label label;
	        String fileName_exp = formatName("adminUsing");
	        response.addHeader("content-disposition", "attachment; filename="+fileName_exp);
	        response.setContentType("application/x-xls");
	        OutputStream os = new BufferedOutputStream(response.getOutputStream());
	        WritableWorkbook workbook = Workbook.createWorkbook(os);
	        Map map=new HashMap();
	        String ordId=request.getParameter("ordId");
	        String bussName=request.getParameter("bussName");
	        String prdId=request.getParameter("prdId");
	        String prdName=request.getParameter("prdName");
	        String startTime=request.getParameter("startTime");
	        String endTime=request.getParameter("endTime");
	        map.put("ordId",""==ordId?null:ordId);
	        map.put("bussName",""==bussName?null:bussName);
	        map.put("prdId",""==prdId?null:prdId);
	        map.put("prdName",""==prdName?null:prdName);
	        if(startTime ==null){
	            map.put("startTime",startTime);
	        }else {
	            map.put("startTime",""==startTime?null:startTime+"-01 00:00:00");
	        }

	        if (endTime==null){
	            map.put("endTime",endTime);
	        }else {
	            map.put("endTime",""==endTime?null:endTime+"-31 23:59:59");
	        }
	        try {
	            //要导出的所有记录
	            list = ordInfoService.selectByMap(map);
	            int size = 100;
	            String[] head = {"工单号","商户名称","产品ID","产品名称","工单执行时间","产品计量单位","数据结果流量"};
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
	    public void createExpCell(List list,WritableWorkbook book,int size,String [] head)throws Exception{
	        int length = list.size();
	        int k = length%size>0?length/size+1:length/size;
	        try {
	            for(int l=0;l<=k;l++) {
	                WritableSheet sheet = null;
	                if(l==0||(k>0&&l!=k)){
	                    sheet=book.createSheet("使用量清单("+l+")", l);
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
	                            Label xm = new Label(0, row, map.get("WORK_NO")==null?"":map.get("WORK_NO").toString());
	                            sheet.addCell(xm);
	                            xm = new Label(1, row, map.get("BUSS_NAME")==null?"":map.get("BUSS_NAME").toString());
	                            sheet.addCell(xm);
	                            xm = new Label(2, row, map.get("PRD_ID")==null?"":map.get("PRD_ID").toString());
	                            sheet.addCell(xm);
	                            xm = new Label(3, row, map.get("PRD_NAME")==null?"":map.get("PRD_NAME").toString());
	                            sheet.addCell(xm);
	                            xm = new Label(4, row, map.get("RUN_TIME")==null?"":map.get("RUN_TIME").toString());
	                            sheet.addCell(xm);
	                            if("2".equals(map.get("PRD_UNIT")==null?"":map.get("PRD_UNIT").toString())){
	                                xm = new Label(5, row, "MB");
	                                sheet.addCell(xm);
	                                xm = new Label(6, row, map.get("DATA_SIZE")==null?"":map.get("DATA_SIZE").toString());
		                            sheet.addCell(xm);
	                            }else {
	                                xm = new Label(5, row, "条");
	                                sheet.addCell(xm);
	                                xm = new Label(6, row, map.get("ROW_NUM")==null?"":map.get("ROW_NUM").toString());
		                            sheet.addCell(xm);
	                            }
	                         
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
