package com.bme.syx.cloud.service;


import com.bme.syx.cloud.dao.EmissionSourceMapper;
import com.bme.syx.cloud.entity.EmissionSource;
import com.bme.syx.common.Untils;
import littlebee.excel.ExcelImport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EmissionSourceService {
    


    @Autowired
    private EmissionSourceMapper emissionSourceMapper;
    @Autowired
    private Untils u;

    @Transactional
    public  String  insertEissionSource(String customerId) {

        long   starttime =  System.currentTimeMillis();
        List<EmissionSource> list = new ArrayList<>();
        ExcelImport middleExcel = new ExcelImport(EmissionSource.class,1,"E:\\import\\排放源基本信息表.xlsx");
        int sum = 0;
        String error="维护成功";
        try {

            //取到list值
            list=middleExcel.getModelList(EmissionSource.class);

            sum = list.size();
            ////设置日期格式
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
            //// new Date()为获取当前系统时间
            String importData = df.format(new Date());
            list.stream().forEach(l-> l.setImport_data(importData));
            List<List<EmissionSource>> listGroup = new ArrayList<List<EmissionSource>>();
            listGroup  = u.groupList(list);

            for (List<EmissionSource> sublist : listGroup) {
                emissionSourceMapper.insertEmissionSource(sublist);
            }

            emissionSourceMapper.updateEmissionType(customerId);

            emissionSourceMapper.updateEmissionFactory(customerId);

            emissionSourceMapper.updateEmissionLine(customerId);

            emissionSourceMapper.updateEmissionSection(customerId);

            emissionSourceMapper.updateEmissionOrganizationW(customerId);

            emissionSourceMapper.updateEmissionOrganizationY(customerId);

        } catch (Exception e) {
            sum = 0;
            error = e.getMessage();
            e.printStackTrace();
        }
        long   endtime =  System.currentTimeMillis();
        long time = endtime-starttime;
        return "客户ID："+customerId+"：维护排放源清单数据(t_import_emission_source)结果:"+error+",维护条数："+sum+",耗时："+time+"ms";
    }

}
