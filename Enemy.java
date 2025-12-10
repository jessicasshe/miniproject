public abstract class Enemy{
    protected int health;
    protected String name;
    protected String difficultyLevel;
    protected static final String RED = "\033[31m";
    protected static final String GREEN = "\u001B[32m";
    public static final String RESET = "\u001b[0m";


    
    public Enemy(int health, String difficultyLevel, String name){ //int damagePerAnswer
        this.health = health;
        this.difficultyLevel = difficultyLevel;
        this.name = name;
    }
    
    public String getName(){
        return name;
    }
    
    public int getHealth(){
        return health;
    }
    
    public abstract int getStartingHealth();
    
    public String getDifficultyLevel()
    {
        return difficultyLevel;
    }
    
    public void attack(int damage, MainCharacter player)
    {
        player.takeDamage(damage);
    }
    
    public void takeDamage(int damage)
    {
        health -= damage;
        System.out.println(name+" took " + damage + " damage");
    }
    
    //public int getAttackDamageValue(){
      //  return attackDamage;
    //}
    
    public int damage_taken_per_ans(){ 
        return getStartingHealth() /2;
    }
    
    public void reset_health_to_starting(){
        health = getStartingHealth();
    }
}
