package com.bme.syx.cloud.entity;

public class Export {

    private  String customerId;

    private  String  customerName;

    //1 315公有云  2 315混合云   3  315私有云
    private  String isPublic;

    //生产设备总数
    private int productDeviceCount;

    //生产设备信号
    private int productSignalCount;

    //治理设备总数
    private int dusterDeviceCount;

    //治理设备信号
    private int dusterSignalCount;

    //TSP监测仪总数
    private int tspDeviceCount;

    //空气质量微站总数
    private int pmDeviceCount;

    //VOCs检测仪
    private int vocsDeviceCount;

    //CEMS监测仪
    private int cemsDeviceCount;

    //监测设备CEMS信号总数
    private int cemsSignalCount;

    //VDM监测仪
    private int vdmDeviceCount;

    //含水率监测仪
    private int hslDeviceCount;


}
