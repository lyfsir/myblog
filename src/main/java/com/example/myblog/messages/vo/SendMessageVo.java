package com.example.myblog.messages.vo;

import lombok.Data;

/**
 * @author lyf
 * @date 2020/9/28-0:45
 */
@Data
public class SendMessageVo {
    private Long jid;
    private String content;
}
