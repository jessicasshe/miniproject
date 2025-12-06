import java.util.ArrayList;
abstract class Location {
    public String name;
    public boolean unlocked = false; // default: false
    public Clue clue;
    public ArrayList<String> actions = new ArrayList<>();
    
    public Location(String name, ArrayList<String> actions, Clue clue){
        this.name = name;
        this.actions = actions;
        this.clue = clue;
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
    
    public ArrayList<String> getActions(){
        return actions;
    }
    
    public ArrayList<String> addAction(String action){
        actions.add(action);
        return actions;
    }
 
    public abstract String getCutsceneText();
    
    public abstract String getLocationTitle();
    
    public abstract String getActionResult();
    // pass in user's num entered 
    
    
    
}
    
