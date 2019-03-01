package com.xmcc.House.dao;

import com.xmcc.House.pojo.Agency;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AgencyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Agency record);

    int insertSelective(Agency record);

    Agency selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Agency record);

    int updateByPrimaryKey(Agency record);
}