package com.example.myblog.messages.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author lyf
 * @date 2020/9/27-23:41
 */
@Data
@TableName("m_user_message")
public class MUserMessageEntity {
    @TableId
    private Long id;
    private Long fromUserId;
    private Long toUserId;
    private Long postId;
    private Long commentId;
    private String content;
    /**
     * 消息类型
     * 0系统消息 1评论文章 2评论评论
     */
    private Integer type;
    private Date created;
    private Date modified;
    private Integer status;
}
