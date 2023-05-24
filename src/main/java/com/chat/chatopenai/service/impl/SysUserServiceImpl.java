package com.chat.chatopenai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chat.chatopenai.dto.ConstBean;
import com.chat.chatopenai.dto.Result;
import com.chat.chatopenai.dto.ResultUtls;
import com.chat.chatopenai.entity.SysUser;
import com.chat.chatopenai.service.SysUserService;
import com.chat.chatopenai.mapper.SysUserMapper;
import com.chat.chatopenai.utils.JwtUtils;
import com.chat.chatopenai.viewBean.LoginBean;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
* @author Administrator
* @description 针对表【sys_user】的数据库操作Service实现
* @createDate 2023-05-13 16:42:18
*/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
    implements SysUserService{

    @Override
    public Result addUser(SysUser user) {
        Md5Hash MD5 = new Md5Hash(user.getPassword(), ConstBean.salt,1024);
        user.setPassword(MD5.toHex());
        getBaseMapper().insert(user);
        return ResultUtls.success();
    }

    @Override
    public SysUser findByName(String name) {
        QueryWrapper<SysUser> query = new QueryWrapper<>();
        query.eq("user_name", name);
        return getBaseMapper().selectOne(query);
    }

    @Override
    public Result login(LoginBean bean) {
        Subject subject = SecurityUtils.getSubject();

        try {
            // 执行登录，如果登录失败会直接抛出异常，并进入对应的catch
            subject.login(new UsernamePasswordToken(bean.getUsername(), bean.getPassword()));

            // 获取主体的身份信息
            // 实际上是User。为什么？
            // 取决于LoginRealm中的doGetAuthenticationInfo()方法中SimpleAuthenticationInfo构造函数的第一个参数
            SysUser user = (SysUser) subject.getPrincipal();

            // 生成jwt
            String jwt = JwtUtils.generateJwt(user.getUserName(), ConstBean.jwtSercrt);

            // 将jwt放入到响应头中
            Map<String, String> tokenMap = new HashMap<>();
            tokenMap.put("token", jwt);
            return ResultUtls.data(tokenMap);
        } catch (UnknownAccountException e) {
            // username 错误
            e.printStackTrace();
            return ResultUtls.fail("账号或者密码错误");
        } catch (IncorrectCredentialsException e) {
            // password 错误
            e.printStackTrace();
            return ResultUtls.fail("账号或者密码错误");
        }


    }

    @Override
    public void logout() {

    }

    @Override
    public Result findUser(SysUser user) {
        return null;
    }
}




