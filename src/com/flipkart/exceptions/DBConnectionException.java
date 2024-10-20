package com.flipkart.exceptions;

public class DBConnectionException extends Exception{
    public DBConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

}
