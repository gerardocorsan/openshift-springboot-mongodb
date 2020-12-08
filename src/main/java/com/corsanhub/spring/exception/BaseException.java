package com.corsanhub.spring.exception;

public class BaseException extends RuntimeException {
	private static final long serialVersionUID = -8034440099558628040L;

	public BaseException(String message) {
		super(message);
	}

	public BaseException(String message, Exception ex) {
		super(message, ex);
	}

	public BaseException(Exception ex) {
		super(ex);
	}

}
