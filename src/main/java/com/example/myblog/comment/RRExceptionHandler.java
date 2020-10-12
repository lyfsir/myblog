/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.example.myblog.comment;

import com.example.myblog.codeMesg.MyCodemsg;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * 异常处理器
 *
 * @author Mark sunlightcs@gmail.com
 */
@Slf4j
@RestControllerAdvice
public class RRExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public R getExc(MethodArgumentNotValidException e) {
		log.error("实体校验异常" + e.getClass());
		BindingResult result = e.getBindingResult();
		Map<String, String> map = new HashMap<>();
		result.getFieldErrors().forEach((item) -> {
			String message = item.getDefaultMessage();
			String field = item.getField();
			map.put(field, message);
		});
		return R.error(MyCodemsg.VOLATILE_EXCEPTION.getCode(), MyCodemsg.VOLATILE_EXCEPTION.getMsg()).put("data", map);
	}

	/**
	 * 处理自定义异常
	 */
//	@ExceptionHandler(RRException.class)
//	public R handleRRException(RRException e){
//		R r = new R();
//		r.put("code", e.getCode());
//		r.put("msg", e.getMessage());
//
//		return r;
//	}


//	@ExceptionHandler(NoHandlerFoundException.class)
//	public R handlerNoFoundException(Exception e) {
//		logger.error(e.getMessage(), e);
//		return R.error(404, "路径不存在，请检查路径是否正确");
//	}
//
//	@ExceptionHandler(DuplicateKeyException.class)
//	public R handleDuplicateKeyException(DuplicateKeyException e){
//		logger.error(e.getMessage(), e);
//		return R.error("数据库中已存在该记录");
//	}
//
//	@ExceptionHandler(AuthorizationException.class)
//	public R handleAuthorizationException(AuthorizationException e){
//		logger.error(e.getMessage(), e);
//		return R.error("没有权限，请联系管理员授权");
//	}

//	@ExceptionHandler(Exception.class)
//	public R handleException(Exception e){
//		logger.error(e.getMessage(), e);
//		return R.error();
//	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public R handleException(HttpRequestMethodNotSupportedException e){
		R r = new R();
		r.put("code", 405);
		r.put("msg", e.getMessage());

		return r;
	}
}
