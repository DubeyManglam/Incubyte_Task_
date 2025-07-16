package com.incubyte.sweetshop.customExceptions;

public class SweetAlreadyExistsException extends RuntimeException{
        public SweetAlreadyExistsException(String message) {
            super(message);
        }
}
