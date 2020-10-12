package com.example.myblog.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.myblog.constant.EsConstant;
import com.example.myblog.service.ESBlogUpdateService;
import com.example.myblog.vo.ZanVo;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author lyf
 * @date 2020/9/26-23:42
 */
@Slf4j
@Service
public class ESBlogUpdateServiceImpl implements ESBlogUpdateService {

    @Autowired
    RestHighLevelClient restHighLevelClient;

    @Override
    public boolean updateblog(ZanVo vo , Long id) throws IOException {
        UpdateRequest updateRequest = new UpdateRequest(EsConstant.BLOGS_INDEX, String.valueOf(id));
        String jsonString = JSON.toJSONString(vo);
        updateRequest.doc(jsonString, XContentType.JSON);
        UpdateResponse response = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
        RestStatus status = response.status();
        int status1 = status.getStatus();
        if (status1!=200){
            log.error("es修改失败",response.getId());
            return false;
        }
        return true;
    }
}
