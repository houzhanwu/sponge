package com.apr7.sponge.exception;

public class SpongeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8031726389328723827L;
	private int code = ExceptionCode.UNKNOW;

	public int getCode() {
		return code;
	}

	public SpongeException() {
		super();
	}

	public SpongeException(int code) {
		super();
		this.code = code;
	}

	public SpongeException(String message) {
		super(message);
	}

	public SpongeException(String message, Throwable cause) {
		super(message, cause);
	}

	public SpongeException(Throwable cause) {
		super(cause);
	}
}
