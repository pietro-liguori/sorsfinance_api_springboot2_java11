package com.vili.sorsfinance.api.exceptions;

public class IllegalMethodRequestException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public IllegalMethodRequestException(Object value, String field, Class<?> enumClass) {
		super("Enum value not found for " + field + " = " + value + " on " + enumClass.getSimpleName());
	}

	public IllegalMethodRequestException(String msg) {
		super(msg);
	}
}