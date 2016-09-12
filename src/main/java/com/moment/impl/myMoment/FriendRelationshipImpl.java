package com.moment.impl.myMoment;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.moment.pojo.CommandBean;
import com.moment.service.myMoment.FriendRelationshipService;
import com.moment.util.CommanFunc;
import com.moment.util.JsonOperator;

public class FriendRelationshipImpl implements FriendRelationship{
	@Autowired
	private FriendRelationshipService friendRelationshipService;

	@Override
	public String addAttention(Integer userId, Integer attentionUserId,Integer isAddFocus) {
		if(null == attentionUserId||null == userId||null == isAddFocus)
			return JsonOperator.toJson(CommanFunc.setCommandBeanContent(500, "请求参数错误，请检查参数", ""));
		CommandBean command = friendRelationshipService.attentionFriend(userId, attentionUserId, isAddFocus);
		String json_result = JsonOperator.toJson(command);
		System.out.println("----------------" + json_result + "------------------------\n");
		return json_result;
	}
	
	@Override
	public String getRecommendFriends(Integer userId) {
		if(null == userId)
			return JsonOperator.toJson(CommanFunc.setCommandBeanContent(500, "请求参数错误，请检查参数", ""));
		CommandBean command = friendRelationshipService.getRecommendFriends(userId);
		String json_result = JsonOperator.toJson(command);
		System.out.println("----------------" + json_result + "------------------------\n");
		return json_result;
	}
	
	@Override
	public String addNewFriends(Integer userId, String focusList) {
		if(null == userId||null == focusList)
			return JsonOperator.toJson(CommanFunc.setCommandBeanContent(500, "请求参数错误，请检查参数", ""));
		CommandBean command = friendRelationshipService.addNewFriends(userId, focusList);
		String json_result = JsonOperator.toJson(command);
		System.out.println("----------------" + json_result + "------------------------\n");
		return json_result;
	}
	
	@Override
	public String getOtherUserMoments(Integer userId, Integer pageNum,
			Integer pageSize, Integer otherUserId) {
		if(null == userId||null == pageNum||null == pageSize||null == otherUserId)
			return JsonOperator.toJson(CommanFunc.setCommandBeanContent(500, "请求参数错误，请检查参数", ""));
		CommandBean command = friendRelationshipService.getMomentList(userId, otherUserId, pageNum, pageSize);
		String json_result = JsonOperator.toJson(command);
		System.out.println("----------------" + json_result + "------------------------\n");
		return json_result;
	}
	
	@Override
	public String getOtherUserWatchedMoments(Integer userId, Integer pageNum,
			Integer pageSize, Integer otherUserId) {
		if(null == userId||null == pageNum||null == pageSize||null == otherUserId)
			return JsonOperator.toJson(CommanFunc.setCommandBeanContent(500, "请求参数错误，请检查参数", ""));
		CommandBean command = friendRelationshipService.getOtherUserWathedMomentList(userId, otherUserId, pageNum, pageSize);
		String json_result = JsonOperator.toJson(command);
		System.out.println("----------------" + json_result + "------------------------\n");
		return json_result;
	}

	public FriendRelationshipService getFriendRelationshipService() {
		return friendRelationshipService;
	}

	@Resource
	public void setFriendRelationshipService(FriendRelationshipService friendRelationshipService) {
		this.friendRelationshipService = friendRelationshipService;
	}
}
