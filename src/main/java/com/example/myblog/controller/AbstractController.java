/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.example.myblog.controller;

import com.example.myblog.entity.TUserEntity;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller公共组件
 *
 * @author Mark sunlightcs@gmail.com
 */
public abstract class AbstractController {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	protected TUserEntity getUser() {
		return (TUserEntity) SecurityUtils.getSubject().getPrincipal();
	}

	/**
	 * 通过SecurityUtils.getSubject().getPrincipal()得到当前用户id
	 * @return
	 */
	protected Long getUserId() {
		return getUser().getId();
	}
}
