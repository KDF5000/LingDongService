package com.moment.beans.myMoment;

public class CommentPraise {

	private Integer id;
	private Integer userId;
	private Integer commentId;
	
	public CommentPraise()
	{
		
	}
	
	public CommentPraise(Integer id, Integer userId, Integer commentId) {
		super();
		this.setId(id);
		this.setUserId(userId);
		this.setCommentId(commentId);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	
	
}
