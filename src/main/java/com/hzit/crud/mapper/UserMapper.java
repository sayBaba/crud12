package com.hzit.crud.mapper;

import com.hzit.crud.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Long uid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long uid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> queryUsers(@Param("page") Integer page , @Param("limit")Integer limit);


    int countNum();

    List<User> queryAllUsers();

}