package com.chat.auth.shiro;

import com.chat.auth.entity.SysUser;
import com.chat.auth.service.SysUserService;
import com.chat.auth.utils.ApplicationContextUtil;
import com.chat.auth.utils.JwtCredentialsMatcher;
import com.chat.auth.utils.JwtUtils;

import com.chat.common.dto.ConstBean;
import org.apache.logging.log4j.util.Strings;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @author mengw
 * @Description  jwtToken realm
 */
public class JwtRealm extends AuthorizingRealm {

    public JwtRealm() {
        // 用我们自定的Matcher
        this.setCredentialsMatcher(new JwtCredentialsMatcher());
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        // 取决于JwtToken的getPrincipal()
        String tokenStr = (String) token.getPrincipal();

        // 从jwt字符串中解析出username信息
        String username = JwtUtils.getClaimByKey(tokenStr, "username");
        if (!Strings.isEmpty(username)) {
            SysUserService userService = (SysUserService) ApplicationContextUtil.getBean("sysUserServiceImpl");
            // 根据token中的username去数据库核对信息，返回用户信息，并封装称SimpleAuthenticationInfo给Matcher去校验
            SysUser user = userService.findByName(username);
            // principle是身份信息，简单的可以放username，也可以将User对象作为身份信息
            // 身份信息可以在登录成功之后通过subject.getPrinciple()取出
            return new SimpleAuthenticationInfo(user, ConstBean.jwtSercrt, this.getName());
        }

        return null;
    }


}
