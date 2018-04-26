package com.wut.user;

public class LoginUser {
	/*
	 * 
	 * 创建登陆用户类 State登陆状态 userName登陆名 password登陆密码 返回的userID 返回的Token
	 * 
	 */
	private String State;
	private String userName;
	private String password;
	private String userID;
	private String Token;

	public LoginUser(String State, String userName, String password, String userID, String Token) {
		this.State = State;
		this.userName = userName;
		this.password = password;
		this.userID = userID;
		this.Token = Token;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getToken() {
		return Token;
	}

	public void setToken(String token) {
		Token = token;
	}

}
