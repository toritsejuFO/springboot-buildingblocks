package com.stacksimplify.restservices.exceptions;

public class UsernameNotFoundException extends Exception {
    public static final long serialVersionUID = 1l;

    public UsernameNotFoundException(String message) {
        super(message);
    }
}
