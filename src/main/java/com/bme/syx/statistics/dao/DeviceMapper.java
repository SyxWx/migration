package com.bme.syx.statistics.dao;


import com.bme.syx.statistics.entity.Device;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeviceMapper {

    List<Device> getDeviceListAll(long customerId,long primaryId);

}
