package com.bme.syx.cloud.web;


import com.bme.syx.cloud.service.DeviceInfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//设备表
@RestController
@RequestMapping(value = "device")
public class DeviceInfoController {


    @Autowired
    private DeviceInfoService deviceInfoService;


    //http://localhost:8080/mig/device/import?customerId=8
    @RequestMapping(value="import")
    public String importDeviceInfo(String customerId){
        if(!StringUtils.isNotBlank(customerId)){
            customerId = "9999";
        }
        String  result =  deviceInfoService.insertDeviceInfo(customerId);
        return result;
    }



    
    /*
    *

 -- 把t_device_import 数据维护到 t_device
select *    from  t_device where customer_id  = 8 ;

insert into t_device
(device_no,device_name,device_alias_name,customer_id,branch_factory_id,branch_factory,
production_line_id,production_line,processes,process_section,longitude,latitude,primary_type_id,
primary_type_name,type_id,last_type_name,detail_info,is_enable,remark,gb_tdc,gb_so2,gb_nox)
select
device_no,device_name,device_alias_name,customer_id,branch_factory_id,branch_factory,
production_line_id,production_line,processes,process_section,longitude,latitude,primary_type_id,
primary_type_name,type_id,last_type_name,detail_info,is_enable,remark,gb_tdc,gb_so2,gb_nox
from t_import_device where customer_id  = 38  and import_data = '202105261709';

     **/
}
