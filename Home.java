import java.util.ArrayList;

public class Home extends Location {
    
    public Home(String name, ArrayList<String> actions, Clue clue)
    {
        super(name, actions, clue);
    }

    public String getCutsceneText()
    {
        return "It is past midnight, and you sit alone at the dining table, experiencing another sleepless night. It has been like this ever since then, when the damage first occurred…";
        
    }
    
    public String getLocationTitle()
    {
        return Days.MON + " 2:00 AM - Home, Jane St. Toronto...";
    }
    
    public String getActionResult()
    {
        return "Searching bookshelf...\n" + "You find a file folder.\n" + "Press 1 to read its contents.\n";
    }

}
