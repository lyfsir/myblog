package com.example.myblog.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author lyf
 * @date 2020/9/25-0:39
 */
@Data
@TableName("m_blog")
public class MBlogEntity {
    @TableId
    private Long id;
    private Long userId;
    private String title;
    private String description;
    private String content;
    private String created;
    private Integer status;
    private Long likeNum;
    private Long commentCount;
}
