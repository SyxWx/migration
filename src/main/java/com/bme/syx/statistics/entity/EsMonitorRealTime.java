package com.bme.syx.statistics.entity;

import lombok.Data;

@Data
public class EsMonitorRealTime {

    Long id;
    String deviceNo;
    Long customerId;
    String deviceName;
    Long branchFactoryId;
    String branchFactory;
    Long primaryTypeId;
    String primaryTypeName;
    Long typeId;
    String lastTypeName;
    String productionLine;
    String processes;
    String processSection;
    Double latitude;
    Double longitude;
    Integer runStatus;
    Double tspRealTime;
    Double pm2_5RealTime;
    Double pm10RealTime;
    Double cemsRealTime;
    Double vdmRealtime;
    String realTimeUpdateTime;
}
