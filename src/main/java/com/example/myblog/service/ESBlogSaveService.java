package com.example.myblog.service;

import com.example.myblog.model.BlogModel;

import java.io.IOException;

/**
 * @author lyf
 * @date 2020/9/25-21:08
 */
public interface ESBlogSaveService {
    /**
     * 保存信息到es中
     * @param blogModel
     * @return
     */
    boolean saveblodtoes(BlogModel blogModel) throws IOException;

    /**
     * 删除es信息
     */
}
