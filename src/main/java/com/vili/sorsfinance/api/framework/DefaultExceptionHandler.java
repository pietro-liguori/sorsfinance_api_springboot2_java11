package com.vili.sorsfinance.api.framework;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.vili.sorsfinance.api.exceptions.DatabaseException;
import com.vili.sorsfinance.api.exceptions.EnumValueNotFoundException;
import com.vili.sorsfinance.api.exceptions.InvalidRequestParamException;
import com.vili.sorsfinance.api.exceptions.NoSuchEntityException;
import com.vili.sorsfinance.api.exceptions.NoSuchPathException;
import com.vili.sorsfinance.api.exceptions.ResourceNotFoundException;
import com.vili.sorsfinance.api.exceptions.StandardError;
import com.vili.sorsfinance.api.exceptions.ValidationError;

@ControllerAdvice
public class DefaultExceptionHandler {
	
	@ExceptionHandler(EnumValueNotFoundException.class)
	public ResponseEntity<StandardError>enumValueNotFound(EnumValueNotFoundException e, HttpServletRequest request) {
		e.printStackTrace();
		String name = "Enum value not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), name, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(NoSuchEntityException.class)
	public ResponseEntity<StandardError>enumValueNotFound(NoSuchEntityException e, HttpServletRequest request) {
		e.printStackTrace();
		String name = "Entity not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), name, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		String name = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), name, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {
		String name = "Database error";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), name, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	
	@ExceptionHandler(InvalidRequestParamException.class)
	public ResponseEntity<StandardError> database(InvalidRequestParamException e, HttpServletRequest request) {
		String name = "Invalid request parameter";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), name, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(NoSuchPathException.class)
	public ResponseEntity<StandardError> database(NoSuchPathException e, HttpServletRequest request) {
		String name = "No such path";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), name, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(PropertyReferenceException.class)
	public ResponseEntity<StandardError> database(PropertyReferenceException e, HttpServletRequest request) {
		String name = "Property not found";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), name, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
		String name = "Validation error";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ValidationError err = new ValidationError(Instant.now(), status.value(), name, e.getMessage(), request.getRequestURI());
		
		for (FieldError x : e.getBindingResult().getFieldErrors()) {
			err.add(x.getField(), x.getDefaultMessage());
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<StandardError> validation(HttpMessageNotReadableException e, HttpServletRequest request) {
		String name = "Deserialization error";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ValidationError err = new ValidationError(Instant.now(), status.value(), name, e.getMessage(), request.getRequestURI());
		String errorMsg = e.getMessage();
		String simpleMsg = errorMsg.split(";")[0];
		int fieldNameStart = errorMsg.indexOf("DTO[") + 5;
		int fieldNameEnd = errorMsg.indexOf("\"]", fieldNameStart);
		String field = errorMsg.substring(fieldNameStart, fieldNameEnd);
		err.add(field, simpleMsg);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
}
