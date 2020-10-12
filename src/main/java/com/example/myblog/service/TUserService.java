package com.example.myblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.myblog.entity.TUserEntity;
import com.example.myblog.vo.AccountVo;
import com.example.myblog.vo.LoginFrom;
import com.example.myblog.vo.RegisterVo;

/**
 * @author lyf
 * @date 2020/9/25-0:50
 */
public interface TUserService extends IService<TUserEntity> {

    void register(RegisterVo tUserEntity);
    AccountVo login(LoginFrom loginFrom);
}
