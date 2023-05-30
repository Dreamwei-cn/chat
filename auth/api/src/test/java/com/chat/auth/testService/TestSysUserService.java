package com.chat.auth.testService;

import com.chat.auth.entity.SysPermission;
import com.chat.auth.entity.SysRole;
import com.chat.auth.entity.SysRolePermission;
import com.chat.auth.entity.SysUser;
import com.chat.auth.mapper.SysPermissionMapper;
import com.chat.auth.mapper.SysRoleMapper;
import com.chat.auth.mapper.SysRolePermissionMapper;
import com.chat.auth.mapper.SysUserRoleMapper;
import com.chat.auth.service.SysUserService;
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
        sysUser.setPassword("21232f297a57a5a743894a0e4a801fc3");
        sysUserServiceImpl.addUser(sysUser);
    }



    @Resource
    private SysPermissionMapper sysPermissionMapper;

    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    @Resource
    private SysRoleMapper sysRoleMapper;


    @Resource
    private SysRolePermissionMapper sysRolePermissionMapper;


    @Test
    public void testRoleMapper(){
        SysRole role = new SysRole();
        role.setRoleName("admin");
        role.setRoleCode("admin");
        role.setStatus(1);

        sysRoleMapper.insert(role);
    }
    @Test
    public void testPermMapper(){
        SysRolePermission rolePermission = new SysRolePermission();
        rolePermission.setRoleid(2L);
        rolePermission.setPermissionid(1L);
        sysRolePermissionMapper.insert(rolePermission);
    }

    @Test
    public void testAddPerm(){
        SysPermission perm = new SysPermission();

        sysPermissionMapper.insert(perm);
    }

    @Test
    public void testAddUserRol(){

    }




}
