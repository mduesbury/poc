package com.example.contentsearch.exceptions;

/**
 * Thrown to indicate that the specified resource does not exist.
 */
public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException(String message) {
		super(message);
	}
}
