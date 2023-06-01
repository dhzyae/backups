package com.mouse.service;

import com.mouse.mapper.MusicLibraryMapper;
import com.mouse.mapper.UserMapper;
import com.mouse.pojo.MusicLibrary;
import com.mouse.pojo.User;
import com.mouse.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class UserService {

    /**
     * 用户登录查询的方法
     * @param username 用户名
     * @param password 密码
     * @return
     */
    public User selectUser(String username, String password) {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.selectUser(username, password);
        sqlSession.close();
        return user;
    }

    /**
     * 用户注册插入的方法
     * @param user 传递用户名密码，userId 映射给 user 对象
     * @return
     */
    public int addUser(User user) {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int i = mapper.addUser(user);
        sqlSession.commit();
        sqlSession.close();
        return i;
    }

    /**
     * 用户更新头像的方法
     * @param userId 用户 id
     * @param avatarUrl 头像 url
     * @return
     */
    public int updateAvatar(Integer userId, String avatarUrl) {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int i = mapper.updateAvatar(userId, avatarUrl);
        sqlSession.commit();
        sqlSession.close();
        return i;
    }

}
