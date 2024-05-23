package com.banco.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.http.HttpStatus;

@ControllerAdvice
public class ExceptionHandler {
	
	@org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
	public ResponseEntity<String> exceptionHandler(Exception err) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getMessage());
	}
}
