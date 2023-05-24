package com.chat.chatopenai.contoller;

import com.chat.chatopenai.dto.BaseController;
import com.chat.chatopenai.dto.Result;
import com.chat.chatopenai.dto.ResultUtls;
import com.chat.chatopenai.entity.SysUser;
import com.chat.chatopenai.service.SysUserService;
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
