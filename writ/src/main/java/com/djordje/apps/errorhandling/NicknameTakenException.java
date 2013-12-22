package com.djordje.apps.errorhandling;

/**
 * Created with IntelliJ IDEA.
 * User: pro
 * Date: 22/12/13
 * Time: 15:41
 * To change this template use File | Settings | File Templates.
 */
public class NicknameTakenException extends RuntimeException {

    public NicknameTakenException(String message) {
        super(message);
    }
}
