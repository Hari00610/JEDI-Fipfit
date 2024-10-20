package com.flipkart.exceptions;

public class GymOwnerNotFoundException extends RuntimeException{
    public GymOwnerNotFoundException(String gymOwnerId){
        super("Gym Owner with ID: " + gymOwnerId + " does not exist.");
    }
}
