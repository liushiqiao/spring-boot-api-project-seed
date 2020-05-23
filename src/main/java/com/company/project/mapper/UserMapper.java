package com.company.project.mapper;

import com.company.project.core.Mapper;
import com.company.project.entry.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@org.apache.ibatis.annotations.Mapper
public interface UserMapper extends Mapper<User> {

    @Select("select * from user_account where name=#{name} and password=#{password}")
    User queryUser(@Param("name") String username, @Param("password") String password);
}