public class AISentinel extends Enemy{
    
    public AISentinel(int health, String difficultyLevel, String name)
    {
        super(health, difficultyLevel, name);
    }
    
    public String attack(){
        // lower user HP
        return "You take " + (attackDamage*4) + " damage from the attack.";
    }

    
}
