package com.example.myblog.like.task;

import com.example.myblog.like.service.UserLikeService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lyf
 * @date 2020/9/27-16:18
 */
@Slf4j
public class LikeTask extends QuartzJobBean {

    @Autowired
    UserLikeService userLikeService;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("LikeTask-------- {}", sdf.format(new Date()));

        // 将 Redis 里的点赞信息同步到数据库里
        userLikeService.transLikedFromRedis2DB();
        // 将 Redis 里的点赞总数同步到数据库和es中
        userLikeService.transLikedCountFromRedis2DB();
    }
}
