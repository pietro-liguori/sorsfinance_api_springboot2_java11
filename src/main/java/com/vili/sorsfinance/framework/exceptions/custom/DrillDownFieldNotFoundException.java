package com.vili.sorsfinance.framework.exceptions.custom;

public class DrillDownFieldNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public DrillDownFieldNotFoundException(String msg) {
		super(msg);
	}
	
	public DrillDownFieldNotFoundException(String name, Class<?> object) {
		super("Field '" + name + "' not found in class '" + object.getSimpleName() + "'");
	}
}