package com.bme.syx.cloud.web;

import com.bme.syx.cloud.service.SignalService;
import com.bme.syx.cloud.service.UlevCameraService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="camera")
public class UlevCameraController {

    @Autowired
    private UlevCameraService ulevCameraService;


    //http://localhost:8080/mig/camera/import?customerId=8
    @RequestMapping(value="import")
    public String importSignal(String customerId){
        if(!StringUtils.isNotBlank(customerId)){
            customerId = "9999";
        }
        String  result =  ulevCameraService.insertCamera(customerId);
        return result;
    }


    /*
    *

select * from t_ulev_camera where customer_id = 38 ;

select * from t_import_ulev_camera where customer_id = 38  order by  classify_name;

select * from t_ulev_camera where customer_id = 38 ;

select * from t_import_ulev_camera where customer_id = 38  order by  classify_name;


insert into t_ulev_camera
(area_name,camera_name,channel_id,ip_address,classify_id,classify_name,
customer_id,seq,status,nvr_ip,nvr_username,nvr_password,model,type,camera_type)
select area_name,camera_name,channel_id,ip_address,
classify_id,classify_name,customer_id,seq,
status,nvr_ip,nvr_username,nvr_password,model,
type,camera_type
from  t_import_ulev_camera   where customer_id = 38 and import_data = '202105261718';



*
*
*
*
     **/

}
