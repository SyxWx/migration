package com.bme.syx.cloud.web;

import com.bme.syx.cloud.service.CarWashService;
import com.bme.syx.cloud.service.CleanCarService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="car")
public class CarWashController {


    @Autowired
    private CarWashService carWashService;


    // http://localhost:8080/mig/car/import?customerId=8
    @RequestMapping(value="import")
    public String importSignal(String customerId){
        if(!StringUtils.isNotBlank(customerId)){
            customerId = "9999";
        }
        String  result =  carWashService.insertCarWashInfo(customerId);
        return result;
    }
}
