package com.github.ironr.magicmirror.routes;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;


@Path("/hello")
public class Test {

	
	
	@GET
	@Path("/{test}")
	public Response getIt(@PathParam("test") String msg) {
		return Response.status(200).entity("Hello "+msg + " welcome to magic mirror!!").build();
	}
	
}
