import java.util.ArrayList;

public class GovHeadquarters extends Location {
    
    public GovHeadquarters(String name, ArrayList<String> actions, Clue clue)
    {
        super(name, actions, clue);
    }

    public String getCutsceneText()
    {
        return "With all the clues collected, you make your way to the Government's headquarters. You know from your father's file that this is where you need to enter the complete algorithm to shut down the malicious AI for good. ";
    }
    
    public String getLocationTitle()
    {
        return Days.WED + " 9:00 AM - City Hall...";
        
    }
    
    public String getActionResult()
    {
        return "The robots have been deconstructed and the city is safe again. Your father's mission is complete!";

    }

}
