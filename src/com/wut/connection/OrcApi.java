package com.wut.connection;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.inject.New;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.wut.datebase.Data;

import com.wut.orc.TencentApi;

import net.sf.json.JSONObject;

@Path("/Sdnote")
public class OrcApi {
	Data data = new Data(); 
	@POST
	@Path("/orcapi")
	@Produces(MediaType.APPLICATION_JSON)  
    @Consumes(MediaType.APPLICATION_JSON)
	public Map<String, String> OrcApi(Object  img) throws Exception{
		
		JSONObject Jnote = JSONObject.fromObject(img);
		String ToKen = (String)Jnote.get("Token");
		String url = (String)Jnote.get("url");
		
		Map<String, String> a1 = new HashMap<String, String>();
		//调用 ToKen 查验权限，这里应该用arrary来查询
		//这里调用方法查询ToKen
		boolean token = data.token(ToKen);
		if(token){
			System.out.println("123");
			String str = new TencentApi().api(url);
		
		
		//orc 检验失败
		if(str.equals("")||str.equals(null)){
			a1.put("State", "400");
			a1.put("Results", "");
			
			return a1;
		}
		a1.put("State", "200");
		a1.put("Results", str);
		return a1;
		}
		else {
			a1.put("State", "400");
			a1.put("Results", "");
			
			return a1;
		}
		
	}
}
