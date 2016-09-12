package com.moment.impl.notification;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@WebService
@Path("/Notification") 
public interface Notification {
	
	@WebMethod
	  @POST
	  @Path("/getNotification")  
	  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String getNotification(@FormParam("userId") Integer userId);
	
	@WebMethod
	  @POST
	  @Path("/updateNotifyState")  
	  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String updateNotifyState(@FormParam("userId") Integer userId);
	
	@WebMethod
	  @POST
	  @Path("/updateNewFansState")  
	  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String updateNewFansState(@FormParam("userId") Integer userId);

}
