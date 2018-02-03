package com.apr7.sponge.exception;

public class SpongeLoginException extends SpongeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int code = ExceptionCode.LOGIN;

	public int getCode() {
		return code;
	}

	public SpongeLoginException(String message) {
		super(message);
	}
}
