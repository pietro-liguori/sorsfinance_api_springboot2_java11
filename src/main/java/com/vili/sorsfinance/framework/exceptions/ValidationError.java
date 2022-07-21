package com.vili.sorsfinance.framework.exceptions;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vili.sorsfinance.framework.FieldMessage;

@JsonIgnoreProperties(value = { "message" })
public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;

	private List<FieldMessage> errors = new ArrayList<>();
	
	public ValidationError(Instant timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void add(String fieldName, String message) {
		errors.add(new FieldMessage(fieldName, message));
	}

}
