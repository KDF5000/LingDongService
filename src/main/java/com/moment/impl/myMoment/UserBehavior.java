package com.moment.impl.myMoment;

import javax.jws.WebMethod;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface UserBehavior {

	@WebMethod
	  @POST
	  @Path("/recordUserBehavior")  
	  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String recordUserBehavior(@FormParam("userId") Integer userId,@FormParam("momentId") Integer momentId,
			                 @FormParam("stickTime") Integer stickTime);
}
