package com.zsc.csg.web.common.exception;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zsc.csg.web.common.ResultEntity;
import com.zsc.csg.web.common.ResultEntity.Meta;

/**
 * 全局异常处理
 * @author zsc
 *
 */
@ControllerAdvice
@ResponseBody
public class ExceptionAdvice {

	private static Logger LOGGER = LoggerFactory.getLogger(ExceptionAdvice.class);
	
	@ExceptionHandler(value = CsgAppException.class)
	public ResponseEntity<Meta> defaultErrorHandler(HttpServletRequest req, CsgAppException e) {
		LOGGER.error("通用异常",e);
		return ResponseEntity.status(e.getCode()).body(ResultEntity.error(e));
	} 
}
