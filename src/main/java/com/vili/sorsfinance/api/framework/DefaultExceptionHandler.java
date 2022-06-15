package com.vili.sorsfinance.api.framework;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.vili.sorsfinance.api.exceptions.DatabaseException;
import com.vili.sorsfinance.api.exceptions.EnumValueNotFoundException;
import com.vili.sorsfinance.api.exceptions.InvalidRequestParamException;
import com.vili.sorsfinance.api.exceptions.NoSuchPathException;
import com.vili.sorsfinance.api.exceptions.ResourceNotFoundException;
import com.vili.sorsfinance.api.exceptions.StandardError;

@ControllerAdvice
public class DefaultExceptionHandler {
	
	@ExceptionHandler(EnumValueNotFoundException.class)
	public ResponseEntity<StandardError>enumValueNotFound(EnumValueNotFoundException e, HttpServletRequest request) {
		String error = "Enum value not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		String error = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {
		String error = "Database error";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(InvalidRequestParamException.class)
	public ResponseEntity<StandardError> database(InvalidRequestParamException e, HttpServletRequest request) {
		String error = "Invalid request parameter";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(NoSuchPathException.class)
	public ResponseEntity<StandardError> database(NoSuchPathException e, HttpServletRequest request) {
		String error = "No such path";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(PropertyReferenceException.class)
	public ResponseEntity<StandardError> database(PropertyReferenceException e, HttpServletRequest request) {
		String error = "Property not found";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}
