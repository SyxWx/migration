package com.bme.syx.cloud.entity;

import littlebee.excel.ExcelField;
import lombok.Data;

//信号信息
@Data
public class SignalInfo {

    //客户ID
    @ExcelField(sort = 1, required = false)
    private String customer_id ;

    //信号ID
    @ExcelField(sort = 2, required = false)
    private String signal_no ;

    //信号名称
    @ExcelField(sort = 3, required = false)
    private String signal_name ;

    //信号编码
    @ExcelField(sort = 4, required = false)
    private String category_id ;

    //单位
    @ExcelField(sort = 5, required = false)
    private String unit ;

    //取值频率
    @ExcelField(sort = 6, required = false)
    private String period ;

    //备注
    @ExcelField(sort = 7, required = false)
    private String remark ;

    //OPC_nodeid
    @ExcelField(sort = 8, required = false)
    private String node_id ;

    //上限
    @ExcelField(sort = 9, required = false)
    private String upper_limit ;

    //下限
    @ExcelField(sort = 10, required = false)
    private String lower_limit ;

    //设备编码
    @ExcelField(sort = 11, required = false)
    private String device_no ;

    //停止阈值
    @ExcelField(sort = 12, required = false)
    private String open_range ;

    private String import_data;
}
