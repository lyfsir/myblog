package com.example.myblog.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.myblog.entity.ShiroUserTokenEntity;
import com.example.myblog.entity.TUserEntity;
import com.example.myblog.service.ShiroUserTokenService;
import com.example.myblog.service.TUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author lyf
 * @date 2020/9/22-21:46
 */
@Component
public class OAuth2Realm extends AuthorizingRealm {

    @Autowired
    private TUserService tUserService;

    @Autowired
    private ShiroUserTokenService shiroUserTokenServer;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof OAuth2Token;
    }

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String accessToken = (String) token.getPrincipal();
        ShiroUserTokenEntity entity = shiroUserTokenServer.getBaseMapper()
                .selectOne(new QueryWrapper<ShiroUserTokenEntity>().eq("token", accessToken));
        if (entity == null || entity.getExpireTime().getTime() < System.currentTimeMillis()) {
            throw new IncorrectCredentialsException("token失效，请重新登陆");
        }

        //查询用户信息
        TUserEntity userEntity = tUserService.getBaseMapper()
                .selectOne(new QueryWrapper<TUserEntity>().eq("id", entity.getUid()));

        //账号锁定
        if (userEntity.getStatus() == 0) {
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userEntity, accessToken, getName());

        return info;
    }
}
