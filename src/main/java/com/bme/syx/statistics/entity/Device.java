package com.bme.syx.statistics.entity;

import lombok.Data;

@Data
public class Device {

    private Long id;

    /**
     * 客户名称
     */
    private Long customerId;

    /**
     * 设备sn编号
     */
    private String deviceNo;

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 分厂/所属区域
     */
    private String branchFactory;

    /**
     * 生产工艺线
     */
    private String productionLine;

    /**
     * 经度
     */
    private Double longitude;

    /**
     * 纬度
     */
    private Double latitude;

    /**
     * 一级设备类型名称
     */
    private String primaryTypeName;

    /**
     * 设备类型id
     */
    private Long typeId;


    /**
     * 设备类型名称
     */
    private String lastTypeName;

    /**
     * 设备类型名称
     */
    private String detailInfo;

    /**
     * 是否开启
     */
    private Integer isEnable;

    /**
     * 备注
     */
    private String remark;

}
