import java.util.ArrayList;
public class Location {
    public String name;
    public boolean unlocked = false; // default: false
    public boolean hasCombat = false;
    public Clue clue;
    public ArrayList<ActionChoice> actions = new ArrayList<>();
    public String cutscene_text;
    public String location_title;

    public Location(String name, Clue clue, String location_title, boolean has_combat){
        this.name = name;
        this.clue = clue;
        this.location_title = location_title;
        hasCombat = has_combat;
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
    
    public Clue getClue()
    {
        return clue;
    }
    
    public ArrayList<ActionChoice> getActions(){
        return actions;
    }
    
    public ArrayList<ActionChoice> addAction(ActionChoice action){ // use this method instread of passing in a list?
        actions.add(action);
        return actions;
    }
    
    public boolean hasCombat()
    {
        if(hasCombat)
        {
            return true;
        }
        return false;
    }
 
    public String getCutsceneText()
    {
        return cutscene_text;
    }
    
    public String getLocationTitle()
    {
        return location_title;
    }
    
    public String setCutsceneText(String text) // long parameter
    {
        cutscene_text = text;
        return cutscene_text;
    }
    
