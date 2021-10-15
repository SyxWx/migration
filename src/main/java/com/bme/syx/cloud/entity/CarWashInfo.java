package com.bme.syx.cloud.entity;

import littlebee.excel.ExcelField;
import lombok.Data;

@Data
public class CarWashInfo {

    @ExcelField(sort = 1, required = false)
    private String customer_id ;

    @ExcelField(sort = 2, required = false)
    private String car2 ;

    @ExcelField(sort = 3, required = false)
    private String car3 ;

    @ExcelField(sort = 4, required = false)
    private String dtu_num ;

    @ExcelField(sort = 5, required = false)
    private String dtu_name ;


    @ExcelField(sort = 6, required = false)
    private String car6 ;

    @ExcelField(sort = 7, required = false)
    private String car7 ;

    @ExcelField(sort = 8, required = false)
    private String car8 ;

    @ExcelField(sort = 9, required = false)
    private String car9 ;

    @ExcelField(sort = 10, required = false)
    private String is_water_shaking_table ;

    @ExcelField(sort = 11, required = false)
    private String is_drying_device ;

    @ExcelField(sort = 12, required = false)
    private String is_sewage_treatment ;

    @ExcelField(sort = 13, required = false)
    private String lat ;

    @ExcelField(sort = 14, required = false)
    private String lng ;

    @ExcelField(sort = 15, required = false)
    private String device_no ;

    private String status;

    private String address;

    private String import_data;
}
