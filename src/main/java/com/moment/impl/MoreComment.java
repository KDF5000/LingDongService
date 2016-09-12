package com.moment.impl;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@WebService
@Path("/Comments") 
public interface MoreComment {

	@WebMethod
	  @POST
	  @Path("/getMoreComment")  
	  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String getMoreComment(@FormParam("momentId") Integer momentId,@FormParam("pageNum") Integer pageNum,
			              @FormParam("pageSize") Integer pageSize);
}
