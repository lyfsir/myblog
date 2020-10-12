package com.example.myblog.like.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.myblog.like.entity.UserLikeEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lyf
 * @date 2020/9/27-1:11
 */
@Mapper
public interface UserLikeDao extends BaseMapper<UserLikeEntity> {
}
