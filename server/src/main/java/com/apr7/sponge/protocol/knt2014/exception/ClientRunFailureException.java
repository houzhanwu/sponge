package com.apr7.sponge.protocol.knt2014.exception;

public class ClientRunFailureException extends Knt2014Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClientRunFailureException() {
		super();
	}

	public ClientRunFailureException(String message) {
		super(message);
	}

	public ClientRunFailureException(String message, Throwable cause) {
		super(message, cause);
	}
}
