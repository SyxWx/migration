package com.bme.syx.cloud.service;


import com.bme.syx.cloud.dao.DeviceMapper;
import com.bme.syx.cloud.entity.DeviceInfo;
import com.bme.syx.common.Untils;
import littlebee.excel.ExcelImport;
import org.elasticsearch.common.recycler.Recycler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class DeviceInfoService {

    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private Untils u;

    public String insertDeviceInfo(String customerId){

        long   starttime =  System.currentTimeMillis();
        List<DeviceInfo> list = new ArrayList<>();
        ExcelImport deviceExcel = new ExcelImport(DeviceInfo.class,1,"E:\\import\\设备基本信息表.xlsx");
        int sum = 0;
        String error="维护成功!!!";
        try {
            //取到list值
            list=deviceExcel.getModelList(DeviceInfo.class);
            sum = list.size();
            ////设置日期格式
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
            //// new Date()为获取当前系统时间
            String importData = df.format(new Date());
            list.stream().forEach(l-> l.setImport_data(importData));
            List<List<DeviceInfo>> listGroup = new ArrayList<List<DeviceInfo>>();
            listGroup  = u.groupList(list);
            for (List<DeviceInfo> sublist : listGroup) {
                deviceMapper.insertDevice(sublist);
            }
            deviceMapper.updateDeviceFactory(customerId);
            System.out.println("更新分厂");
            deviceMapper.updateDeviceLine(customerId);
            System.out.println("更新生产线");
            deviceMapper.updateDevicePType(customerId);

            deviceMapper.updateDeviceType(customerId);

            deviceMapper.updateDeviceGBTdc(customerId);

            deviceMapper.updateDeviceGBSo2(customerId);

            deviceMapper.updateDeviceGBNox(customerId);

            deviceMapper.updateDeviceLongitudeNull(customerId);

            deviceMapper.updateDeviceLatitudeNull(customerId);


        } catch (Exception e) {
            sum = 0;
            error = e.getMessage();
            e.printStackTrace();
        }
        long   endtime =  System.currentTimeMillis();
        long time = endtime-starttime;
        return "客户ID："+customerId+"：本次设备数据(t_import_device)结果:"+error+",维护条数："+sum+",耗时："+time+"ms";

    }







}
