package com.apr7.sponge.exception;

public class SpongeNotLoggedInException extends SpongeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -242283576464289705L;
	private int code = ExceptionCode.NOT_LOGGED_IN;

	public int getCode() {
		return code;
	}

	public SpongeNotLoggedInException(String message) {
		super(message);
	}

	public SpongeNotLoggedInException(String message, Throwable cause) {
		super(message, cause);
	}

	public SpongeNotLoggedInException(Throwable cause) {
		super(cause);
	}
}
