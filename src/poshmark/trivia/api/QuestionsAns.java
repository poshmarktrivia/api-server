package poshmark.trivia.api;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class QuestionsAns {
	private String question;
	private int type_id;
	private String[] options;
	private String[] answers;
	private int level;
	private int score;
	private int timer; 
	public String getQuestions() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public int getType() {
        return type_id;
    }
    public void setType(int type_id) {
        this.type_id = type_id;
    }
    public String[] getOptions() {
        return options;
    }
    public void setOptions(String[] options) {
        this.options = options;
    }
    public String[] getAnswer() {
        return answers;
    }
    public void setAnswers(String[] answers) {
        this.answers = answers;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public int getTimer() {
        return timer;
    }
    public void setTimer(int timer) {
        this.timer = timer;
    }
}