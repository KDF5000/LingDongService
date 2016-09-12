package com.moment.impl.momentRecommend;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@WebService
@Path("/Classify")
public interface Classify {
	
	@WebMethod
	  @GET
	  @Path("/getClassifies")  
	  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String getMomentList();  
	
	@WebMethod
	  @POST
	  @Path("/getClassifyMoments")  
	  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String getMomentList(@FormParam("channelId") Integer channelId, @FormParam("pageNum") Integer pageNum,
	           @DefaultValue("10")@FormParam("pageSize") Integer pageSize,@FormParam("userId") Integer userId);
}
