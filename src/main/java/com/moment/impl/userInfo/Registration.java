package com.moment.impl.userInfo;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@WebService
@Path("/Registration") 
public interface Registration {

	@WebMethod
	  @POST
	  @Path("/")  
	  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String registrate(@FormParam("nickName") String nickName, @FormParam("mobilePhone") String mobilePhone, 
			                @FormParam("password") String password,
		                   @FormParam("area") String area,@FormParam("sex") Integer sex,
		                   @FormParam("userAvatar") String userAvatar,
		                   @FormParam("packname") String packname,@FormParam("deviceId") String deviceId);
	
	@WebMethod
	  @POST
	  @Path("/updateUserInfo")  
	  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String updateUserInfo(@FormParam("nickName") String nickName,@FormParam("userId") Integer userId,
		                   @FormParam("userArea") String userArea,@FormParam("sex") Integer sex,
		                   @FormParam("userAvatar") String userAvatar,
		                   @FormParam("birthday") String birthday,@FormParam("signature") String signature);
}
