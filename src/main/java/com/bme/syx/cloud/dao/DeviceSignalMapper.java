package com.bme.syx.cloud.dao;

import com.bme.syx.cloud.entity.DeviceSignalInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeviceSignalMapper {

    void insertDeviceSignal(List<DeviceSignalInfo> list);
}
