package com.mouse.mapper;

import com.mouse.pojo.SongList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SongListMapper {

    /**
     * 添加歌曲到歌单的 Mapper
     * @param songList
     * @return
     */
    int addSong(SongList songList);

    /**
     * 搜索歌单详情的 Mapper
     * @param songListId
     * @return
     */
    List<SongList> searchListDetail(String songListId);

    /**
     * 根据歌曲 id 歌单中的歌曲
     * @param songListId
     * @param songId
     * @return
     */
    int deleteSong(@Param("songListId") String songListId, @Param("songId") String songId);

}
