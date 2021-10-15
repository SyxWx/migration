package com.bme.syx.cloud.service;

import com.bme.syx.cloud.dao.EmissionSourceDeviceMapper;
import com.bme.syx.cloud.entity.DeviceInfo;
import com.bme.syx.cloud.entity.EmissionSourceDevice;
import com.bme.syx.common.Untils;
import littlebee.excel.ExcelImport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EmissionSourceDeviceService {


    @Autowired
    private EmissionSourceDeviceMapper emissionSourceDeviceMapper;

    @Autowired
    private Untils u;

    public String insertEmissionSourceDevice(String customerId){

        long   starttime =  System.currentTimeMillis();
        List<EmissionSourceDevice> list = new ArrayList<>();
        ExcelImport emissionDeviceExcel = new ExcelImport(EmissionSourceDevice.class,1,"E:\\import\\排放源设备信息关联表.xlsx");
        int sum = 0;
        String error="维护成功!!!";
        try {
            //取到list值
            list=emissionDeviceExcel.getModelList(EmissionSourceDevice.class);
            sum = list.size();
            ////设置日期格式
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
            //// new Date()为获取当前系统时间
            String importData = df.format(new Date());
            list.stream().forEach(l-> l.setImport_data(importData));
            List<List<EmissionSourceDevice>> listGroup = new ArrayList<List<EmissionSourceDevice>>();
            listGroup  = u.groupList(list);
            for (List<EmissionSourceDevice> sublist : listGroup) {
                emissionSourceDeviceMapper.insertEmissionSourceDevice(sublist);
            }
            emissionSourceDeviceMapper.updateEmissionDeviceName(customerId);

            emissionSourceDeviceMapper.updateEmissionDeviceType(customerId);
        } catch (Exception e) {
            sum = 0;
            error = e.getMessage();
            e.printStackTrace();
        }
        long   endtime =  System.currentTimeMillis();
        long time = endtime-starttime;
        return "客户ID："+customerId+"：本次排放源关联设备信息(t_import_emission_source_device)结果:"+error+",维护条数："+sum+",耗时："+time+"ms";


    }


}
