package com.bme.syx.es.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface DeviceTypeMapper {

     String getDeviceType(@Param("deviceNo") String  devioceNo, @Param("customerId") String customerID);
}