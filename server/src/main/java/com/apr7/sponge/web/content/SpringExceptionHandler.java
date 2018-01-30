package com.apr7.sponge.web.content;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apr7.sponge.exception.ExceptionCode;
import com.apr7.sponge.exception.SpongeException;

@ControllerAdvice
public class SpringExceptionHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringExceptionHandler.class);

	@ExceptionHandler
	@ResponseBody
	public ResponseBodyModel handleOtherExceptions(Exception e) {
		ResponseBodyModel responseBodyModel = new ResponseBodyModel();
		if (e instanceof SpongeException) {
			LOGGER.error(e.toString());
			SpongeException spongeException = (SpongeException) e;
			responseBodyModel.setCode(spongeException.getCode());
			responseBodyModel.setMessage(spongeException.getMessage());
		} else {
			LOGGER.error(e.toString(), e);
			responseBodyModel.setCode(ExceptionCode.UNKNOW);
		}
		return responseBodyModel;
	}
}
