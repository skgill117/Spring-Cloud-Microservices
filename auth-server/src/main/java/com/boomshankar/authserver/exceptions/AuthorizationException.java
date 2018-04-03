package com.boomshankar.authserver.exceptions;

/**
 *
 * Throw exception of this type when user has valid credentials but does not
 * have sufficient permission to perform necessary operation.
 *
 * @see HasPermission.
 *
 * @author Mehul Shah
 * @version 1.0
 * @since 12-Feb-2017
 *
 */
public class AuthorizationException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public AuthorizationException(String message) {
		super(message);
	}

	public AuthorizationException(Throwable t) {
		super(t);
	}

	public AuthorizationException(String message, Throwable t) {
		super(message, t);
	}

}
