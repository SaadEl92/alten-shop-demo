package io.saad.altenshop.demo.security.exception.model;

public class AppUserNotFoundException extends RuntimeException{
	
    private static final long serialVersionUID = 1L;

	public AppUserNotFoundException() {
        super("User cannot be found");
    }
    
}
