package com.bme.syx.cloud.service;

import com.bme.syx.cloud.dao.SignalMapper;
import com.bme.syx.cloud.entity.SignalInfo;
import com.bme.syx.common.Untils;
import littlebee.excel.ExcelImport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SignalService {

    @Autowired
    private SignalMapper signalMapper;

    @Autowired
    private Untils u;

    public String insertSignalInfo(String customerId){

        long   starttime =  System.currentTimeMillis();
        List<SignalInfo> list = new ArrayList<>();
        ExcelImport signalExcel = new ExcelImport(SignalInfo.class,1,"E:\\import\\数据采集信号基本信息表.xlsx");
        int sum = 0;
        String error="维护成功!!!";
        try {
            //取到list值
            list=signalExcel.getModelList(SignalInfo.class);
            sum = list.size();
            ////设置日期格式
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
            //// new Date()为获取当前系统时间
            String importData = df.format(new Date());
            list.stream().forEach(l-> l.setImport_data(importData));
            List<List<SignalInfo>> listGroup = new ArrayList<List<SignalInfo>>();
            listGroup  = u.groupList(list);
            for (List<SignalInfo> sublist : listGroup) {
                signalMapper.insertSignal(sublist);
            }

            signalMapper.updateSignalPeriod30(customerId);

            signalMapper.updateSignalPeriod60(customerId);

            signalMapper.updateSignalPeriod300(customerId);

            signalMapper.updateSignalStandard(customerId);

        } catch (Exception e) {
            sum = 0;
            error = e.getMessage();
            e.printStackTrace();
        }
        long   endtime =  System.currentTimeMillis();
        long time = endtime-starttime;
        return "客户ID："+customerId+"：本次信号数据(t_import_signal)结果:"+error+",维护条数："+sum+",耗时："+time+"ms";

    }


    public String insertFileSignalInfo(String customerId, MultipartFile file){

        long   starttime =  System.currentTimeMillis();
        List<SignalInfo> list = new ArrayList<>();
        ExcelImport signalExcel = new ExcelImport(SignalInfo.class,1,"E:\\import\\数据采集信号基本信息表.xlsx");
        int sum = 0;
        String error="维护成功!!!";
        try {
            //取到list值
            list=signalExcel.getModelList(SignalInfo.class);
            sum = list.size();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
            String importData = df.format(new Date());// new Date()为获取当前系统时间
            list.stream().forEach(l-> l.setImport_data(importData));
            List<List<SignalInfo>> listGroup = new ArrayList<List<SignalInfo>>();
            listGroup  = u.groupList(list);
            for (List<SignalInfo> sublist : listGroup) {
                signalMapper.insertSignal(sublist);
            }

            signalMapper.updateSignalPeriod30(customerId);

            signalMapper.updateSignalPeriod60(customerId);

            signalMapper.updateSignalPeriod300(customerId);

        } catch (Exception e) {
            sum = 0;
            error = e.getMessage();
            e.printStackTrace();
        }
        long   endtime =  System.currentTimeMillis();
        long time = endtime-starttime;
        return "客户ID："+customerId+"：本次信号数据(t_import_device)结果:"+error+",维护条数："+sum+",耗时："+time+"ms";

    }
}
