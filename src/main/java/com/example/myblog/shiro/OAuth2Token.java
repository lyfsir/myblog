package com.example.myblog.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author lyf
 * @date 2020/9/22-21:52
 */
public class OAuth2Token implements AuthenticationToken {
    private String token;
    public OAuth2Token(String token){
        this.token = token;
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
