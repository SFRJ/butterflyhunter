package main.java.com.djordje.apps.core.errors;

public class TermAlreadyExistsException extends RuntimeException {

    public TermAlreadyExistsException(String message) {
        super(message);
    }
}
