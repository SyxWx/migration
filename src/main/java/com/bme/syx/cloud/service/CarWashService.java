package com.bme.syx.cloud.service;


import com.bme.syx.cloud.dao.CarWashMapper;
import com.bme.syx.cloud.dao.CleanCarMapper;
import com.bme.syx.cloud.entity.CarWashInfo;
import com.bme.syx.cloud.entity.CleanCarInfo;
import littlebee.excel.ExcelImport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CarWashService {


    @Autowired
    private CarWashMapper carWashMapper;


    public String insertCarWashInfo(String  customerId){

        long starttime = System.currentTimeMillis();
        List<CarWashInfo> list = new ArrayList<>();
        ExcelImport cameraExcel = new ExcelImport(CarWashInfo.class, 1, "E:\\import\\洗车台信息信号关联表.xlsx");
        int sum = 0;
        String error = "维护成功!!!";
        try {
            //取到list值
            list = cameraExcel.getModelList(CarWashInfo.class);
            sum = list.size();
            //设置日期格式
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
            // new Date()为获取当前系统时间
            String importData = df.format(new Date());
            list.stream().forEach(l -> l.setImport_data(importData));
            list.stream().forEach(l -> l.setStatus("1"));

            for (CarWashInfo car : list) {
                 if("是".equals(car.getIs_drying_device())){
                     car.setIs_drying_device("1");
                 }else{
                     car.setIs_drying_device("0");
                 }
                if("是".equals(car.getIs_water_shaking_table())){
                    car.setIs_water_shaking_table("1");
                }else{
                    car.setIs_water_shaking_table("0");
                }
                if("是".equals(car.getIs_sewage_treatment())){
                    car.setIs_sewage_treatment("1");
                }else{
                    car.setIs_sewage_treatment("0");
                }
            }

           carWashMapper.insertCarWashList(list);

        } catch (Exception e) {
            sum = 0;
            error = e.getMessage();
            e.printStackTrace();
        }
        long endtime = System.currentTimeMillis();
        long time = endtime - starttime;
        return "客户ID：" + customerId + "：本次洗车台数据(t_import_car_wash_info)结果:" + error + ",维护条数：" + sum + ",耗时：" + time + "ms";


    }


}
