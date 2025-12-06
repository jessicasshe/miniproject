import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.io.Console;

// add console screen flusher later 

public class GameManager {
    /* attributes: all object references
    */
    private Console console = null;
    private MainCharacter main_character;
    private static Scanner input = new Scanner(System.in);
    private ArrayList<Location> map_locations = new ArrayList<Location>();
    private AIRobot ai_robot;
    private AISentinel ai_sentinel;
    private Home home; 
    private AbandonedOffice office;
    private RobotFactory robot_factory;
    private GovHeadquarters headquarters;
    private Clue home_clue;
    private Clue abandoned_clue;
    private Clue factory_clue;
    private Quiz office_quiz;
    private Quiz factory_quiz;
    private Quiz hq_quiz;
    private FileWriter username_writer;
    private File username_list;

    public GameManager()
    {
        // create a console object
        
        try
        {
            console = System.console();

        }
        catch (Exception e)
        {
            System.out.println("Something went wrong...");
        }
        
        // initialize HOME location
        ArrayList<String> home_actions = new ArrayList<>();
        home_actions.add("Search Bookshelf");
        
        home_clue = new Clue("File Folder", "For years, I have been researching how to get rid of these Evil AI robots form out city. Here is one piece of the algorithm to deconstruct them. Find the other two clues to save the city. - Father");
        home = new Home("Home", home_actions, home_clue);
        map_locations.add(home);
        
        // initialize ABANDONED OFFICE location
        ArrayList<String> office_actions = new ArrayList<>();
        office_actions.add("Search a file cabinet");
        abandoned_clue = new Clue("USB Stick", "Message: Xyik]$ڒXݗ");
        office = new AbandonedOffice("Abandoned Office", office_actions, abandoned_clue);
        map_locations.add(office);
        
        // initialize ROBOT FACTORY location
        ArrayList<String> factory_actions = new ArrayList<>();
        factory_actions.add("Inspect the conveyor belt");
        factory_actions.add("Inspect the hardware");
        factory_clue = new Clue("Memory Chip", "Message: 01100101010");
        robot_factory = new RobotFactory("Robot Factory", factory_actions, factory_clue);
        map_locations.add(robot_factory);
        
        // initialize GOV HEADQUARTERS location
        ArrayList<String> hq_actions = new ArrayList<>();
        hq_actions.add("Use File Folder Contents");
       // hq_actions.add("Use USB Contents");
       // hq_actions.add("Use Memory Chip Contents");
        headquarters = new GovHeadquarters("Government Headquarters", hq_actions, null);
        map_locations.add(headquarters);
        
        // initialize enemies 
        ai_robot = new AIRobot(90, "EASY", "32321H8");
        ai_sentinel = new AISentinel(100, "HARD", "32135557");
        
    }
    
    public void flushScreen()
    {
        if(console != null)
        {
            console.flush();
        }
    }
    
    public void showStartingScreen()
    {
        

        System.out.println("PROTOCOL: DECONSTRUCT");
        System.out.println("1 - Start");
        System.out.println("2 - Quit");
        System.out.println("3 - View Leaderboard");
        int user_choice = input.nextInt();
        
        switch(user_choice)
        {
            case 1:
                playBeginningCutscene();
                break;
            case 2:
                System.out.println("Quitting the program");
                break;
                // ask if they want to quit, exit the program
            case 3:
                System.out.println("Showing leaderboard");
                break;
        
        }
    }
    
    public void playBeginningCutscene()
    {
        console.flush();
        
        try{
        File cutscene_text = new File("beginning_cutscene.txt");
        Scanner cutscene_reader = new Scanner(cutscene_text);

        System.out.println("Cutscene: The Beginning");
        while (cutscene_reader.hasNextLine()) // print text line by line
        {
            System.out.println(cutscene_reader.nextLine());
            try{
            Thread.sleep(3000);
            }
            catch(InterruptedException e)
            {
                System.out.println("Something went wrong.");
            }
                
        }
        cutscene_reader.close();

        System.out.println("Press 1 to begin your mission: ");
        Scanner input = new Scanner(System.in);
        int user_choice = input.nextInt(); // add validation later 
        
        if(user_choice == 1)
        {
            System.out.println("Entering home...");
            home.unlockLocation();
            this.showLocationCutscene();// call the first location cutscene method 
        }
        }
        catch (IOException e)
        {
            System.out.println("Something went wrong.");
        }
    }
    
