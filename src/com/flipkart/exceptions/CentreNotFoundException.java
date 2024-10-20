package com.flipkart.exceptions;

public class CentreNotFoundException extends RuntimeException{
    public CentreNotFoundException(String gymId){
        super("Gym Centre" + gymId + " not found!");
    }

}
