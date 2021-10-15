package com.bme.syx.cloud.entity;


import littlebee.excel.ExcelField;
import lombok.Data;

//排放源清单
@Data
public class EmissionSource {

    @ExcelField(sort = 1, required = false)
    private String customer_id ;

    @ExcelField(sort = 2, required = false)
    private String emission_source_no ;

    @ExcelField(sort = 3, required = false)
    private String shed_no ;

    @ExcelField(sort = 4, required = false)
    private String emission_source_type ;

    @ExcelField(sort = 5, required = false)
    private String branch_factory ;

    @ExcelField(sort = 6, required = false)
    private String production_line ;

    @ExcelField(sort = 7, required = false)
    private String processes ;

    @ExcelField(sort = 8, required = false)
    private String process_section ;

    @ExcelField(sort = 9, required = false)
    private String emission_source_name ;

    @ExcelField(sort = 10, required = false)
    private String materiel ;

    @ExcelField(sort = 11, required = false)
    private String static_production_name ;

    @ExcelField(sort = 12, required = false)
    private String static_production_info ;

    @ExcelField(sort = 13, required = false)
    private String close_detail ;

    @ExcelField(sort = 14, required = false)
    private String image_url ;

    @ExcelField(sort = 15, required = false)
    private String organization_type ;
    
    @ExcelField(sort = 16, required = false)
    private String remark ;

    @ExcelField(sort = 17, required = false)
    private String production_describe ;

    @ExcelField(sort = 18, required = false)
    private String government_describe ;

    @ExcelField(sort = 19, required = false)
    private String monitor_describe ;



    private String import_data;

}
