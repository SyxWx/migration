package com.bme.syx.es.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EsDusterRealTime {

    private String deviceName;
    private String deviceNo;
    private String branchFactory;
    private String detailInfo;
    private String lastTypeName;
    private Long runStatus;
    private String address;
    private String rateAir;
    private String ratePower;
    private String productionLine;
    private String realUpdateTime;


}
