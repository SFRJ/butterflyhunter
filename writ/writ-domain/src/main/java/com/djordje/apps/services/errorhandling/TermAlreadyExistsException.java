package main.java.com.djordje.apps.services.errorhandling;

public class TermAlreadyExistsException extends RuntimeException {

    public TermAlreadyExistsException(String message) {
        super(message);
    }
}
