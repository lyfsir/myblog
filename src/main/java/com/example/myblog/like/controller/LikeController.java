package com.example.myblog.like.controller;

import com.example.myblog.comment.R;
import com.example.myblog.controller.AbstractController;
import com.example.myblog.like.service.RedisService;
import com.example.myblog.like.vo.LikeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lyf
 * @date 2020/9/27-1:38
 */
@RestController
public class LikeController extends AbstractController {

    @Autowired
    RedisService redisService;

    /**
     * 点赞
     * @param
     * @return
     */
    @PostMapping("/like")
    public R like(@RequestBody LikeVo vo) {

        //先把数据存到Redis里,再定时存回数据库
        redisService.saveLiked2Redis(vo.getLikedBlogId(), String.valueOf(getUserId()));
        redisService.incrementLikedCount(vo.getLikedBlogId());
        return R.ok();
    }

    /**
     * 取消点赞
     * @param
     * @return
     */
    @PostMapping("/unlike")
    public R unlike(@RequestBody LikeVo vo) {
        //取消点赞,先存到Redis里面，再定时写到数据库里
        redisService.unlikeFromRedis(vo.getLikedBlogId(), String.valueOf(getUserId()));
        redisService.decrementLikedCount(vo.getLikedBlogId());
        return R.ok();
    }

    /**
     * 测试根据uid查看此用户是否对此博客点赞过，点赞了返回true，没则false
     */
    @GetMapping("/like/status")
    public R status(@RequestParam String likedBlogId) {
        boolean b = redisService.checkLikeStatus(likedBlogId, String.valueOf(getUserId()));
        return R.ok().put("data",b);
    }

}
