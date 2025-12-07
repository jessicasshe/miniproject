public class AIRobot extends Enemy{
    
    public AIRobot(int health, String difficultyLevel, String name)
    {
        super(health, difficultyLevel, name);
    }
    
    public String attack(){ // use this instead of user's method to show association ltr
        // lower user HP
        return "You take " + (attackDamage*2) + " damage from the attack.";
    }

    
}
