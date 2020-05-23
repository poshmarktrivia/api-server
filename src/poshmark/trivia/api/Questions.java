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

@Path("/questions")
public class Questions {
	
MySQLConnection MySQL = new MySQLConnection();
	
	Connection connect;
	JSONObject obj = new JSONObject();
	Gson gson = new GsonBuilder().disableHtmlEscaping().create();

	// Create a question
	@POST
	@Path("/newquestion")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String createUser(@FormParam("input_string") String inputString){
		

		String question = null;
		Long type_id = 0L;
		JSONArray options =null;
		JSONArray answers =null;
		Long level = 0L;
		Long score = 0L;
		Long timer = 0L;
	 
		try {
			connect = MySQL.createConnection();
			JSONParser parser = new JSONParser(); 
			JSONObject json = (JSONObject) parser. parse(inputString);
			question = (String) json.get("question");
			type_id= (Long) json.get("type_id");
			options = (JSONArray) json.get("options");
			answers = (JSONArray) json.get("answers");
			level= (Long) json.get("level");
			score= (Long) json.get("score");
			timer= (Long) json.get("timer");
			
			String optionsStr="";
			for (int i=0;i<options.size();i++) {
				System.out.println("size and value "+i+options.get(i));
				optionsStr+=options.get(i)+",";
			}
			
			String answersStr="";
			for (int i=0;i<answers.size();i++) {
				answersStr+=answers.get(i)+",";
			}			
			String query="insert into questions (question,type_id,options,answers,level,score,timer) values ('"+question+"',"+type_id+",'"+optionsStr.substring(0, optionsStr.length() - 1)+"','"+answersStr.substring(0, answersStr.length() - 1)+"',"+level+","+score+","+timer+");";
			MySQL.updateOrDeleteDate(connect,query);

			obj.put("code",200);
			obj.put("message","Question created successfully");

			MySQL.closeConnection(connect);
			return gson.toJson(obj);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;

	}
	
	
	//Delete question
	@DELETE
	@Path("/{question_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteUser(@PathParam("question_id") String questionID) {
		
		try {
			connect = MySQL.createConnection();
			String query="delete from questions where question_id="+questionID+";";
			MySQL.updateOrDeleteDate(connect,query);
			obj.put("code",200);
			obj.put("message","Question deleted successfully");

			MySQL.closeConnection(connect);
			return gson.toJson(obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	
	}

	//View all questions - from admin page
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllUserDetails() {
		
		Connection connect;
		JSONObject obj = new JSONObject();
		JSONArray questionsArray = new JSONArray();
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();

		try {
			connect = MySQL.createConnection();
			boolean role = false;
			String query="select * from questions ORDER BY question_id;";
			ResultSet rs=MySQL.getData(connect, query);
			
			int question_id=0;
			String question = null;
			int type_id =0;
			String options =null;
			String answers =null;
			int level = 0;
			int score = 0;
			int timer =0;
			

			
			while (rs.next()) {
				JSONObject qa = new JSONObject();
				String[] optionsTemp =null;
				System.out.println("rs: " + rs.toString());
	            
				question_id = rs.getInt(1);
	            question = rs.getString(2);
	            type_id=rs.getInt(3);
	            options=rs.getString(4);
	            answers=rs.getString(5);
	            level=rs.getInt(6);
	            score=rs.getInt(7);
	            timer=rs.getInt(8);
	            
	            qa.put("question_id",question_id);
	            qa.put("question",question);
	            qa.put("type_id",type_id);
	            qa.put("options",options.split(","));
	            qa.put("answers",answers.split(","));
	            qa.put("level",level);
	            qa.put("score",score);
	            qa.put("timer",timer);
	            questionsArray.add(qa);
	        }
			
			obj.put("questions",questionsArray);
			MySQL.closeConnection(connect);
			return gson.toJson(obj);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	//Get Questions - for web app based on level
	@GET
	@Path("/{level}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllUserDetails(@PathParam("level") String level) {
		
		Connection connect;
		JSONObject obj = new JSONObject();
		JSONArray questionsArray = new JSONArray();
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();

		try {
			connect = MySQL.createConnection();
			boolean role = false;
			String query="select * from questions where level="+level+" ORDER BY question_id;";
			ResultSet rs=MySQL.getData(connect, query);
			
			int question_id=0;
			String question = null;
			int type_id =0;
			String options =null;
			String answers =null;
			int score = 0;
			int timer =0;
			
			
			while (rs.next()) {
				
				JSONObject qa = new JSONObject();
				
				System.out.println("rs: " + rs.toString());
	            
				question_id = rs.getInt(1);
	            question = rs.getString(2);
	            type_id=rs.getInt(3);
	            options=rs.getString(4);
	            answers=rs.getString(5);
	            score=rs.getInt(7);
	            timer=rs.getInt(8);
	            
	            qa.put("question_id",question_id);
	            qa.put("question",question);
	            qa.put("type_id",type_id);
	            qa.put("options",options.split(","));
	            qa.put("answers",answers.split(","));
	            qa.put("level",level);
	            qa.put("score",score);
	            qa.put("timer",timer);
	            questionsArray.add(qa);
	        }
			
			obj.put("questions",questionsArray);
			MySQL.closeConnection(connect);
			return gson.toJson(obj);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
}
