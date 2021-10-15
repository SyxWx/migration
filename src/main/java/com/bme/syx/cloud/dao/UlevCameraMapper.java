package com.bme.syx.cloud.dao;

import com.bme.syx.cloud.entity.UlevCameraInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UlevCameraMapper {

    void insertUlevCamera(List<UlevCameraInfo> list);

}
