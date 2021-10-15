package com.bme.syx.cloud.web;

import com.bme.syx.cloud.service.CleanCarService;
import com.bme.syx.cloud.service.UlevCameraService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value="clean")
public class CleanCarController {

    @Autowired
    private CleanCarService cleanCarService;


    // http://localhost:8080/mig/clean/import?customerId=8
    @RequestMapping(value="import")
    public String importSignal(String customerId){
        if(!StringUtils.isNotBlank(customerId)){
            customerId = "9999";
        }
        String  result =  cleanCarService.insertCleanCarInfo(customerId);
        return result;
    }

}
