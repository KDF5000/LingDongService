package com.moment.impl.userInfo;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@WebService
@Path("/UserInfo") 
public interface UserOwnInfo {

	@WebMethod
	  @POST
	  @Path("/getUserOwnInfo")  
	  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String getUserOwnInfo(@FormParam("userId") Integer userId); 
	
	@WebMethod
	  @POST
	  @Path("/getOtherUserInfo")  
	  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String getOtherUserInfo(@FormParam("userId") Integer userId,@FormParam("otherUserId") Integer otherUserId); 
	
	@WebMethod
	  @POST
	  @Path("/getCollectMoments")  
	  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String getCollectMoments(@FormParam("userId") Integer userId, @FormParam("pageNum") Integer pageNum,
	           @DefaultValue("10")@FormParam("pageSize") Integer pageSize); 
	
	@WebMethod
	  @POST
	  @Path("/getPraiseMoments")  
	  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String getPraiseMoments(@FormParam("userId") Integer userId, @FormParam("pageNum") Integer pageNum,
	           @DefaultValue("10")@FormParam("pageSize") Integer pageSize); 
	
	@WebMethod
	  @POST
	  @Path("/getFans")  
	  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String getFans(@FormParam("userId") Integer userId, @FormParam("pageNum") Integer pageNum,
	           @DefaultValue("10")@FormParam("pageSize") Integer pageSize); 
	
	@WebMethod
	  @POST
	  @Path("/getAtentionUsers")  
	  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String getAtentionUsers(@FormParam("userId") Integer userId, @FormParam("pageNum") Integer pageNum,
	           @DefaultValue("10")@FormParam("pageSize") Integer pageSize); 
	
	@WebMethod
	  @POST
	  @Path("/getMyOwnMoments")  
	  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String getMyOwnMoments(@FormParam("userId") Integer userId, @FormParam("pageNum") Integer pageNum,
	           @DefaultValue("10")@FormParam("pageSize") Integer pageSize);
	
	@WebMethod
	  @POST
	  @Path("/getOtherUserFans")  
	  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String getOtherUserFans(@FormParam("userId") Integer userId, @FormParam("pageNum") Integer pageNum,
	           @DefaultValue("10")@FormParam("pageSize") Integer pageSize,@FormParam("otherUserId") Integer otherUserId);
	
	@WebMethod
	  @POST
	  @Path("/getOtherUserAttentions")  
	  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String getOtherUserAttentions(@FormParam("userId") Integer userId, @FormParam("pageNum") Integer pageNum,
	           @DefaultValue("10")@FormParam("pageSize") Integer pageSize,@FormParam("otherUserId") Integer otherUserId);
	
	@WebMethod
	  @POST
	  @Path("/getNewFans")  
	  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String getNewFans(@FormParam("userId") Integer userId); 
}
