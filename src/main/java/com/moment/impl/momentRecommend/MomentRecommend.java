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
@Path("/MomentRecommend")
public interface MomentRecommend {

	@WebMethod
	  @POST
	  @Path("/getRecommendMoment")  
	  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String getRecommendMoment(@FormParam("userId") Integer userId, @FormParam("pageNum") Integer pageNum,
			           @DefaultValue("10")@FormParam("pageSize") Integer pageSize); 
}
