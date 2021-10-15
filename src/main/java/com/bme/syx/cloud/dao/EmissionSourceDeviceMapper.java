package com.bme.syx.cloud.dao;

import com.bme.syx.cloud.entity.EmissionSourceDevice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmissionSourceDeviceMapper {


    void insertEmissionSourceDevice(List<EmissionSourceDevice> list);

    void  updateEmissionDeviceName(String customerId);

    void  updateEmissionDeviceType(String customerId);
}
