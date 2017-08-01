package com.tydic.dbs.ws.job;

import com.tydic.dbs.commons.constant.ConfigConstants;
import com.tydic.dbs.commons.enums.ResultState;
import com.tydic.dbs.order.service.OrdResultFileHisService;
import com.tydic.dbs.order.service.OrdResultFileService;
import com.tydic.dbs.order.vo.OrdResultFile;
import com.tydic.dbs.ws.common.CommonConfig;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2016/8/8.
 */
@Component
public class TaskJob {
    private Logger logger   = Logger.getLogger(this.getClass());

    @Autowired
    private CommonConfig commonConfig;

    @Autowired
    private OrdResultFileService ordResultFileService;

    @Autowired
    private OrdResultFileHisService ordResultFileHisService;


    public void excute()  {
        logger.info("开始处理工单返回文件......");

        Map<String,String> param = new HashMap<>();
        param.put("state", ConfigConstants.SCAN_FLAG);
        List<OrdResultFile> ordResultFileList = null;
        try {
            //扫描工单返回文件表，获取需处理的文件
            ordResultFileList = ordResultFileService.getListByParamMap(param);
        } catch (Exception ex) {
            logger.info("扫描工单异常");
            return;
        }
        if(ordResultFileList == null||ordResultFileList.size()==0){
            logger.info("没有需要处理的工单文件");
            return;
        }
        List<Long> ids = new ArrayList<>();
        for(OrdResultFile f :ordResultFileList){
            ids.add(f.getFileId());
        }
        //将扫描出来的工单文件状态转为正在处理中
        try {
            ordResultFileService.modifyStatus(ids);
        } catch (Exception ex) {
            logger.info("变更工单文件状态异常", ex);
            return;
        }

        for(OrdResultFile f :ordResultFileList){
            try {
                ordResultFileService.meteAndCharge(f);
            } catch (Exception ex) {
                String msg = "工单文件【" + f.getFileName() + "】异常";
                logger.info(msg, ex);
                ordResultFileService.moveToHis(f, ResultState.FAIL.getCode(),msg);
            }
        }
        logger.info("工单返回文件处理完成......");
    }


}
