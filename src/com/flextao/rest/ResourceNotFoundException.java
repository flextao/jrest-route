
package com.flextao.rest;

public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -301250111003112201L;

    public ResourceNotFoundException(ResourceInfo info) {
        this(info.resourceNotFoundMessage());
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

}
