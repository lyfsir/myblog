package com.example.myblog.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author lyf
 * @date 2020/9/25-21:47
 */
@Data
public class LoginFrom {
    @NotNull
    private String username;
    @NotNull
    private String password;
}
