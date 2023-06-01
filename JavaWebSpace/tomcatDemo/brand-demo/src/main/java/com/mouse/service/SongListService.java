package com.mouse.service;

import com.mouse.mapper.SongListMapper;
import com.mouse.pojo.SongList;
import com.mouse.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class SongListService {

    /**
     * 添加歌曲到歌单
     * @param songList
     * @return
     */
    public int addSong(SongList songList) {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SongListMapper mapper = sqlSession.getMapper(SongListMapper.class);
        int i = mapper.addSong(songList);
        sqlSession.commit();
        sqlSession.close();
        return i;
    }

    /**
     * 根据歌单 id 搜索歌单详情
     * @param songListId 歌单 id
     * @return
     */
    public List<SongList> searchListDetail(String songListId) {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SongListMapper mapper = sqlSession.getMapper(SongListMapper.class);
        List<SongList> songLists = mapper.searchListDetail(songListId);
        sqlSession.close();
        return songLists;
    }

    /**
     * 根据歌单 id 和歌曲 id 删除歌单中的歌曲
     * @param songListId
     * @param songId
     * @return
     */
    public int deleteSong(String songListId, String songId) {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SongListMapper mapper = sqlSession.getMapper(SongListMapper.class);
        int i = mapper.deleteSong(songListId, songId);
        sqlSession.commit();
        sqlSession.close();
        return i;
    }

}
