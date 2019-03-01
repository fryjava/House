package com.xmcc.House.dao;


import com.xmcc.House.pojo.House_msg;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface House_msgMapper {
    int deleteByPrimaryKey(Long id);

    int insert(House_msg record);

    int insertSelective(House_msg record);

    House_msg selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(House_msg record);

    int updateByPrimaryKey(House_msg record);
}