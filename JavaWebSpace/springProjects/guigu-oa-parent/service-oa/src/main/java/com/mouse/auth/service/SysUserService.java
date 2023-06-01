package com.mouse.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mouse.model.system.SysUser;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author mouse
 * @since 2023-03-09
 */
public interface SysUserService extends IService<SysUser> {

    void updateStatus(Long id, Integer status);

    SysUser getUserByUserName(String username);
}
