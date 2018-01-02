package com.apr7.sponge.web.content;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class SpringExceptionHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringExceptionHandler.class);

	@ExceptionHandler
	@ResponseBody
	public ResponseBodyModel handleOtherExceptions(RuntimeException e) {
		LOGGER.error(e.toString(), e);
		ResponseBodyModel responseBodyModel = new ResponseBodyModel();
		responseBodyModel.setCode(11000);
		return responseBodyModel;
	}
}
