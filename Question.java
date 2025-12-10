import java.util.ArrayList;
import java.util.List;


public class Question {
    private String questionText;
    private ArrayList<String> options;
    private int correctAnswerIndex;
    private int attemptsRemaining;
    private final int MAX_ATTEMPTS = 3; // per question
    
    public Question(String questionText, ArrayList<String> options, int correctAnswerIndex){
        this.questionText = questionText;
        this.correctAnswerIndex = correctAnswerIndex;
        this.options = options;
        this.attemptsRemaining = MAX_ATTEMPTS;
    }
    
    public String getQuestionText(){
        return questionText;
    }
    
    public ArrayList<String> getOptions(){
        return options;
    }
    
    public boolean isCorrect(int selectedOptionIndex){
        return selectedOptionIndex == correctAnswerIndex;
    }
    
    public int getAttemptsRemaining(){
        return attemptsRemaining;
    }
    
    public void decrementAttempts(){
        attemptsRemaining--;
    }
    
    public int getCorrectAnswerIndex(){
        return correctAnswerIndex;
    }
    
    public void resetAttempts(){
        this.attemptsRemaining = MAX_ATTEMPTS;
    }
    
}
