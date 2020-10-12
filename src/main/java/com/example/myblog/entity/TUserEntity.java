package com.example.myblog.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author lyf
 * @date 2020/9/25-0:33
 */
@Data
@TableName("t_user")
public class TUserEntity {
    @TableId
    private Long id;
    private String userName;
    private String password;
    private String createTime;
    private Integer status;
    private String logo;
    private String salt;
}
