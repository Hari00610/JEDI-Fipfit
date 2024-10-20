package com.flipkart.exceptions;

public class RegistrationFailedException extends RuntimeException{
    public RegistrationFailedException(String gymOwnerId) {
        super("Failed to register Gym Owner with message: " + gymOwnerId);
    }

}
