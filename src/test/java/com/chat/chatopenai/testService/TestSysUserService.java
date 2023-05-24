package com.chat.chatopenai.testService;

import com.chat.chatopenai.entity.SysUser;
import com.chat.chatopenai.service.SysUserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class TestSysUserService {

    @Resource
    private SysUserService sysUserServiceImpl;
    @Test
    public void testAdd(){
        SysUser sysUser = new SysUser();
        sysUser.setUserNo("admin");
        sysUser.setUserName("admin");

        sysUser.setPassword("8914de686ab28dc22f30d3d8e107ff6c");
        sysUserServiceImpl.addUser(sysUser);
    }
}
