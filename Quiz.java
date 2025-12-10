import java.util.Scanner;
import java.util.ArrayList;

public class Quiz {
    
    private ArrayList<Question> questions;
    //private int score;
    private Scanner scanner;
    private MainCharacter player;
    private Enemy enemy;
    private final int maxQuizAttempts = 3; 
    //private boolean failedAQuestion = false;
    
    public Quiz(Scanner theScanner, MainCharacter playerCharacter, Enemy enemy){
        this.questions = new ArrayList<>();
        //this.score = 0;
        this.scanner = theScanner;
        this.player = playerCharacter;
        this.enemy = enemy;
        //this.totalQuizAttempts = maxQuizAttempts;
    }
    
    public ArrayList<Question> addQuestion(Question question){
        questions.add(question);
        return questions;
    }
    
    public ArrayList<Question> reset_all_question_attempts()
    {
        for(int i = 0; i < questions.size(); i++)
        {
            questions.get(i).resetAttempts();
        }
        return questions;
    }
    
    public int startQuiz(int attempts){
        

        // gamemanager controls quiz attempts, quiz controls question attempts

        int quizScore = 0; 

        for (int i = 0; i < questions.size(); i++){
            Question currentQuestion = questions.get(i); 
            
            boolean question_correct = false;
            int initialAttempts = currentQuestion.getAttemptsRemaining(); // default 3
            
        /*if(currentQuestion.getAttemptsRemaining() = 0){
            System.out.println("Question "+(i+1)+" (Attempts left: none");
            allQuestionsCorrect = false;
            break;
        }
        */
        
            while (!question_correct){ 
            
            // check first if user has attempts remaining 
            
                if(currentQuestion.getAttemptsRemaining() == 0)
                {
                    return 0; // failed battle 
                }
            
            else
            {
                System.out.println("Question "+(i+1)+" (Attempts left: "+currentQuestion.getAttemptsRemaining()+") ");

                System.out.println(currentQuestion.getQuestionText()); //option
                
                ArrayList<Integer> option_numbers = new ArrayList<>();
                for (int j = 0; j < currentQuestion.getOptions().size(); j++){
                        System.out.println((j + 1)+". " + currentQuestion.getOptions().get(j));
                        option_numbers.add(j+1);
                    }
                    
                // validate user option
                int userAnswer = GameManager.validateIntInput("Enter your answer (1-"+currentQuestion.getOptions().size()+"):", option_numbers);

                
                if (currentQuestion.isCorrect(userAnswer - 1)){
                    System.out.println("Answer correct!");
                    
                    if (currentQuestion.getAttemptsRemaining() == initialAttempts && attempts == 0){ 
                        quizScore++;
                        player.updateScore(1); // only increase score if u didnt try again
                    }
                    
                    if(enemy != null){
                        player.attack(enemy.damage_taken_per_ans(), enemy);
                    }
                
               /* if(!player.getCurrentLocation().equals("Government Headquarters"))
                {
                    player.attack(enemy.getStartingHealth()/2, enemy);
                }
                */

                } 
                else {
                    currentQuestion.decrementAttempts();
                    System.out.println("Incorrect. You have "+ currentQuestion.getAttemptsRemaining()+ " attempts remaining.");
                    if(enemy != null)
                    {enemy.attack(player.damage_taken_per_ans(), player);
                    }
                }
                
                if(enemy!=null)
                {
                    System.out.println("Enemy HP: "+enemy.getHealth()+"/"+enemy.getStartingHealth());
                }
                System.out.println("Your HP: "+player.getHealth() + "/"+player.getStartingHealth());
                question_correct = true;

            }
        }
    }
    // reached end of quiz
    return quizScore;
}
}
