package com.example.myblog.vo;

import lombok.Data;

/**
 * @author lyf
 * @date 2020/9/25-21:53
 */
@Data
public class AccountVo {
    private Long uid;
    private String username;
    private String token;
    private String logo;
}
