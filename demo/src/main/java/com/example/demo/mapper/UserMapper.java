package com.example.demo.mapper;

import com.example.demo.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select  * from user where id = 1")
    User getUser();
    List<User> getUsers();

    User getUserById(int id);
}
