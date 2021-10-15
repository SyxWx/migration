package com.bme.syx.cloud.web;

import com.bme.syx.cloud.service.DeviceSignalService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//排放源关联设备信息
@RestController
@RequestMapping(value="deviceSignal")
public class DeviceSignalController {


    @Autowired
    private DeviceSignalService deviceSignalService;


    //http://localhost:8080/mig/deviceSignal/import?customerId=8
    @GetMapping("import")
    public String eissionSourceImport(String customerId){

        if(!StringUtils.isNotBlank(customerId)){
            customerId = "9999";
        }
        String  result =  deviceSignalService.insertDeviceSignal(customerId);
        return result;
    }

    /*
    *

select *    from  t_device_signal   where customer_id = 8;

insert into t_device_signal
(device_no,signal_no,customer_id)
select device_no,signal_no,customer_id
from  t_import_device_signal   where customer_id = 8 and import_data = '202105251752';


     **/
}
