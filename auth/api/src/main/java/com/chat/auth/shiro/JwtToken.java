package com.chat.auth.shiro;

import org.apache.shiro.authc.HostAuthenticationToken;

/**
 * @author mengw
 *
 */
public class JwtToken implements HostAuthenticationToken {

    /**
     *
     */
    private String token;

    private String host;

    public JwtToken(String token) {
        this(token, null);
    }

    public JwtToken(String token, String host) {
        this.token = token;
        this.host = host;
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
