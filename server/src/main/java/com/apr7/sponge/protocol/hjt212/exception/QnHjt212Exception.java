package com.apr7.sponge.protocol.hjt212.exception;

public class QnHjt212Exception extends Hjt212Exception {

	private static final long serialVersionUID = -1209773167077007223L;
	private int code;

	public int getCode() {
		return code;
	}

	public QnHjt212Exception() {
		super();
	}

	public QnHjt212Exception(int code) {
		this.code = code;
	}

	public QnHjt212Exception(String message) {
		super(message);
	}

	public QnHjt212Exception(String message, Throwable cause) {
		super(message, cause);
	}

	public QnHjt212Exception(Throwable cause) {
		super(cause);
	}
}
