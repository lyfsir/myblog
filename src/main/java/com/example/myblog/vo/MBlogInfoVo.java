package com.example.myblog.vo;

import lombok.Data;

/**
 * @author lyf
 * @date 2020/9/27-19:28
 */
@Data
public class MBlogInfoVo {
    private Long userId;
    private String title;
    private String description;
    private String content;
    private String created;
    private Long likeNum;
    private String userName;
    private String logo;

}
