package io.saad.altenshop.demo.security.exception.model;


public class UserAlreadyExistException extends RuntimeException{
	
    private static final long serialVersionUID = 1L;

	public UserAlreadyExistException() {
        super("Email is already in use");
    }
    
}
