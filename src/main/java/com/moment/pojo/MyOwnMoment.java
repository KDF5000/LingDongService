package com.moment.pojo;

public class MyOwnMoment {
	
	private Integer momentId;
	private String title;
	private String content;
	private String contentAbstract;
	private String momentImgs;
	private Integer dirty;
	private String label;
	private String postTime;
	private String audioUrl;
	private Integer isPublic;
	private Integer isClipper;
	
	public MyOwnMoment()
	{
		
	}
	
	public MyOwnMoment(Integer momentId, String title, String content,String contentAbstract,
			String momentImgs, Integer dirty, String label,String postTime, String audioUrl,
			Integer isPublic, Integer isClipper) {
		super();
		this.setMomentId(momentId);
		this.setTitle(title);
		this.setContent(content);
		this.setContentAbstract(contentAbstract);
		this.setMomentImgs(momentImgs);
		this.setDirty(dirty);
		this.setLabel(label);
		this.setPostTime(postTime);
		this.setAudioUrl(audioUrl);
		this.setIsPublic(isPublic);
		this.setIsClipper(isClipper);
	}

	public Integer getMomentId() {
		return momentId;
	}

	public void setMomentId(Integer momentId) {
		this.momentId = momentId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMomentImgs() {
		return momentImgs;
	}

	public void setMomentImgs(String momentImgs) {
		this.momentImgs = momentImgs;
	}

	public Integer getDirty() {
		return dirty;
	}

	public void setDirty(Integer dirty) {
		this.dirty = dirty;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getAudioUrl() {
		return audioUrl;
	}

	public void setAudioUrl(String audioUrl) {
		this.audioUrl = audioUrl;
	}

	public Integer getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(Integer isPublic) {
		this.isPublic = isPublic;
	}

	public Integer getIsClipper() {
		return isClipper;
	}

	public void setIsClipper(Integer isClipper) {
		this.isClipper = isClipper;
	}

	public String getPostTime() {
		return postTime;
	}

	public void setPostTime(String postTime) {
		this.postTime = postTime;
	}

	public String getContentAbstract() {
		return contentAbstract;
	}

	public void setContentAbstract(String contentAbstract) {
		this.contentAbstract = contentAbstract;
	}
	
	

}
