package com.xmcc.House.dao;


import com.xmcc.House.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> queryAll() ;

    int checkUser(User user);

    int deleteUserByEmail(@Param("email") String value);

    User selectByUserNameAndPassword(@Param("email") String username,@Param("password") String password);

    int updateByEmail(String email);

    List<User> queryUser(User user);
}