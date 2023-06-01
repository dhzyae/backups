package com.mouse.auth;

import com.mouse.auth.service.SysRoleService;
import com.mouse.model.system.SysRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestMpDemo2 {

    @Autowired
    private SysRoleService service;

    @Test
    public void getAll() {
        List<SysRole> list = service.list();
        System.out.println(list);
    }

}
