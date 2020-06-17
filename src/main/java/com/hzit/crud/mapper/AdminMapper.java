package com.hzit.crud.mapper;

import com.hzit.crud.model.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    Admin selectByUserName(@Param("userName") String userName);

    /**
     * 获取当前用户的权限
     * @param userName
     * @return
     */
    List<String> queryUserPerms(@Param("userName") String userName);

}