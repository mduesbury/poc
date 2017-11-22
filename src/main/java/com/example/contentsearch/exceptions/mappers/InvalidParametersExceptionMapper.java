package com.example.contentsearch.exceptions.mappers;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import com.example.contentsearch.exceptions.InvalidParametersException;

/**
 * Takes an InvalidParametersException and maps it on to a Response with a status code of 400 Bad
 * Request and an entity body of the exception message, returned with a MediaType of text/plain.
 */
public class InvalidParametersExceptionMapper implements ExceptionMapper<InvalidParametersException> {

	@Override
	public Response toResponse(InvalidParametersException exception) {
		return Response
			.status(Response.Status.BAD_REQUEST)
			.entity(exception.getMessage())
			.type(MediaType.TEXT_PLAIN_TYPE)
			.build();
	}
}
