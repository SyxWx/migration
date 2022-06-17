package com.bme.syx.es.entity;


import lombok.Data;

@Data
public class SignalData {

    private String device_no;
    private String signal_no;
    private String signal_name;
    private long customer_id;
    private String unit;
    private int category_id;

}
