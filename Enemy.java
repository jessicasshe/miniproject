public abstract class Enemy{
    private int health;
    private String name;
    private String difficultyLevel;
    public int attackDamage;
    
    public Enemy(int health, String difficultyLevel, String name){
        this.health = health;
        this.difficultyLevel = difficultyLevel;
        this.name = name;
        this.attackDamage = 10;
    }
    
    public String getName(){
        return name;
    }
    
    public int getHealth(){
        return health;
    }
    
    public String getDifficultyLevel(){
        return this.difficultyLevel;
    }
    
    public abstract String attack();
    
    public void takeDamage(int damage){
        this.health -= damage;
        System.out.println(name +" took "+damage+" damage.");
        if (this.health <= 0){
            System.out.println(name+" has been defeated");
        }
    }
}
