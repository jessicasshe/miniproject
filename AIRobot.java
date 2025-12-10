public class AIRobot extends Enemy{
    
    public AIRobot(int health, String difficultyLevel, String name)
    {
        super(health, difficultyLevel, name);
    }
    

    public String getDifficultyLevel()
    {
        return GREEN+difficultyLevel+RESET;
    }

    public int getStartingHealth()
    {
        return 100;
    }
