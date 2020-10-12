package com.example.myblog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.myblog.codeMesg.MyCodemsg;
import com.example.myblog.comment.R;
import com.example.myblog.comment.UserNameException;
import com.example.myblog.entity.TUserEntity;
import com.example.myblog.service.ShiroUserTokenService;
import com.example.myblog.service.TUserService;
import com.example.myblog.vo.AccountVo;
import com.example.myblog.vo.LoginFrom;
import com.example.myblog.vo.RegisterVo;
import com.example.myblog.vo.UpdateUserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author lyf
 * @date 2020/9/25-0:54
 */
@RestController
public class TUserController extends AbstractController {
    @Autowired
    ShiroUserTokenService shiroUserTokenService;

    @Autowired
    TUserService tUserService;


    /**
     * 注册
     * @param tUserEntity
     * @return
     */
    @PostMapping("/register")
    public R register(@Validated @RequestBody RegisterVo tUserEntity) {
        try {
            tUserService.register(tUserEntity);
        }catch (UserNameException e) {
            return R.error(MyCodemsg.USERNAME_EXCEPTION.getCode(),MyCodemsg.USERNAME_EXCEPTION.getMsg());

        }
        return R.ok();
    }


    /**
     * 登陆
     */
    @PostMapping("/login")
    public R login(@Validated @RequestBody LoginFrom loginFrom){
        AccountVo login = tUserService.login(loginFrom);
        if (login == null) {
            return R.error(MyCodemsg.USERSTATUS_EXCEPTION.getCode(),MyCodemsg.USERSTATUS_EXCEPTION.getMsg());
        }
        R r = shiroUserTokenService.createToken(login.getUid());
        String token = (String) r.get("token");
        login.setToken(token);
        return R.ok().put("data",login);
    }



    /**
     * 退出
     */
    @PostMapping("/logout")
    public R logout() {
        shiroUserTokenService.logout(getUserId());
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return R.ok();
    }

    /**
     * 修改头像
     */
    @PutMapping("/upUser")
    public R updateUser(@RequestBody UpdateUserVo vo){
        TUserEntity userEntity = new TUserEntity();
        userEntity.setLogo(vo.getLogo());
        userEntity.setId(getUserId());
        boolean id = tUserService.updateById(userEntity);
        if (id) {
            return R.ok();
        }else {
            return R.error(MyCodemsg.UPDATEUSERINFO_EXCEPTION.getCode(),MyCodemsg.UPDATEUSERINFO_EXCEPTION.getMsg());
        }
    }
}
