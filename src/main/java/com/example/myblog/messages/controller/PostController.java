package com.example.myblog.messages.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.myblog.comment.R;
import com.example.myblog.controller.AbstractController;
import com.example.myblog.entity.MBlogEntity;
import com.example.myblog.entity.TUserEntity;
import com.example.myblog.messages.entity.MCommentEntity;
import com.example.myblog.messages.entity.MUserMessageEntity;
import com.example.myblog.messages.service.MCommentService;
import com.example.myblog.messages.service.MUserMessageService;
import com.example.myblog.messages.service.WebSocketService;
import com.example.myblog.messages.vo.SendMessageVo;
import com.example.myblog.service.MBlogService;
import com.example.myblog.service.TUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author lyf
 * @date 2020/9/28-0:08
 */
@RestController
public class PostController extends AbstractController {

    @Autowired
    MBlogService mBlogService;

    @Autowired
    MCommentService mCommentService;

    @Autowired
    MUserMessageService mUserMessageService;

    @Autowired
    TUserService tUserService;

    @Autowired
    WebSocketService webSocketService;

    @Transactional
    @PostMapping("/post/reply/")
    public R reply(@RequestBody SendMessageVo vo) {
        MBlogEntity blog = mBlogService.getById(vo.getJid());
        if (blog == null) {
            return R.error(404, "该文章已被删除");
        }

        /**
         * 这是在博客下评论区显示的表
         */
        MCommentEntity mCommentEntity = new MCommentEntity();
        mCommentEntity.setPostId(vo.getJid());
        mCommentEntity.setContent(vo.getContent());
        mCommentEntity.setUserId(getUserId());
        mCommentEntity.setCreated(new Date());
        mCommentEntity.setModified(new Date());
        mCommentEntity.setLevel(0);
        mCommentEntity.setVoteDown(0);
        mCommentEntity.setVoteUp(0);
        mCommentService.save(mCommentEntity);

        // 评论数量加一
        blog.setCommentCount(blog.getCommentCount() + 1);
        mBlogService.updateById(blog);




        // 通知被@的人，有人回复了你的文章(需要点击某个用户的评论，在content头加上@username；例如：回复lyf的评论为你好  @lyf 你好)
        if(vo.getContent().startsWith("@")) {
            String username = vo.getContent().substring(1, vo.getContent().indexOf(" "));
            System.out.println(username);
            TUserEntity user = tUserService.getOne(new QueryWrapper<TUserEntity>().eq("user_name", username));
            if(user != null) {
                MUserMessageEntity message = new MUserMessageEntity();
                message.setPostId(vo.getJid());
                message.setCommentId(mCommentEntity.getId());
                message.setFromUserId(getUserId());
                message.setToUserId(user.getId());
                message.setType(2);
                message.setContent(vo.getContent());
                message.setCreated(new Date());
                message.setStatus(0);
                mUserMessageService.save(message);
                // TODO 即时通知被@的用户
                webSocketService.sendMessCountToUser(user.getId());
            }
            // TODO 如果@的用户是此条帖子的作者则通知有人@了你，不用进行文章通知
            if(user.getId()==blog.getUserId()) {
                return R.ok().put("data",blog.getId());
            }

        }


        // 通知作者，有人评论了你的文章
        // 作者自己评论自己文章，不需要通知  (这是在我的消息中心中显示消息的表)
        if (mCommentEntity.getUserId() != blog.getUserId()) {
            MUserMessageEntity messageEntity = new MUserMessageEntity();
            messageEntity.setPostId(vo.getJid());
            messageEntity.setCommentId(mCommentEntity.getId());
            messageEntity.setFromUserId(getUserId());
            messageEntity.setToUserId(blog.getUserId());
            messageEntity.setType(1);
            messageEntity.setContent(vo.getContent());
            messageEntity.setCreated(new Date());
            messageEntity.setStatus(0);
            mUserMessageService.save(messageEntity);

            // TODO 即时通知作者（websocket）
            webSocketService.sendMessCountToUser(messageEntity.getToUserId());

        }

        return R.ok().put("data",blog.getId());
    }

    /**
     * 心跳检测 给前端返回
     */
    @MessageMapping("/v3/check")
    public R gameInfo(String pong){
        webSocketService.sendPong(pong);
        return R.ok();
    }
}
