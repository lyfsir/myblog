package com.example.myblog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.myblog.codeMesg.MyCodemsg;
import com.example.myblog.comment.NotAuthorDel;
import com.example.myblog.comment.R;
import com.example.myblog.dao.MBlogDao;
import com.example.myblog.entity.MBlogEntity;
import com.example.myblog.messages.entity.MUserMessageEntity;
import com.example.myblog.messages.utils.PageUtils;
import com.example.myblog.messages.utils.Query;
import com.example.myblog.model.SearchParam;
import com.example.myblog.service.ESBlogDelService;
import com.example.myblog.service.ESBlogSelectService;
import com.example.myblog.service.ESBlogUpdateService;
import com.example.myblog.service.MBlogService;
import com.example.myblog.vo.MBlodVo;
import com.example.myblog.vo.MBlogInfoVo;
import com.example.myblog.vo.SearchResponseVo;
import com.example.myblog.vo.ZanVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

/**
 * @author lyf
 * @date 2020/9/25-0:54
 */
@RestController
public class MBlogController extends AbstractController {

    @Autowired
    MBlogService mBlogService;

    @Autowired
    ESBlogSelectService esBlogSelectService;

    @Autowired
    ESBlogDelService esBlogDelService;

    @Autowired
    ESBlogUpdateService esBlogUpdateService;

    @Autowired
    MBlogDao mBlogDao;


    /**
     * 保存博客
     * @param vo
     * @return
     */
    @PostMapping("/save/blog")
    public R save(@Validated @RequestBody MBlodVo vo) {
        vo.setUserId(getUserId());
        mBlogService.saveblod(vo);
        return R.ok();
    }

    /**
     * 检索博客
     * @param searchParam
     * @return
     */
    @GetMapping("/get/blogs")
    public R get(SearchParam searchParam){
        SearchResponseVo search = esBlogSelectService.search(searchParam);
        return R.ok().put("data",search);
    }

    /**
     * 删除博客
     * @param id
     * @return
     */
    @DeleteMapping("/del/blogs")
    public R del(@RequestParam Long id){
        try {
            mBlogService.delBlod(id,getUserId());
        }catch (NotAuthorDel e) {
            return R.error(MyCodemsg.AUTHORDEL_EXCEPTION.getCode(),MyCodemsg.AUTHORDEL_EXCEPTION.getMsg());
        }
        try {
            esBlogDelService.delblog(id);
        } catch (IOException e) {
            return R.error(MyCodemsg.ES_EXCEPTION.getCode(),MyCodemsg.ES_EXCEPTION.getMsg());
        }
        return R.ok();
    }

    @GetMapping("/get/blogsInfo")
    public R getInfo(@RequestParam Long bid){
        MBlogInfoVo mlogInfo = mBlogService.findMlogInfo(bid);
        if (mlogInfo==null) {
            return R.error(MyCodemsg.BLOGNOT_EXCEPTION.getCode(),MyCodemsg.BLOGNOT_EXCEPTION.getMsg());
        }
        return R.ok().put("data",mlogInfo);
    }

}
