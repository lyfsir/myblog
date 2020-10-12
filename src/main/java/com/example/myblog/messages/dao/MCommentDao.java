package com.example.myblog.messages.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.myblog.messages.entity.MCommentEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lyf
 * @date 2020/9/28-0:06
 */
@Mapper
public interface MCommentDao extends BaseMapper<MCommentEntity> {
}
