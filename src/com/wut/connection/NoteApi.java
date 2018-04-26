package com.wut.connection;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.wut.datebase.Data;
import com.wut.tool.tool;
import com.wut.user.Note;
import com.wut.user.StateInformation;
import com.wut.user.returnNote;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Path("/Note")
/*
 * 便签api
 * 
 * 主要四个功能
 * 
 * 1，增加便签 add() 思路：客户端只需要传送用户名，用户校验码，便签标题，便签时间，便签内容，便签类别即可完成。 2，更新便签 Update()
 * 思路：更新便签相当于重新增加一次，只不过此便签的便签ID不变 3，删除便签 Delete() 删除需要校验码及用户名，以及需要删除的便签ID，此做伪删除
 * 4，获取所有标签 getall() 校验，然后拉去所有的该用户的便签发送。
 * 
 */
public class NoteApi {
	Data date = new Data();

	// 笔记增加
	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public StateInformation add(Object ReceiveNote) {
		JSONObject note = JSONObject.fromObject(ReceiveNote);
		String UserID = "";
		String Token = "";
		String Title = "";
		String NoteTime = "";
		String Content = "";
		String Tag = "";
		try {
			UserID = note.getString("UserID");// 用户名
			Token = note.getString("Token");// 用户校验码
			Title = note.getString("Title");// 用户标题
			NoteTime = note.getString("time");// 笔记(标签)时间
			Content = note.getString("Content");// 标签内容
			Tag = note.getString("Tag");
			;// 标签类别
		} catch (Exception e) {
			return new StateInformation("body error", "body error");
		}
		Note note2 = new Note(UserID, Token, Title, NoteTime, Content, Tag);
		StateInformation re = date.addNote(note2);
		return re;
	}

	// 笔记更新修改
	@POST
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public StateInformation Update(Object ReceiveNote) {
		JSONObject note = JSONObject.fromObject(ReceiveNote);
		// 这里需要传来 笔记ID以用来修改
		String noteID = "";
		String Token = "";
		String Title = "";
		String NoteTime = "";
		String Content = "";
		String Tag = "";

		try {
			noteID = note.getString("noteID");// noteId
			Token = note.getString("Token");// 用户校验码
			Title = note.getString("Title");// 用户标题
			NoteTime = note.getString("time");// 笔记(标签)时间
			Content = note.getString("Content");// 标签内容
			Tag = note.getString("Tag");// 标签类别
		} catch (Exception e) {
			return new StateInformation("body error", "body error");
		}
		Note note2 = new Note(noteID, Token, Title, NoteTime, Content, Tag);
		StateInformation re = date.UpdateNote(note2);
		return re;
	}

	// 笔记删除

	// 只需要给用户返回是否已经成功删除的状态即可
	@POST
	@Path("/delete")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public StateInformation Delete(Object ReceiveNote) {
		JSONObject note = JSONObject.fromObject(ReceiveNote);
		// 删除笔记只需要传来ID以及校验码和笔记id即可
		String noteID = "";
		String Token = "";
		String UserID = "";

		try {

			noteID = note.getString("noteID");// noteId
			Token = note.getString("Token");// 用户校验码
			UserID = note.getString("UserID");// 用户名
		} catch (Exception e) {
			return new StateInformation("body error", "body error");
		}

		JSONObject Incoming = new JSONObject();
		Incoming.put("noteID", noteID);
		Incoming.put("Token", Token);
		Incoming.put("UserID", UserID);
		StateInformation re = date.DeleteNote(Incoming);
		return re;
	}

	// 返回整个用户的笔记
	@POST
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Map<String, Object> getAll(Object ReceiveNote) {
		JSONObject note = JSONObject.fromObject(ReceiveNote);
		String userID = "";
		String Token = "";
		try {

			userID = note.getString("UserID");// noteId
			Token = note.getString("Token");// 用户校验码
		} catch (Exception e) {
			Map<String, Object> a1 = new HashMap<String, Object>();
			a1.put("State", "500");
			a1.put("information", "body error");
			return a1;
		}

		Map<String, Object> re = date.getAllNote(note);
		return re;
	}
}
