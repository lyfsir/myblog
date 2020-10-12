package com.example.myblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.myblog.entity.MBlogEntity;
import com.example.myblog.messages.utils.PageUtils;
import com.example.myblog.vo.MBlodVo;
import com.example.myblog.vo.MBlogInfoVo;

import java.util.Map;

/**
 * @author lyf
 * @date 2020/9/25-0:46
 */
public interface MBlogService extends IService<MBlogEntity> {
    void saveblod(MBlodVo vo);

    void delBlod(Long id,Long uid);

    MBlogInfoVo findMlogInfo(Long bid);


}
