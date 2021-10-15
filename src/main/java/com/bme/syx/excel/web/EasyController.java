package com.bme.syx.excel.web;


import com.bme.syx.excel.service.EasyService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//排放源关联设备信息
@RestController
@RequestMapping(value="eesd")
public class EasyController {

    @Autowired
    private EasyService easyService;


    //http://localhost:8080/mig/esd/import?customerId=8
    @GetMapping("import")
    public String eissionSourceImport(String customerId){

        if(!StringUtils.isNotBlank(customerId)){
            customerId = "9999";
        }
        String  result =  easyService.insertEmissionSourceDevice(customerId);
        return result;
    }
    /*

select *   from  t_emission_source_device where customer_id = 8 ;
insert into t_emission_source_device
(emission_source_no,device_name,device_no,customer_id,type_id)
select emission_source_no,device_name,device_no,customer_id,type_id
from  t_import_emission_source_device where customer_id  = 38 and import_data = '202105261713';

     **/

}
