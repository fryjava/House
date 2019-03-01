package com.xmcc.House.dao;


import com.xmcc.House.pojo.Agent_msg;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Agent_msgMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Agent_msg record);

    int insertSelective(Agent_msg record);

    Agent_msg selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Agent_msg record);

    int updateByPrimaryKey(Agent_msg record);
}