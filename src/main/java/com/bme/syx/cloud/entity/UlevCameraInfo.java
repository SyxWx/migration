package com.bme.syx.cloud.entity;


import littlebee.excel.ExcelField;
import lombok.Data;

@Data
public class UlevCameraInfo {

    @ExcelField(sort = 1, required = false)
    private String seq ;

    @ExcelField(sort = 2, required = false)
    private String area_name ;

    @ExcelField(sort = 3, required = false)
    private String camera_name ;

    @ExcelField(sort = 4, required = false)
    private String classify_name ;

    @ExcelField(sort = 5, required = false)
    private String model ;

    @ExcelField(sort = 6, required = false)
    private String ip_address ;

    @ExcelField(sort = 7, required = false)
    private String nvr_ip ;

    @ExcelField(sort = 8, required = false)
    private String channel_id ;

    @ExcelField(sort = 9, required = false)
    private String nvr_username ;

    @ExcelField(sort = 10, required = false)
    private String nvr_password ;


    private String import_data;

    private String status;

    private String customer_id;

    private String type;

}
