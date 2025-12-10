public class AISentinel extends Enemy{
    
    public AISentinel(int health, String difficultyLevel, String name)
    {
        super(health, difficultyLevel, name);
    }
    
    public int getStartingHealth()
    {
        return 150;
    }
    
    public String getDifficultyLevel()
    {
        return RED+difficultyLevel+RESET;
    }
    

    //public int getAttackDamageValue(){
      //  return attackDamage * 4;
    //}

    
}
