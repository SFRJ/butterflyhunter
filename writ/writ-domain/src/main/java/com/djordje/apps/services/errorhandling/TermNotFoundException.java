package main.java.com.djordje.apps.services.errorhandling;

/**
 * Created with IntelliJ IDEA.
 * User: pro
 * Date: 19/12/13
 * Time: 19:50
 * To change this template use File | Settings | File Templates.
 */
public class TermNotFoundException extends RuntimeException {
    public TermNotFoundException(String message) {
        super(message);
    }
}
