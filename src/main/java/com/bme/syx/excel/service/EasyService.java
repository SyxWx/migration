package com.bme.syx.excel.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.bme.syx.excel.entity.EasyDevice;
import com.bme.syx.common.Untils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EasyService {


    @Autowired
    private Untils u;

    public String insertEmissionSourceDevice(String customerId){

        long   starttime =  System.currentTimeMillis();
        List<EasyDevice> list = new ArrayList<EasyDevice>();

        String filename = "E:\\import\\排放源设备信息关联表.xlsx";

        List<EasyDevice> finalList = list;
        EasyExcel.read(filename, EasyDevice.class, new AnalysisEventListener<EasyDevice>() {
            @Override
            public void invoke(EasyDevice ed, AnalysisContext analysisContext) {
                finalList.add(ed);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                System.out.println("读取完成！");
            }
        }).sheet().doRead();



        int sum = finalList.size();
        ////设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
        //// new Date()为获取当前系统时间
        String importData = df.format(new Date());
        list.stream().forEach(l-> l.setImport_data(importData));
        List<List<EasyDevice>> listGroup = new ArrayList<List<EasyDevice>>();
        listGroup  = u.groupList(finalList);
        for (List<EasyDevice> sublist : listGroup) {

            System.out.println(sublist.toString());

        }
        long   endtime =  System.currentTimeMillis();
        long time = endtime-starttime;
        return "客户ID："+customerId+"：本次排放源关联设备信息(t_import_emission_source_device)结果:"+",维护条数："+sum+",耗时："+time+"ms";


    }

    public String insertEasyDevice(String customerId){

        long   starttime =  System.currentTimeMillis();
        List<EasyDevice> list = new ArrayList<EasyDevice>();

        String filename = "E:\\import\\排放源设备信息关联表.xlsx";

        File file = new File(filename);


        List<EasyDevice> finalList = list;
        EasyExcel.read(filename, EasyDevice.class, new AnalysisEventListener<EasyDevice>() {
            @Override
            public void invoke(EasyDevice ed, AnalysisContext analysisContext) {
                finalList.add(ed);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                System.out.println("读取完成！");
            }
        }).sheet().doRead();



        int sum = finalList.size();
        ////设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
        //// new Date()为获取当前系统时间
        String importData = df.format(new Date());
        list.stream().forEach(l-> l.setImport_data(importData));
        List<List<EasyDevice>> listGroup = new ArrayList<List<EasyDevice>>();
        listGroup  = u.groupList(finalList);
        for (List<EasyDevice> sublist : listGroup) {

            System.out.println(sublist.toString());

        }
        long   endtime =  System.currentTimeMillis();
        long time = endtime-starttime;
        return "客户ID："+customerId+"：本次排放源关联设备信息(t_import_emission_source_device)结果:"+",维护条数："+sum+",耗时："+time+"ms";


    }
}
