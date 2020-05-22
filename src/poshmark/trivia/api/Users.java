package poshmark.trivia.api;

import java.sql.Connection;
import java.sql.ResultSet;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

@Path("/user")
public class Users {
	
	MySQLConnection MySQL = new MySQLConnection();
			

	
	//Create User
	@POST
	@Path("/newuser")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String createUser(UserProfile userProfile) throws Exception {
		Connection connect=MySQL.createConnection();
		String name = userProfile.getName();
	    boolean role = userProfile.getRole();
	    String query="insert into users(name,isadmin) values ("+name+","+role+")";
	    MySQL.updateOrDeleteDate(connect,query);
	    String response = "{ 'code': '200' , 'message':'User created successfully'}";
		return new Gson().toJson(response);
	}
	
	
	//Get all users - to view all users details in the admin page
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllUserDetails() {
		//DB Connection
		return null;
	}
	
	
	//Get user - to view user details in app
	@GET
	@Path("/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUserDetails(@PathParam("username") String userName) {
		
		Connection connect;
		try {
			connect = MySQL.createConnection();
			boolean role = false;
			String query="select * from users where name='"+userName+"';";
			ResultSet rs=MySQL.getData(connect, query);
			String name= null;
			int score = 0;
			
			
			while (rs.next()) {
				System.out.println("rs: " + rs.toString());
	            name = rs.getString(1);
	            score = rs.getInt(2);
	            role =rs.getBoolean(3);
	        }
			
			String resut = UserProfile.getUserDetails(name, score, role);
			
			return new Gson().toJson(resut);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return null;
	}
	
	//Delete user in the admin page
	@DELETE
	@Path("/users/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteUser(@PathParam("username") String userName) {
		//DB Connection
		String response = "{ 'code': '200' , 'message':'User deleted successfully'}";
		return new Gson().toJson(response);
	}

}
