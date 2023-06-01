package com.donghao.mywechat.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("address")
public class Address {
    @TableId(type = IdType.AUTO)
    private Integer id;//地址ID

    @TableField("contacts")
    private String contacts;//联系人

    @TableField("phone_number")
    private String phoneNumber;//手机号码

    @TableField("province")
    private String province;//省份

    @TableField("city")
    private String city;//城市

    @TableField("area")
    private String area;//地区

    @TableField("address_detail")
    private String addressDetail;//详细地址

    @TableField("postal_code")
    private String postalCode;//邮政编码

}
