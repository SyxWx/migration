package com.bme.syx.module.productdata.dao.tdb;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductHistoryMapper {

    List<Map<String,Object>> findProductList(Map map);
}
