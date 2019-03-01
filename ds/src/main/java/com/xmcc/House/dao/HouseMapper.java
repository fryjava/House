package com.xmcc.House.dao;


import com.xmcc.House.pojo.House;
import com.xmcc.House.vo.HouseSearchVo;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HouseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(House record);

    int insertSelective(House record);

    House selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);

    List<House> queryList(HouseSearchVo houseSearchVo);

    List<House> getHotHouse();
}