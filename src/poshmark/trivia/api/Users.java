package poshmark.trivia.api;

import java.sql.Connection;
import java.sql.ResultSet;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path("/users")
public class Users {
	
	MySQLConnection MySQL = new MySQLConnection();
	
	Connection connect;
	JSONObject obj = new JSONObject();
	Gson gson = new GsonBuilder().disableHtmlEscaping().create();
			

	
	//Create User
	@POST
	@Path("/newuser")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String createUser(@FormParam("input_string") String inputString){
		
		String name = null;
	    boolean isAdmin = false;
	 
		try {
			connect = MySQL.createConnection();
			JSONParser parser = new JSONParser(); 
			JSONObject json = (JSONObject) parser. parse(inputString);
			name= (String) json.get("name");
			isAdmin=(boolean) json.get("is_admin");	
			
			String query="insert into users(name,isadmin) values ('"+name+"',"+isAdmin+");";
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
	
	
	//Get all users - to view all users details in the admin page
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllUserDetails() {
		
		Connection connect;
		JSONObject obj = new JSONObject();
		JSONArray usersArray = new JSONArray();
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();

		try {
			connect = MySQL.createConnection();
			boolean role = false;
			String query="select * from users ORDER BY name;";
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
			
			obj.put("user_details",usersArray);
			MySQL.closeConnection(connect);
			return gson.toJson(obj);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	//Get user - to view user details in app
	@GET
	@Path("/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUserDetails(@PathParam("username") String userName) {
		
		Connection connect;
		JSONObject obj = new JSONObject();
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();

		try {
			connect = MySQL.createConnection();
			boolean role = false;
			String query="select * from users where name='"+userName+"';";
			ResultSet rs=MySQL.getData(connect, query);
			String name= null;
			int score = 0;
			
			
			while (rs.next()) {
	            name = rs.getString(1);
	            score = rs.getInt(2);
	            role =rs.getBoolean(3);
	        }
			
			obj.put("name",name);
			obj.put("score",score);
			obj.put("isadmin",role);
			
			MySQL.closeConnection(connect);
			return gson.toJson(obj);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	//Delete user in the admin page
	@DELETE
	@Path("/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteUser(@PathParam("username") String userName) {

		
		try {
			connect = MySQL.createConnection();
			String query="delete from users where name='"+userName+"';";
			MySQL.updateOrDeleteDate(connect,query);
			obj.put("code",200);
			obj.put("message","User deleted successfully");

			MySQL.closeConnection(connect);
			return gson.toJson(obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	
	}

}
