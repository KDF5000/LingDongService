package com.moment.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.tencent.xinge.Message;
import com.tencent.xinge.XingeApp;

public class MessagePush {

	public static String CUSTOM_MSG_NOTIFICATION = "1";
	public static String CUSTOM_NEW_FANS = "2";
	public static String CUSTOM_CHAT_MSG = "3";
	    /**
	     * 单个设备下发透传消息
	     * @param title
	     * @param content
	     * @param account
	     * @param userName
	     */
		public static void PushSingleDeviceMessage(String title,String content,String account,String userName)
		{
			XingeApp xinge = new XingeApp(2100108290, "8b936cb725adf5e3a4ecd91f02b18cb6");
			Message message = new Message();
			message.setTitle(title);
			message.setType(Message.TYPE_MESSAGE);
			message.setExpireTime(86400);
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("userName",userName);
			map.put("message",content);
			message.setCustom(map);
			//JSONObject ret = xinge.pushSingleDevice("token", message);
			//xinge.createMultipush(message);
			xinge.pushSingleAccount(0,account, message);
			//return ret;
		}
		
		 /**
		  * 所有设备下发透传消息
		  * @param title
		  * @param content
		  */
		public static void PushAllDeviceMessage(String title,String content)
		{
			XingeApp xinge = new XingeApp(2100108290, "8b936cb725adf5e3a4ecd91f02b18cb6");
			Message message = new Message();
			message.setTitle(title);
			message.setContent(content);
			message.setType(Message.TYPE_MESSAGE);
			message.setExpireTime(86400);
			xinge.createMultipush(message);
			//return ret;
		}
		
		/**
		 * 给指定列表中用户推送消息
		 * @param title
		 * @param content
		 * @param users
		 * @param userName
		 */
		public static void pushTagAccount(String title,String content,List<Integer> users,String userName)
		{

			XingeApp xinge = new XingeApp(2100108290, "8b936cb725adf5e3a4ecd91f02b18cb6");
			Message message = new Message();
			message.setTitle(title);
			message.setType(Message.TYPE_MESSAGE);
			message.setExpireTime(86400);
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("userName",userName);
			map.put("message",content);
			message.setCustom(map);
			List<String> tagList = new ArrayList<String>();
			for(Integer userId : users)
				tagList.add("" + userId);
			xinge.pushTags(0, tagList, "OR", message);
		}
}
