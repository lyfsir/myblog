package com.example.myblog.model;

import lombok.Data;

/**
 * @author lyf
 * @date 2020/9/25-20:59
 */
@Data
public class BlogModel {
    private Long id; // id
    private String title; // 标题
    private String description; // 描述
    private String createTime; // 创建时间
    private Long uid; // 用户id
    private String username; // 用户名称
    private String logo; // 用户头像
    private Long zan; // 点赞数

}
