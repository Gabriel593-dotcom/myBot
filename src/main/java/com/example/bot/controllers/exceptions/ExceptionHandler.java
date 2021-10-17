package com.example.bot.controllers.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.example.bot.service.exceptions.ResourceAlreadyExistsException;
import com.example.bot.service.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ExceptionHandler {

	private ResponseEntity<StandardError> handlerBuilder(String error, HttpStatus status, Exception e,
			HttpServletRequest request) {
		StandardError standardError = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(standardError);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFOund(HttpServletRequest request, ResourceNotFoundException e) {
		String error = "Resource not found.";
		HttpStatus status = HttpStatus.NOT_FOUND;
		return handlerBuilder(error, status, e, request);

	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(ResourceAlreadyExistsException.class)
	public ResponseEntity<StandardError> resourceAlreadyExists(HttpServletRequest request, ResourceAlreadyExistsException e){
		String error = "Resource Already Exists.";
		HttpStatus status = HttpStatus.NOT_FOUND;
		return handlerBuilder(error, status, e , request);
	}
}
