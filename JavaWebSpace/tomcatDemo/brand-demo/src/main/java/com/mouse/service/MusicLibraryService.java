package com.mouse.service;

import com.mouse.mapper.MusicLibraryMapper;
import com.mouse.pojo.MusicLibrary;
import com.mouse.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class MusicLibraryService {

    /**
     * 添加歌单的方法
     * @param musicLibrary 用户 id，歌单名称
     * @return
     */
    public int addSongList(MusicLibrary musicLibrary) {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        MusicLibraryMapper mapper = sqlSession.getMapper(MusicLibraryMapper.class);
        int i = mapper.addSongList(musicLibrary);
        sqlSession.commit();
        sqlSession.close();
        return i;
    }

    /**
     * 根据用户 id 获取音乐库中的歌单
     * @param userId
     * @return
     */
    public List<MusicLibrary> searchAllSongListByUserId(Integer userId) {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        MusicLibraryMapper mapper = sqlSession.getMapper(MusicLibraryMapper.class);
        return mapper.searchAllSongListByUserId(userId);
    }

    public int deleteSongList(String songListId) {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        MusicLibraryMapper mapper = sqlSession.getMapper(MusicLibraryMapper.class);
        int i = mapper.deleteSongList(songListId);
        sqlSession.commit();
        sqlSession.close();
        return i;
    }
}
