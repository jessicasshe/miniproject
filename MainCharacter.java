import java.util.ArrayList;
public class MainCharacter{
    private String username;
    private ArrayList<Clue> cluesCollected;
    private int score;
    private int health; 
    private int attackDamage; 
    private Location currentLocation;
    
    public MainCharacter(String username, int health, int attackDamage, Location startingLocation){
        this.username = username;
        this.currentLocation = startingLocation;
        this.score = 0;
        this.cluesCollected = new ArrayList<>();
        this.attackDamage = attackDamage;
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
        return "\n-----"+username+"'s Stats ---- \n Health: "+health + "\nScore: "+ score;
    }
    
    public ArrayList<Clue> pickUpClue(Clue clue){
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
    
    public void takeDamage(int damage){
        this.health -= damage;
        if (this.health < 0) this.health = 0;
        System.out.println(username+" took "+ damage+" damage. Current health: "+getHealth());
    }

}
