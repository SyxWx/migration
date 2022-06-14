package com.bme.syx.es.entity;


import lombok.Data;

@Data
public class EsSignalRealTime {

    private String customerId;
    private String deviceNo;
    private String signalName;
    private String signalNo;
    private String categoryId;

}
