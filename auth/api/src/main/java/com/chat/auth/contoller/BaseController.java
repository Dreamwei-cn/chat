package com.chat.auth.contoller;

import com.chat.auth.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * @author mengw
 * @Description 基础controller
 */
public class BaseController {

    public SysUser getUser() {
        Subject subject = SecurityUtils.getSubject();
        SysUser user = (SysUser) subject.getPrincipal();
        return user;
    }
}
