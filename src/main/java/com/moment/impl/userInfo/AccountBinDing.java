package com.moment.impl.userInfo;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@WebService
@Path("/Account") 
public interface AccountBinDing {

	@WebMethod
	  @POST
	  @Path("/AccountBinding")  
	  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String accountBinding(@FormParam("userId") String userId, @FormParam("identifier") String identifier); 
}