    public void showLocationCutscene()
    {
        // same for all locations
        System.out.println(main_character.getCurrentLocation().getLocationTitle());
        printDivider();
        System.out.println(main_character.getCurrentLocation().getCutsceneText());
        
        boolean clue_not_found = true;
        
        while(clue_not_found)
        {
            System.out.println("Select an action: ");
            for (int i = 0; i < main_character.getCurrentLocation().getActions().size(); i++)
            {
                System.out.println(i+1 + ". " + main_character.getCurrentLocation().getActions().get(i));
            }
            
            // get user input for action selected
            int action_num = input.nextInt(); 
    
            
            switch(main_character.getCurrentLocation().getName())
            {
                // create a local currnet location var to reduce duplication
                System.out.println(main_character.getCurrentLocation().getActionResult());
                press_one_to_continue();
                main_character.analyze();

                // switch statement bc each location has diff behaviour, 
                // change repetitive code later
                case "Home": // no combat
                    // print action result
                    System.out.println(home.getClue());
                    // let user pick up clue 
                    main_character.pickUpClue(home_clue);
                    clue_not_found = false;
                    office.unlockLocation();
                    press_one_to_continue();
                    printClueUnlockedMessage();

                    break;
                    
                case "Abandoned Office": // combat
                    System.out.println(office.getClue());
                    main_character.pickUpClue(abandoned_clue);
                    clue_not_found = false;
                    robot_factory.unlockLocation();
                    press_one_to_continue();
                    showCombatCutscene();
                    printClueUnlockedMessage();

                    break;
                    
                case "Robot Factory": // combat, 2 action nums
                    if(action_num == 1) // conveyor belt
                    {
                        System.out.println("Inspecting the conveyor belt...No clue found, please try again.");
                    }
                    else // hardware
                    {
                        System.out.println(robot_factory.getClue());
                        main_character.pickUpClue(factory_clue);
                        headquarters.unlockLocation();
                        clue_not_found = false;
                    }
                    press_one_to_continue();
                    showCombatCutscene();
                    printClueUnlockedMessage();

                    break;
                
                case "Government Headquarters":
                    System.out.println(headquarters.getActionResult());
                    // add switch statements later 
                    // call ending cutscene
                    showEndingCutscene();
                    showEndingStats();
                    break;
                    
            }
        }
    }
    
    public void showEndingCutscene()
    {
        System.out.println("Ending - Rooftop, 366 Fred Well, Braunview...");
        printDivider();
        System.out.println("You look out at the vast view, reflecting on your accomplishment, but can't shake out the feeling of your existential dread. You think about the memories of him during simpler times, back when humanity hadn't totally succumbed to the consequences of their potential…");
        
    }
    
    public void showEndingStats()
    {
        System.out.println(main_character.getStats());
        // add user's score to leaderboard using file io;
        try
        {
            File leaderboard_file = new File("leaderboard.txt");
            FileWriter leaderboard_writer = new FileWriter(leaderboard_file);
            leaderboard_writer.write(main_character.getStats());
            leaderboard_writer.close();

        }
        catch (IOException e)
        {
            System.out.println("Something went wrong...");
        }
        
        
  
    }
    
    
    public void printClueUnlockedMessage(){
        System.out.println("Clue unlocked! " + (3-main_character.getCluesCollected().size()) + " more to go...");
        main_character.getCurrentLocation().lockLocation();
        System.out.println("Press 1 to open the travel map: ");
        // validate input
        displayTravelMap();
    };
    
    
    public void displayTravelMap()
    {
        boolean invalid_choice = true;
        
        while(invalid_choice)
        {
            System.out.println("\nTRAVEL MAP");
            printDivider();
            for (int i = 0; i < map_locations.size(); i++)
            {
                System.out.println(i+1 + ". " + map_locations.get(i).getName() + " -- UNLOCKED: " + map_locations.get(i).isUnlocked());
            }
            
            System.out.println("Select the next location to visit: ");
        
        // get user input

            int choice = -1;
            try{
                choice = input.nextInt();
            } catch (InputMismatchException e){
                System.out.println("Invalid input. Please enter a number.");
                input.nextLine();
                return;
            }
            
            if (choice > 0 && choice <= map_locations.size()){
                Location chosenLocation = map_locations.get(choice-1);
                
                System.out.println(chosenLocation.getName());
                
                if (chosenLocation.isUnlocked()){
                    main_character.changeLocation(chosenLocation);
                    showLocationCutscene();
                    invalid_choice = false;
                } else {
                    System.out.println("This location is locked...");
                }
            } else {
                System.out.println("Invalid selection, please try again.");
            }
        }
    }
    
