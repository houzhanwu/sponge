package com.apr7.sponge.Exception;

public class SpongeException extends RuntimeException {

	private static final long serialVersionUID = 8707758496724857059L;
	private int code = 11000;

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
