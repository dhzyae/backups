package com.mouse.springbootmybatis.mapper;

import com.mouse.springbootmybatis.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM t_user")
    List<User> findAll();

}
