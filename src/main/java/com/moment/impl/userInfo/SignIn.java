package com.moment.impl.userInfo;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@WebService
@Path("/SignIn") 
public interface SignIn {
    
	@WebMethod
	  @POST
	  @Path("/login")  
	  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String login(@FormParam("mobilePhone") String mobilePhone, @FormParam("password") String password, 
			    @FormParam("packname") String packname,@FormParam("deviceId") String deviceId);  
	
	@WebMethod
	  @POST
	  @Path("/thirdparty/login")  
	  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String thirdpartyLogin(@FormParam("identifier") String identifier, @FormParam("logintype") String logintype, 
			    @FormParam("nickName") String nickName,@FormParam("userAvatar") String userAvatar,
			    @FormParam("sex") Integer sex); 
}
