package com.example.myblog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.myblog.entity.ShiroUserTokenEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lyf
 * @date 2020/9/25-0:45
 */
@Mapper
public interface ShiroUserTokenDao extends BaseMapper<ShiroUserTokenEntity> {
}
