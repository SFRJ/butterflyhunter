package main.java.com.djordje.apps.core.errors;

public class AllreadyVotedOnThatTermException extends RuntimeException {

    public AllreadyVotedOnThatTermException(String message) {
        super(message);
    }
}
