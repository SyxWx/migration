package com.bme.syx.password;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InitAdminPasswordMapper {


    List<AdminPassword> queryPassword();

    void updatePassword(AdminPassword adminPassword);

}
