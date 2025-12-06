import java.util.List;
import java.util.ArrayList;


public class Question {
    private String questionText;
    private List<String> options;
    private int correctAnswerIndex;
    private int attemptsRemaining;
    private final int MAX_ATTEMPTS = 3;
    
    public Question(String questionText, List<String> options, int correctAnswerIndex){
        this.questionText = questionText;
        this.correctAnswerIndex = correctAnswerIndex;
        this.options = new ArrayList<>(options);
        this.attemptsRemaining = MAX_ATTEMPTS;
    }
    
    public String getQuestionText(){
        return questionText;
    }
    
    public List<String> getOptions(){
        return options;
    }
    
    public boolean isCorrect(int selectedOptionIndex){
        return selectedOptionIndex == correctAnswerIndex;
    }
    
    //they can try twice
    public void displayQuestion(){
        System.out.println(questionText);
        for (int i = 0; i < options.size(); i++){
            System.out.println((i + 1)+". "+options.get(i));
        }
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
    
}