    public void showCombatCutscene()
    {
        // switch to users current location 
        System.out.println("DANGER!");
        printDivider();

        switch(main_character.getCurrentLocation().getName())
        {
            case "Abandoned Office":
                System.out.println("Just as you put the USB stick away, you see an eerie-looking mechanical robot, sensing a violent encounter. Solve the code to defeat it!");
                System.out.println("Enemy name: " + ai_robot.getName());
                System.out.println("Enemy difficulty: " + ai_robot.getDifficultyLevel());
                System.out.println("Enemy health: " + ai_robot.getHealth());
                break;
                
            case "Robot Factory":
                System.out.println("Just as you put the memory chip away, you see 2 eerie-looking AI Sentinels, sensing a violent encounter you equip your computer. Solve the code to defeat it!");
                System.out.println("Enemy name: " + ai_sentinel.getName());
                System.out.println("Enemy difficulty: " + ai_sentinel.getDifficultyLevel());
                System.out.println("Enemy health: " + ai_sentinel.getHealth());
                break;
        }

        press_one_to_continue();
        // go to multiple choice
        runQuizForCurrentLocation();

    }
    
   /* public void run_multiple_choice()
    {
        // match the choice to the location
        switch(main_character.getCurrentLocation().getName())
        {
            case "Abandoned Office":
                AbandonedOfficeQuiz(input, main_character);
                break;
            case "Robot Factory":
                RobotFactoryQuiz(input, main_character);
                break;
            case "Government Headquarters":
                GovernmentHeadquartersQuiz(input, main_character);
                break;
        }
    }
    */
    
    public void recordUsername() {
    try {
        boolean is_invalid = true;

        File username_list = new File("usernames.txt");

        while (is_invalid) {
            Scanner username_reader = new Scanner(username_list);


            System.out.println("Enter a username: ");
            String username_entered = input.nextLine().trim(); // safer than next()

            boolean found_duplicate = false;

            while (username_reader.hasNextLine()) {
                if (username_reader.nextLine().trim().equalsIgnoreCase(username_entered)) {
                    found_duplicate = true;
                    break;
                }
            }

          // close Scanner after reading
          username_reader.close();

            if (!found_duplicate) {
                FileWriter username_writer = new FileWriter("usernames.txt", true);
                username_writer.write(username_entered + "\n"); // add newline
                username_writer.flush(); // ensure data is written
                username_writer.close(); // close file
                main_character = new MainCharacter(username_entered, 100, 15, home);
                is_invalid = false;
            } else {
                System.out.println("Username taken, please try again.");
            }
        }

        showStartingScreen();

    } catch (IOException e) {
        System.out.println("Something went wrong.");
    }
}
    
    // public void press_-1_to_quit()

    public void press_one_to_continue()
    {
        System.out.println("Press 1 to continue: ");
        int num = -1;
        try {
            num = input.nextInt();
            input.nextLine();
        } catch (InputMismatchException e){
            System.out.println("Invalid input. Press 1 to continue.");
        }
    }
    
    //quiz interation
    public void runQuizForCurrentLocation(){
        Quiz quiz = null;
        
        switch (main_character.getCurrentLocation().getName()){
            case "Abandoned Office":
                quiz = Quiz.AbandonedOfficeQuiz(input, main_character);
                break;
            case "Robot Factory":
                quiz = Quiz.RobotFactoryQuiz(input, main_character);
                break;
            case "Government Headquarters":
                quiz = Quiz.GovHeadquartersQuiz(input, main_character);
                break;
            default:
                System.out.println("No quiz avaliable.");
                return;
        }
        
        System.out.println("The battle begins!!!!");
        int result = quiz.startQuiz();
        
        if (result == 1){
            System.out.println("You defeated the AI Robots!!");
        } else {
            System.out.println("You were defeated.. ");
            main_character.takeDamage(20);
            if (main_character.getHealth() <= 0){
                System.out.println("You have died. GAME OVER");
                System.exit(0);
            }
        }
    }
    
    public void printDivider()
    {
        System.out.println("------------------");
    }
    
    public static void main(String[] args)
    {
        GameManager game_manager = new GameManager();
        game_manager.recordUsername();
    }

}
