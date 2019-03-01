package com.xmcc.House.dao;


import com.xmcc.House.pojo.Community;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommunityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Community record);

    int insertSelective(Community record);

    Community selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Community record);

    int updateByPrimaryKey(Community record);
}