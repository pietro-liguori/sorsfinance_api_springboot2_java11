package com.vili.sorsfinance.framework.exceptions.custom;

public class ComponentNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ComponentNotFoundException(String msg) {
		super(msg);
	}
}