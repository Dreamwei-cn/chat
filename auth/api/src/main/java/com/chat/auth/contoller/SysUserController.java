package com.chat.auth.contoller;


import com.chat.auth.entity.SysUser;
import com.chat.auth.service.SysUserService;
import com.chat.common.dto.Result;
import com.chat.common.dto.ResultUtls;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author mengw
 *  for sysuser
 */

@RestController
@RequestMapping("/user")
public class SysUserController extends BaseController {

    @Resource
    private SysUserService sysUserServiceImpl;

    @GetMapping("/{id}")
    public Result queryById(@PathVariable("id") String id){
        return ResultUtls.success();
    }


    @PostMapping("/add")
    public Result addUser(@RequestBody SysUser user) {
        return sysUserServiceImpl.addUser(user);
    }



    @GetMapping("/info")
    public Result info(){
        return sysUserServiceImpl.findUser(getUser());
    }

}
