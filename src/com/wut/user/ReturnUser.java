package com.wut.user;

import javax.xml.bind.annotation.XmlRootElement;

//返回的注册用户的信息
/*
 *是否有重复
 * 
 */
@XmlRootElement
public class ReturnUser {
	/*
	 * 注册后返回给用户的信息 State 注册是否成功 成功为200 不成功为400 UserName 为 True 则没有重复
	 * 若为false则为有重复 Email 同上 phone 同上
	 * 
	 */
	private String State;
	private String UserName;
	private String Email;
	private String phone;

	public ReturnUser(String State, String UserName, String Email, String phone) {
		this.State = State;
		this.UserName = UserName;
		this.Email = Email;
		this.phone = phone;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
