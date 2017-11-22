package com.example.contentsearch.exceptions.mappers;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {

	final static Logger LOG = LoggerFactory.getLogger(WebApplicationExceptionMapper.class);

	/**
	 * Takes a WebApplicationException and maps it on to a Response with a status code of 500 internal server error. 
	 *
	 * @param exception The exception to be mapped
	 * @return Response The mapped response
	 */
	@Override
	public Response toResponse(WebApplicationException exception) {
		LOG.warn("Web exception generated - " + exception.getMessage());
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
			.entity("")
			.type(MediaType.TEXT_PLAIN_TYPE)
			.build();
	}
}
