package com.flipkart.exceptions;

public class InvalidChoiceException extends RuntimeException{
    public InvalidChoiceException(){
        super("Please enter valid choice");
    }

}
