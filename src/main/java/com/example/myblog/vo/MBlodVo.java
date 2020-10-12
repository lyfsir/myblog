package com.example.myblog.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author lyf
 * @date 2020/9/26-20:00
 */
@Data
public class MBlodVo {
    private Long userId;
    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private String content;
}
