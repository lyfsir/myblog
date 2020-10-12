package com.example.myblog.vo;

import com.example.myblog.model.BlogModel;
import lombok.Data;

import java.util.List;

/**
 * @author lyf
 * @date 2020/9/26-21:48
 */
@Data
public class SearchResponseVo {
    private List<BlogModel> blog;
    //分页信息
    private Integer pageNum; // 当前页吗
    private Long total; // 总记录数
    private Integer totalPages; // 总页码
}
