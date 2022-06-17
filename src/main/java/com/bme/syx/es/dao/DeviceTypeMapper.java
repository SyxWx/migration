package com.bme.syx.es.dao;

import com.bme.syx.es.entity.SignalData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


@Mapper
public interface DeviceTypeMapper {

     String getDeviceType(@Param("deviceNo") String  devioceNo, @Param("customerId") String customerID);


     List<SignalData> getSignalList(Map<String,String> paramMap);

}