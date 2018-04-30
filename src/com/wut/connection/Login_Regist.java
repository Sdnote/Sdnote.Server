package com.wut.connection;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.wut.datebase.Data;

import com.wut.user.LoginUser;
import com.wut.user.ReturnUser;
import com.wut.user.User;
import com.wut.user.returnNote;

import net.sf.json.JSONObject;

// 有关于用户注册，用户信息修改的相关API

@Path("/Login")

public class Login_Regist {
	Data date = new Data();

	// 注册
	@POST
	@Path("/register")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ReturnUser main(Object name) {
		// 将传过来json转化成Object，然后通过强制转化成JSONObject
		JSONObject RequestUser = JSONObject.fromObject(name);
		// 读取JSON的数据
		// 防止 传过来的json信息不完全
		String userName = "";
		String password = "";
		String Email = "";
		String phone = "";
		int age = 0;
		int sex = 0;
		try {
			userName = (String) RequestUser.get("userName");
			password = (String) RequestUser.get("password");
			Email = (String) RequestUser.get("Email");
			phone = (String) RequestUser.get("phone");
			age = RequestUser.getInt("age");
			sex = RequestUser.getInt("sex");
		} catch (Exception e) {
			return new ReturnUser("body error", "body error", "body error", "body error");
		}

		// new一个user对象
		User useruser = new User(userName, password, Email, phone, age, sex);
		// 传入一个user对象给数据库校验，返回校验成果。
		ReturnUser name1 = date.validateregister(useruser);
		System.out.println(name1);
		return name1;
	}

	// 登陆
	@POST
	@Path("/Login")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public LoginUser test(Object name) throws SQLException {
		JSONObject RequestUser = JSONObject.fromObject(name);
		String userName = "";
		String password = "";
		try {

			userName = RequestUser.getString("userName");
			password = RequestUser.getString("password");
			
		} catch (Exception e) {
			return new LoginUser("body error", "body error", "body error", "body error", "body error");
		}
		
		LoginUser user = date.validateUser(userName, password);
		return user;
	}

	// 找回/修改 密码

	@POST
	@Path("/backPw")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Map<String, String> backPssword(Object name) {
		JSONObject RequestUser = JSONObject.fromObject(name);
		// 选择找回还是修改密码,返回值是
		// 1：修改密码
		// 2：找回密码

		try {

			int choose = RequestUser.getInt("choose");
			if (choose == 1) { // 修改密码
				String userName = RequestUser.getString("userName");
				String Oldpassword = RequestUser.getString("OldPassword");
				String newpassword = RequestUser.getString("NewPassword");
				Map<String, String> return1 = date.modifyPassword(userName, Oldpassword, newpassword);

				return return1;
			}

			if (choose == 2) {// 找回密码

				String userName = RequestUser.getString("userName");
				String newpassword = RequestUser.getString("NewPassword");
				String phone = RequestUser.getString("phone");
				Map<String, String> return1 = date.backPassword(userName, newpassword, phone);
				return return1;
			}

		} catch (Exception e) {
			Map<String, String> a1 = new HashMap<String, String>();
			
			a1.put("state", "500");
			a1.put("information", "body error");
			return a1;
		}

		Map<String, String> a1 = null;
		a1.put("state", "400");
		a1.put("information", "choose error");
		return a1;

	}

}
