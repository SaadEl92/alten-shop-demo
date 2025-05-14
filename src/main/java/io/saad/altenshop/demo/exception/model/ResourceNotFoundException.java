package io.saad.altenshop.demo.exception.model;

public class ResourceNotFoundException extends RuntimeException{
	
    private static final long serialVersionUID = 1L;

	public ResourceNotFoundException() {
        super();
    }
    
    public ResourceNotFoundException(String resourceName, Long resourceId) {
        super(resourceName + " with id: [ " + resourceId + " ] cannot be found");
    }
    
}
