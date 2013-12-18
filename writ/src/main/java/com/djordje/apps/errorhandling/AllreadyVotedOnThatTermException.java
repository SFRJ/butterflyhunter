package com.djordje.apps.errorhandling;

public class AllreadyVotedOnThatTermException extends RuntimeException {

    public AllreadyVotedOnThatTermException(String message) {
        super(message);
    }
}
