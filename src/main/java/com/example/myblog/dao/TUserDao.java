package com.example.myblog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.myblog.entity.TUserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lyf
 * @date 2020/9/25-0:46
 */
@Mapper
public interface TUserDao extends BaseMapper<TUserEntity> {
}
