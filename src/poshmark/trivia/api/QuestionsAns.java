package poshmark.trivia.api;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class QuestionsAns {
	private String question;
	private int type;
	private String[] options;
	private String[] answers;
	private int level;
	private int score;
	public String getQuestions() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
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
}