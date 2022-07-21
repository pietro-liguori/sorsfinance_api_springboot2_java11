package com.vili.sorsfinance.framework.exceptions;

public class ComponentNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ComponentNotFoundException(String msg) {
		super(msg);
	}
}