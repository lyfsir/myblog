package com.example.myblog.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.myblog.config.LyfEsConfig;
import com.example.myblog.constant.EsConstant;
import com.example.myblog.model.BlogModel;
import com.example.myblog.service.ESBlogSaveService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author lyf
 * @date 2020/9/25-21:08
 */
@Slf4j
@Service
public class ESBlodSaveServiceImpl implements ESBlogSaveService {

    @Autowired
    RestHighLevelClient restHighLevelClient;

    @Override
    public boolean saveblodtoes(BlogModel blogModel) throws IOException {
        IndexRequest indexRequest = new IndexRequest(EsConstant.BLOGS_INDEX);
        indexRequest.id(blogModel.getId().toString());
        String jsonString = JSON.toJSONString(blogModel);
        indexRequest.source(jsonString, XContentType.JSON);
        IndexResponse index = restHighLevelClient.index(indexRequest, LyfEsConfig.COMMON_OPTIONS);
        RestStatus status = index.status();
        int statusStatus = status.getStatus();
        if(statusStatus != 200 && statusStatus != 201){
            log.error("es保存失败",index.getId());
            return false;
        }else {
            log.info("es保存成功",index.getId());
            return true;
        }
    }
}
