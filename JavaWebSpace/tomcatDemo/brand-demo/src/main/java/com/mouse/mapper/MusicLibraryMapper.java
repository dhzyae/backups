package com.mouse.mapper;

import com.mouse.pojo.MusicLibrary;

import java.util.List;

public interface MusicLibraryMapper {

    /**
     * 添加歌单，歌单 id 映射到 musicLibrary 对象上
     * @param musicLibrary 用户 id， 歌单名称
     * @return
     */
    int addSongList(MusicLibrary musicLibrary);

    /**
     * 搜索用户的所有歌单
     * @param userId 用户 id
     * @return
     */
    List<MusicLibrary> searchAllSongListByUserId(Integer userId);

    /**
     * 根据歌单 id 删除歌单所有歌曲
     * @param songListId
     * @return
     */
    int deleteSongList(String songListId);
}
