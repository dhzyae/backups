package com.mouse.pojo;

/**
 * 音乐库类
 */
public class MusicLibrary {
    private Integer songListId;// 歌单 id
    private Integer userId;// 用户 id
    private String songListTitle;// 歌单名称

    public Integer getSongListId() {
        return songListId;
    }

    public void setSongListId(Integer songListId) {
        this.songListId = songListId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getSongListTitle() {
        return songListTitle;
    }

    public void setSongListTitle(String songListTitle) {
        this.songListTitle = songListTitle;
    }
}
