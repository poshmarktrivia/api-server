package poshmark.trivia.api;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class ScoreDetails {
    private Integer score;
    /**
     * @return the firstName
     */
    public Integer getScore() {
        return score;
    }
    /**
     * @param firstName the firstName to set
     */
    public void setName(Integer score) {
        this.score = score;
    }
}