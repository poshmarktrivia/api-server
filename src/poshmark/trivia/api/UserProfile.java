package poshmark.trivia.api;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class UserProfile {
    private String name;
    private Integer score;
    private boolean isadmin;
    /**
     * @return the firstName
     */
    public String getName() {
        return name;
    }
    /**
     * @param firstName the firstName to set
     */
    public void setName(String name) {
        this.name = name;
    }
    public boolean getRole() {
        return isadmin;
    }
    /**
     * @param firstName the firstName to set
     */
    public void setRole(boolean role) {
        this.isadmin = role;
    }
    public Integer getScore() {
        return score;
    }
    /**
     * @param lastName the lastName to set
     */
    public void setScore(Integer score) {
        this.score = score;
    }
    public static String getUserDetails(String userName, Integer score, boolean role) {
    	String result = "{'name': '"+userName + "' , 'score' : '"+ score +"' , 'is_admin' : '"+role+"' }";
    	return result;
    }
}