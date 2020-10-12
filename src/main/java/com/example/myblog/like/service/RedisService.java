package com.example.myblog.like.service;

import com.example.myblog.like.dto.LikedCountDTO;
import com.example.myblog.like.entity.UserLikeEntity;

import java.util.List;

/**
 * @author lyf
 * @date 2020/9/27-0:46
 */
public interface RedisService {
    void saveLiked2Redis(String likedBlogId, String likedPostId);
    void unlikeFromRedis(String likedBlogId, String likedPostId);
    void deleteLikedFromRedis(String likedBlogId, String likedPostId);
    void incrementLikedCount(String likedBlogId);
    void decrementLikedCount(String likedBlogId);
    List<UserLikeEntity> getLikedDataFromRedis();
    List<LikedCountDTO> getLikedCountFromRedis();

    /**
     * 检查redis有没有此用户对此博客点赞，没有则查数据库，有这查status
     */
    boolean checkLikeStatus(String likedBlogId,String likedPostId);
}
