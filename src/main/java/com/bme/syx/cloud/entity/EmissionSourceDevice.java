package com.bme.syx.cloud.entity;

import littlebee.excel.ExcelField;
import lombok.Data;

@Data
public class EmissionSourceDevice {

    @ExcelField(sort = 1, required = false)
    private String device_no ;

    @ExcelField(sort = 2, required = false)
    private String emission_source_no ;



    @ExcelField(sort = 3, required = false)
    private String customer_id ;


    private String import_data;
}
