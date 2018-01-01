package com.apr7.sponge.web.content;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class SpringExceptionHandler {
	@ExceptionHandler(value = { Exception.class })
	@ResponseBody
	public ResponseBodyModel handleOtherExceptions(RuntimeException ex) {
		return new ResponseBodyModel();
	}
}
