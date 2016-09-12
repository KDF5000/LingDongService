package com.moment.impl.momentRecommend;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@WebService
@Path("/Attention")
public interface Attention {

	@WebMethod
	  @POST
	  @Path("/getMomentList")  
	  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String getMomentList(@FormParam("userId") Integer userId, @FormParam("pageNum") Integer pageNum,
			           @DefaultValue("10")@FormParam("pageSize") Integer pageSize);  
}
