package com.example.myblog.service.impl;

import com.example.myblog.constant.EsConstant;
import com.example.myblog.service.ESBlogDelService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteRequestBuilder;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.reindex.UpdateByQueryRequest;
import org.elasticsearch.rest.RestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author lyf
 * @date 2020/9/26-23:02
 */
@Slf4j
@Service
public class ESBlogDelServiceImpl implements ESBlogDelService {

    @Autowired
    RestHighLevelClient restHighLevelClient;

    @Override
    public boolean delblog(Long id) throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest(EsConstant.BLOGS_INDEX,String.valueOf(id));
        DeleteResponse response = null;
        response = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
        RestStatus status = response.status();
        int status1 = status.getStatus();
        if (status1!=200){
            log.error("es删除失败",response.getId());
            return false;
        }
        return true;
    }
}
