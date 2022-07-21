package com.vili.sorsfinance.framework.exceptions;

import com.vili.sorsfinance.framework.enums.QueryOperator;

public class OperationNotSupportedException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public OperationNotSupportedException(QueryOperator op) {
		super("Operation not supported yet: " + op);
	}
}