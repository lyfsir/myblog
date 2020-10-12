package com.example.myblog.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author lyf
 * @date 2020/9/26-16:49
 */
@Data
public class RegisterVo {
    @NotNull
    private String username;
    @NotNull
    private String password;
}
