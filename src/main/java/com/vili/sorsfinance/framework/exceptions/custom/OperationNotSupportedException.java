package com.vili.sorsfinance.framework.exceptions.custom;

import com.vili.sorsfinance.framework.request.QueryOperator;

public class OperationNotSupportedException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public OperationNotSupportedException(QueryOperator op) {
		super("Operation not supported yet: " + op);
	}
}