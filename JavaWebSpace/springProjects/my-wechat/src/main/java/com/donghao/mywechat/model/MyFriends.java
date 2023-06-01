package com.donghao.mywechat.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("my_friends")
public class MyFriends {

    @TableId(type = IdType.AUTO)
    private Integer id;//通讯录ID

    @TableField("user_id")
    private Integer userId;//用户ID

    @TableField("friend_id")
    private Integer friendId;//好友ID

}
