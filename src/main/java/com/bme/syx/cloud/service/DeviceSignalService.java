package com.bme.syx.cloud.service;

import com.bme.syx.cloud.dao.DeviceSignalMapper;
import com.bme.syx.cloud.entity.DeviceSignalInfo;
import com.bme.syx.common.Untils;
import littlebee.excel.ExcelImport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DeviceSignalService {


    @Autowired
    private DeviceSignalMapper deviceSignalMapper;

    @Autowired
    private Untils u;

    public String insertDeviceSignal(String customerId){

        long   starttime =  System.currentTimeMillis();
        List<DeviceSignalInfo> list = new ArrayList<>();
        ExcelImport deviceSignalExcel = new ExcelImport(DeviceSignalInfo.class,1,"E:\\import\\采集设备信号关系表.xlsx");
        int sum = 0;
        String error="维护成功!!!";
        try {
            //取到list值
            list=deviceSignalExcel.getModelList(DeviceSignalInfo.class);
            sum = list.size();
            //设置日期格式
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
            // new Date()为获取当前系统时间
            String importData = df.format(new Date());
            list.stream().forEach(l-> l.setImport_data(importData));
            List<List<DeviceSignalInfo>> listGroup = new ArrayList<List<DeviceSignalInfo>>();
            listGroup  = u.groupList(list);
            for (List<DeviceSignalInfo> sublist : listGroup) {
                deviceSignalMapper.insertDeviceSignal(sublist);
            }
        } catch (Exception e) {
            sum = 0;
            error = e.getMessage();
            e.printStackTrace();
        }
        long   endtime =  System.currentTimeMillis();
        long time = endtime-starttime;
        return "客户ID："+customerId+"：本次设备关联信号信息(t_import_device_signal)结果:"+error+",维护条数："+sum+",耗时："+time+"ms";


    }


}
