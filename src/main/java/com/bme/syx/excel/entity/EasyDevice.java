package com.bme.syx.excel.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class EasyDevice {

    @ExcelProperty(value = "设备号",index = 1)
    private String device_no ;

    @ExcelProperty(value = "排放源编码",index = 2)
    private String emission_source_no ;

    @ExcelProperty(value = "客户号",index = 3)
    private String customer_id ;


    private String import_data;
}
