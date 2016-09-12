package com.moment.impl.momentRecord;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@WebService
@Path("/Comment")
public interface AddComment {

	@WebMethod
	  @POST
	  @Path("/addComment")  
	  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String addComment(@FormParam("userId") Integer userId,@FormParam("momentId") Integer momentId,
			               @FormParam("repalyUserId") Integer repalyUserId,@FormParam("content") String content);
}
