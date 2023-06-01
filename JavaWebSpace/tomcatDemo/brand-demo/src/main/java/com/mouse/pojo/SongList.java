package com.mouse.pojo;

/**
 * 歌单详情类
 */
public class SongList {
    private Integer songListId;// 歌单 id
    private Integer userId;// 用户 id
    private String songId;// 歌曲id（网易云接口的音乐 id）
    private String songTitle;// 歌曲名称
    private String songAuthor;// 歌曲作者
    private String songImgUrl;// 歌曲封面

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

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getSongAuthor() {
        return songAuthor;
    }

    public void setSongAuthor(String songAuthor) {
        this.songAuthor = songAuthor;
    }

    public String getSongImgUrl() {
        return songImgUrl;
    }

    public void setSongImgUrl(String songImgUrl) {
        this.songImgUrl = songImgUrl;
    }
}
