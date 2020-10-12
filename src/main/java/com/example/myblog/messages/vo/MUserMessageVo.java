package com.example.myblog.messages.vo;

import com.example.myblog.messages.entity.MUserMessageEntity;
import lombok.Data;

/**
 * @author lyf
 * @date 2020/9/28-18:59
 */
@Data
public class MUserMessageVo extends MUserMessageEntity {
    private String toUserName;
    private String fromUserName;
    private String postTitle;
    private String commentContent;
}
