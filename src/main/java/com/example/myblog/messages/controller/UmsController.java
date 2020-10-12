package com.example.myblog.messages.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.myblog.comment.R;
import com.example.myblog.controller.AbstractController;
import com.example.myblog.messages.entity.MCommentEntity;
import com.example.myblog.messages.entity.MUserMessageEntity;
import com.example.myblog.messages.service.MCommentService;
import com.example.myblog.messages.service.MUserMessageService;
import com.example.myblog.messages.utils.PageUtils;
import com.example.myblog.messages.utils.Query;
import com.example.myblog.messages.vo.DisCussVo;
import com.example.myblog.messages.vo.MUserMessageVo;
import com.example.myblog.service.TUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author lyf
 * @date 2020/9/28-18:55
 */

@RestController
public class UmsController extends AbstractController {

    @Autowired
    TUserService tUserService;

    @Autowired
    MUserMessageService mUserMessageService;

    @Autowired
    MCommentService mCommentService;


    /**
     * 查看我的消息中心
     * @param params
     * @return
     */
    @GetMapping("/user/message")
    public R message(@RequestParam Map<String, Object> params){
        PageUtils page = mUserMessageService.paging(params, getUserId());
        return R.ok().put("page",page);
    }

    /**
     * 删除自己的某条消息
     * @param id
     * @param all
     * @return
     */
    @DeleteMapping("/del/message")
    public R delmessage(Long id,
                        @RequestParam(defaultValue = "false") Boolean all){
        boolean remove = mUserMessageService.remove(new QueryWrapper<MUserMessageEntity>()
                .eq("to_user_id", getUserId())
                .eq(!all, "id", id));
        if (!remove) {
            return R.error("删除失败");
        }
        return R.ok();
    }

    /**
     * 查看我有几条未读消息
     */
    @GetMapping("/message/count")
    public R count(){
        int count = mUserMessageService.count(
                new QueryWrapper<MUserMessageEntity>()
                        .eq("to_user_id", String.valueOf(getUserId()))
                        .eq("status", 0));
        return R.ok().put("count",count);
    }

    /**
     * 查看评论区的消息
     */
    @GetMapping("/discuss")
    public R discuss(@RequestParam Long bid) {
        List<DisCussVo> list = mCommentService.getdiscuss(bid);
        return R.ok().put("data",list);
    }


}
