package com.example.myblog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.myblog.entity.MBlogEntity;
import com.example.myblog.vo.MBlogInfoVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lyf
 * @date 2020/9/25-0:44
 */
@Mapper
public interface MBlogDao extends BaseMapper<MBlogEntity> {
    MBlogInfoVo fingMlogInfo(Long bid);
}
