package com.mouse.auth.utils;

import com.mouse.model.system.SysMenu;

import java.util.ArrayList;
import java.util.List;

public class MenuHelper {
    // 使用递归方法建立菜单
    public static List<SysMenu> buildTree(List<SysMenu> sysMenuList) {
        // 创建list集合，用于最终数据
        ArrayList<SysMenu> trees = new ArrayList<>();
        // 把所有菜单数据进行遍历
        for (SysMenu sysMenu : sysMenuList) {
            // 递归入口进入
            // parentId = 0 是入口
            if (sysMenu.getParentId().longValue() == 0) {
                trees.add(getChildren(sysMenu, sysMenuList));
            }
        }
        return trees;
    }

    public static SysMenu getChildren(SysMenu sysMenu, List<SysMenu> sysMenuList) {
        sysMenu.setChildren(new ArrayList<SysMenu>());
        for (SysMenu it : sysMenuList) {
            if (sysMenu.getId().longValue() == it.getParentId().longValue()) {
                if (sysMenu.getChildren() == null) {
                    sysMenu.setChildren(new ArrayList<>());
                }
                sysMenu.getChildren().add(getChildren(it, sysMenuList));
            }
        }
        return sysMenu;
    }
}
