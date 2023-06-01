package com.donghao.mywechat.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user")
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;//用户ID

    @TableField("phone_number")
    private String phoneNumber;//手机号

    @TableField("password")
    private String password;//密码

    @TableField("nickname")
    private String nickname;//名字

    @TableField("vx_id")
    private String VxId;//微信号

    @TableField("gender")
    private String gender;//性别

    @TableField("slogan")
    private String slogan;//个性签名

    @TableField("avatar")
    private String avatar;//头像
}
