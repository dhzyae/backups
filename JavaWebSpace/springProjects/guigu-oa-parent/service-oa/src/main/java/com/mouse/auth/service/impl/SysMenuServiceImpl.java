package com.mouse.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mouse.auth.mapper.SysMenuMapper;
import com.mouse.auth.service.SysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mouse.auth.service.SysRoleMenuService;
import com.mouse.auth.utils.MenuHelper;
import com.mouse.common.config.exception.GuiguException;
import com.mouse.model.system.SysMenu;
import com.mouse.model.system.SysRoleMenu;
import com.mouse.vo.system.AssginMenuVo;
import com.mouse.vo.system.MetaVo;
import com.mouse.vo.system.RouterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author mouse
 * @since 2023-05-18
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    // 菜单列表接口
    @Override
    public List<SysMenu> findNodes() {
        // 1 查询所有菜单数据
        List<SysMenu> sysMenuList = baseMapper.selectList(null);
        // 2 构建树形结构
        return MenuHelper.buildTree(sysMenuList);
    }

    // 删除菜单
    @Override
    public void removeMenuById(Long id) {
        // 判断当前菜单是否有下层菜单
        LambdaQueryWrapper<SysMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysMenu::getParentId, id);
        Integer count = baseMapper.selectCount(wrapper);
        if (count > 0) {
            throw new GuiguException(201, "菜单不能删除");
        }
        baseMapper.deleteById(id);
    }

    // 查询所有菜单和角色分配的菜单
    @Override
    public List<SysMenu> findMenuByRoleId(Long roleId) {
        // 1 查询所有的菜单 添加条件 status = 1
        LambdaQueryWrapper<SysMenu> sysMenuWrapper = new LambdaQueryWrapper<>();
        sysMenuWrapper.eq(SysMenu::getStatus, 1);
        List<SysMenu> allSysMenuList = baseMapper.selectList(sysMenuWrapper);
        // 2 根据角色id roleId 查询，角色菜单关系表里面 角色id对应所有的菜单id
        LambdaQueryWrapper<SysRoleMenu> sysRoleMenuWrapper = new LambdaQueryWrapper<>();
        sysRoleMenuWrapper.eq(SysRoleMenu::getRoleId, roleId);
        List<SysRoleMenu> sysRoleMenuList = sysRoleMenuService.list(sysRoleMenuWrapper);
        // 3 根据获取菜单id，获取对应菜单对象
        List<Long> menuIdList = sysRoleMenuList.stream()
                .map(SysRoleMenu::getMenuId)
                .collect(Collectors.toList());
        //3.1 拿着菜单id和所有菜单集合里面id进行比较，如果相同封装
        allSysMenuList.forEach(item -> {
            if (menuIdList.contains(item.getId())) {
                item.setSelect(true);
            } else {
                item.setSelect(false);
            }
        });
        // 4 返回规定格式菜单列表
        return MenuHelper.buildTree(allSysMenuList);
    }

    @Override
    public void doAssign(AssginMenuVo assginMenuVo) {
        // 1 根据角色id，删除菜单角色表，分配数据
        LambdaQueryWrapper<SysRoleMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysRoleMenu::getRoleId, assginMenuVo.getRoleId());
        sysRoleMenuService.remove(wrapper);
        // 2 从参数里面获取角色新分配菜单id列表
        // 进行遍历，把每个id数据添加菜单角色表
        List<Long> menuIdList = assginMenuVo.getMenuIdList();
        for (Long menuId : menuIdList) {
            if (StringUtils.isEmpty(menuId)) {
                continue;
            }
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setMenuId(menuId);
            sysRoleMenu.setRoleId(assginMenuVo.getRoleId());
            sysRoleMenuService.save(sysRoleMenu);
        }
    }

    // 根据用户id获取用户可以操作菜单列表
    @Override
    public List<RouterVo> findUserMenuListByUserId(Long userId) {
        List<SysMenu> sysMenuList = null;
        // 1 判断当前用户是否是管理员 userid = 1 是管理员
        // 1.1 如果是管理员，查询所有菜单列表
        if (userId.longValue() == 1) {
            // 查询所有菜单列表
            LambdaQueryWrapper<SysMenu> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(SysMenu::getStatus, 1);
            wrapper.orderByAsc(SysMenu::getSortValue);
            sysMenuList = baseMapper.selectList(wrapper);
        } else {
            // 1.2 如果不是管理员，根据userId查询可以操作菜单列表
            // 多表关联查询：用户角色关系表、角色菜单关系表、菜单表
            sysMenuList = baseMapper.findMenuListByUserId(userId);
        }
        // 2 把查询出来数据列表-构建成框架要求的路由结构
        // 使用菜单操作工具类构建树形结构
        List<SysMenu> sysMenuTreeList = MenuHelper.buildTree(sysMenuList);
        List<RouterVo> routerList = this.buildRouter(sysMenuTreeList);

        return routerList;
    }

    // 构建成框架要求的路由结构
    private List<RouterVo> buildRouter(List<SysMenu> menus) {
        // 创建list集合，存储最终数据
        ArrayList<RouterVo> routerVos = new ArrayList<>();
        // 遍历menus
        for (SysMenu menu : menus) {
            RouterVo router = new RouterVo();
            router.setHidden(false);
            router.setAlwaysShow(false);
            router.setPath(getRouterPath(menu));
            router.setComponent(menu.getComponent());
            router.setMeta(new MetaVo(menu.getName(), menu.getIcon()));
            // 下一层数据部分
            List<SysMenu> children = menu.getChildren();
            if (menu.getType().intValue() == 1) {
                // 加载出来下面隐藏路由
                List<SysMenu> hiddenMenuList = children.stream()
                        .filter(item -> !StringUtils.isEmpty(item.getComponent()))
                        .collect(Collectors.toList());
                for (SysMenu hiddenMenu : hiddenMenuList) {
                    RouterVo hiddenRouter = new RouterVo();
                    hiddenRouter.setHidden(true);
                    hiddenRouter.setAlwaysShow(false);
                    hiddenRouter.setPath(getRouterPath(hiddenMenu));
                    hiddenRouter.setComponent(hiddenMenu.getComponent());
                    hiddenRouter.setMeta(new MetaVo(hiddenMenu.getName(), hiddenMenu.getIcon()));
                    routerVos.add(hiddenRouter);
                }
            } else {
                if (!CollectionUtils.isEmpty(children)) {
                    if (children.size() > 0) {
                        router.setAlwaysShow(true);
                    }
                    // 递归操作
                    router.setChildren(buildRouter(children));
                }
            }
            routerVos.add(router);
        }
        return routerVos;
    }

    /**
     * 获取路由地址
     *
     * @param menu 菜单信息
     * @return 路由地址
     */
    public String getRouterPath(SysMenu menu) {
        String routerPath = "/" + menu.getPath();
        if(menu.getParentId().intValue() != 0) {
            routerPath = menu.getPath();
        }
        return routerPath;
    }

    @Override
    public List<String> findUserPermsByUserId(Long userId) {
        // 1 判断是否是管理员，如果是管理员，查询所有按钮列表
        List<SysMenu> sysMenuList = null;
        if (userId.longValue() == 1) {
            LambdaQueryWrapper<SysMenu> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(SysMenu::getStatus, 1);
            sysMenuList = baseMapper.selectList(wrapper);
        } else {
            // 2 如果不是管理员，根据userId查询可以操作按钮列表
            // 多表关联查询：用户角色关系表、角色菜单关系、菜单表
            sysMenuList = baseMapper.findMenuListByUserId(userId);
        }
        // 3 从查询出来的数据里面，获取可以操作按钮值的list集合，返回
        return sysMenuList.stream()
                .filter(item -> item.getType() == 2)
                .map(SysMenu::getPerms)
                .collect(Collectors.toList());
    }
}
