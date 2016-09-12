package com.moment.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.moment.beans.momentRecommend.ClassifyBean;
import com.moment.beans.momentRecommend.Comments;
import com.moment.beans.momentRecommend.Moments;
import com.moment.beans.userInfo.Notification;
import com.moment.beans.userInfo.SecretMsg;
import com.moment.beans.userInfo.UserInfo;
import com.moment.pojo.CommandBean;
import com.moment.pojo.MyOwnMoment;
import com.moment.pojo.MyOwnPageResult;
import com.moment.pojo.ResultUserInfo;
import com.moment.pojo.UserDetailInfo;

public class CommanFunc {
	
	/**
	 * 获取系统当前时间
	 * @return
	 */
	public static String getSystemDate()
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		df.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		Date nowTime = new Date();// new Date()为获取当前系统时间
		return df.format(nowTime);
	}
	
	/**
	 * 设置最普通command返回值
	 * @param status
	 * @param msg
	 * @param result
	 * @return
	 */
	public static CommandBean setCommandBeanContent(Integer status,String msg,String result)
	{
		CommandBean command = new CommandBean();
		command.setStatus(status);
		command.setMsg(msg);
		command.setResult(result);
		return command;
	}
	
	
	/**
	 * 设置最普通command返回值,返回我的灵感
	 * @param status
	 * @param msg
	 * @param result
	 * @return
	 */
	public static CommandBean setCommandBeanContent(Integer status,String msg,int flag,List<MyOwnMoment> myOwnMomentList)
	{
		CommandBean command = new CommandBean();
		command.setStatus(status);
		command.setMsg(msg);
		if(null != myOwnMomentList)
		{
	        String result = "{\"moment\":" + JsonOperator.toJson(myOwnMomentList) + "}";
	        command.setResult(result);
		}
		else
		{
			command.setResult("");
		}
		return command;
	}
	
	
	/**
	 * 设置返回信息内容函数，返回用户信息
	 * @param status 返回状态
	 * @param msg  返回信息
	 * @return
	 */
	public static CommandBean setCommandBeanContent(Integer status,String msg,UserInfo userInfo,Integer isNewUser)
	{
		CommandBean command = new CommandBean();
		command.setStatus(status);
		command.setMsg(msg);
		if(null != userInfo)
		{
			ResultUserInfo resultUserInfo = new ResultUserInfo(userInfo.getId(),userInfo.getUser_name(),userInfo.getPhone_num(),
		               userInfo.getHead_image(),userInfo.getUserArea(),userInfo.getSignature(),userInfo.getSex(),null,null,null,null,null,null,userInfo.getBirthday(),isNewUser);
	        String result = "{\"user\":" + JsonOperator.toJson(resultUserInfo) + "}";
	        command.setResult(result);
		}
		else
		{
			command.setResult("");
		}
		return command;
	}
	
	/**
	 * 设置返回信息内容函数，返回推荐用户列表
	 * @param status 返回状态
	 * @param msg  返回信息
	 * @return
	 */
	public static CommandBean setCommandBeanContent(Integer status,String msg,List<UserInfo> friendList)
	{
		CommandBean command = new CommandBean();
		command.setStatus(status);
		command.setMsg(msg);
		if(null != friendList)
		{
			List<ResultUserInfo> freinds = new ArrayList<ResultUserInfo>();
			for(UserInfo userInfo : friendList)
			{
			  ResultUserInfo resultUserInfo = new ResultUserInfo(userInfo.getId(),userInfo.getUser_name(),null,
		               userInfo.getHead_image(),null,userInfo.getSignature(),userInfo.getSex(),1,
		               null,null,null,null,null,null,null);
			  freinds.add(resultUserInfo);
			}
	        String result = "{\"user\":" + JsonOperator.toJson(freinds) + "}";
	        command.setResult(result);
		}
		else
		{
			command.setResult("");
		}
		return command;
	}
	
	/**
	 * 设置返回信息内容函数，返回一个用户查看另一个用户信息界面的数据
	 * @param status 返回状态
	 * @param msg  返回信息
	 * @return
	 */
	public static CommandBean setCommandBeanContent(Integer status,String msg,UserInfo userInfo,UserDetailInfo userDetailInfo,int isAttented,int praisesNum)
	{
		CommandBean command = new CommandBean();
		command.setStatus(status);
		command.setMsg(msg);
		if(null != userInfo)
		{
			ResultUserInfo resultUserInfo = new ResultUserInfo(userInfo.getId(),userInfo.getUser_name(),null,
		               userInfo.getHead_image(),userInfo.getUserArea(),userInfo.getSignature(),userInfo.getSex(),
		               isAttented,userDetailInfo.getMomentNum(),userDetailInfo.getWatchNum(),userDetailInfo.getAttentionNum(),userDetailInfo.getFansNum(),praisesNum,userInfo.getBirthday(),null);
	        String result = "{\"user\":" + JsonOperator.toJson(resultUserInfo) + "}";
	        command.setResult(result);
		}
		else
		{
			command.setResult("");
		}
		return command;
	}
	
	/**
	 * 设置返回信息内容函数，返回用户信息列表
	 * @param status 返回状态
	 * @param msg  返回信息
	 * @return
	 */
	public static CommandBean setCommandBeanContent(Integer status,String msg,List<UserInfo> userList,List<Integer> isAttentions)
	{
		CommandBean command = new CommandBean();
		command.setStatus(status);
		command.setMsg(msg);
		if(null != userList)
		{
			List<ResultUserInfo> resultUserList = new ArrayList<ResultUserInfo>();
			int index = 0;
			for(UserInfo userInfo : userList)
			{
				ResultUserInfo resultUserInfo = new ResultUserInfo(userInfo.getId(),userInfo.getUser_name(),null,
			               userInfo.getHead_image(),null,userInfo.getSignature(),null,isAttentions.get(index),null,null,null,null,null,null,null);
				resultUserList.add(resultUserInfo);
				index++;
			}
	        String result = "{\"user\":" + JsonOperator.toJson(resultUserList) + "}";
	        command.setResult(result);
		}
		else
		{
			command.setResult("");
		}
		return command;
	}
	
	/**
	 * 设置返回信息，返回通知消息列表
	 * @param status
	 * @param msg
	 * @param momentList
	 * @param focuses
	 * @return
	 */
	public static CommandBean setCommandBeanContent(Integer status,String msg,List<Notification> notifications,int flag,int flag2)
	{
		CommandBean command = new CommandBean();
		command.setStatus(status);
		command.setMsg(msg);
		if(null != notifications)
		{
			String notificationList = "{\"Notification\":" + JsonOperator.toJson(notifications)  + "}";
			command.setResult(notificationList);
		}
		else
		{
			command.setResult("");
		}
		return command;
	}
	
	/**
	 * 设置返回信息，返回通知私信列表
	 * @param status
	 * @param msg
	 * @param momentList
	 * @param focuses
	 * @return
	 */
	public static CommandBean setCommandBeanContent(Integer status,String msg,int flag,List<SecretMsg> secretMsgList,int flag2)
	{
		CommandBean command = new CommandBean();
		command.setStatus(status);
		command.setMsg(msg);
		String secretMsgs = "";
		if(1 == flag)
	         secretMsgs = "{\"Message\":" + JsonOperator.toJson(secretMsgList)  + "}";
		else secretMsgs = "{\"message\":" + JsonOperator.toJson(secretMsgList)  + "}";
		command.setResult(secretMsgs);
		return command;
	}
	
	/**
	 * 设置返回信息，返回评论内容
	 * @param status
	 * @param msg
	 * @param momentList
	 * @param focuses
	 * @return
	 */
	public static CommandBean setCommandBeanContent(Integer status,String msg,Comments comment)
	{
		CommandBean command = new CommandBean();
		command.setStatus(status);
		command.setMsg(msg);
		if(null != comment)
		{
			String commentStr = "{\"comment\":" + JsonOperator.toJson(comment)  + "}";
			command.setResult(commentStr);
		}
		else
		{
			command.setResult("");
		}
		return command;
	}
	
	/**
	 * 设置返回信息，返回灵感列表
	 * @param status
	 * @param msg
	 * @param momentList
	 * @param focuses
	 * @return
	 */
	public static CommandBean setCommandBeanContent(Integer status,String msg,List<Moments> momentList,int flag)
	{
		CommandBean command = new CommandBean();
		command.setStatus(status);
		command.setMsg(msg);
		if(null != momentList)
		{
			String moments = "{\"momentList\":" + JsonOperator.toJson(momentList)  + "}";
			command.setResult(moments);
		}
		else
		{
			command.setResult("");
		}
		return command;
	}
	
	/**
	 * 设置返回信息，返回灵感详细信息和部分评论内容
	 * @param status
	 * @param msg
	 * @param moment
	 * @param commentList
	 * @return
	 */
	public static CommandBean setCommandBeanContent(Integer status,String msg,Moments moment,List<Comments> commentList)
	{
		CommandBean command = new CommandBean();
		command.setStatus(status);
		command.setMsg(msg);
		if(null != moment)
		{
			String moments = "\"moment\":" + JsonOperator.toJson(moment);
			String result = "{" + moments + "}"; 
			command.setResult(result);
		}
		else
		{
			command.setResult("");
		}
		return command;
	}
	
	/**
	 * 设置返回信息，返回指定页大小数量和指定页的评论内容列表,为了不产生歧义，故在函数参数前面加一个没有什么意义的flag
	 * @param status
	 * @param msg
	 * @param commentList
	 * @return
	 */
	public static CommandBean setCommandBeanContent(int flag,Integer status,String msg,List<Comments> commentList)
	{
		CommandBean command = new CommandBean();
		command.setStatus(status);
		command.setMsg(msg);
		if(null != commentList)
		{
			String comments = "{\"commentList\":" + JsonOperator.toJson(commentList)  + "}";
			command.setResult(comments);
		}
		else
		{
			command.setResult("");
		}
		return command;
	}
	
	/**
	 * 设置返回信息，返回分类列表信息
	 * @param status
	 * @param msg
	 * @param commentList
	 * @return
	 */
	public static CommandBean setCommandBeanContent(Integer status,int flag,String msg,List<ClassifyBean> classifyList)
	{
		CommandBean command = new CommandBean();
		command.setStatus(status);
		command.setMsg(msg);
		if(null != classifyList)
		{
			String classifies = "{\"channelList\":" + JsonOperator.toJson(classifyList)  + "}";
			command.setResult(classifies);
		}
		else
		{
			command.setResult("");
		}
		return command;
	}
	
	/**
	 * 设置返回信息，返回我的页面信息
	 * @param status
	 * @param msg
	 * @param commentList
	 * @return
	 */
	public static CommandBean setCommandBeanContent(Integer status,String msg,int flag,MyOwnPageResult myOwnPageResult,UserInfo userInfo)
	{
		CommandBean command = new CommandBean();
		command.setStatus(status);
		command.setMsg(msg);
		if(null != userInfo)
		{
			ResultUserInfo resultUserInfo = new ResultUserInfo(userInfo.getId(),userInfo.getUser_name(),null,
		               userInfo.getHead_image(),userInfo.getUserArea(),userInfo.getSignature(),userInfo.getSex(),
		               null,null,myOwnPageResult.getWatchNum(),myOwnPageResult.getAttentionNum(),myOwnPageResult.getFansNum(),myOwnPageResult.getPraiseNum(),null,null);
	        String result = "{\"user\":" + JsonOperator.toJson(resultUserInfo) + "}";
	        command.setResult(result);
		}
		else
		{
			command.setResult("");
		}
		return command;
	}

}
