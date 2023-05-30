package com.chat.auth.service;


import com.chat.auth.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chat.auth.viewBean.LoginBean;
import com.chat.common.dto.Result;

/**
* @author Administrator
* @description 针对表【sys_user】的数据库操作Service
* @createDate 2023-05-13 16:42:18
*/
public interface SysUserService extends IService<SysUser> {

    /**
    * @Description: 新增用户
    * @Param: [user]
    * @return: com.chat.chatopenai.dto.Result
    * @Author: mengw
     * **/
    Result addUser(SysUser user);

    /**
     * @Description: 用户名查找用户
     * @param name
     * @return com.chat.chatopenai.entity.SysUser
     */
    public SysUser findByName(String name);

    /**
     * @Description: 用户名查找用户
     * @param bean
     * @return Result
     */
    Result login(LoginBean bean);

    /**
     * @Description: 退出
     */
    void logout();

    /**
     * @Description: 获取用户信息
     * @Param: [user]
     * @return: com.chat.chatopenai.dto.Result
     * @Author: mengw
     * */
    Result findUser(SysUser user);
}
