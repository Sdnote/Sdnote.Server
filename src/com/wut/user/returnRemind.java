package com.wut.user;

public class returnRemind {
	private String remindID ;//提醒ID
	private String Title ;//用户标题
	private String Time ;//记录时间
	private String stratTime ;//开始时间
	private String endTime ;//提醒时间
	private String remindTime ;//结束提醒时间
	private String Content;//标签内容
	private String Tag ;//标签类别
	
	public returnRemind(String remindID,String Title,String Time,String stratTime,String endTime,String remindTime,String Content,String Tag){
		this.remindID = remindID;
		this.Title = Title;
		this.Time = Time;
		this.stratTime = stratTime;
		this.endTime = endTime;
		this.remindTime = remindTime;
		this.Content = Content;
		this.Tag = Tag;

	}

	

	public String getRemindID() {
		return remindID;
	}

	public void setRemindID(String remindID) {
		this.remindID = remindID;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		Time = time;
	}

	public String getStratTime() {
		return stratTime;
	}

	public void setStratTime(String stratTime) {
		this.stratTime = stratTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getRemindTime() {
		return remindTime;
	}

	public void setRemindTime(String remindTime) {
		this.remindTime = remindTime;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getTag() {
		return Tag;
	}

	public void setTag(String tag) {
		Tag = tag;
	}
	
	
}
