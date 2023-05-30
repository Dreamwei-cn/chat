package com.chat.auth.utils;

import com.chat.auth.entity.SysUser;

import com.chat.common.dto.ConstBean;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;

/**
 * @author mengw
 *  jwt token 比较器
 */
public class JwtCredentialsMatcher implements CredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        //  AuthenticationInfo info 是我们在JwtRealm中doGetAuthenticationInfo()返回的那个
        SysUser user = (SysUser) info.getPrincipals().getPrimaryPrincipal();

        String tokenStr = (String) token.getPrincipal();
        // 校验jwt有效
        return JwtUtils.verifyJwt(tokenStr, user.getUserName(), ConstBean.jwtSercrt);

    }
}
