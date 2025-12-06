public class Clue{
    private String clue_name;
    private String clue_text;
    private boolean obtained = false;

    public Clue(String name, String text)
    {
        this.clue_name = name;
        this.clue_text = text;
    }
    
    public String getName(){
        return clue_name;
    }
    
    public String getText(){
        return clue_text;
    }
    
    public boolean isObtained(){
        return obtained;
    }
}
