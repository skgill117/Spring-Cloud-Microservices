package com.boomshankar.authserver.exceptions;

/**
 * This class is use when user is unable to login because of invalid
 * credentials.
 *
 * @author Mehul Shah
 * @version 1.0
 * @since 12-Feb-2017
 */
public class InvalidPasswordException extends AuthenticationException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public InvalidPasswordException(String message) {
        super(message);
    }

    public InvalidPasswordException(Throwable t) {
        super(t);
    }

    public InvalidPasswordException(String message, Throwable t) {
        super(message, t);
    }

}
