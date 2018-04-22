package com.wut.connection;



import javax.ws.rs.Path;  
import javax.ws.rs.Produces;  
import javax.ws.rs.core.MediaType;
import com.wut.datebase.Date;
import com.wut.orc.TencentApi;
import com.wut.user.User;
import net.sf.json.JSONObject;
import java.sql.SQLException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;





@Path("/login")
public class Login {
	Date date = new Date();

	@GET
	@Path("/a")
	@Produces(MediaType.APPLICATION_JSON)
	public String a() throws Exception{
		
		new TencentApi().dome();;
		return "123";
	}
	
	
	@POST
    @Path("/update")
    @Produces("application/json")
	//@Produces(MediaType.APPLICATION_JSON)  
    @Consumes(MediaType.APPLICATION_JSON)
    //@Consumes("application/x-www-form-urlencoded")  

    public User test(Object  name) throws SQLException{
		
		JSONObject a = JSONObject.fromObject(name);
    	System.out.println(a.get("name"));
    	String aString = date.validateUser(a.get("name").toString(), a.get("password").toString());
    	System.out.println(a.get("password"));
    	System.out.println("test");
    	System.out.println(aString);
    	return new User("123", "123", "123", "123", 0, 0);
    }
}
