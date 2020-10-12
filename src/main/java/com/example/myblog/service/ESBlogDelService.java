package com.example.myblog.service;

import java.io.IOException;

/**
 * @author lyf
 * @date 2020/9/26-23:02
 */
public interface ESBlogDelService {
    boolean delblog(Long id) throws IOException;
}
