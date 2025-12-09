import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.InputMismatchException;


public class GameManager {
    public static final String CYAN = "\u001b[36m";
    public static final String RESET = "\u001b[0m";
    public static final String RED = "\033[31m";
    private MainCharacter main_character;
    private static Scanner input = new Scanner(System.in);
    private AIRobot ai_robot;
    private AISentinel ai_sentinel;
    private TravelMap map;
    private Location home; 
    private Location office;
    private Location robot_factory;
    private Location headquarters;
    private Clue home_clue;
    private Clue abandoned_clue;
    private Clue factory_clue;
    private Quiz office_quiz;
    private Quiz factory_quiz;
    private Quiz hq_quiz;
    private FileWriter username_writer; // keep as attribute or as local?
    private File username_list;
    

    public GameManager()
    {
        // initialize MAP
        map = new TravelMap();
        
        // initialize enemies 
        ai_robot = new AIRobot(90, "EASY", "32321H8");
        ai_sentinel = new AISentinel(100, "HARD", "32135557");
        
        // initialize HOME location (name, clue, title)
        home_clue = new Clue("File Folder", "For years, I have been researching how to get rid of these Evil AI robots form out city. Here is one piece of the algorithm to deconstruct them. Find the other two clues to save the city. - Father");
        String home_title = CYAN+ Days.MON + " 2:00 AM - HOME, Jane St. Toronto"+ RESET;
        home = new Location("Home", home_clue, home_title, false, null, null);
        
        // can now initialize main_character with home location
        main_character = new MainCharacter(null, 100, 30, home); 

        home.addAction(new ActionChoice("Search Bookshelf", "Searching bookshelf...\nYou find a file folder.\nPress 1 to read its contents.", home_clue, main_character));
        home.addAction(new ActionChoice("Check Phone Notifications", "Your phone has no new notifications.", null, main_character));
        home.addAction(new ActionChoice("Search a kitchen cupboard for some empty dishes", "Nothing here..You remind yourself to do the dishes later.", null, main_character));
        home.setCutsceneText("It is past midnight, and you sit alone at the dining table, experiencing another sleepless night. It has been like this ever since then, when the damage first occurred…");

        map.addLocation(home);

        
        // initialize QUIZ objects
        office_quiz = new Quiz(input, main_character, ai_robot);
        
        ArrayList<String> options1 = new ArrayList<>();
        options1.add("int(num)");
        options1.add("Double y = double(x)");
        options1.add("int x = 4; double y = x;");
        
        office_quiz.addQuestion(new Question("What is an example of implicit casting?", options1, 2));
                
        ArrayList<String> options2 = new ArrayList<>();
        options2.add("Inheritance");
        options2.add("General Association");
        options2.add("Composition");
        
        office_quiz.addQuestion(new Question("Dog IS A Animal shows what type of relationship?", options2, 0));

        factory_quiz = new Quiz(input, main_character, ai_sentinel);
        ArrayList<String> options3 = new ArrayList<>();
        options3.add("Public void main Person{");
        options3.add("public class Person{");
        options3.add("Person{");
        
        factory_quiz.addQuestion(new Question("What is the correct way to code an object class?", options3, 1));
              
        ArrayList<String> options4 = new ArrayList<>();
        
        options4.add("for");
        options4.add("while");
        options4.add("do");
        
        factory_quiz.addQuestion(new Question("____(int i = 0;, i<p, i++){ --> What type of loop is this??", options4, 0));

        hq_quiz = new Quiz(input, main_character, null);
        ArrayList<String> options5 = new ArrayList<>();
        options5.add("O(n log n)");
        options5.add(" O(n)");
        options5.add(" O(n^2)");
        hq_quiz.addQuestion(new Question("What is the time complexity of a Merge Sort?", options5, 0));
                
        ArrayList<String> options6 = new ArrayList<>();
        options6.add("When you are trying to modify a value and already have a reference to the item");
        options6.add("When you don't have a reference to the item and are trying to modify a value");
        options6.add("When you are trying to search for a value");
        hq_quiz.addQuestion(new Question("When should a LinkList be used over an ArrayList?", options6, 0));



        // initialize ABANDONED OFFICE location
        String office_title = CYAN+Days.MON + " 5:00 AM - 17 Arlington Avenue..."+RESET;
        abandoned_clue = new Clue("USB Stick", "Message: Xyik]$ڒXݗ");
        office = new Location("Abandoned Office", abandoned_clue, office_title, true, ai_robot, office_quiz);
        office.setCutsceneText("You enter an abandoned office and look around...");
        office.setCombatText("Just as you put the USB stick away, you see an eerie-looking mechanical robot, sensing a violent encounter. Solve the code to defeat it!");
        office.addAction(new ActionChoice("Search a file cabinet", "You search the file cabinet and find a labelled USB Stick, which you think matches the one your father spoke about, containing documentation of the artificial intelligence technology. Press 1 to equip.", abandoned_clue, main_character));
        office.addAction(new ActionChoice("Turn on a computer", "Shoot. You need a password.", null, main_character));
        office.addAction(new ActionChoice("Pick up a paper from the floor", "Nothing here...Just a financial statement.", null, main_character));
        map.addLocation(office);
        
        // initialize ROBOT FACTORY location
        String factory_title = CYAN+Days.TUE + " 7:00 AM - TSMC..."+RESET;
        factory_clue = new Clue("Memory Chip", "Message: 01100101010");
        robot_factory = new Location("Robot Factory", factory_clue, factory_title, true, ai_sentinel, factory_quiz);
        robot_factory.setCutsceneText("The sun is rising as you arrive at the factory, entering through the back window - while avoiding cameras - you weave through many crates and spot a revolving conveyor belt with lots of hardware. You walk closer to...");
        robot_factory.setCombatText("Just as you put the memory chip away, you see 2 eerie-looking AI Sentinels, sensing a violent encounter you equip your computer. Solve the code to defeat it!");
        robot_factory.addAction(new ActionChoice("Inspect the hardware", "Inspecting hardware...\nItem found: Memory Chip.\n Press 1 to read its contents.", factory_clue, main_character));
        robot_factory.addAction(new ActionChoice("Inspect the conveyor belt", "Inspecting conveyor belt...\n Item found: Nothing.\n Press 1 to try again.", null, main_character));
        map.addLocation(robot_factory);
        
        // initialize GOV HEADQUARTERS location

        String hq_title = CYAN+Days.WED + " 9:00 AM - City Hall..."+RESET;
       // hq_actions.add("Use USB Contents");
       // hq_actions.add("Use Memory Chip Contents");
        headquarters = new Location("Government Headquarters", null, hq_title, false, null, hq_quiz);
        headquarters.setCutsceneText("With all the clues collected, you make your way to the Government's headquarters. You know from your father's file that this is where you need to enter the complete algorithm to shut down the malicious AI for good. ");
        headquarters.addAction(new ActionChoice("Hack into a computer", "The robots have been deconstructed and the city is safe again. Your father's mission is complete.", null, main_character));
        map.addLocation(headquarters);

        //quiz obj.
        //office_quiz = Quiz.AbandonedOfficeQuiz(input, main_character);
        //robot_factory = Quiz.RobotFactoryQuiz(input, main_character);
        //hq_quiz = GovHeadquartersQuiz(input, main_character);

        // location and their quiz
        //office = new Location("Abandoned Office", abandoned_clue, office_title, true, ai_robot, office_quiz);
        //robot_factory = new Location("Robot Factory", factory_clue, factory_title, true, ai_sentinal, factory_quiz);
        //headquarters = new Location("Government Headquarters", null, hq_title, false, null, hq_quiz);
        
    } 

