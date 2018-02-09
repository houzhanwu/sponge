package com.apr7.sponge.exception;

public class SpongeTokenExpireException extends SpongeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int code = ExceptionCode.TOKEN_EXPIRE;

	public int getCode() {
		return code;
	}

	public SpongeTokenExpireException() {
	}

	public SpongeTokenExpireException(String message) {
		super(message);
	}
}
