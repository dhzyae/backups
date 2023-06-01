package com.mouse.service;

import com.mouse.mapper.MomentMapper;
import com.mouse.pojo.Moment;
import com.mouse.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class MomentService {

    /**
     * 分享歌曲到社区的方法
     * @param moment
     * @return
     */
    public int addMoment(Moment moment) {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        MomentMapper mapper = sqlSession.getMapper(MomentMapper.class);
        int i = mapper.addMoment(moment);
        sqlSession.commit();
        sqlSession.close();
        return i;
    }

    /**
     * 查询社区中的所有分享
     * @return
     */
    public List<Moment> selectAllMoment() {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        MomentMapper mapper = sqlSession.getMapper(MomentMapper.class);
        List<Moment> moments = mapper.searchAllMoment();
        sqlSession.close();
        return moments;
    }

}
