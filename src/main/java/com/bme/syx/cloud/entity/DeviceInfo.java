package com.bme.syx.cloud.entity;

import littlebee.excel.ExcelField;
import lombok.Data;


// 设备信息
@Data
public class DeviceInfo {
    @ExcelField(sort = 1, required = false)
    private String customer_id ;

    @ExcelField(sort = 2, required = false)
    private String branch_factory ;

    @ExcelField(sort = 3, required = false)
    private String production_line ;

    @ExcelField(sort = 4, required = false)
    private String device_no ;

    @ExcelField(sort = 5, required = false)
    private String device_name ;

    @ExcelField(sort = 6, required = false)
    private String primary_type_name ;

    @ExcelField(sort = 7, required = false)
    private String last_type_name ;

    @ExcelField(sort = 8, required = false)
    private String remark ;

    @ExcelField(sort = 9, required = false)
    private String detail_info ;

    @ExcelField(sort = 10, required = false)
    private String device_alias_name ;

    @ExcelField(sort = 11, required = false)
    private double longitude ;

    @ExcelField(sort = 12, required = false)
    private double latitude ;

    private String import_data;
}
