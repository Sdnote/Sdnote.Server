package com.wut.user;

public class returnNote {
	//返回给用户的 标签list
	private String noteID;//用户名
	private String Title;//用户标题
	private String NoteTime;//笔记(标签)时间
	private String Content;//标签内容
	private String Tags;//标签类别
	public returnNote(String noteID,String Title,String NoteTime,String Content,String Tags){
		this.noteID = noteID;
		this.Title = Title;
		this.NoteTime = NoteTime;
		this.Content = Content;
		this.Tags = Tags;
	}
	public String getNoteID() {
		return noteID;
	}
	public void setNoteID(String noteID) {
		this.noteID = noteID;
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
