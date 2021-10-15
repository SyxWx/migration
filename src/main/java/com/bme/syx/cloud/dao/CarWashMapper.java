package com.bme.syx.cloud.dao;

import com.bme.syx.cloud.entity.CarWashInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CarWashMapper {

    void insertCarWashList(List<CarWashInfo> list);

}
