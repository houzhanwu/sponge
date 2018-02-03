package com.apr7.sponge.exception;

public class SpongeNotLoggedInException extends SpongeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int code = ExceptionCode.NOT_LOGGED_IN;

	public int getCode() {
		return code;
	}

	public SpongeNotLoggedInException(String message) {
		super(message);
	}
}
