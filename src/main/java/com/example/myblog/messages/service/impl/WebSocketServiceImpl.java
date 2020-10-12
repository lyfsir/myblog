package com.example.myblog.messages.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.myblog.messages.entity.MUserMessageEntity;
import com.example.myblog.messages.service.MUserMessageService;
import com.example.myblog.messages.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author lyf
 * @date 2020/9/28-23:44
 */

@Service
public class WebSocketServiceImpl implements WebSocketService {

    @Autowired
    MUserMessageService mUserMessageService;

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @Async
    @Override
    public void sendMessCountToUser(Long toUserId) {
        int count = mUserMessageService.count(new QueryWrapper<MUserMessageEntity>()
                .eq("to_user_id", toUserId)
                .eq("status", "0"));
        // websocket通知 (/user/20/messCount)
        simpMessagingTemplate.convertAndSendToUser(toUserId.toString(), "/messCount", count);
    }

    @Override
    public void sendPong(String pong) {
        simpMessagingTemplate.convertAndSend(pong);
    }
}
