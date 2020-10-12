package com.example.myblog.like.utils;

/**
 * @author lyf
 * @date 2020/9/27-0:38
 */
public enum  LikedStatusEnum {
    LIKE(1, "点赞"),
    UNLIKE(0, "取消点赞/未点赞");
    private final int code;
    private final String msg;
    LikedStatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public int getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }


}
