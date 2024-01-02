package com.bme.syx.statistics.entity;

import lombok.Data;

@Data
public class EsProductRealTime {

    private Long id;

    private Integer branchFactoryId;

    private String branchFactory;

    private String deviceNo;

    private String deviceName;

    private Long typeId;

    private String lastTypeName;

    private String rimaryTypeName;

    private Integer runStatus;

    private String updateTime;

}
