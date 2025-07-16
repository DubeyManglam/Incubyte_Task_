package com.incubyte.sweetshop.customizedExceptions;

public class SweetAlreadyExists extends RuntimeException{
        public SweetAlreadyExists(String message) {
            super(message);
        }
}
