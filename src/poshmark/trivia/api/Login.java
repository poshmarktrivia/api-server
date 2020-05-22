package poshmark.trivia.api;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.json.simple.JSONObject;

import com.google.gson.Gson;

@Path("/login")
public class Login {

	@GET
	@Produces("application/json")
	public String requestLogin() {
		String str = "{ 'test': 'hai' }";
		return new Gson().toJson(str);
	}
	
	
}
