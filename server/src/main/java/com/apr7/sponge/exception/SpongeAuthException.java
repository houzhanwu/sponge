package com.apr7.sponge.exception;

public class SpongeAuthException extends SpongeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int code = ExceptionCode.AUTH;

	public int getCode() {
		return code;
	}

	public SpongeAuthException(String message) {
		super(message);
	}

	public SpongeAuthException(String message, String logRemark) {
		super(message, logRemark);
	}
}
