package com.wut.user;

import java.util.Date;

public class Note {

	private String UserID;// 用户名
	private String Check;// 用户校验码
	private String Title;// 用户标题
	private String NoteTime;// 笔记(标签)时间
	private String Content;// 标签内容
	private String Tags;// 标签类别

	public Note(String UserName, String Check, String Title, String NoteTime, String Content, String Tags) {
		this.UserID = UserName;
		this.Check = Check;
		this.Title = Title;
		this.NoteTime = NoteTime;
		this.Content = Content;
		this.Tags = Tags;
	}

	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

	public String getCheck() {
		return Check;
	}

	public void setCheck(String check) {
		Check = check;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getNoteTime() {
		return NoteTime;
	}

	public void setNoteTime(String noteTime) {
		NoteTime = noteTime;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getTags() {
		return Tags;
	}

	public void setTags(String tags) {
		Tags = tags;
	}

}
