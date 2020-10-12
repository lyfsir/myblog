package com.example.myblog.messages.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.myblog.messages.entity.MCommentEntity;
import com.example.myblog.messages.entity.MUserMessageEntity;
import com.example.myblog.messages.utils.PageUtils;
import com.example.myblog.messages.vo.DisCussVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author lyf
 * @date 2020/9/27-23:47
 */
public interface MUserMessageService extends IService<MUserMessageEntity> {
    PageUtils paging(Map<String, Object> params,Long uid);
    void updateToReaded(List<Long> ids);

}
