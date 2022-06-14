package com.bme.syx.es.entity;

import lombok.Data;

@Data
public class EsProductRealTime {

    private String id;
    private String deviceNo;
    private String customerId;
    private String deviceName;
    private String branchFactoryId;
    private String branchFactory;
    private String isEnable;
    private String primaryTypeId;
    private String primaryTypeName;
    private String typeId;
    private String lastTypeName;
    private String productionLine;
    private String runStatus;
    private String dataStatus;
    private String updateTime;

}
