package com.apr7.sponge.exception;

public class SpongeLoginException extends SpongeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3855438346088485673L;
	private int code = ExceptionCode.LOGIN;

	public int getCode() {
		return code;
	}

	public SpongeLoginException(String message) {
		super(message);
	}

	public SpongeLoginException(String message, Throwable cause) {
		super(message, cause);
	}

	public SpongeLoginException(Throwable cause) {
		super(cause);
	}
}
