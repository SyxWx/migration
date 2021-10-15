package com.bme.syx.cloud.dao;

import com.bme.syx.cloud.entity.DeviceInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeviceMapper {

    void insertDevice(List<DeviceInfo> list);

    void  updateDeviceFactory(String customerId);

    void  updateDeviceLine(String customerId);

    void  updateDevicePType(String customerId);

    void  updateDeviceType(String customerId);




    void  updateDeviceGBTdc(String customerId);

    void  updateDeviceGBSo2(String customerId);

    void  updateDeviceGBNox(String customerId);

    void  updateDeviceLongitudeNull(String customerId);

    void  updateDeviceLatitudeNull(String customerId);

}
