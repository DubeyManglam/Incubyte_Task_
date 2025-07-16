package com.incubyte.sweetshop.customizedExceptions;

public class SweetNotFoundException extends RuntimeException {
    public SweetNotFoundException(String message) {
        super(message);
    }
}
