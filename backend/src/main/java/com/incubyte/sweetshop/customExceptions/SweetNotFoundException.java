package com.incubyte.sweetshop.customExceptions;

public class SweetNotFoundException extends RuntimeException {
    public SweetNotFoundException(String message) {
        super(message);
    }
}
