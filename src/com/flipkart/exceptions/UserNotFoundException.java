package com.flipkart.exceptions;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(String message){
        super(message);
        System.out.println("User not found!!");
    }
}
