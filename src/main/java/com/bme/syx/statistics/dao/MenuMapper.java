package com.bme.syx.statistics.dao;

import com.bme.syx.statistics.entity.MenuEnitty;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface MenuMapper {


    List<MenuEnitty> queryMenuByPramas(MenuEnitty enitty);

}
