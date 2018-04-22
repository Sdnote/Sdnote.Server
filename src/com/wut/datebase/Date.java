package com.wut.datebase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;
import com.wut.user.ReturnUser;
import com.wut.user.User;


//先用于数据库连接
//后期转用Hibernate

public class Date {
	Connection conn;
	Statement stst;
	ArrayList<String> phonelist;
	ArrayList<String> Emaillist;
	public  Date(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		String url = "jdbc:mysql://47.100.21.174:3306/studentassistant?user=qq494296145&password=494296145";
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {}
		try {
			stst = (Statement) conn.createStatement();
		} catch (SQLException e) {}
		try {
			init();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/*用户注册信息验证
	 *
	 * 
	 */
	public ReturnUser validateregister(User user){
		String username = user.getUser();
		String password = user.getPassword();
		String Email = user.getEmail();
		String phone = user.getPhone();
		int age = user.getAge();
		int gender = user.getGender();
		boolean busername = true;
		boolean bphone = true;
		boolean bEmail = true;
		//电话号码是否有重复
		for (int i = 0; i <phonelist.size() ; i++) {
			if(phonelist.get(i).equals(phone)){
				busername = false;
			}
		}
		//Email是否有重复
		for(int i = 0 ; i<Emaillist.size() ; i++){
			if(Emaillist.get(i).equals(Email)){
				bEmail = false;
			}
		}
		
		System.out.println(username.toString());
		String sql = "insert into user(username,password,email,phone,age,gender) "
				+ "values ('"+username+"','"+password+"','"+Email+"','"+phone+"','"+age+"','"+gender+"')";
		int i = 0;
		try {
			if(busername&&bEmail){
			 i = stst.executeUpdate(sql);
			}
		} catch (SQLException e) {
			busername = false;
		}
		String name1;
		String phone1;
		String email1 = "Unverified";
		if(busername){
			name1 = "success";
		}else {
			name1 = "error";
		}
		
		if(bphone){
			phone1 = "success";
		}else {
			phone1 = "error";
		}
		
		if(bEmail){
			email1 = "success";
		}else {
			email1 = "error";
		}
		return new ReturnUser(name1 , phone1 ,email1);
		
		
	}
	
	//邮箱以及手机号码的初始化
//	public ArrayList<Integer> phone = new Arraylist<integer>();
	public void init() throws SQLException{
		phonelist = new ArrayList<String>();
		Emaillist = new ArrayList<String>();
		String sql = "select  phone,Email from user";
		ResultSet re = stst.executeQuery(sql);
		while(re.next()){
			phonelist.add(re.getString("phone"));
			Emaillist.add(re.getString("Email"));
		}
		
	}
	
	// 用户登陆信息验证
	public String validateUser(String username , String password ) throws SQLException{
		
		String sql = "select * from user where username = "+"\'"+username+"\'";
		System.out.println(sql);
		ResultSet resultSetes = stst.executeQuery(sql);
		while(resultSetes.next()){
			if(resultSetes.getString("password").equals(password)){
				
				return username;
			}
		}
		return "error"; 
	}
	
	public ResultSet SqlReturn(String sql) throws SQLException {
		ResultSet resultSetes = stst.executeQuery(sql);
		int sum=0;
		return resultSetes;
		
	}
	public void close() throws SQLException {
		conn.close();
	}
}
