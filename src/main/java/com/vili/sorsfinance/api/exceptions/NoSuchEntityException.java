package com.vili.sorsfinance.api.exceptions;

public class NoSuchEntityException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public NoSuchEntityException() {
		super("No such element");
	}

	public NoSuchEntityException(String msg) {
		super(msg);
	}

}