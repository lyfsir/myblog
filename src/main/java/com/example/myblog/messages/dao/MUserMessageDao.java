package com.example.myblog.messages.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.myblog.messages.entity.MUserMessageEntity;
import com.example.myblog.messages.vo.MUserMessageVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @author lyf
 * @date 2020/9/27-23:45
 */
@Mapper
public interface MUserMessageDao extends BaseMapper<MUserMessageEntity> {
    IPage<MUserMessageVo> selectMessages(IPage<MUserMessageEntity> page, @Param(Constants.WRAPPER) QueryWrapper<MUserMessageEntity> wrapper);
    @Update("update m_user_message set status = 1 ${ew.customSqlSegment}")
    void updateToReaded(@Param(Constants.WRAPPER)QueryWrapper<MUserMessageEntity> wrapper);
}
