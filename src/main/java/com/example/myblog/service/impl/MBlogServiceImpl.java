package com.example.myblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.myblog.comment.ESException;
import com.example.myblog.comment.NotAuthorDel;
import com.example.myblog.dao.MBlogDao;
import com.example.myblog.entity.MBlogEntity;
import com.example.myblog.entity.TUserEntity;
import com.example.myblog.messages.utils.PageUtils;
import com.example.myblog.messages.utils.Query;
import com.example.myblog.model.BlogModel;
import com.example.myblog.service.ESBlogSaveService;
import com.example.myblog.service.MBlogService;
import com.example.myblog.service.TUserService;
import com.example.myblog.vo.MBlodVo;
import com.example.myblog.vo.MBlogInfoVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

/**
 * @author lyf
 * @date 2020/9/25-0:47
 */
@Service
public class MBlogServiceImpl extends ServiceImpl<MBlogDao, MBlogEntity> implements MBlogService {

    @Autowired
    TUserService tUserService;

    @Autowired
    ESBlogSaveService esBlodService;


    @Override
    @Transactional
    public void saveblod(MBlodVo vo) {
        TUserEntity userInfo = tUserService.getById(vo.getUserId());
        BlogModel blogModel = new BlogModel();
        LocalDate now = LocalDate.now();
        MBlogEntity mBlogEntity = new MBlogEntity();
        BeanUtils.copyProperties(vo,mBlogEntity);
        mBlogEntity.setCreated(String.valueOf(now));
        this.save(mBlogEntity);
        blogModel.setId(mBlogEntity.getId());
        blogModel.setCreateTime(String.valueOf(mBlogEntity.getCreated()));
        blogModel.setTitle(mBlogEntity.getTitle());
        blogModel.setDescription(mBlogEntity.getDescription());
        blogModel.setUid(mBlogEntity.getUserId());
        blogModel.setUsername(userInfo.getUserName());
        blogModel.setZan(0l);
        if (StringUtils.isEmpty(userInfo.getLogo())) {
            blogModel.setLogo("头像路径");
        } else {
            blogModel.setLogo(userInfo.getLogo());
        }
        boolean b = false;
        try {
            b = esBlodService.saveblodtoes(blogModel);
        } catch (IOException e) {
            log.error("es保存审核通过信息错误",e);
            throw new ESException();
        }
    }

    @Override
    public void delBlod(Long id, Long uid) {
        MBlogEntity blogEntity = this.getOne(new QueryWrapper<MBlogEntity>().eq("id", id).eq("user_id", uid));
        if (blogEntity==null) {
            throw new NotAuthorDel();
        }
        this.removeById(id);
    }

    @Override
    public MBlogInfoVo findMlogInfo(Long bid) {
        MBlogInfoVo mlogInfo = this.getBaseMapper().fingMlogInfo(bid);
        if (mlogInfo!=null) {
            return mlogInfo;
        }
        else {
            return null;
        }
    }

}
