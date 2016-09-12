package com.moment.impl.momentRecord;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@WebService
@Path("/Moment")
public interface AddMoment { 

	@WebMethod
	  @POST
	  @Path("/addMoment")  
	  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String addMoment(@FormParam("userId") Integer userId,@FormParam("momentId") Integer momentId,
			              @FormParam("title") String title,@FormParam("postTime") String postTime,
			             @FormParam("label") String label,@FormParam("content") String content,@FormParam("contentAbstract") String contentAbstract,
			             @FormParam("isClipper") Integer isClipper,@FormParam("momentImgs") String momentImgs,
			             @FormParam("audioUrl") String audioUrl,@FormParam("isPublic") Integer isPublic,@FormParam("updateTime") String updateTime);
	
	@WebMethod
	  @POST
	  @Path("/updateRankList")  
	  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String updateRankList(@FormParam("momentId") Integer momentId);
}
