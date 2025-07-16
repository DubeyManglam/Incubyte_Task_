package com.incubyte.sweetshop.customizedExceptions;

public class SweetAlreadyExistsException extends RuntimeException{
        public SweetAlreadyExistsException(String message) {
            super(message);
        }
}
