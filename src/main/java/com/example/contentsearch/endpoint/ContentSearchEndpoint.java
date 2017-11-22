package com.example.contentsearch.endpoint;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.contentsearch.exceptions.InvalidParametersException;
import com.example.contentsearch.service.FileSearchService;

@Path("/")
public class ContentSearchEndpoint {

	@Inject
	private FileSearchService fileSearchService;

	@GET
	@Path("/findfiles")
	@Produces({MediaType.APPLICATION_JSON})
	public Response findFilesByKeyword(@QueryParam("term") final List<String> terms) {

		if (terms == null) {
			throw new InvalidParametersException("Invalid parameters");
		}

		try {
			List<String> foundFiles = fileSearchService.findFilesByContent(terms);
			return Response.ok(foundFiles).build();
		} catch (IOException e) {
			throw new WebApplicationException();
		}
	}
}
