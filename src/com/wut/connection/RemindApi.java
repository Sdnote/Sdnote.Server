package com.wut.connection;

import java.util.Date;
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
import com.wut.user.Remind;
import com.wut.user.StateInformation;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Path("/remid")
/*
 *  提醒api
 *  
 *  主要四个功能
 *  
 *  1，增加日程 add()
 *  思路：客户端只需要传送用户名，用户校验码，日程 标题，日程记录时间，，日程开始时间，日程提醒时间，日程结束时间，日程内容，日程类别即可完成。
 *  2，更新日程 Update()
 *  思路：更新日程相当于重新增加一次，只不过此日程的日程ID不变
 *  3，删除日程 Delete()
 *  删除需要校验码及用户名，以及需要删除的日程ID，此做伪删除
 *  4，获取所有日程getall()
 *  校验，然后拉去所有的该用户的日程发送。
 * 
 */
public class RemindApi {
	Data date = new Data();
// 日程增加
	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)  
    @Consumes(MediaType.APPLICATION_JSON)
	public StateInformation add(Object Receive){
		JSONObject note = JSONObject.fromObject(Receive);
		String UserID = note.getString("UserID");//用户名
		String Token = note.getString("Token");//用户校验码
		String Title = note.getString("Title");//用户标题
		String Time = note.getString("time");//记录时间
		String stratTime = note.getString("stratTime");//开始时间
		String endTime = note.getString("endTime");//提醒时间
		String remindTime = note.getString("remindTime");//结束提醒时间
		String Content = note.getString("Content");//标签内容
		String Tag = note.getString("Tag");//标签类别
		Remind remind = new Remind(UserID,Token,Title,Time,stratTime,endTime,remindTime,Content,Tag);
		StateInformation re = date.addRemind(remind);
		return re;
	}
	
	// 日程修改
		@PUT
		@Path("/update")
		@Produces(MediaType.APPLICATION_JSON)  
	    @Consumes(MediaType.APPLICATION_JSON)
		public StateInformation Update(Object Receive){
			//这里不需要用户id
			JSONObject note = JSONObject.fromObject(Receive);
			String remindID = note.getString("remindID");//日程ID
			String Token = note.getString("Token");//用户校验码
			String Title = note.getString("Title");//用户标题
			String Time = note.getString("time");//记录时间
			String stratTime = note.getString("stratTime");//开始时间
			String endTime = note.getString("endTime");//提醒时间
			String remindTime = note.getString("remindTime");//结束提醒时间
			String Content = note.getString("Content");//标签内容
			String Tag = note.getString("Tag");//标签类别
			Remind remind = new Remind(remindID,Token,Title,Time,stratTime,endTime,remindTime,Content,Tag);
			StateInformation re = date.UpdateRemind(remind);
			return re;
		}
		
		
		//日程删除
		
		// 只需要给用户返回是否已经成功删除的状态即可
		@DELETE
		@Path("/delete")
		@Produces(MediaType.APPLICATION_JSON)  
	    @Consumes(MediaType.APPLICATION_JSON)
		public StateInformation Delete(Object ReceiveNote){
			JSONObject note = JSONObject.fromObject(ReceiveNote);
			//删除笔记只需要传来ID以及校验码和笔记id即可
			String noteID = note.getString("remindID");//noteId
			String Token = note.getString("Token");//用户校验码
			String UserID = note.getString("UserID");//用户名
			JSONObject Incoming = new JSONObject();
			Incoming.put("remindID", noteID);
			Incoming.put("Token", Token);
			Incoming.put("UserID", UserID);
			StateInformation  re = date.DeleteRemind(Incoming);
			return re;
		}
		
	
		
		
		
		
		
		//返回整个用户的日程 
		@POST
		@Path("/getAll")
		@Produces(MediaType.APPLICATION_JSON)  
	    @Consumes(MediaType.APPLICATION_JSON)
		public Map<String, Object> getAll(Object ReceiveNote){
			JSONObject note = JSONObject.fromObject(ReceiveNote);
			String userID = note.getString("UserID");//noteId
			String Token = note.getString("Token");//用户校验码
			Map<String, Object> re = date.getAllRemind(note);
			return re;
		}
}
