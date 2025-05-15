package io.saad.altenshop.demo.exception.model;

public class UserNotFoundException extends RuntimeException{
	
    private static final long serialVersionUID = 1L;

	public UserNotFoundException() {
        super("User cannot be found");
    }
    
}
