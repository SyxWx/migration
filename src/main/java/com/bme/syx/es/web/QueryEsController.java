package com.bme.syx.es.web;

import com.bme.syx.es.entity.EsMonitorRealTime;
import com.bme.syx.es.service.TestEsQuery;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "QueryEs/")
public class QueryEsController {


    @Autowired
    private TestEsQuery testEsQuery;


    //http://localhost:8080/mig/QueryEs/queryEs
    @RequestMapping(value = "queryEs")
    public  String index(){
       return "queryEs";
    }

    //http://localhost:8080/mig/QueryEs/getMon
    //http://localhost:8080/mig/QueryEs/getMon?deviceNo=BMEVDM0A210521012&customerId=42
    @RequestMapping(value = "getMon")
    @ResponseBody
    public  String getMonitorReal(@RequestParam("deviceNo")String deviceNo, @RequestParam("customerId")long customerId){

        List<EsMonitorRealTime> list = new ArrayList<EsMonitorRealTime>();
        list = testEsQuery.queryEs(deviceNo,customerId);
        String res = "";
        if(list!=null && list.size()>0){
            EsMonitorRealTime esMonitorRealTime = list.get(0);
            res = JSONObject.fromObject(list.get(0)).toString();
        }
        return  res;
    }
}
