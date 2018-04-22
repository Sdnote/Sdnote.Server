package com.wut.connection;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.wut.datebase.Date;
import com.wut.user.ReturnUser;
import com.wut.user.User;

import net.sf.json.JSONObject;

// 有关于用户注册，用户信息修改的相关API

@Path("/register")

public class register {
	Date date = new Date();
	
	@POST
	@Path("/main")
	@Produces(MediaType.APPLICATION_JSON)  
    @Consumes(MediaType.APPLICATION_JSON)
	public ReturnUser main(Object  name){
		JSONObject RequestUser = JSONObject.fromObject(name);
		String user = (String)RequestUser.get("name");
		String password = (String)RequestUser.get("password");
		String Email = (String)RequestUser.get("Email");
		String phone = (String) RequestUser.get("phone");
		int age = RequestUser.getInt("age");
		int gender = RequestUser.getInt("gender");
		User useruser = new User(user , password , Email , phone , age , gender);
		ReturnUser name1 = date.validateregister(useruser);
		System.out.println(name1);
		return name1;
	}
}
