package com.moment.impl.notification;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@WebService
@Path("/SecreteMessage") 
public interface SecreteMessage {

	@WebMethod
	  @POST
	  @Path("/getSecreteMessage")  
	  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String getSecreteMessage(@FormParam("userId") Integer userId);
	
	@WebMethod
	  @POST
	  @Path("/getAllSecreteMessage")  
	  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String getAllSecreteMessage(@FormParam("userId") Integer userId,@FormParam("otherUserId") Integer otherUserId,
			          @FormParam("pageNum") Integer pageNum,
	           @DefaultValue("10")@FormParam("pageSize") Integer pageSize);
	
	@WebMethod
	  @POST
	  @Path("/sendSecreteMessage")  
	  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String sendSecreteMessage(@FormParam("sendUserId") Integer sendUserId,@FormParam("recieveUserId") Integer recieveUserId,
			      @FormParam("msgContent") String msgContent);
}
