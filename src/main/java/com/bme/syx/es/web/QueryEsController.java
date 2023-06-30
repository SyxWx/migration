package com.bme.syx.es.web;

import com.bme.syx.es.service.EsDeviceService;
import com.bme.syx.es.service.EsQueryCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "QueryEs/")
public class QueryEsController {


    @Autowired
    private EsDeviceService esDeviceService;


    @Autowired
    private EsQueryCarService esQueryCarService;


    //http://localhost:8080/mig/QueryEs/queryEs
    @RequestMapping(value = "queryEs")
    public  String index(){
       return "queryEs";
    }

    //http://localhost:8080/mig/QueryEs/getMon
    //http://localhost:8080/mig/QueryEs/getMon?deviceNo=BMEVDM0A210521012&customerId=42
    //http://localhost:8080/mig/QueryEs/getMon?deviceNo=AG-ZL-LTC-070&customerId=15
    @RequestMapping(value = "getMon")
    @ResponseBody
    public  String getMonitorReal(@RequestParam("deviceNo")String deviceNo, @RequestParam("customerId")long customerId){

        String res = esDeviceService.getDeviceType(deviceNo,customerId);
        return  res;
    }


    //http://localhost:8080/mig/QueryEs/getCarName?deviceNo=BME_14_4_W_0001&customerId=14
    @RequestMapping(value = "getCarName")
    @ResponseBody
    public  String getCarName(@RequestParam("deviceNo")String deviceNo, @RequestParam("customerId")long customerId){
        String res = esQueryCarService.getCarWashName(customerId,deviceNo);
        return  res;
    }
}
