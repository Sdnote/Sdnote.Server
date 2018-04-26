package com.wut.user;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.registry.infomodel.EmailAddress;

@XmlRootElement
// 用户类
// 以此作为test
public class User {

	private String user;
	// 用户名
	private String password; //
	// 密码
	private String Email;
	// 邮箱
	private String Phone;
	// 手机号
	private int age;
	// 年龄
	private int gender;

	// 性别 1为男，2为女
	public User(String user, String password, String Email, String Phone, int age, int gender) {
		this.user = user;
		this.password = password;
		this.Email = Email;
		this.Phone = Phone;
		this.age = age;
		this.gender = gender;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

}
