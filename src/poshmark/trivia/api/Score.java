package poshmark.trivia.api;

import java.sql.Connection;
import java.sql.ResultSet;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path("/scores")
public class Score {
	
	MySQLConnection MySQL = new MySQLConnection();
	
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public String getDashboard() {
		
		Connection connect;
		JSONObject obj = new JSONObject();
		JSONArray usersArray = new JSONArray();
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();

		try {
			connect = MySQL.createConnection();
			boolean role = false;
			String query="select * from users ORDER BY score desc limit 10;";
			ResultSet rs=MySQL.getData(connect, query);
			String name= null;
			int score = 0;
			
			
			while (rs.next()) {
				JSONObject user = new JSONObject();
				System.out.println("rs: " + rs.toString());
	            name = rs.getString(1);
	            score = rs.getInt(2);
	            role =rs.getBoolean(3);
	            user.put("name",name);
				user.put("score",score);
				user.put("role",role);
				usersArray.add(user);
	        }
			
			
			obj.put("scores",usersArray);
			MySQL.closeConnection(connect);
			return gson.toJson(obj);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	//Update score
	@PUT
	@Path("/newscore")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String updateScore(@FormParam("input_string") String inputString){
		
		
		Connection connect;
		JSONObject obj = new JSONObject();
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		
		String name = null;
	    Long score = 0L;

		try {
			connect = MySQL.createConnection();
			JSONParser parser = new JSONParser(); 
			JSONObject json = (JSONObject) parser. parse(inputString);
			name= (String) json.get("name");
			score=(Long) json.get("score");	
			String query="UPDATE users SET score = "+score+" WHERE name = '"+name+"';";
			MySQL.updateOrDeleteDate(connect,query);

			obj.put("code",200);
			obj.put("message","User created successfully");

			MySQL.closeConnection(connect);
			return gson.toJson(obj);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}



}
