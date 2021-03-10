package com.stacksimplify.restservices.exceptions;

public class UserNotFoundException extends Exception {
    public static final long serialVersionUID = 1L;

    public UserNotFoundException() {
        super("User Not Found");
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
