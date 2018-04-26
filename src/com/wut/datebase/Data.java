package com.wut.datebase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.mysql.jdbc.Statement;
import com.wut.tool.tool;
import com.wut.user.LoginUser;
import com.wut.user.Note;
import com.wut.user.Remind;
import com.wut.user.ReturnUser;
import com.wut.user.StateInformation;
import com.wut.user.User;
import com.wut.user.returnNote;
import com.wut.user.returnRemind;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

//先用于数据库连接
//后期转用Hibernate

public class Data {
	Connection conn;
	Statement stst;

	ArrayList<Map<String, String>> userValidate;

	// 初始化数据库
	public Data() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
		String url = "jdbc:mysql://47.100.21.174:3306/studentassistant?user=qq494296145&password=494296145";
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
		}
		try {
			stst = (Statement) conn.createStatement();
		} catch (SQLException e) {
		}
		try {
			init();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * 用户注册信息验证
	 *
	 * 先做list判断，在做数据库判断
	 *
	 */
	public ReturnUser validateregister(User user) {
		String username = user.getUser();
		String password = user.getPassword();
		String Email = user.getEmail();
		String phone = user.getPhone();
		int age = user.getAge();
		int sex = user.getGender();
		boolean busername = true;
		boolean bphone = true;
		boolean bEmail = true;
		// 用户名，邮箱，电话号码是是否有重复
		for (int i = 0; i < userValidate.size(); i++) {
			if (userValidate.get(i).get("SU_phone").equals(phone)) {
				bphone = false;
			}
			if (userValidate.get(i).get("SU_Email").equals(Email)) {
				bEmail = false;
			}
			if (userValidate.get(i).get("SU_userName").equals(username)) {
				busername = false;
			}
		}

		if (!busername || !bEmail || !bphone) {
			return new ReturnUser("400", String.valueOf(busername), String.valueOf(bphone), String.valueOf(bEmail));
		}
		// 生成UID
		String uid = UUID.randomUUID().toString().trim().replaceAll("-", "");

		String sql = "insert into Sdnote_User(SU_userName,SU_password,SU_Token,SU_Email,SU_phone,SU_age,SU_sex) "
				+ "values ('" + username + "','" + password + "','" + uid + "','" + Email + "','" + phone + "','" + age
				+ "','" + sex + "')";

		try {
			if (busername && bEmail) {
				stst.executeUpdate(sql);
			}
		} catch (SQLException e) {
			// 未知错误
			System.out.println(e.toString());

			return new ReturnUser("401", "error", "error", "error");
		}

		return new ReturnUser("200", String.valueOf(busername), String.valueOf(bphone), String.valueOf(bEmail));
	}

	// 邮箱以及手机号码的初始化
	// public ArrayList<Integer> phone = new Arraylist<integer>();
	public void init() throws SQLException {

		userValidate = new ArrayList<Map<String, String>>();

		String sql = "select  SU_userID,SU_userName,SU_password,SU_Token,SU_Email,SU_phone from Sdnote_User";
		ResultSet re = stst.executeQuery(sql);
		while (re.next()) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("SU_userID", re.getString("SU_userID"));
			map.put("SU_userName", re.getString("SU_userName"));
			map.put("SU_Token", re.getString("SU_Token"));
			map.put("SU_Email", re.getString("SU_Email"));
			map.put("SU_phone", re.getString("SU_phone"));
			map.put("SU_password", re.getString("SU_password"));
			userValidate.add(map);
		}

	}

	// 用户ID及token校验

	public boolean token(String token) {

		for (int i = 0; i < userValidate.size(); i++) {
			if (userValidate.get(i).get("SU_Token").equals(token)) {
				// 校验token成功
				return true;
			}
		}
		// 校验token失败
		return false;
	}

	// 用户登陆信息验证
	public LoginUser validateUser(String username, String password) throws SQLException {

		String sql = "select SU_userID,SU_userName,SU_password,SU_Token from Sdnote_User where SU_userName = " + "\'"
				+ username + "\'";

		ResultSet resultSetes = stst.executeQuery(sql);
		while (resultSetes.next()) {
			if (resultSetes.getString("SU_password").equals(password)) {

				return new LoginUser("200", resultSetes.getString("SU_userName"), "",
						resultSetes.getString("SU_userID"), resultSetes.getString("SU_Token"));
			}
			// 401:密码错误
			return new LoginUser("401", "true", "密码错误", "", "");
		}
		// 400：无此用户
		return new LoginUser("400", "无此用户", "", "", "");

	}

	// 用户增加标签
	public StateInformation addNote(Note note) {

		String UserID = note.getUserID();
		String Token = note.getCheck();
		String Title = note.getTitle();
		String NoteTime = note.getNoteTime();
		String Content = note.getContent();
		String Tag = note.getTags();
		String time = note.getNoteTime();
		// 校验 ID 以及token
		boolean T = false;
		for (int i = 0; i < userValidate.size(); i++) {
			if (userValidate.get(i).get("SU_userID").equals(UserID)
					&& userValidate.get(i).get("SU_Token").equals(Token)) {
				T = true;
			}
		}
		if (T) {
			String sql = "insert into Sdnote_Note(SN_userID,SN_Token,SN_title,SN_time,SN_content,SN_tag) " + "values ('"
					+ UserID + "','" + Token + "','" + Title + "','" + time + "','" + Content + "','" + Tag + "')";
			try {
				// 成功
				stst.executeUpdate(sql);

				return new StateInformation("200", "success");
			} catch (SQLException e) {
				// 数据库失败
				e.printStackTrace();
				return new StateInformation("401", "error");
			}
		} else {
			// 验证失败

			return new StateInformation("400", "UserID or Token error");
		}

	}

	// 用户更新标签
	public StateInformation UpdateNote(Note note) {

		String SN_noteID = note.getUserID();
		String Token = note.getCheck();
		String Title = note.getTitle();
		String NoteTime = note.getNoteTime();
		String Content = note.getContent();
		String Tag = note.getTags();
		// 校验 ID 以及token
		boolean T = false;
		for (int i = 0; i < userValidate.size(); i++) {
			if (userValidate.get(i).get("SU_Token").equals(Token)) {
				T = true;
			}
		}
		if (T) {
			String sql = "UPDATE Sdnote_Note SET  SN_title = '" + Title + "',SN_time = '" + NoteTime
					+ "',SN_content = '" + Content + "',SN_tag='" + Tag + "' WHERE SN_noteID = '" + SN_noteID + "'";

			try {
				// 成功
				int i = stst.executeUpdate(sql);
				if (i == 0) {
					return new StateInformation("401", "noteID error");
				}
				return new StateInformation("200", "success");
			} catch (SQLException e) {
				// 数据库失败
				e.printStackTrace();
				return new StateInformation("401", "error");
			}
		} else {
			// 验证失败

			return new StateInformation("400", "Token error");
		}

	}

	// 用户删除标签
	public StateInformation DeleteNote(JSONObject note) {
		JSONObject date;
		String noteID = note.getString("noteID");
		String Token = note.getString("Token");
		String userid = note.getString("UserID");
		// 校验 ID 以及token
		boolean T = false;
		for (int i = 0; i < userValidate.size(); i++) {
			if (userValidate.get(i).get("SU_userID").equals(userid)
					&& userValidate.get(i).get("SU_Token").equals(Token)) {
				T = true;
			}
		}
		if (T) {
			String sql = "UPDATE Sdnote_Note SET SN_vd = '1'   WHERE SN_noteID = '" + noteID + "'";

			try {
				// 成功
				int i = stst.executeUpdate(sql);
				if (i == 0) {
					return new StateInformation("400", "noteID error");
				}
				return new StateInformation("200", "success");
			} catch (SQLException e) {
				// 数据库失败
				e.printStackTrace();
				return new StateInformation("401", "error");
			}
		} else {
			// 延迟失败
			return new StateInformation("400", "UserID or Token error");
		}

	}

	// 获取用户的所有标签
	public Map<String, Object> getAllNote(JSONObject note) {
		Map<String, Object> a1 = new HashMap<String, Object>();
		String userid = note.getString("UserID");
		String Token = note.getString("Token");

		// 校验 ID 以及token
		boolean T = false;
		for (int i = 0; i < userValidate.size(); i++) {
			if (userValidate.get(i).get("SU_userID").equals(userid)
					&& userValidate.get(i).get("SU_Token").equals(Token)) {
				T = true;
			}
		}
		if (T) {
			String sql = "select * from Sdnote_Note where SN_userID = '" + userid + "' and SN_vd = " + 0 + "";
			System.out.println(sql);
			ArrayList<returnNote> a = new ArrayList<returnNote>();

			try {
				// 成功

				ResultSet resultSetes = stst.executeQuery(sql);
				while (resultSetes.next()) {
					a.add(new returnNote(resultSetes.getString("SN_noteID"), resultSetes.getString("SN_title"),
							resultSetes.getString("SN_time"), resultSetes.getString("SN_content"),
							resultSetes.getString("SN_tag")));
				}

				a1.put("data", a);
				a1.put("information", "success");
				a1.put("State", "200");

				return a1;
			} catch (SQLException e) {
				// 数据库失败
				e.printStackTrace();

				a1.put("information", "error");
				a1.put("State", "400");

				return a1;
			}
		} else {
			// 验证失败

			a1.put("information", "userid or Token error");
			a1.put("State", "401");

			return a1;
		}
	}
	// ———————————————————————————————————————remind———————————————————————————————————————————

	// 增家提醒
	public StateInformation addRemind(Remind remind) {

		String UserID = remind.getUserID();// 用户名
		String Token = remind.getToken();// 用户校验码
		String Title = remind.getTitle();// 用户标题
		String Time = remind.getTime();// 记录时间
		String stratTime = remind.getStratTime();// 开始时间
		String endTime = remind.getEndTime();// 提醒时间
		String remindTime = remind.getRemindTime();// 结束提醒时间
		String Content = remind.getContent();// 标签内容
		String Tag = remind.getTag();// 标签类别
		// 校验 ID 以及token
		boolean T = false;
		for (int i = 0; i < userValidate.size(); i++) {
			if (userValidate.get(i).get("SU_userID").equals(UserID)
					&& userValidate.get(i).get("SU_Token").equals(Token)) {
				T = true;
			}
		}
		if (T) {
			String sql = "insert into Sdnote_Remind(SR_userID,SR_Token,SR_title,SR_time,SR_stratTime,SR_endTime,SR_remindTime,SR_content,SR_tag) "
					+ "values ('" + UserID + "','" + Token + "','" + Title + "','" + Time + "','" + stratTime + "','"
					+ endTime + "','" + remindTime + "','" + Content + "','" + Tag + "')";
			try {
				// 成功
				stst.executeUpdate(sql);

				return new StateInformation("200", "success");
			} catch (SQLException e) {
				// 数据库失败
				e.printStackTrace();

				return new StateInformation("401", "error");
			}
		} else {
			// 验证失败
			return new StateInformation("400", "UserID or Token error");
		}

	}

	// 更新提醒
	public StateInformation UpdateRemind(Remind remind) {

		String remindID = remind.getUserID();// 日程id
		String Token = remind.getToken();// 用户校验码
		String Title = remind.getTitle();// 用户标题
		String Time = remind.getTime();// 记录时间
		String stratTime = remind.getStratTime();// 开始时间
		String endTime = remind.getEndTime();// 提醒时间
		String remindTime = remind.getRemindTime();// 结束提醒时间
		String Content = remind.getContent();// 标签内容
		String Tag = remind.getTag();// 标签类别
		// 校验 ID 以及token
		boolean T = false;
		for (int i = 0; i < userValidate.size(); i++) {
			if (userValidate.get(i).get("SU_Token").equals(Token)) {
				T = true;
			}
		}
		if (T) {
			String sql = "UPDATE Sdnote_Remind SET  SR_title = '" + Title + "',SR_time = '" + Time + "',SR_stratTime ="
					+ "'" + stratTime + "',SR_endTime = '" + endTime + "',SR_remindTime = '" + remindTime
					+ "',SR_content = " + "'" + Content + "',SR_tag='" + Tag + "' WHERE SR_remindID = '" + remindID
					+ "'";
			System.out.println(sql);
			try {
				// 成功
				int i = stst.executeUpdate(sql);

				if (i == 0) {
					return new StateInformation("400", "remindID error");
				}
				return new StateInformation("200", "success");
			} catch (SQLException e) {
				// 数据库失败
				e.printStackTrace();
				return new StateInformation("401", "error");
			}
		} else {
			// 验证失败
			return new StateInformation("400", "Token error");
		}

	}

	// DeleteRemind
	public StateInformation DeleteRemind(JSONObject note) {

		String remindID = note.getString("remindID");
		String Token = note.getString("Token");
		String userid = note.getString("UserID");
		// 校验 ID 以及token
		boolean T = false;
		for (int i = 0; i < userValidate.size(); i++) {
			if (userValidate.get(i).get("SU_userID").equals(userid)
					&& userValidate.get(i).get("SU_Token").equals(Token)) {
				T = true;
			}
		}
		if (T) {
			String sql = "UPDATE Sdnote_Remind SET SR_vd = '1'   WHERE SR_remindID = '" + remindID + "'";

			try {
				// 成功
				int i = stst.executeUpdate(sql);
				if (i == 0) {
					return new StateInformation("400", "remindID error");
				}
				return new StateInformation("200", "success");
			} catch (SQLException e) {
				// 数据库失败
				e.printStackTrace();
				return new StateInformation("401", "error");
			}
		} else {
			// 延迟失败
			return new StateInformation("400", "UserID or Token error");
		}

	}

	// getAllRemind
	public Map<String, Object> getAllRemind(JSONObject note) {

		Map<String, Object> a1 = new HashMap<String, Object>();
		String userid = note.getString("UserID");
		String Token = note.getString("Token");

		// 校验 ID 以及token
		boolean T = false;
		for (int i = 0; i < userValidate.size(); i++) {
			if (userValidate.get(i).get("SU_userID").equals(userid)
					&& userValidate.get(i).get("SU_Token").equals(Token)) {
				T = true;
			}
		}
		if (T) {
			String sql = "select * from Sdnote_Remind where SR_remindID = '" + userid + "'and SR_vd = " + 0 + "";
			ArrayList<returnRemind> a = new ArrayList<returnRemind>();

			try {
				// 成功

				ResultSet resultSetes = stst.executeQuery(sql);
				while (resultSetes.next()) {

					a.add(new returnRemind(resultSetes.getString("SR_remindID"), resultSetes.getString("SR_title"),
							resultSetes.getString("SR_time"), resultSetes.getString("SR_stratTime"),
							resultSetes.getString("SR_endTime"), resultSetes.getString("SR_remindTime"),
							resultSetes.getString("SR_content"), resultSetes.getString("SR_tag")));

				}

				a1.put("data", a);
				a1.put("State", "200");
				a1.put("information", "success");

				return a1;
			} catch (SQLException e) {
				// 数据库失败
				e.printStackTrace();

				a1.put("State", "400");
				a1.put("information", "error");
				return a1;
			}
		} else {
			// 延迟失败

			a1.put("State", "401");
			a1.put("information", "userid or Token error");
			return a1;
		}
	}

	public void close() throws SQLException {
		conn.close();
	}

	public Map<String, String> modifyPassword(String userName, String oldpassword, String newpassword) {
		boolean T = false;
		Map<String, String> a = new HashMap<String, String>();
		for (int i = 0; i < userValidate.size(); i++) {
			if (userName.equals(userValidate.get(i).get("SU_userName"))
					&& oldpassword.equals(userValidate.get(i).get("SU_password"))) {
				T = true;
			}
			System.out.println(userName + "  " + userValidate.get(i).get("SU_userName"));
			System.out.println(oldpassword + "  " + userValidate.get(i).get("SU_password"));
		}

		// 密码验证正确
		if (T) {
			String sql = "UPDATE Sdnote_User SET  SU_password = '" + newpassword + "' WHERE SU_userName = '" + userName
					+ "'";
			try {
				int i = stst.executeUpdate(sql);
				if (i == 0) {
					a.put("state", "401");
					a.put("information", "error");
					return a;
				}
				a.put("state", "200");
				a.put("information", "success");
				return a;
			} catch (SQLException e) {
				a.put("state", "401");
				a.put("information", "error");
				e.printStackTrace();
			}
		}
		// 密码验证错误
		a.put("state", "400");
		a.put("information", "userID or password error");
		return a;

	}

	public Map<String, String> backPassword(String userName, String newpassword, String phone) {
		boolean T = false;
		Map<String, String> a = new HashMap<String, String>();
		for (int i = 0; i < userValidate.size(); i++) {
			if (userName.equals(userValidate.get(i).get("SU_userName"))
					&& phone.equals(userValidate.get(i).get("SU_phone"))) {
				T = true;
			}
		}

		if (T) {
			String sql = "UPDATE Sdnote_User SET  SU_password = '" + newpassword + "' WHERE SU_userName = '" + userName
					+ "'";
			try {
				int i = stst.executeUpdate(sql);
				if (i == 0) {
					a.put("state", "401");
					a.put("information", "error");
					return a;
				}
				a.put("state", "200");
				a.put("information", "success");
				return a;
			} catch (SQLException e) {
				a.put("state", "401");
				a.put("information", "error");
				e.printStackTrace();
			}
		}

		// 手机验证错误
		a.put("state", "400");
		a.put("information", "userID or SU_phone error");
		return a;
	}

}
