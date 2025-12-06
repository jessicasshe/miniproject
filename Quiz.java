import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Quiz {
    
    private List<Question> questions;
    //private int score;
    private Scanner scanner;
    private MainCharacter player;
    //private int totalQuizAttempts;
    //private final int maxQuizAttempts = 3;
    
    
    public Quiz(Scanner theScanner, MainCharacter playerCharacter){
        this.questions = new ArrayList<>();
        //this.score = 0;
        this.scanner = theScanner;
        this.player = playerCharacter;
        //this.totalQuizAttempts = maxQuizAttempts;
    }
    
    public void addQuestions(Question question){
        questions.add(question);
    }
    
    public int startQuiz(){
        System.out.println("Answer the questions to defeat the robots!!!");
        
        int quizScore = 0;
        boolean allQuestionsCorrect = true;
        
        //if (totalQuizAttempts <= 0){
            //System.out.println("No more quiz chances");
            //return 0;
        //}
        
        for (int i = 0; i < questions.size(); i++){
            Question currentQuestion = questions.get(i);
            boolean questionCorrectInCurrentAttempt = false;
            
            int initialAttempts = currentQuestion.getAttemptsRemaining();
            
            //System.out.println("\n Question "+(i+1)+" (Attempts left: "currentQuestion.getAttemptsRemaining()+"): ");
            //currentQuestion.displayQuestion();
            
            if(currentQuestion.getAttemptsRemaining() <= 0){
                System.out.println("Question "+(i+1)+" (Attempts left: none");
                allQuestionsCorrect = false;
                continue;
            }
            
            while (currentQuestion.getAttemptsRemaining() > 0 && !questionCorrectInCurrentAttempt){
                System.out.println("Question "+(i+1)+" (Attempts left: "+currentQuestion.getAttemptsRemaining()+"): ");
                currentQuestion.displayQuestion();
            
            
                int userAnswer = -1;
                while (true){
                    System.out.print("Enter your answer (1-"+currentQuestion.getOptions().size()+"): ");
                    if (scanner.hasNextInt()){
                        userAnswer = scanner.nextInt();
                        if (userAnswer >= 1 && userAnswer <= currentQuestion.getOptions().size()){
                            break;
                        } else {
                            System.out.println("Invalid.");
                        }
                    } else {
                        System.out.println("Invalid.");
                        scanner.next();
                    }
                }
            
                if (currentQuestion.isCorrect(userAnswer - 1)){
                    System.out.println("correct!");
                    if (currentQuestion.getAttemptsRemaining() == initialAttempts){
                        quizScore++;
                    }
                    questionCorrectInCurrentAttempt = true;
                } else {
                    currentQuestion.decrementAttempts();
                    System.out.println("Incorrect. You have "+ currentQuestion.getAttemptsRemaining()+" attempts left");
                    if (currentQuestion.getAttemptsRemaining() == 0){
                        System.out.println("You've run out of attempts. GAME OVER.");
                        allQuestionsCorrect = false;
                    }
                }
            }
            if (!questionCorrectInCurrentAttempt && currentQuestion.getAttemptsRemaining() > 0){
                allQuestionsCorrect = false;
            }
            
        }
    
    //totalQuizAttempts--;
    
    System.out.println("Finished!");
    System.out.println("Your score for this round is: "+quizScore+" out of "+questions.size());
    
    if (quizScore == questions.size() && allQuestionsCorrect){
        return 1;
    } else {
        System.out.println("Your skills were lacking and you got questions wrong!");
        System.out.println("You take 20 damage for getting the question wrong. Game over for this location");
        return 0;
    } // mc health managed by gamemanager - just for show here
}
    
public static Quiz AbandonedOfficeQuiz(Scanner scanner, MainCharacter player){
        Quiz quiz = new Quiz(scanner, player);
        
        List<String> options1 = List.of("int(num)","Double y = double(x)","int x = 4; double y = x;");
        quiz.addQuestions(new Question("What is an example of implicit casting?", options1, 2));
                
        List<String> options2 = List.of("Inheritance","General Association","Composition");
        quiz.addQuestions(new Question("Dog IS A Animal shows what type of relationship?", options2, 0));
        
        return quiz;
    } 
public static Quiz RobotFactoryQuiz(Scanner scanner, MainCharacter player){
        Quiz quiz = new Quiz(scanner, player);
        
        List<String> options3 = List.of("Public void main Person{","public class Person{","Person{");
        quiz.addQuestions(new Question("What is the correct way to code an object class?", options3, 1));
                
        List<String> options4 = List.of("for","while","do");
        quiz.addQuestions(new Question("____(int i = 0;, i<p, i++){ --> What type of loop is this??", options4, 0));
        
        return quiz;
    }
public static Quiz GovHeadquartersQuiz(Scanner scanner, MainCharacter player){
        Quiz quiz = new Quiz(scanner, player);
        
        List<String> options5 = List.of("O(n log n)"," O(n)"," O(n^2)");
        quiz.addQuestions(new Question("What is the time complexity of a Merge Sort?", options5, 0));
                
        List<String> options6 = List.of("When you are trying to modify a value and already have a reference to the item","When you don't have a reference to the item and are trying to modify a value","When you are trying to search for a value");
        quiz.addQuestions(new Question("When should a LinkList be used over an ArrayList?", options6, 0));
        
        return quiz;
    }

}
