public class ActionChoice {
    private MainCharacter character; // needs a character reference
    private String action_text;
    private String action_result;
    private Clue clue_revealed;
    
    public ActionChoice(String text, String result, Clue clue, MainCharacter player)
    {
        action_text = text;
        action_result = result;
        clue_revealed = clue;
        character = player;
    }
    
    public String getText()
    {
        return action_text;
    }
    
    public String getResult()
    {
        return action_result;
    }
    
    public boolean hasClue()
    {
        // so gov headquarters can break out of loop even with no clue
        if(clue_revealed != null)
        {
            return true;
        }
        return false;
    }
    
}
