package com.tydic.dbs.ws.job;

import com.tydic.dbs.billing.service.BillingListService;
import com.tydic.dbs.billing.service.ChaBillService;
import com.tydic.dbs.billing.vo.BillVo;
import com.tydic.dbs.commons.constant.ConfigConstants;
import com.tydic.dbs.commons.enums.ResultState;
import com.tydic.dbs.commons.utils.DateUtil;
import com.tydic.dbs.ws.common.CommonConfig;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by dell on 2016/8/8.
 */
@Component
public class MonthBillJob {
    private Logger logger   = Logger.getLogger(this.getClass());

    @Autowired
    private CommonConfig commonConfig;

    @Autowired
    private BillingListService billingListService;
    @Autowired
    private ChaBillService chaBillService;

    public void excute()  {
        logger.info("开始统计月账单......");

        //获取月份
        String monthId = DateUtil.getDate(new Date(),DateUtil.YYYYMM);
       // String monthId = "201608";

        //查询计费清单表并统计指定月份账单数据
        List <BillVo> billVoList = null;
        try {
             chaBillService.statMonthBill(monthId);
        } catch (Exception e) {
            logger.error("查询统计月账单出错！",e);
            return;
        }

        logger.info("月账单统计结束......");
    }

    public static void main(String [] args){
        new MonthBillJob().excute();
    }


}
