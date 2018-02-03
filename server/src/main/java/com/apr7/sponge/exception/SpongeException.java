package com.apr7.sponge.exception;

public class SpongeException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int code = ExceptionCode.UNKNOW;

	private String logRemark = "";

	public int getCode() {
		return code;
	}

	public String getLogRemark() {
		return logRemark;
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

	public SpongeException(String message, String logRemark) {
		super(message);
		this.logRemark = logRemark;
	}

	public SpongeException(String message, Throwable cause) {
		super(message, cause);
	}

	public SpongeException(Throwable cause) {
		super(cause);
	}

	@Override
	public String toString() {
		return super.toString() + "：" + logRemark;
	}
}
