package com.example.contentsearch.exceptions.mappers;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import com.example.contentsearch.exceptions.ResourceNotFoundException;

/**
  * Takes a ResourceNotFoundException and maps it on to a Response with a status code of 404 Not
  * Found and an entity body of the exception message, returned with a MediaType of text/plain.
  */
public class ResourceNotFoundExceptionMapper implements ExceptionMapper<ResourceNotFoundException> {
	@Override
	public Response toResponse(ResourceNotFoundException exception) {
		return Response
			.status(Response.Status.NOT_FOUND)
			.entity("")
			.type(MediaType.TEXT_PLAIN_TYPE)
			.build();
	}
}
