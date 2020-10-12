package com.example.myblog.like.utils;

/**
 * @author lyf
 * @date 2020/9/27-0:42
 */
public class RedisKeyUtils {

    //保存用户点赞数据的key
    public static final String MAP_KEY_USER_LIKED = "MAP_USER_LIKED";

    //保存博客被点赞数量的key
    public static final String MAP_KEY_USER_LIKED_COUNT = "MAP_BLOG_LIKED_COUNT";

    /**
     * 拼接被点赞的用户id和点赞的人的id作为key。格式 222222::333333
     *
     * @param likedblogId 被点赞的博客id
     * @param likedPostId 点赞的人的id
     * @return
     */
    public static String getLikedKey(String likedblogId, String likedPostId) {
        StringBuilder builder = new StringBuilder();
        builder.append(likedblogId);
        builder.append("::");
        builder.append(likedPostId);
        return builder.toString();
    }
}
