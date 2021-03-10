package com.stacksimplify.restservices.exceptions;

public class UserNotFoundException extends Exception {
    public static final long serialVersionUID = 1l;

    public UserNotFoundException() {
        super("User Not Found");
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
