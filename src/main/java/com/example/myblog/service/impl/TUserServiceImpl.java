package com.example.myblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.myblog.comment.UserNameException;
import com.example.myblog.dao.TUserDao;
import com.example.myblog.entity.TUserEntity;
import com.example.myblog.service.TUserService;
import com.example.myblog.vo.AccountVo;
import com.example.myblog.vo.LoginFrom;
import com.example.myblog.vo.RegisterVo;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * @author lyf
 * @date 2020/9/25-0:50
 */
@Service
public class TUserServiceImpl extends ServiceImpl<TUserDao, TUserEntity> implements TUserService {
    @Override
    public void register(RegisterVo tUserEntity) {
        TUserEntity user_name = this.getOne(new QueryWrapper<TUserEntity>().eq("user_name", tUserEntity.getUsername()));
        if (user_name !=null){
            throw new UserNameException();
        }
        String password = tUserEntity.getPassword();
        LocalDate now = LocalDate.now();
        TUserEntity userEntity = new TUserEntity();
        userEntity.setUserName(tUserEntity.getUsername());
        userEntity.setCreateTime(String.valueOf(now));
        //sha256加密
        String salt = RandomStringUtils.randomAlphanumeric(20);
        userEntity.setPassword(new Sha256Hash(tUserEntity.getPassword(), salt).toHex());
        userEntity.setSalt(salt);
        this.save(userEntity);
    }

    @Override
    public AccountVo login(LoginFrom loginFrom) {
        TUserEntity userEntity = this.getOne(new QueryWrapper<TUserEntity>().eq("user_name", loginFrom.getUsername()));
        //账号不存在、密码错误
        if(userEntity == null) {
            return null;
        }
        //账号锁定
        if(userEntity.getStatus() == 0){
            return null;
        }
        boolean b = userEntity.getPassword().equals(new Sha256Hash(loginFrom.getPassword(), userEntity.getSalt()).toHex());
        if (b) {
            //密码正确
            AccountVo umsAccount = new AccountVo();
            umsAccount.setUid(userEntity.getId());
            umsAccount.setUsername(userEntity.getUserName());
            umsAccount.setLogo(userEntity.getLogo());
            return umsAccount;
        } else {
            return null;
        }
    }
}
