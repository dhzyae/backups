package com.mouse.mapper;

import com.mouse.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    /**
     * 用户登录查询的 Mapper
     * @param username
     * @param password
     * @return
     */
    User selectUser(@Param("username") String username, @Param("password") String password);

//    int addUser(@Param("username") String username, @Param("password") String password);

    /**
     * 用户注册插入的 Mapper
     * @param user
     * @return
     */
    int addUser(User user);

    /**
     * 用户更新头像的更新Mapper
     * @param userId 用户 id
     * @param avatarUrl 头像的 url
     * @return
     */
    int updateAvatar(@Param("userId") Integer userId, @Param("avatarUrl") String avatarUrl);
}
