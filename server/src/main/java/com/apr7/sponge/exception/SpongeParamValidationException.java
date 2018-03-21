package com.apr7.sponge.exception;

public class SpongeParamValidationException extends SpongeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int code = ExceptionCode.PARAM_VALIDATION;

	public int getCode() {
		return code;
	}

	public SpongeParamValidationException() {
		super();
	}

	public SpongeParamValidationException(String message) {
		super(message);
	}
}
