package com.example.myblog.like.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.myblog.like.entity.UserLikeEntity;

/**
 * @author lyf
 * @date 2020/9/27-1:12
 */
public interface UserLikeService extends IService<UserLikeEntity> {

    /**
     * 将Redis里的点赞数据存入数据库中
     */
    void transLikedFromRedis2DB();

    /**
     * 将Redis中的点赞数量数据存入数据库
     */
    void transLikedCountFromRedis2DB();
}
