package com.chat.chatopenai.contoller;

import com.chat.chatopenai.dto.BaseController;
import com.chat.chatopenai.dto.Result;
import com.chat.chatopenai.dto.ResultUtls;
import com.chat.chatopenai.entity.SysUser;
import com.chat.chatopenai.service.SysUserService;
import com.chat.chatopenai.viewBean.LoginBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * @author mengw
 */
@RestController
@Slf4j

public class AuthController extends BaseController {

    @Resource
    private SysUserService sysUserServiceImpl;

    /**
     * @Deprecated 登录
     * @param bean
     * @return
     */
    @PostMapping("/auth/login")
    public Result login(@RequestBody LoginBean bean){
        log.info(bean.toString());
        return sysUserServiceImpl.login(bean);
    }



    @PostMapping("/auth/logout")
    public Result logout(){
        Subject subject = SecurityUtils.getSubject();
        SysUser user = (SysUser) subject.getPrincipal();
        log.info(user.getUserName());
        subject.logout();
        sysUserServiceImpl.logout();
        return ResultUtls.success();
    }

    @PostMapping("/auth/2step-code")
    public Result auth(@RequestBody LoginBean bean){
        log.info(bean.toString());
        return ResultUtls.data(false);
    }
}
