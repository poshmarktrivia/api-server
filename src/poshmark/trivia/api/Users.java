package poshmark.trivia.api;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.google.gson.Gson;

@Path("/user")
public class Users {
	
	@POST
	@Path("/newuser")
	@Produces("application/json")
	public String createUser() {
		String response = "{ 'code': '200' , 'message':'User created successfully'}";
		return new Gson().toJson(response);
	}
	
//	@GET
//	@Path("")
	
	
}
