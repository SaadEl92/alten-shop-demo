package io.saad.altenshop.demo.security.exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SecurityExceptionHandler {
	
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(BadCredentialsException.class)
	public String badCredentialsException(BadCredentialsException exception) {
		return exception.getMessage();
	}
	
	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler(AccessDeniedException.class)
	public String accessDeniedException(AccessDeniedException exception) {
		return exception.getMessage();
	}
	
}
