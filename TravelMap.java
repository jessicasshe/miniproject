import java.util.ArrayList;

public class TravelMap {
    private ArrayList<Location> locations = new ArrayList<>();
    
    public ArrayList<Location> addLocation(Location location)
    {
        locations.add(location);
        return locations;
    }
    
    public ArrayList<Location> getLocations()
    {
        return locations;
    }
    
    public ArrayList<Location> unlockLocation(Location location)
    {
        // get curr location index
        int loc_index = 0;
        for (int i = 0; i < locations.size(); i++)
        { 
            if (locations.get(i) == location)
            {
                loc_index = i;
            }
        }
        locations.get(loc_index+1).unlockLocation(); // use curr index+1
        return locations;
    }
}
