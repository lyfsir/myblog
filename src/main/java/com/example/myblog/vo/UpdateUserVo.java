package com.example.myblog.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author lyf
 * @date 2020/10/5-1:10
 */
@Data
public class UpdateUserVo {
    private Long id;
    @NotNull
    private String logo;
}
