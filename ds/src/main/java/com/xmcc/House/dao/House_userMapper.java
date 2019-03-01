package com.xmcc.House.dao;


import com.xmcc.House.pojo.House_user;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface House_userMapper {
    int deleteByPrimaryKey(Long id);

    int insert(House_user record);

    int insertSelective(House_user record);

    House_user selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(House_user record);

    int updateByPrimaryKey(House_user record);
}