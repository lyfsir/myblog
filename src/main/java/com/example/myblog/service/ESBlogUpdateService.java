package com.example.myblog.service;

import com.example.myblog.vo.ZanVo;

import java.io.IOException;

/**
 * @author lyf
 * @date 2020/9/26-23:42
 */
public interface ESBlogUpdateService {
    boolean updateblog(ZanVo vo , Long id) throws IOException;
}
