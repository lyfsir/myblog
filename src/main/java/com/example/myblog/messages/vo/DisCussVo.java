package com.example.myblog.messages.vo;

import lombok.Data;

/**
 * @author lyf
 * @date 2020/10/6-23:05
 */
@Data
public class DisCussVo {
    private Long id;
    private String content;
    private Long userId;
    private String username;
    private String logo;
}
