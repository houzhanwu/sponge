package com.apr7.sponge.protocol.hjt212.exception;

public class ExeHjt212Exception extends Hjt212Exception {

	private static final long serialVersionUID = 4388849458921196183L;
	private int code;

	public int getCode() {
		return code;
	}

	public ExeHjt212Exception() {
		super();
	}

	public ExeHjt212Exception(int code) {
		this.code = code;
	}

	public ExeHjt212Exception(String message) {
		super(message);
	}

	public ExeHjt212Exception(String message, Throwable cause) {
		super(message, cause);
	}

	public ExeHjt212Exception(Throwable cause) {
		super(cause);
	}
}
