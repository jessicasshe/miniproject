import java.util.ArrayList;

public class AbandonedOffice extends Location {
    
    public AbandonedOffice(String name, ArrayList<String> actions, Clue clue)
    {
        super(name, actions, clue);
    }

    public String getCutsceneText()
    {
        return "You enter an abandoned office and look around";
        
    }
    
    public String getLocationTitle()
    {
        return Days.MON + " 5:00 AM - 17 Arlington Avenue, Abandoned Office...";
    }
    
    public String getActionResult()
    {
        return "You search the file cabinet and find a labelled USB Stick, which you think matches the one your father spoke about, containing documentation of the artificial intelligence technology. Press 1 to equip.";
    }

}