    public int validateIntInput(String prompt, ArrayList<Integer> valid_options)
    {
        int user_choice = 0;
        boolean invalid_input = true;
        boolean option_found = false;
        while(invalid_input)
        {
            try{
                if(prompt != null)
                {
                System.out.println(prompt);
                }
                user_choice = input.nextInt();
                for (Integer option : valid_options)
                {
                    if(option == user_choice) // valid 
                    {
                        option_found = true;
                        invalid_input = false;
                        break;
                    }
                }
                if(!option_found)
                {
                    System.out.println("Please enter a valid option.");
                    input.nextLine();
                }
            }
            catch(InputMismatchException e)
            {
                System.out.println("Please enter an integer choice.");
                input.nextLine();
            }
        }
        return user_choice;
    }
    
    public void showStartingScreen()
    {
        System.out.println(CYAN + "PROTOCOL: DECONSTRUCT" + RESET);
        
        
        System.out.println("1 - Start");
        System.out.println("2 - Quit");
        System.out.println("3 - View Leaderboard");
        // ADD EXCEPTION HANDLING HERE 
        int user_choice = validateIntInput("Select an option:", new ArrayList<>(List.of(1, 2, 3)));
        clearConsole();
        switch(user_choice)
        {
            case 1:
                playBeginningCutscene();
                break;
            case 2:
                // add 'are u sure' prompt
                System.out.println("Thank you for playing.");
                break;
            case 3:
                System.out.println("Showing leaderboard");
                // implement !!!!
                break;
        
        }

    }
    
