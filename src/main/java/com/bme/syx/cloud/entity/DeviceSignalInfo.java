package com.bme.syx.cloud.entity;


import littlebee.excel.ExcelField;
import lombok.Data;

//设备信号关联关系信息
@Data
public class DeviceSignalInfo {

    @ExcelField(sort = 1, required = false)
    private String customer_id ;

    @ExcelField(sort = 2, required = false)
    private String device_no ;

    @ExcelField(sort = 3, required = false)
    private String signal_no ;


    private String import_data;

}
