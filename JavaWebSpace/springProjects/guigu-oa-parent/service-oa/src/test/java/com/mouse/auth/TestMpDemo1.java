package com.mouse.auth;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mouse.auth.mapper.SysRoleMapper;
import com.mouse.model.system.SysRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class TestMpDemo1 {

    @Autowired
    private SysRoleMapper mapper;

    @Test
    public void getAll() {
        List<SysRole> sysRoleList = mapper.selectList(null);
        System.out.println(sysRoleList);
    }

    @Test
    public void add() {
        SysRole sysRole = new SysRole();
        sysRole.setRoleName("角色管理员1");
        sysRole.setRoleCode("role1");
        sysRole.setDescription("角色管理员1");
        int rows = mapper.insert(sysRole);
        System.out.println(rows);
        System.out.println(sysRole.getId());
    }

    @Test
    public void update() {
        SysRole sysRole = mapper.selectById(10);
        sysRole.setRoleName("atguigu角色管理员");
        int rows = mapper.updateById(sysRole);
        System.out.println(rows);
    }

    @Test
    public void deleteId() {
        int rows = mapper.deleteById(10);
    }

    @Test
    public void deleteDeleteBatchIds() {
        int result = mapper.deleteBatchIds(Arrays.asList(1, 2));
        System.out.println(result);
    }

    @Test
    public void testQuery1() {
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        wrapper.eq("role_name", "用户管理员");
        //lambda方式
        /*LambdaQueryWrapper<SysRole> wrapper2 = new LambdaQueryWrapper<>();
        wrapper2.eq(SysRole::getRoleName, "用户管理员");*/
        List<SysRole> sysRoles = mapper.selectList(wrapper);
        System.out.println(sysRoles);
    }

}
