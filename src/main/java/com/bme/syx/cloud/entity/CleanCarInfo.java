package com.bme.syx.cloud.entity;

import littlebee.excel.ExcelField;
import lombok.Data;

@Data
public class CleanCarInfo {


    @ExcelField(sort = 1, required = false)
    private String dtu_num ;

    @ExcelField(sort = 2, required = false)
    private String dtu_name ;

    @ExcelField(sort = 3, required = false)
    private String device_no ;

    @ExcelField(sort = 4, required = false)
    private String customer_id ;



    @ExcelField(sort = 5, required = false)
    private String type_id ;

    @ExcelField(sort = 6, required = false)
    private String type_name ;

    @ExcelField(sort = 7, required = false)
    private String device_name ;


    @ExcelField(sort = 8, required = false)
    private String remarks ;



    private String import_data;

}
