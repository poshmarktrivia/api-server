package poshmark.trivia.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class Admin {

	// create a question
	@POST
	@Path("/question")
	@Consumes("application/json")
	@Produces("application/json")
	public String createQAs(QuestionsAns quesAns) {
		return "";
	}
	
	//Delete question
	@DELETE
	@Path("/questions/{ques_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteUser(@PathParam("ques_id") String ques_id) {
		return null;
	}
	
	//View all questions - from admin page

}
