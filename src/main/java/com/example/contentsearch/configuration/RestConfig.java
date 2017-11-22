package com.example.contentsearch.configuration;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.contentsearch.exceptions.InvalidParametersException;
import com.example.contentsearch.exceptions.mappers.ResourceNotFoundExceptionMapper;
import com.example.contentsearch.exceptions.mappers.WebApplicationExceptionMapper;

public class RestConfig extends ResourceConfig {

	public static final String NAME = "mark-web-app";
	public static final String VERSION = "1.0.0";
	final static Logger LOG = LoggerFactory.getLogger(RestConfig.class);

	public RestConfig() {
		LOG.info("Initializing RestConfig - called by Jersey framework to bootstrap the Jersey servlet");

		LOG.debug("Recursively registering Jersey components (those containing @Path annotation) in application base package and swagger");
		packages("com.example.contentsearch", "io.swagger.jersey.listing");

		LOG.debug("Registering a RequestContextFilter so we can use Spring injection");
		register(RequestContextFilter.class);

		LOG.debug("Registering exception mapper classes");
		register(ResourceNotFoundExceptionMapper.class);
		register(WebApplicationExceptionMapper.class);
		register(InvalidParametersException.class);
	}
}