    public void playBeginningCutscene()
    {

        try{
        File cutscene_text = new File("beginning_cutscene.txt");
        Scanner cutscene_reader = new Scanner(cutscene_text);

        System.out.println(CYAN+"CUTSCENE: The Beginning"+RESET);
        while (cutscene_reader.hasNextLine()) // print text line by line
        {
            System.out.println(cutscene_reader.nextLine());
            try{
            Thread.sleep(2000);
            }
            catch(InterruptedException e)
            {
                System.out.println("Something went wrong.");
            }
                
        }
        
        cutscene_reader.close();
        
        String user_choice = validateEnterPressed();
        
        clearConsole();
        home.unlockLocation();
        main_character.changeLocation(home);
        showLocationCutscene();// call the first location cutscene method 
        
        }
        catch (IOException e)
        {
            System.out.println("Something went wrong while loading the file.");
        }
    }
    
    public String validateEnterPressed()
    {
        boolean invalid_input = true;
        String user_input = null;
        while(invalid_input)
        {
            System.out.println("Press ENTER to continue:");
            try
            {
                user_input = input.nextLine();
                user_input = input.nextLine();
                if(user_input.isEmpty())
                {
                    invalid_input = false;
                }
                else
                {
                    System.out.println("Invalid input, please try again.");
                    input.nextLine();
                }
            }
            catch(InputMismatchException e)
            {
                System.out.println("Please press the enter key.");
                input.nextLine();
            }
        }
        return user_input;
    }
    
    
    public void showLocationCutscene()
    {
        Location users_curr_location = main_character.getCurrentLocation();
        
        // same for all locations
        System.out.println(users_curr_location.getLocationTitle());
        printDivider();
        System.out.println(users_curr_location.getCutsceneText());
        
        boolean clue_not_found = true;
        
        while(clue_not_found)
        {
            ArrayList<Integer> action_indexes = new ArrayList<>();
            System.out.println("Select an action: ");
            for (int i = 0; i < users_curr_location.getActions().size(); i++)
            {
                System.out.println(i+1 + ". " + users_curr_location.getActions().get(i).getText());
                action_indexes.add(i+1);
                
            }
            
            int action_num = validateIntInput("Select an action:", action_indexes);
            ActionChoice action_chosen = users_curr_location.getActions().get(action_num-1); // entire object
            System.out.println(action_chosen.getResult()); // get action's result text

            if(users_curr_location.getName().equals("Government Headquarters"))
            {
                clue_not_found = false;
                showCombatCutscene(users_curr_location);
                showEndingCutscene();
                showEndingStats(); // end of program
            }

            else
            {

                if(action_chosen.hasClue())
                {
                    validateIntInput(null, new ArrayList<>(List.of(1)));
                    main_character.analyze();
                    System.out.println(users_curr_location.getClue().getText());
                    main_character.pickUpClue(users_curr_location.getClue());
                    validateEnterPressed();
                    clearConsole();
                    if(users_curr_location.hasCombat())
                    {
                        showCombatCutscene(users_curr_location);
                    }
                    clue_not_found = false;
                    map.unlockNextLocation(users_curr_location); 
                    printClueUnlockedMessage();
                    press_one_to_continue();
                }
            }
        }
    }
    
    public void showEndingCutscene()
    {
        System.out.println("Ending - Rooftop, 366 Fred Well, Braunview...");
        printDivider();
        System.out.println("You look out at the vast view, reflecting on your accomplishment, but can't shake out the feeling of your existential dread. You think about the memories of him during simpler times, back when humanity hadn't totally succumbed to the consequences of their potential…");
        clearConsole();
    }
    
