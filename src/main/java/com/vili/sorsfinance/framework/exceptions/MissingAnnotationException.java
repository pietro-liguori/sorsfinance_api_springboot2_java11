package com.vili.sorsfinance.framework.exceptions;

public class MissingAnnotationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public MissingAnnotationException(String msg) {
		super(msg);
	}
}