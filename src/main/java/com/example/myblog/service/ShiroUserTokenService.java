package com.example.myblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.myblog.comment.R;
import com.example.myblog.entity.ShiroUserTokenEntity;

/**
 * @author lyf
 * @date 2020/9/25-0:49
 */
public interface ShiroUserTokenService extends IService<ShiroUserTokenEntity> {
    R createToken(long userId);

    void logout(long userId);
}
