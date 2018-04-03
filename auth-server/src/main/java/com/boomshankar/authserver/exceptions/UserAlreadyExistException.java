package com.boomshankar.authserver.exceptions;

/**
 * Custom exception to be thrown when user is not available in our list.
 *
 * @author admin
 *
 */
public class UserAlreadyExistException extends AuthenticationException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public UserAlreadyExistException(String message) {
        super(message);
    }

    public UserAlreadyExistException(Throwable throwable) {
        super(throwable);
    }

    public UserAlreadyExistException(String message, Throwable throwable) {
        super(message, throwable);
    }
}