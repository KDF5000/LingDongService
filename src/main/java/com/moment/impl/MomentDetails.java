package com.moment.impl;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@WebService
@Path("/MomentDetails") 
public interface MomentDetails {

	@WebMethod
	  @POST
	  @Path("/getMomentDetails")  
	  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String getMomentDetails(@FormParam("userId") Integer userId,@FormParam("momentId") Integer momentId);
}
