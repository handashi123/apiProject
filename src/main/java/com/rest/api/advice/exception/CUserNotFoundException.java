package com.rest.api.advice.exception;

public class CUserNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -6960248283889549513L;
	
	public CUserNotFoundException(String msg, Throwable t) {
		super(msg, t);
	}
	public CUserNotFoundException(String msg) {
		super(msg);
	}
	public CUserNotFoundException() {
		super();
	}
}
