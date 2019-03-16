package com.springtutorial48.spring.web.controllers;

import org.springframework.dao.DataAccessException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {
	
	@ExceptionHandler(DataAccessException.class)
	public String handleDatabaseException(DataAccessException ex) {
		return "databaseError";
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public String handleDatabaseException(AccessDeniedException ex) {
		return "denied";
	}

}
