package com.example.contentsearch.exceptions;

/**
 * Thrown to indicate that the parameters entered are invalid.
 */
public class InvalidParametersException extends RuntimeException {

	public InvalidParametersException(String message) {
		super(message);
	}
}
