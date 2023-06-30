package com.bme.syx.hbasetest;


import com.bme.syx.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("Hbase/query/")
public class TestHabseController {


    @Autowired
    private TestHbase testHbase;

    @RequestMapping("signal")
    public CommonResult getRealTimeValue(){

        //  localhost:8080/mig/Hbase/query/signal
        List<Map<String, Object>>  list = testHbase.getLastRowDataFromHbase();

        System.out.println(list.toString());
        return CommonResult.success(list);
    }

    @RequestMapping("signalList")
    public CommonResult getSignalValue(){

        //  localhost:8080/mig/Hbase/query/signalList
        List<Map<String, Object>>  list = testHbase.getRowDataFromHbase();

        System.out.println(list.toString());
        return CommonResult.success(list);
    }

    @RequestMapping("signalnoList")
    public CommonResult getSignalValueBySignal(String signalNo){

        //  localhost:8080/mig/Hbase/query/signalnoList?signalNo='BMEVDM_ELC_220005_C3_1'
        List<Map<String, Object>>  list = testHbase.getRowDataFromHbaseBySignal(signalNo);

        System.out.println(list.toString());
        return CommonResult.success(list);
    }


}