    public void showEndingStats()
    {
        System.out.println(main_character.getStats());
        // add user's score to leaderboard using file io;
        try
        {
            File leaderboard_file = new File("leaderboard.txt");
            FileWriter leaderboard_writer = new FileWriter(leaderboard_file, true);
            leaderboard_writer.write(main_character.getStats());
            leaderboard_writer.flush(); // instantly added
            leaderboard_writer.close();

        }
        catch (IOException e)
        {
            System.out.println("Something went wrong...");
        }
        
        
  
    }
    
    
    public void printClueUnlockedMessage(){
        if(main_character.getCluesCollected().size() == 3)
        {
            System.out.println("You have collected all the clues. Head swiftly to the final location before darkness falls.");
        }
        else
        {
            System.out.println("Clue unlocked! " + (3-main_character.getCluesCollected().size()) + " more to go...");
        }
        main_character.getCurrentLocation().lockLocation();

        validateIntInput("Press 1 to open the travel map: ", new ArrayList<Integer>(List.of(1)));
        displayTravelMap();
    }
    
    
    public void displayTravelMap()
    {
        System.out.println("\nTRAVEL MAP");
        printDivider();
        
        ArrayList<Integer> location_indexes = new ArrayList<>();
        
        for (int i = 0; i < map.getLocations().size(); i++)
        {
            System.out.println(i+1 + ". " + map.getLocations().get(i).getName() + " -- UNLOCKED: " + map.getLocations().get(i).isUnlocked());
            location_indexes.add(i+1);

        }
        
        int user_choice = validateIntInput("Select the next location to visit:", location_indexes);

        Location chosenLocation = map.getLocations().get(user_choice-1);
            
        if (chosenLocation.isUnlocked()){
            clearConsole();
            main_character.changeLocation(chosenLocation);
            showLocationCutscene();
            } 
        else {
            System.out.println("This location is locked...");
            }
    }
    
    public void showCombatCutscene(Location curr_location) // use the same local variable
    {
        
        if(!curr_location.equals("Government Headquarters"))
        {
            System.out.println(RED+"DANGER!"+RESET);
            printDivider();
            
            System.out.println(curr_location.getCombatText());
            System.out.println("Enemy name: " + curr_location.getEnemy().getName());
            System.out.println("Enemy difficulty: " + curr_location.getEnemy().getDifficultyLevel());
            System.out.println("Enemy health: " + curr_location.getEnemy().getStartingHealth());
            System.out.println("Answer the questions to defeat the robots!");
            clearConsole();
        }
        else
        {
            System.out.println("Beginning the hack...");
            System.out.println("Use your intelligence to construct the final algorithm!");
        }
        validateEnterPressed();
        runQuizForCurrentLocation();
        clearConsole();
    }
    
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
                main_character.setUsername(username_entered);
                is_invalid = false;
            } else {
                System.out.println("Username taken, please try again.");
            }
        }
        clearConsole();
        showStartingScreen();

    } catch (IOException e) {
        System.out.println("Something went wrong.");
    }
}
    
    public void runQuizForCurrentLocation(){

        Quiz quiz = main_character.getCurrentLocation().getQuiz();
        if (quiz == null){
            System.out.println("No quiz");
            return;
        }

        Enemy enemyz = main_character.getCurrentLocation().getEnemy();
        if(enemyz == null){
            System.out.println("No enemy's here");
            return;
        }
        int userMaxHP = 100;
        int enemyMaxHP = enemy.getHealth();
        
        System.out.println("The battle begins!!!!");
        int attempts = 3;
        while (attempts > 0){
            //reset hp
            int userHP = userMaxHP;
            int enemyHP = enemyMaxHP;

            System.out.println("Your HP: "+userHP+"/"+userMaxHP);
            System.out.println("Enemy HP: "+enemyHP+"/"+enemyMaxHP");
            
            //quiz.setupCombat(main_character, main_character.getCurentLocation().getEnemy());
            int result = quiz.startQuiz();
        
            if (result == 1){
                System.out.println("You defeated the AI Robots!!");
                return ;//1
            } else {
                attempts--;
                System.out.println("Failed quiz. You were defeated.. ");
                main_character.takeDamage(60);
                if (main_character.getHealth() <= 0){
                    System.out.println("You have died. GAME OVER");
                    return ;//0
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
    
    public static void clearConsole(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
