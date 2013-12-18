package com.djordje.apps.errorhandling;

public class TermAlreadyExistsException extends RuntimeException {

    public TermAlreadyExistsException(String message) {
        super(message);
    }
}
