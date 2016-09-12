package com.moment.impl.momentRecord;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@WebService
@Path("/MomentOperate")
public interface MomentOperate {

	@WebMethod
	  @POST
	  @Path("/collectMoment")  
	  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String collectMoment(@FormParam("userId") Integer userId,@FormParam("momentId") Integer momentId,
			               @FormParam("isAddWatch") Integer isAddWatch);
	
	@WebMethod
	  @POST
	  @Path("/praiseMoment")  
	  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String praiseMoment(@FormParam("userId") Integer userId,@FormParam("momentId") Integer momentId,
			               @FormParam("isAddPraise") Integer isAddPraise);
	
	@WebMethod
	  @POST
	  @Path("/praiseComment")  
	  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String praiseComment(@FormParam("userId") Integer userId,@FormParam("commentId") Integer commentId,
			               @FormParam("isAddPraise") Integer isAddPraise);
	
	@WebMethod
	  @POST
	  @Path("/clipperMoment")  
	  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String clipperMoment(@FormParam("userId") Integer userId,@FormParam("momentId") Integer momentId,
			               @FormParam("isAddClipper") Integer isAddClipper);
	
	@WebMethod
	  @POST
	  @Path("/shareMoment")  
	  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String shareMoment(@FormParam("userId") Integer userId,@FormParam("momentId") Integer momentId);
}
