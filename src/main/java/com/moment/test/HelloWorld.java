package com.moment.test;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@WebService
public interface HelloWorld { 
	@WebMethod
	  @GET
	  @Path("/getHelloWorld/{name}")  
	  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML }) 
	public String sayHello(@PathParam("name")String name);
}