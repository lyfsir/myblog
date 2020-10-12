package com.example.myblog.like.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.myblog.like.dto.LikedCountDTO;
import com.example.myblog.like.entity.UserLikeEntity;
import com.example.myblog.like.service.RedisService;
import com.example.myblog.like.service.UserLikeService;
import com.example.myblog.like.utils.LikedStatusEnum;
import com.example.myblog.like.utils.RedisKeyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author lyf
 * @date 2020/9/27-1:00
 */
@Service
public class RedisServiceIml implements RedisService {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    UserLikeService userLikeService;

    @Override
    public void saveLiked2Redis(String likedBlogId, String likedPostId) {
        String key = RedisKeyUtils.getLikedKey(likedBlogId, likedPostId);
        redisTemplate.opsForHash().put(RedisKeyUtils.MAP_KEY_USER_LIKED, key, LikedStatusEnum.LIKE.getCode());
    }

    @Override
    public void unlikeFromRedis(String likedBlogId, String likedPostId) {
        String key = RedisKeyUtils.getLikedKey(likedBlogId, likedPostId);
        redisTemplate.opsForHash().put(RedisKeyUtils.MAP_KEY_USER_LIKED, key, LikedStatusEnum.UNLIKE.getCode());
    }

    @Override
    public void deleteLikedFromRedis(String likedBlogId, String likedPostId) {
        String key = RedisKeyUtils.getLikedKey(likedBlogId, likedPostId);
        redisTemplate.opsForHash().delete(RedisKeyUtils.MAP_KEY_USER_LIKED, key);
    }

    @Override
    public void incrementLikedCount(String likedBlogId) {
        redisTemplate.opsForHash().increment(RedisKeyUtils.MAP_KEY_USER_LIKED_COUNT, likedBlogId, 1);
    }

    @Override
    public void decrementLikedCount(String likedBlogId) {

        redisTemplate.opsForHash().increment(RedisKeyUtils.MAP_KEY_USER_LIKED_COUNT, likedBlogId, -1);
    }

    @Override
    public List<UserLikeEntity> getLikedDataFromRedis() {
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(RedisKeyUtils.MAP_KEY_USER_LIKED, ScanOptions.NONE);
        List<UserLikeEntity> list = new ArrayList<>();
        while (cursor.hasNext()) {
            Map.Entry<Object, Object> entry = cursor.next();
            String key = (String) entry.getKey();
            //分离出 likedUserId，likedPostId
            String[] split = key.split("::");
            String likedBlogId = split[0];
            String likedPostId = split[1];
            Integer value = (Integer) entry.getValue();

            //组装成 UserLike 对象
            UserLikeEntity userLike = new UserLikeEntity(likedBlogId, likedPostId, value);
            list.add(userLike);

            //存到 list 后从 Redis 中删除
            redisTemplate.opsForHash().delete(RedisKeyUtils.MAP_KEY_USER_LIKED, key);
        }

        return list;
    }

    @Override
    public List<LikedCountDTO> getLikedCountFromRedis() {
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(RedisKeyUtils.MAP_KEY_USER_LIKED_COUNT, ScanOptions.NONE);
        List<LikedCountDTO> list = new ArrayList<>();
        while (cursor.hasNext()) {
            Map.Entry<Object, Object> map = cursor.next();
            //将点赞数量存储在 LikedCountDT
            String key = (String) map.getKey();
            LikedCountDTO dto = new LikedCountDTO(key, (Integer) map.getValue());
            list.add(dto);
            //从Redis中删除这条记录
            redisTemplate.opsForHash().delete(RedisKeyUtils.MAP_KEY_USER_LIKED_COUNT, key);
        }
        return list;
    }

    @Override
    public boolean checkLikeStatus(String likedBlogId, String likedPostId) {
        String key = RedisKeyUtils.getLikedKey(likedBlogId, likedPostId);
        Boolean aBoolean = redisTemplate.opsForHash().hasKey(RedisKeyUtils.MAP_KEY_USER_LIKED, key);
        if (aBoolean) {
            // redis中这对点赞健还未删除
            Integer value =(Integer) redisTemplate.opsForHash().get(RedisKeyUtils.MAP_KEY_USER_LIKED, key);
            if (value.equals(1)) {
                // 已经点赞
                return true;
            }else {
                return false;
            }
        }else {
            // redis中这对点赞健已经删除，查看数据库有没有
            UserLikeEntity like = userLikeService.getOne(
                    new QueryWrapper<UserLikeEntity>()
                            .eq("liked_blog_id", likedBlogId)
                            .eq("liked_post_id", likedPostId));
            if (like == null) {
                // 没有此用户对此博客的点赞记录
                return false;
            }
            if (like.getStatus().equals(0)) {
                // 取消了点赞
                return false;
            }
            return true;
        }
    }
}
