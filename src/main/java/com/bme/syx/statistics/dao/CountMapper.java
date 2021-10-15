package com.bme.syx.statistics.dao;


import com.bme.syx.statistics.entity.CountEntity;
import com.bme.syx.statistics.entity.Customer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CountMapper {


    List<Customer> selectAllCustomer();


    List<CountEntity> selectCountByPTypes(CountEntity count);

    List<CountEntity> selectCountSignalByPTypes(CountEntity count);


    List<CountEntity> selectCountSignalByTypesOld(CountEntity count);


    List<CountEntity> selectCountByTypes(CountEntity count);

    List<CountEntity> selectCountSignalByTypes(CountEntity count);





}
