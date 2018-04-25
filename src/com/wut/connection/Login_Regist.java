package com.wut.connection;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.wut.datebase.Data;

import com.wut.user.LoginUser;
import com.wut.user.ReturnUser;
import com.wut.user.User;

import net.sf.json.JSONObject;

// 有关于用户注册，用户信息修改的相关API

@Path("/Login")

public class Login_Regist {
	Data date = new Data();
	
	@POST
	@Path("/register")
	@Produces(MediaType.APPLICATION_JSON)  
    @Consumes(MediaType.APPLICATION_JSON)
	public ReturnUser main(Object name){
		//将传过来json转化成Object，然后通过强制转化成JSONObject
		JSONObject RequestUser = JSONObject.fromObject(name);
		//读取JSON的数据
		String userName = (String)RequestUser.get("userName");
		String password = (String)RequestUser.get("password");
		String Email = (String)RequestUser.get("Email");
		String phone = (String) RequestUser.get("phone");
		int age = RequestUser.getInt("age");
		int sex = RequestUser.getInt("sex");
		//new一个user对象
		User useruser = new User(userName , password , Email , phone , age , sex);
		//传入一个user对象给数据库校验，返回校验成果。
		ReturnUser name1 = date.validateregister(useruser);
		System.out.println(name1);
		return name1;
	}
	
	@POST
    @Path("/Login")
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public LoginUser test(Object  name) throws SQLException{
		JSONObject RequestUser = JSONObject.fromObject(name);
		
		String userName = RequestUser.getString("userName");
		String password = RequestUser.getString("password");
		LoginUser user = date.validateUser(userName,password);
		return user;
    }
	
	
	
}
