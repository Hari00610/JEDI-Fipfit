package com.flipkart.exceptions;

public class DataEntryException extends RuntimeException{
    public DataEntryException(){
        super("Incorrect Data format. Please refer to the format.");
    }

}
