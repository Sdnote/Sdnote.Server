package com.wut.user;

import javax.xml.bind.annotation.XmlRootElement;

//返回的注册用户的信息
/*
 *是否有重复
 * 
 */
@XmlRootElement
public class ReturnUser {
	private String UserName;
	private String Email;
	private String phone;
	
	public ReturnUser(String UserName, String Email ,String phone){
		this.UserName = UserName;
		this.Email = Email;
		this.phone = phone;
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
