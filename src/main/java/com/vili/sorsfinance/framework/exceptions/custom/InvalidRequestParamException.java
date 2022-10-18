package com.vili.sorsfinance.framework.exceptions.custom;

import java.util.ArrayList;
import java.util.List;

import com.vili.sorsfinance.framework.exceptions.FieldMessage;

public class InvalidRequestParamException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> errors = new ArrayList<>();
	
	public InvalidRequestParamException(List<FieldMessage> errors) {
		super("Invalid request parameter");
		this.errors = errors;
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}
}