package io.saad.altenshop.demo.exception.model;

public class ForbiddenResourceException extends RuntimeException{
	
    private static final long serialVersionUID = 1L;

	public ForbiddenResourceException() {
        super("Resource doesn't belong to the current user");
    }
    
}