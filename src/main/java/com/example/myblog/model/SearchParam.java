package com.example.myblog.model;

import lombok.Data;

/**
 * @author lyf
 * @date 2020/9/26-21:39
 */
@Data
public class SearchParam {
    private String keyword;
    private Long uid;
    private String sort;
    private Integer pageNum = 1; //页码(默认进来就是第一页)

}
