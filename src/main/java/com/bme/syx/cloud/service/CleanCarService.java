package com.bme.syx.cloud.service;

import com.bme.syx.cloud.dao.CleanCarMapper;
import com.bme.syx.cloud.entity.CleanCarInfo;
import com.bme.syx.cloud.entity.UlevCameraInfo;
import littlebee.excel.ExcelImport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CleanCarService {

    @Autowired
    private CleanCarMapper cleanCarMapper;


    public String insertCleanCarInfo(String  customerId){

        long starttime = System.currentTimeMillis();
        List<CleanCarInfo> list = new ArrayList<>();
        ExcelImport cameraExcel = new ExcelImport(CleanCarInfo.class, 1, "E:\\import\\环保车辆信息信号关联表.xlsx");
        int sum = 0;
        String error = "维护成功!!!";
        try {
            //取到list值
            list = cameraExcel.getModelList(CleanCarInfo.class);
            sum = list.size();
            //设置日期格式
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
            // new Date()为获取当前系统时间
            String importData = df.format(new Date());
            list.stream().forEach(l -> l.setImport_data(importData));
            cleanCarMapper.insertCleanCarList(list);

        } catch (Exception e) {
            sum = 0;
            error = e.getMessage();
            e.printStackTrace();
        }
        long endtime = System.currentTimeMillis();
        long time = endtime - starttime;
        return "客户ID：" + customerId + "：本次环保车辆数据(t_import_cleancar_info)结果:" + error + ",维护条数：" + sum + ",耗时：" + time + "ms";
    }
}
