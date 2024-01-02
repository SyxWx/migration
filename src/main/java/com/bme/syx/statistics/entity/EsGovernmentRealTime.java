package com.bme.syx.statistics.entity;


import lombok.Data;

@Data
public class EsGovernmentRealTime {

    private Long id;

    private Integer branchFactoryId;

    private String branchFactory;

    private String deviceNo;

    private String deviceName;

    private Long typeId;

    private String lastTypeName;

    private String rimaryTypeName;

    private Integer runStatus = 2;

    private String realUpdateTime;

}
