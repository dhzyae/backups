package com.donghao.mywechat.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("friends")
public class Friends {

    @TableId(type = IdType.AUTO)
    private Integer id;//好友ID

    @TableField("nickname")
    private String nickname;//昵称

    @TableField("vx_id")
    private String VxId;//微信ID

    @TableField("remark")
    private String remark;//备注

    @TableField("tags")
    private String tags;//标签

    @TableField("slogan")
    private String slogan;//个性签名

    @TableField("privacy")
    private String privacy;//朋友权限

    @TableField("address")
    private String address;//地区

}
