package com.mouse.pojo;

/**
 * 用户类
 */
public class User {
    private Integer userId;// 用户id
    private String username;// 用户名
    private String password;// 密码
    private String musicLibraryId;// 音乐库 id（用来存储歌单列表）
    private String avatarUrl;// 头像 url（新注册用户的时候不可设置，MySQL 会设置一个默认头像 url）

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMusicLibraryId() {
        return musicLibraryId;
    }

    public void setMusicLibraryId(String musicLibraryId) {
        this.musicLibraryId = musicLibraryId;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
