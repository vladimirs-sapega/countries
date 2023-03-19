package com.example.demo.exception;

public class CountryInfoException extends RuntimeException{

    public CountryInfoException(String message, Throwable cause) {
        super(message, cause);
    }

    public CountryInfoException(String message) {

        super(message);
    }

}
