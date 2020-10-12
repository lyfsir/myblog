package com.example.myblog.messages.service;

/**
 * @author lyf
 * @date 2020/9/28-23:44
 */
public interface WebSocketService {
    void sendMessCountToUser(Long toUserId);

    /**
     * 心跳检测 直接给源路径返回"pang"作标志
     */
    void sendPong(String pong);
}
