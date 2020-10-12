package com.example.myblog.like.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.myblog.like.utils.LikedStatusEnum;
import lombok.Data;

/**
 * @author lyf
 * @date 2020/9/27-0:32
 */
@Data
@TableName("user_like")
public class UserLikeEntity {
    @TableId
    private Long id;
    private String likedBlogId;
    private String likedPostId;
    private Integer status = LikedStatusEnum.UNLIKE.getCode();
    public UserLikeEntity(String likedBlogId, String likedPostId, Integer status) {
        this.likedBlogId = likedBlogId;
        this.likedPostId = likedPostId;
        this.status = status;
    }
}
