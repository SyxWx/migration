package com.bme.syx.cloud.dao;

import com.bme.syx.cloud.entity.SignalInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SignalMapper {

    void insertSignal (List<SignalInfo> list);

    void  updateSignalPeriod30(String customerId);

    void  updateSignalPeriod60(String customerId);

    void  updateSignalPeriod300(String customerId);

    void  updateSignalStandard(String customerId);


}
