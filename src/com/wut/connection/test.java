package com.wut.connection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/test")
public class test {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String testString(){
		return "Hello World";
	}
}
