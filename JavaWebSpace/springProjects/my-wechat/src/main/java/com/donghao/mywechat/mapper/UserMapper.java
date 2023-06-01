package com.donghao.mywechat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.donghao.mywechat.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {

}
