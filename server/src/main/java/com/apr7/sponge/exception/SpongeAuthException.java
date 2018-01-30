package com.apr7.sponge.exception;

public class SpongeAuthException extends SpongeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7772069344562489287L;
	private int code = ExceptionCode.AUTH;

	public int getCode() {
		return code;
	}

	public SpongeAuthException(String message) {
		super(message);
	}

	public SpongeAuthException(String message, Throwable cause) {
		super(message, cause);
	}

	public SpongeAuthException(Throwable cause) {
		super(cause);
	}
}
