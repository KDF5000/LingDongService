package com.moment.service.notification;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.moment.beans.userInfo.SecretMsg;
import com.moment.beans.userInfo.UserInfo;
import com.moment.dao.notification.SecreteMessageDao;
import com.moment.pojo.CommandBean;
import com.moment.util.CommanFunc;
import com.moment.util.MessagePush;

public class SecreteMessageService {
	@Autowired
	private SecreteMessageDao secreteMessageDao;

	/**
	 * 获取用户所有私信列表，按发送者分类
	 * @param userId
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	public CommandBean getSecreteMessage(Integer userId)
	{
		List<SecretMsg> secretMsgList = secreteMessageDao.getAllSecretMsg(userId);
		HashMap<Integer,SecretMsg> hashMsg = new HashMap<Integer,SecretMsg>();
		for(SecretMsg msg : secretMsgList)
		{
			if(hashMsg.containsKey(msg.getSendUserId()))
			{
				int count = hashMsg.get(msg.getSendUserId()).getMsgCount() + 1;
				hashMsg.get(msg.getSendUserId()).setMsgCount(count);;
			}
			else
			{
				msg.setMsgCount(1);
				msg.setRecieveUserAvatar(null);
				msg.setIsRead(null);
				msg.setRecieveUserId(null);
				msg.setMsgType(null);
				msg.setMsgId(null);
				hashMsg.put(msg.getSendUserId(), msg);
			}
		}
		secretMsgList.clear();
		Iterator iter = hashMsg.entrySet().iterator();
		while (iter.hasNext()) 
		{
           Map.Entry entry = (Map.Entry) iter.next();
           SecretMsg msg = (SecretMsg) entry.getValue();
           secretMsgList.add(msg);
		}
		if(null != secretMsgList)
		{
			return CommanFunc.setCommandBeanContent(200,"用户私信列表获取成功",1,secretMsgList,1);
		}
		return CommanFunc.setCommandBeanContent(417,"用户私信列表获取失败","");
	}
	
	/**
	 * 获取用户指定发送者的私信列表
	 * @param userId
	 * @param otherUserId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("unused")
	public CommandBean getSecretMsgBySendUserId(Integer userId, Integer otherUserId,
			Integer pageNum, Integer pageSize)
	{
		List<SecretMsg> recieveMsgList = secreteMessageDao.getSecretMsgBySendUserId(userId, otherUserId, pageNum, pageSize/2);
		List<SecretMsg> sendMsgList = secreteMessageDao.getSecretMsgBySendUserId(otherUserId, userId, pageNum, pageSize/2);
		for(SecretMsg msg : recieveMsgList)
		{
			msg.setMsgType(1);
		}
		for(SecretMsg msg : sendMsgList)
		{
			msg.setMsgType(0);		
		}
		recieveMsgList.addAll(sendMsgList);
		for(SecretMsg msg : recieveMsgList)
		{
			msg.setMsgCount(null);
			msg.setIsRead(null);
			msg.setSendUserName(null);
		}
		
		 Collections.sort(recieveMsgList, new Comparator<SecretMsg>() {

			@Override
			public int compare(SecretMsg arg0, SecretMsg arg1) {
				DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
				Date firstTime = new Date();
				Date secondTime = new Date();
				try 
				{
					 firstTime = dateformat.parse(arg0.getSendTime());
					 secondTime = dateformat.parse(arg1.getSendTime());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(firstTime.before(secondTime))
				    return 1;
				return -1;
			}
	            
	        });
		if(null != recieveMsgList)
		{
			return CommanFunc.setCommandBeanContent(200,"用户私信列表获取成功",2,recieveMsgList,1);
		}
		return CommanFunc.setCommandBeanContent(417,"用户私信列表获取失败","");
	}
	
	/**
	 * 发送私信
	 * @param sendUserId
	 * @param recieveUserId
	 * @param msgContent
	 * @return
	 */
	public CommandBean sendSecretMsg(Integer sendUserId, Integer recieveUserId,
			String msgContent)
	{
		String sendTime = CommanFunc.getSystemDate();
		UserInfo sendUserInfo = secreteMessageDao.getUserInfoById(sendUserId);
		UserInfo reciveUserInfo = secreteMessageDao.getUserInfoById(recieveUserId);
		SecretMsg msg = new SecretMsg(0,sendUserId,sendUserInfo.getHead_image(),recieveUserId,reciveUserInfo.getHead_image(),
				      sendTime,msgContent,null,0,null,sendUserInfo.getUser_name());
		if(secreteMessageDao.addSecretMsg(msg))
		{
			MessagePush.PushSingleDeviceMessage(MessagePush.CUSTOM_CHAT_MSG, "你有了新私信  ", "" + recieveUserId, sendUserInfo.getUser_name());
			return CommanFunc.setCommandBeanContent(200,"用户私信发送成功","");
		}
		return CommanFunc.setCommandBeanContent(417,"用户私信发送失败","");
		
	}
	
	public SecreteMessageDao getSecreteMessageDao() {
		return secreteMessageDao;
	}

	@Resource
	public void setSecreteMessageDao(SecreteMessageDao secreteMessageDao) {
		this.secreteMessageDao = secreteMessageDao;
	}
}
