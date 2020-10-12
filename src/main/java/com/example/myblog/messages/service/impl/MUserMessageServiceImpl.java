package com.example.myblog.messages.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.myblog.messages.dao.MUserMessageDao;
import com.example.myblog.messages.entity.MUserMessageEntity;
import com.example.myblog.messages.service.MUserMessageService;
import com.example.myblog.messages.utils.PageUtils;
import com.example.myblog.messages.utils.Query;
import com.example.myblog.messages.vo.MUserMessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author lyf
 * @date 2020/9/27-23:47
 */
@Service
public class MUserMessageServiceImpl extends ServiceImpl<MUserMessageDao, MUserMessageEntity> implements MUserMessageService {

    @Autowired
    MUserMessageDao mUserMessageDao;

    @Autowired
    MUserMessageService mUserMessageService;


    @Override
    public PageUtils paging(Map<String, Object> params,Long uid) {
        IPage<MUserMessageVo> page = mUserMessageDao.selectMessages(new Query<MUserMessageEntity>().getPage(params),
                new QueryWrapper<MUserMessageEntity>().eq("to_user_id", uid).orderByDesc("created"));
        // 把消息改成已读状态
        List<Long> ids = new ArrayList<>();
        for(MUserMessageEntity messageVo : page.getRecords()) {
            if(messageVo.getStatus() == 0) {
                ids.add(messageVo.getId());
            }
        }
        // 批量修改成已读
        updateToReaded(ids);
        return new PageUtils(page);
    }

    @Override
    public void updateToReaded(List<Long> ids) {
        if(ids.isEmpty()) return;

        mUserMessageDao.updateToReaded(new QueryWrapper<MUserMessageEntity>()
                .in("id", ids)
        );

    }
}
