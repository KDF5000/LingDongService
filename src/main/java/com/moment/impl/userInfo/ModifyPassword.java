package com.moment.impl.userInfo;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@WebService
public interface ModifyPassword {

	@WebMethod
	  @GET
	  @POST
	  @Path(value = "/ModifyPassword/{phoneNum}/{password}")  
	  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String modifyPassword(@PathParam("phoneNum")String phoneNum,
			          @PathParam("password")String password); 
}
