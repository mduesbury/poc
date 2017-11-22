package com.example.contentsearch.healthcheck;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Endpoint for the health check resource.
 */
@Path("/healthcheck")
@Api(value = "/healthcheck", description = "SKY Parental Control Healthcheck")
public class HealthCheckEndpoint {

	/**
	 * GET request for the health check resource.
	 * </p>
	 * Always returns 200 Success and a response body containing the text "Ok" when server is
	 * up and running.
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@ApiOperation(value = "Checks if the application is running", response = String.class)
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Application is running", response = String.class)})
	public Response healthCheck() {
		return Response.ok("Ok")
			.build();
	}
}