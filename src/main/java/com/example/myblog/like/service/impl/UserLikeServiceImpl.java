package com.example.myblog.like.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.myblog.entity.MBlogEntity;
import com.example.myblog.like.dao.UserLikeDao;
import com.example.myblog.like.dto.LikedCountDTO;
import com.example.myblog.like.entity.UserLikeEntity;
import com.example.myblog.like.service.RedisService;
import com.example.myblog.like.service.UserLikeService;
import com.example.myblog.service.ESBlogUpdateService;
import com.example.myblog.service.MBlogService;
import com.example.myblog.vo.ZanVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

/**
 * @author lyf
 * @date 2020/9/27-1:13
 */
@Slf4j
@Service
public class UserLikeServiceImpl extends ServiceImpl<UserLikeDao, UserLikeEntity> implements UserLikeService {

    @Autowired
    RedisService redisService;

    @Autowired
    MBlogService mBlogService;

    @Autowired
    ESBlogUpdateService esBlogUpdateService;

    //将Redis里的点赞数据存入数据库中
    @Override
    @Transactional
    public void transLikedFromRedis2DB() {

        List<UserLikeEntity> list = redisService.getLikedDataFromRedis();

        for (UserLikeEntity like : list) {
            UserLikeEntity ul = this.getOne(
                    new QueryWrapper<UserLikeEntity>()
                            .eq("liked_blog_id",like.getLikedBlogId())
                            .eq("liked_post_id",like.getLikedPostId())
                    );
            if (ul == null){
                //没有记录，直接存入
                save(like);
            }
            else {
                //有记录，需要更新
                ul.setStatus(like.getStatus());
                updateById(ul);
            }
        }
    }

    //将Redis中的点赞数量数据存入数据库
    @Override
    @Transactional
    public void transLikedCountFromRedis2DB() {
        List<LikedCountDTO> list = redisService.getLikedCountFromRedis();
        for (LikedCountDTO dto : list) {
            MBlogEntity blogs = mBlogService.getById(dto.getId());
            //点赞数量属于无关紧要的操作，出错无需抛异常
            if (blogs != null && dto != null){
                if (blogs.getLikeNum()!=null && dto.getCount()!=null) {
                    Long likeNum = blogs.getLikeNum() + dto.getCount();
                    blogs.setLikeNum(likeNum);
                    //更新点赞数量
                    mBlogService.updateById(blogs);
                    ZanVo zanVo = new ZanVo();
                    zanVo.setZan(likeNum);
                    try {
                        esBlogUpdateService.updateblog(zanVo,blogs.getId());
                    } catch (IOException e) {
                        //点赞数量属于无关紧要的操作，出错无需抛异常
                        e.printStackTrace();
                    }
                }

            }
        }
    }
}
