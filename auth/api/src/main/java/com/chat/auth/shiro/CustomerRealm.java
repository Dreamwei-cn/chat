package com.chat.auth.shiro;

import com.chat.auth.entity.SysUser;
import com.chat.auth.service.SysUserService;
import com.chat.auth.utils.ApplicationContextUtil;

import com.chat.common.dto.ConstBean;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.ObjectUtils;



/**
 *  @author mengw
 *
 */

public class CustomerRealm extends AuthorizingRealm {


    /**
     * 或者在ShiroConfig中设置
     */
    public CustomerRealm() {
        // 匹配器。需要与密码加密规则一致
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 设置匹配器的加密算法
        hashedCredentialsMatcher.setHashAlgorithmName(Md5Hash.ALGORITHM_NAME);
        // 设置匹配器的哈希散列次数
        hashedCredentialsMatcher.setHashIterations(1024);
        // 将对应的匹配器设置到Realm中
        this.setCredentialsMatcher(hashedCredentialsMatcher);
    }

    /**
     * 可以往Shiro中注册多种Realm。某种Token对象需要对应的Realm来处理。
     * 复写该方法表示该方法支持处理哪一种Token
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }


    /**
     * @Description 身份验证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String principal = (String) authenticationToken.getPrincipal();
        SysUserService sysUserServiceImpl = (SysUserService) ApplicationContextUtil.getBean("sysUserServiceImpl");
        SysUser user = sysUserServiceImpl.findByName(principal);
        if (!ObjectUtils.isEmpty(user)) {
            return new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(ConstBean.salt), this.getName());
        }
        return null;

    }
}
