package com.boomshankar.authserver.exceptions;

/**
 * Custom exception to be thrown when user is not available in our list.
 *
 * @author admin
 *
 */
public class UserNotFoundException extends AuthenticationException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(Throwable throwable) {
        super(throwable);
    }

    public UserNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}