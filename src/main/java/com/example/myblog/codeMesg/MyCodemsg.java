package com.example.myblog.codeMesg;

/**
 * @author lyf
 * @date 2020/9/25-1:40
 */
public enum  MyCodemsg  {
    USERNAME_EXCEPTION(10006,"用户名已存在"),
    USERSTATUS_EXCEPTION(10007,"用户、密码错误或用户被锁定"),
    AUTHORDEL_EXCEPTION(10008,"没有权限删除"),
    VOLATILE_EXCEPTION(10400, "数据格式校验异常"),
    BLOGNOT_EXCEPTION(10009, "此博客不存在"),
    ES_EXCEPTION(10011,"es异常"),
    UPDATEUSERINFO_EXCEPTION(10012,"修改个人信息失败");

    private final int code;
    private final String msg;
    MyCodemsg(int code, String msg) {
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
