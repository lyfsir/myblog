package com.example.myblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.myblog.comment.R;
import com.example.myblog.dao.ShiroUserTokenDao;
import com.example.myblog.entity.ShiroUserTokenEntity;
import com.example.myblog.service.ShiroUserTokenService;
import com.example.myblog.shiro.TokenGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author lyf
 * @date 2020/9/25-0:49
 */
@Service
public class ShiroUserTokenServiceImpl extends ServiceImpl<ShiroUserTokenDao, ShiroUserTokenEntity> implements ShiroUserTokenService {
    //12小时后过期
    private final static int EXPIRE = 3600 * 12;
    @Override
    public R createToken(long userId) {
        String token = TokenGenerator.generateValue();
        Date date = new Date();
        Date expire = new Date(date.getTime() + EXPIRE * 1000);
        ShiroUserTokenEntity userTokenEntity = this.getById(userId);
        if (userTokenEntity==null){
            userTokenEntity = new ShiroUserTokenEntity();
            userTokenEntity.setUid(userId);
            userTokenEntity.setToken(token);
            userTokenEntity.setExpireTime(expire);
            userTokenEntity.setUpdateTime(date);
            this.save(userTokenEntity);
        }else {
            userTokenEntity.setUpdateTime(date);
            userTokenEntity.setExpireTime(expire);
            userTokenEntity.setToken(token);
            this.updateById(userTokenEntity);
        }
        return R.ok().put("token", token).put("expire", EXPIRE);
    }

    @Override
    public void logout(long userId) {
        String token = TokenGenerator.generateValue();
        ShiroUserTokenEntity shiroUserTokenEntity = new ShiroUserTokenEntity();
        shiroUserTokenEntity.setToken(token);
        shiroUserTokenEntity.setUid(userId);
        this.updateById(shiroUserTokenEntity);
    }
}
