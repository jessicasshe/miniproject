import java.util.ArrayList;

public class MainCharacter{
    private String username;
    private ArrayList<Clue> cluesCollected;
    private int score;
    private int health; 
    private Location currentLocation;
    private int maxHealth;
    
    public MainCharacter(String username, int health, Location startingLocation){
        this.maxHealth = health;
        this.username = username;
        this.currentLocation = startingLocation;
        this.score = 0;
        this.cluesCollected = new ArrayList<>();
        this.health = health;
    }
    
    public void analyze(){
        System.out.println("Analyzing the collected clue...");
    }
    
    public void changeLocation(Location newLocation){
        currentLocation = newLocation;
        System.out.println("Entering " + newLocation.getName() + "...");
    }
    
    public String getStats(){ // should be done in main method
        return "\n-----"+username+"'s Score ----\n"+ score + "/6";
    }
    
    public ArrayList<Clue> pickUpClue(Clue clue){
        clue.setObtained(true);
        cluesCollected.add(clue);
        return cluesCollected;
    }
    
    public String getUsername(){
        return username;
    }
    
    public String setUsername(String username){
        this.username = username;
        return username;
    }
    
    public ArrayList<Clue> getCluesCollected(){
        return cluesCollected;
    }

    public int getScore(){
        return score;
    }
    
    public int addScore(){
        return score++;
    }
    
    public Location getCurrentLocation(){
        return currentLocation;
    }
    

    public int getHealth(){
        return health;
    }
    
    public int getStartingHealth()
    {
        return 90;
    }
    
    public int resetHealthToMax(){
        health = maxHealth;
        return health;
    }
    
    public int updateScore(int score)
    {
        this.score += score;
        return score;
    }
    
    public void takeDamage(int damage){
        health -= damage;
        System.out.println(username+" took "+ damage+" damage");
    }
    
    public void attack(int damage, Enemy enemy)
    {
        enemy.takeDamage(damage);
    }
    
    public int damage_taken_per_ans()
    {
        return getStartingHealth()/6;
    }

}
