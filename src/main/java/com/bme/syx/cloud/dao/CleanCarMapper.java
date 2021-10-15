package com.bme.syx.cloud.dao;

import com.bme.syx.cloud.entity.CleanCarInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CleanCarMapper {


    void insertCleanCarList(List<CleanCarInfo> list);

}
