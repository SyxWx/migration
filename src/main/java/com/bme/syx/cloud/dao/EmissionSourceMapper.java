package com.bme.syx.cloud.dao;


import com.bme.syx.cloud.entity.EmissionSource;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmissionSourceMapper {

    void insertEmissionSource(List<EmissionSource> list);

    void  updateEmissionType(String customerId);

    void  updateEmissionFactory(String customerId);

    void  updateEmissionLine(String customerId);

    void  updateEmissionSection(String customerId);

    void  updateEmissionOrganizationW(String customerId);

    void  updateEmissionOrganizationY(String customerId);


}
