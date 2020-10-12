package com.example.myblog.messages.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.myblog.messages.entity.MCommentEntity;
import com.example.myblog.messages.vo.DisCussVo;

import java.util.List;

/**
 * @author lyf
 * @date 2020/9/28-0:06
 */
public interface MCommentService extends IService<MCommentEntity> {
    List<DisCussVo> getdiscuss(Long bid);
}
