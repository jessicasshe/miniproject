import java.util.HashMap;
public class Location {
    public String name;
    public boolean unlocked = false; // default: false
    public Clue clue;
    public HashMap<String, String> actions = new HashMap<>();
    public String cutscene_text;
    public String location_title;

    public Location(String name, Clue clue, location_title){
        this.name = name;
        this.clue = clue;
        this.location_title = location_title;
    }
    
    public String getName(){
        return name;
    }
    
    public boolean isUnlocked(){
        return unlocked;
    }
    
    public boolean unlockLocation()
    {
        unlocked = true;
        return unlocked;
    }
    
    public boolean lockLocation(){
        unlocked = false;
        return unlocked;
    }
    
    // note: makes use of clue class; location owns the clue, clue given text
    public String getClue(){
        return clue.getText();
    }
    
    public HashMap<String, String> getActions(){
        return actions;
    }
    
    public HashMap<String, String> addAction(String action_desc, String result){ // use this method instread of passing in a list?
        actions.put(action_desc, result);
        return actions;
    }
 
    public String getCutsceneText()
    {
        return cutscene_text;
    }
    
    public String getLocationTitle()
    {
        return location_title;
    }
    
    public String getActionResult()
    {
        // find action based on user input -> add paramneter
        return;
    }
    
    public setCutsceneText(String text) // long parameter
    {
        cutscene_text = text;
        return cutscene_text;
    }
    

    
    
}
    
