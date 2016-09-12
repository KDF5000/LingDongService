package com.moment.impl.myMoment;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@WebService
@Path("/FriendRelationship")
public interface FriendRelationship {

	@WebMethod
	  @POST
	  @Path("/addAttention")  
	  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String addAttention(@FormParam("userId") Integer userId,@FormParam("attentionUserId") Integer attentionUserId,
			                 @FormParam("isAddFocus") Integer isAddFocus);
	
	@WebMethod
	  @POST
	  @Path("/getRecommendFriends")  
	  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String getRecommendFriends(@FormParam("userId") Integer userId);
	
	@WebMethod
	  @POST
	  @Path("/addNewFriends")  
	  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String addNewFriends(@FormParam("userId") Integer userId,@FormParam("focusList") String focusList);
	
	@WebMethod
	  @POST
	  @Path("/getOtherUserMoments")  
	  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String getOtherUserMoments(@FormParam("userId") Integer userId, @FormParam("pageNum") Integer pageNum,
	           @DefaultValue("10")@FormParam("pageSize") Integer pageSize,@FormParam("otherUserId") Integer otherUserId);
	
	@WebMethod
	  @POST
	  @Path("/getOtherUserWatchedMoments")  
	  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String getOtherUserWatchedMoments(@FormParam("userId") Integer userId, @FormParam("pageNum") Integer pageNum,
	           @DefaultValue("10")@FormParam("pageSize") Integer pageSize,@FormParam("otherUserId") Integer otherUserId);
}
