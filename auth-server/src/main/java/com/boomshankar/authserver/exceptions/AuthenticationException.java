package com.boomshankar.authserver.exceptions;

/**
 * This class is use when user is unable to login because of invalid
 * credentials.
 *
 * @author Mehul Shah
 * @version 1.0
 * @since 12-Feb-2017
 */
public class AuthenticationException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public AuthenticationException(String message) {
		super(message);
	}

	public AuthenticationException(Throwable t) {
		super(t);
	}

	public AuthenticationException(String message, Throwable t) {
		super(message, t);
	}
}
