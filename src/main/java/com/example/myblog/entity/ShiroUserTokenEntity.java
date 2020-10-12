package com.example.myblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author lyf
 * @date 2020/9/25-0:42
 */
@Data
@TableName("shiro_user_token")
public class ShiroUserTokenEntity {
    @TableId(type = IdType.INPUT)
    private Long uid;
    private String token;
    private Date expireTime;
    private Date updateTime;
}
