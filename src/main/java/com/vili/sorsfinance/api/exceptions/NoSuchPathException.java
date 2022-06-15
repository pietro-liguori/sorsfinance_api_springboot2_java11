package com.vili.sorsfinance.api.exceptions;

public class NoSuchPathException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public NoSuchPathException() {
		super("Required path not found.");
	}

	public NoSuchPathException(String msg) {
		super(msg);
	}

}