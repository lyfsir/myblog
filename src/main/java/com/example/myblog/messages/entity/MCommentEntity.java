package com.example.myblog.messages.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author lyf
 * @date 2020/9/28-0:02
 */
@Data
@TableName("m_comment")
public class MCommentEntity {
    @TableId
    private Long id;
    private String content;
    private Long parentId;
    private Long postId;
    private Long userId;
    private Integer voteUp;
    private Integer voteDown;
    private Integer level;
    private Integer status;
    private Date created;
    private Date modified;
}
