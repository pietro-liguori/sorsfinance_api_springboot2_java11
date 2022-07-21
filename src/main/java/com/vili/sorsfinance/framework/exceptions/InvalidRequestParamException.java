package com.vili.sorsfinance.framework.exceptions;

public class InvalidRequestParamException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public InvalidRequestParamException(Object param) {
		super("Invalid request paramameter '" + param + "'");
	}

	public InvalidRequestParamException(String msg) {
		super(msg);
	}

}