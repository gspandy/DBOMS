/**
 * com.sunrise.accout.web.BillingListAction.java
 */
package com.tydic.dbs.dbsweb.controller.accout;
import com.tydic.commons.utils.DateUtils;
import com.tydic.commons.utils.OrderUtils;
import com.tydic.commons.utils.Page;
import com.tydic.dbs.billing.service.BillingListService;
import com.tydic.dbs.commons.vo.LoginMemberVo;
import com.tydic.dbs.dbsweb.common.Constants;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.tydic.commons.web.BaseAction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 * @ClassName: BillingListController 
 * @Description: TODO(我的账单)
 * @author huangChuQin
 * @date 2016-7-15 下午6:00:39 
 *
 */
@Controller
@RequestMapping("/author/billingList")
public class BillingListController extends BaseAction{
    private final Log log = LogFactory.getLog(BillingListController.class);

    @Resource
    BillingListService billingListService;
    /**
     * 历史计费清单页
     * @param request
     * @return
     */
    @RequestMapping("/toHistoryChargeList")
    public String toHistoryChargeList(HttpServletRequest request){
        return "account/historyChargeList";
    }

    /**
     * 获取历史计费清单列表
     * @return
     */
    @RequestMapping("/getHistoryChargeList")
    @ResponseBody
    public Page getHistoryChargeList(HttpServletRequest request){
        Map<String,Object> map=new HashMap();
        LoginMemberVo loginMemberVo=(LoginMemberVo)request.getSession().getAttribute(Constants.LOGIN_SESSION_MEMBER_KEY);
        String pageNo = request.getParameter("pageNo");
        String ordId=request.getParameter("ordId");
        String prdId=request.getParameter("prdId");
        String prdName=request.getParameter("prdName");
        String startTime=request.getParameter("startTime");
        String endTime=request.getParameter("endTime");
        pageNo = pageNo==null||pageNo.equals("")? "0":pageNo;
        map.put(Page.CURR_PAGE, Integer.parseInt(pageNo));
        map.put(Page.PAGE_SIZE, 10);
        map.put("bussId", loginMemberVo.getBussId());
        map.put("ordId",""==ordId?null:ordId);
        map.put("prdId",""==prdId?null:prdId);
        map.put("prdName",""==prdName?null:prdName);
       // Date dd=DateUtils.getHymdsByString(startTime);
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
        try {
            Page page=billingListService.getChargebyMap(map);
            return page;
        }catch (Exception e){
            log.error("查询失败",e);
        }
        return null;
    }

    @RequestMapping("/doExport")
    public String doExport(HttpServletRequest request,HttpServletResponse response) throws Exception{
        List list = new ArrayList();
        Label label;
        String fileName_exp = formatName("HistoryCharge");
        response.addHeader("content-disposition", "attachment; filename="+fileName_exp);
        response.setContentType("application/x-xls");
        OutputStream os = new BufferedOutputStream(response.getOutputStream());
        WritableWorkbook workbook = Workbook.createWorkbook(os);
        LoginMemberVo loginMemberVo=(LoginMemberVo) request.getSession().getAttribute(Constants.LOGIN_SESSION_MEMBER_KEY);
        Map map=new HashMap();
        map.put("bussId",loginMemberVo.getBussId());
        String ordId=request.getParameter("ordId");
        String prdId=request.getParameter("prdId");
        String prdName=request.getParameter("prdName");
        String startTime=request.getParameter("startTime");
        String endTime=request.getParameter("endTime");
        map.put("ordId",""==ordId?null:ordId);
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
            list = billingListService.selectByMap(map);
            int size = 100;
            String[] head = {"工单号","产品ID","产品名称","产品计量单位","产品单价(元)","工单执行时间","数据结果计量","计费金额（元)"};
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
                    sheet=book.createSheet("计费清单("+l+")", l);
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
                            if("2".equals(map.get("PRD_UNIT").toString())){
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
                                temp=Double.parseDouble(map.get("PRD_PRICE").toString())*Double.parseDouble(map.get("ROW_NUM").toString());
                            }else {
                                xm = new Label(6, row, map.get("DATA_SIZE").toString());
                                sheet.addCell(xm);
                                temp=Double.parseDouble(map.get("PRD_PRICE").toString())*Double.parseDouble(map.get("DATA_SIZE").toString());
                            }
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
