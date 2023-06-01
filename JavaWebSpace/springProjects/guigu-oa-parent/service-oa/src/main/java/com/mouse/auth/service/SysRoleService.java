package com.mouse.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mouse.model.system.SysRole;
import com.mouse.vo.system.AssginRoleVo;

import java.util.Map;

public interface SysRoleService extends IService<SysRole> {
    Map<String, Object> findRoleDataByUserId(Long userId);

    void doAssign(AssginRoleVo assginRoleVo);
}
