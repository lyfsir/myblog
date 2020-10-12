package com.example.myblog.service;

import com.example.myblog.model.SearchParam;
import com.example.myblog.vo.SearchResponseVo;

/**
 * @author lyf
 * @date 2020/9/26-21:47
 */
public interface ESBlogSelectService {
    SearchResponseVo search(SearchParam searchParam);
}
