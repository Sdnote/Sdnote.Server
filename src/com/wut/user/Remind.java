package com.wut.user;

import java.util.Date;

import com.wut.tool.tool;

//remind 类 用于传送
public class Remind {
	private String UserID ;//用户名
	private String Token ;//用户校验码
	private String Title ;//用户标题
	private String Time ;//记录时间
	private String stratTime ;//开始时间
	private String endTime ;//提醒时间
	private String remindTime ;//结束提醒时间
	private String Content;//标签内容
	private String Tag ;//标签类别
	
	public Remind (String UserID,String Token,String Title ,String Time,String stratTime,
			String endTime,String remindTime,String Content,String Tag){
		this.UserID = UserID;
		this.Token = Token;
		this.Title = Title;
		this.Time = Time;
		this.stratTime = stratTime;
		this.endTime = endTime;
		this.remindTime = remindTime;
		this.Content = Content;
		this.Tag = Tag;
	}

	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	public String getToken() {
		return Token;
	}
	public void setToken(String token) {
		Token = token;
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
