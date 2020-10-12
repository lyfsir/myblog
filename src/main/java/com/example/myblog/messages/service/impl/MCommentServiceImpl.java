package com.example.myblog.messages.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.myblog.entity.TUserEntity;
import com.example.myblog.messages.dao.MCommentDao;
import com.example.myblog.messages.entity.MCommentEntity;
import com.example.myblog.messages.service.MCommentService;
import com.example.myblog.messages.vo.DisCussVo;
import com.example.myblog.service.TUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lyf
 * @date 2020/9/28-0:07
 */
@Service
public class MCommentServiceImpl extends ServiceImpl<MCommentDao, MCommentEntity> implements MCommentService {

    @Autowired
    TUserService tUserService;

    @Override
    public List<DisCussVo> getdiscuss(Long bid) {
        List<MCommentEntity> mCommentEntities = this.getBaseMapper().selectList(
                new QueryWrapper<MCommentEntity>()
                        .eq("post_id", bid)
        .orderByAsc("created"));
        if (mCommentEntities != null && mCommentEntities.size() > 0) {
            List<DisCussVo> collect = mCommentEntities.stream().map(value -> {
                DisCussVo disCussVo = new DisCussVo();
                BeanUtils.copyProperties(value, disCussVo);
                TUserEntity user = tUserService.getById(value.getUserId());
                disCussVo.setUsername(user.getUserName());
                if (user.getLogo() != null) {
                    disCussVo.setLogo(user.getLogo());
                }
                return disCussVo;
            }).collect(Collectors.toList());
            return collect;
        } else {
            return new ArrayList<DisCussVo>();
        }
    }
}
