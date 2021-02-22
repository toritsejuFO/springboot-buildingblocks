package com.stacksimplify.restservices.exceptions;

public class OrderNotFoundException extends Exception {
    public static final long serialVersionUID = 1l;

    public OrderNotFoundException(String message) {
        super(message);
    }
}
