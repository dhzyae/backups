package com.mouse.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mouse.model.system.SysMenu;
import com.mouse.vo.system.AssginMenuVo;
import com.mouse.vo.system.RouterVo;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author mouse
 * @since 2023-05-18
 */
public interface SysMenuService extends IService<SysMenu> {

    List<SysMenu> findNodes();

    void removeMenuById(Long id);

    List<SysMenu> findMenuByRoleId(Long roleId);

    void doAssign(AssginMenuVo assginMenuVo);

    List<RouterVo> findUserMenuListByUserId(Long userId);

    List<String> findUserPermsByUserId(Long userId);
}
